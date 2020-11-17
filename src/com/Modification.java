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

@WebServlet("/Modification")
public class Modification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Modification() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String villeModif = request.getParameter("ville");
		ArrayList<Ville> liste = (ArrayList<Ville>) session.getAttribute("villes");
		Ville villeAModifier = null;
		for (Ville ville : liste) {
			if(ville.getNomCommune().equals(villeModif)) {
				villeAModifier = ville;
			}
		}
		session.setAttribute("villeModif", villeAModifier);
		
		RequestDispatcher req = request.getRequestDispatcher("Modification.jsp");
		req.forward(request, response);
	}
	
}
