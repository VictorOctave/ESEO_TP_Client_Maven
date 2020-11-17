<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.Ville"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modification de la ville</title>
</head>
<body>
	<div class="container">
		<div class="card border-0 shadow my-5">
			<div class="card-body p-5">
				<h1 class="font-weight-light">Modification de la ville</h1>
				<p class="lead">Veuillez renseigner les informations de la ville choisie :</p>
				<div style="height: 500px">
					<br>
					<div class="row vertical-offset-100">
						<div class="col-sm-5 mx-auto">
							<div class="panel panel-default">
								<div class="panel-heading">
								</div>
								<div class="panel-body">
									
									<form accept-charset="UTF-8" role="form" method="post"
										action="envoiModification">
										<fieldset>
										<legend>ModificationVille</legend>
										<%
											Ville ville = (Ville) session.getAttribute("villeModif");
										%>
											<div class="form-group">
												<br> <br> <label>Code commune INSEE : <%=ville.getCodeCommune()%></label>
													
													 <br> <br>
												<div class="form-group">
													<label for="nomCommune">Nom : </label>
													<input class="form-control" placeholder="nomCommune"
														required name="nomCommune" id="nomCommune" type="text" 
														value=<%=ville.getNomCommune()%>>
												</div>
												<div class="form-group">
													<label for="codePostal">Code postal : </label>
													<input class="form-control" placeholder="codePostal"
														required name="codePostal" id="codePostal" type="text" 
														value=<%=ville.getCodePostal()%>>
												</div>
												<div class="form-group">
													<label for="libelleAcheminement">Libellé acheminement : </label>
													<input class="form-control"
														placeholder="libelleAcheminement" required
														name="libelleAcheminement" id="libelleAcheminement" type="text" 
														value=<%=ville.getLibelleAcheminement()%>>
												</div>
												<div class="form-group">
													<label for="ligne">Ligne : </label>
													<input class="form-control" id="ligne" type="text"
														value=<%=ville.getLigne()%>>
												</div>
												<div class="form-group">
													<label for="longitude">Longitude : </label>
													<input class="form-control" placeholder="longitude"
														required name="longitude" id="longitude" type="text" 
														value=<%=ville.getLongitude()%>>
												</div>
												<div class="form-group">
													<label for="latitude">Latitude : </label>
													<input class="form-control" placeholder="latitude" required
														name="latitude" id="latitude" type="text" 
														value=<%=ville.getLatitude()%>>
												</div>
												<br>
												<input class="btn btn-lg btn-primary btn-block"
													type="submit" value="envoyer">
											</div>
										</fieldset>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>