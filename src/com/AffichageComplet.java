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

@WebServlet("/affichage")
public class AffichageComplet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public AffichageComplet() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String page = request.getParameter("page");
		int numeroPage = page != null ? Integer.parseInt(page) : 1;

		ArrayList<Ville> villes = (ArrayList<Ville>) session.getAttribute("villes");
		villes = getElementsByPage(numeroPage, 50, villes);
		request.setAttribute("villesPage", villes);
		request.setAttribute("numPage", numeroPage);
		
		RequestDispatcher req = request.getRequestDispatcher("AffichageComplet.jsp");
		req.forward(request, response);
	}

	private ArrayList<Ville> getElementsByPage(int page, int taille, ArrayList<Ville> villes) {
		int debut = 50 * (page - 1);
		ArrayList<Ville> myList = new ArrayList<>();
		for (int i = debut; i < debut + taille; i++) {
			if (i >= villes.size()) {
				break;
			}
			myList.add(villes.get(i));
		}

		return myList;
	}

}
