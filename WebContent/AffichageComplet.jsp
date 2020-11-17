<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.Ville"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Toutes les villes</title>
</head>
<body>
	<div class="container">
		<nav aria-label="Page navigation example">
		<ul class="pagination">
			<li class="page-item"><a class="page-link"
				href="affichage?page=
				<%Integer pages = (Integer) request.getAttribute("numPage");
				if (pages <= 1) {
					pages = 2;
				}%>
				<%=pages - 1%>">Previous</a></li>
			<li class="page-item"><a class="page-link" href="affichage?page=
				<%Integer pagesS = (Integer) request.getAttribute("numPage");%>
				<%=pagesS + 1%>">Next</a></li>
		</ul>
	</nav>
		<div class="card border-0 shadow my-5">
			<div class="card-body p-5">
				<h1 class="font-weight-light">Liste des villes :</h1>
				<br>
				<div style="height: 2500px">
					<%
					@SuppressWarnings("unchecked")
					ArrayList<Ville> liste2 = (ArrayList<Ville>) request.getAttribute("villesPage");
					for (Ville ville : liste2) {
					%>
					<%=ville.getNomCommune()%>
					<a href="Modification?ville=<%=ville.getNomCommune()%>">Modifier</a>
					<a href="Suppression?ville=<%=ville.getNomCommune()%>">Supprimer</a><br>
					<br>
					<%
						}
					%>

				</div>
			</div>
		</div>
	</div>
</body>
</html>