package com;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@WebServlet("/Calcul")
public class CalculDistance extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CalculDistance() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<Ville> villes = (ArrayList<Ville>) session.getAttribute("villes");
		String nomVille1 = request.getParameter("ville1");
		String nomVille2 = request.getParameter("ville2");
		Ville ville1 = null;
		Ville ville2 = null;
		
		for(Ville ville : villes) {
			if(ville.getNomCommune().equals(nomVille1)) {
				ville1 = ville;
			}
			if(ville.getNomCommune().equals(nomVille2)) {
				ville2 = ville;
			}
		}
		
		DecimalFormat df = new DecimalFormat("###.##");
		double distance = this.calcul(ville1, ville2);
		
		session.setAttribute("ville1", nomVille1);
		session.setAttribute("ville2", nomVille2);
		session.setAttribute("distance", df.format(distance));
		
		if (request.getParameter("meteo") == null) {
			RequestDispatcher req = request.getRequestDispatcher("ResultatDistance.jsp");
			req.forward(request, response);
		} else {
			HttpResponse<JsonNode> reponse1;
			HttpResponse<JsonNode> reponse2;
			String url1 = "http://api.openweathermap.org/data/2.5/weather?APPID=9be37396dcf68905f8b3a49af8b41d6e&lat="
					+ ville1.getLatitude() + "&lon=" + ville1.getLongitude() + "";
			String url2 = "http://api.openweathermap.org/data/2.5/weather?APPID=9be37396dcf68905f8b3a49af8b41d6e&lat="
					+ ville2.getLatitude() + "&lon=" + ville2.getLongitude() + "";
			try {
				reponse1 = Unirest.get(url1).asJson();
				reponse2 = Unirest.get(url2).asJson();
				
				JsonElement jArray1 = JsonParser.parseString(reponse1.getBody().toString());
				JsonElement jArray2 = JsonParser.parseString(reponse2.getBody().toString());
				
				JsonObject rootObject1 = jArray1.getAsJsonObject();
				JsonObject rootObject2 = jArray2.getAsJsonObject();
				
				String tempFVille1 = rootObject1.getAsJsonObject("main").get("temp").toString();
				String tempFVille2 = rootObject2.getAsJsonObject("main").get("temp").toString();
				
				double tempCVille1 = Double.parseDouble(tempFVille1) - 273.15;
				double tempCVille2 = Double.parseDouble(tempFVille2) - 273.15;
				
				session.setAttribute("meteoVille1", df.format(tempCVille1));
				session.setAttribute("meteoVille2", df.format(tempCVille2));
				
				String imageVille1 = rootObject1.getAsJsonArray("weather").get(0).getAsJsonObject().get("icon").toString().split("\"")[1];
				String imageVille2 = rootObject2.getAsJsonArray("weather").get(0).getAsJsonObject().get("icon").toString().split("\"")[1];
				
				String urlImage1 = "http://openweathermap.org/img/wn/"+imageVille1+".png";
				String urlImage2 = "http://openweathermap.org/img/wn/"+imageVille2+".png";
				
				session.setAttribute("imageMeteo1", urlImage1);
				session.setAttribute("imageMeteo2", urlImage2);

			} catch (UnirestException e) {
				e.printStackTrace();
			}
			session.setAttribute("villes", villes);

			RequestDispatcher req = request.getRequestDispatcher("ResultatDistanceEtMeteo.jsp");
			req.forward(request, response);
		}
		
		
		
	}
	
	public double calcul(Ville ville1, Ville ville2) {
		double latitude1 = Math.toRadians(Double.parseDouble(ville1.getLatitude()));
		double latitude2 = Math.toRadians(Double.parseDouble(ville2.getLatitude()));
		double longitude1 = Math.toRadians(Double.parseDouble(ville1.getLongitude()));
		double longitude2 = Math.toRadians(Double.parseDouble(ville2.getLongitude()));
		
		double a = Math.pow(Math.sin((latitude2 - latitude1)/2), 2) + Math.cos(latitude1) * 
				Math.cos(latitude2) * Math.pow(Math.sin((longitude2 - longitude1)/2), 2);
		
		double c = 2 * Math.atan(Math.sqrt(a)/Math.sqrt(1 - a));
		
		return 6371 * c;
	}

}
