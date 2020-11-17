<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Resultat</title>
</head>
<body>
	<div>
		<div class="container">
			<div class="card border-0 shadow my-5">
				<div class="card-body p-5">
					<h1 class="font-weight-light">Résultat du calcul</h1>
					<p class="lead">
						La distance entre
						<%=session.getAttribute("ville1")%>
						et
						<%=session.getAttribute("ville2")%>
						est de
						<%=session.getAttribute("distance")%>
						km. <br><br>
					</p>
						
					<h1 class="font-weight-light">Météo en temps réel</h1>
					<p class="lead">
						La température à
						<%=session.getAttribute("ville1")%>
						est de
						<%=session.getAttribute("meteoVille1")%>
						°C. <br><br> La température à
						<%=session.getAttribute("ville2")%>
						est de
						<%=session.getAttribute("meteoVille2")%>°C.<br><br><br>
						<a href=ChoixVilles.jsp>Relancer un calcul</a>
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>