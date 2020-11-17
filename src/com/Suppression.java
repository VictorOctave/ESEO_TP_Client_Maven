package com;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@WebServlet("/Suppression")
public class Suppression extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Suppression() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String villeSup = request.getParameter("ville");
		ArrayList<Ville> villes = (ArrayList<Ville>) session.getAttribute("villes");
		
		String codeInsee = null;
		for (Ville ville : villes) {
			if (ville.getNomCommune().equals(villeSup)) {
				codeInsee = ville.getCodeCommune();
			}
		}
		
		String url = "http://localhost:8181/ville/delete/{codeCommuneInsee}";
		try {
			Unirest.delete(url).routeParam("codeCommuneInsee", codeInsee).asJson();
		} catch (UnirestException e) {
			e.printStackTrace();
		}

		RequestDispatcher req = request.getRequestDispatcher("SuppressionValide.jsp");
		req.forward(request, response);
	}
	
}
