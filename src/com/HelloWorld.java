package com;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet("/HelloWorld")
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorld() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		HttpResponse<JsonNode> reponse;
		ArrayList<Ville> villes = new ArrayList<>();
		try {
			reponse = Unirest.get("http://localhost:8181/ville").asJson();
			JsonArray jArray = JsonParser.parseString(reponse.getBody().toString()).getAsJsonArray();
			villes = this.tabToVille(jArray);
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		response.getWriter().append(villes.toString());
	}
	
	private ArrayList<Ville> tabToVille(JsonArray tab) {
		final Gson gson = new GsonBuilder().create();
		ArrayList<Ville> villes = new ArrayList<>();

		// Itération autour du tableau de données
		for (JsonElement element : tab) {
			villes.add(gson.fromJson(element, Ville.class));
		}
		return villes;
	}

}
