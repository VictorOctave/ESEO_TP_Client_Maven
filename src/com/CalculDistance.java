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
		RequestDispatcher req = request.getRequestDispatcher("ResultatDistance.jsp");
		req.forward(request, response);
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
