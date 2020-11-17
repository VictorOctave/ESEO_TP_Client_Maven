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
					<h1 class="font-weight-light">R�sultat du calcul</h1>
					<p class="lead">
						La distance entre
						<%=session.getAttribute("ville1")%>
						et
						<%=session.getAttribute("ville2")%>
						est de
						<%=session.getAttribute("distance")%>
						km. <br><br>
					</p>
						
					<h1 class="font-weight-light">M�t�o en temps r�el</h1>
					<p class="lead">
						La temp�rature �
						<%=session.getAttribute("ville1")%>
						est de
						<%=session.getAttribute("meteoVille1")%>�C. 
						<img src=<%=session.getAttribute("imageMeteo1")%> 
						alt="Probl�me d'affichage de l'image"/><br><br> 
						La temp�rature �
						<%=session.getAttribute("ville2")%>
						est de
						<%=session.getAttribute("meteoVille2")%>�C. 
						<img src=<%=session.getAttribute("imageMeteo2")%> 
						alt="Probl�me d'affichage de l'image"/><br><br><br>
						<a href=ChoixVilles.jsp>Revenir � l'accueil</a>
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>