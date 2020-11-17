<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.Ville"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Calcul de distance entre 2 villes</title>
</head>
<body>
	<div class="container">
		<div class="card border-0 shadow my-5">
			<div class="card-body p-5">
				<h1 class="font-weight-light">Calculer la distance entre deux villes</h1>
				<p class="lead">Veuillez choisir les villes ci-dessous ou cliquer 
					sur le lien pour voir les informations sur toutes les villes :</p>
				<div style="height: 400px">
					<br>
						<div class="panel-body">
							<FORM method="post" action="Calcul">
								<fieldset>
									<div class="form-group">

										<SELECT name="ville1" size="1">
											<%
											@SuppressWarnings("unchecked")
											ArrayList<Ville> liste1 = (ArrayList<Ville>) session.getAttribute("villes");
											for (Ville ville : liste1) {
											%>
											<OPTION>
												<%=ville.getNomCommune()%>
												<%
													}
												%>
											
										</SELECT> <SELECT name="ville2" size="1">
											<%
											@SuppressWarnings("unchecked")
											ArrayList<Ville> liste2 = (ArrayList<Ville>) session.getAttribute("villes");
											for (Ville ville : liste2) {
											%>
											<OPTION>
												<%=ville.getNomCommune()%>
												<%
													}
												%>
											
										</SELECT> <br> <br> <input
											class="btn btn-lg btn-primary btn-block" type="submit"
											value="Calcul de la distance" name="action">
									</div>
								</fieldset>
							</FORM>
							<br>
							<a href="afficheVille"> Afficher des informations sur les villes </a><br>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>