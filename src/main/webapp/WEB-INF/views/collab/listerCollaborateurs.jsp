<%@page import="dev.sgp.entite.Collaborateur"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>SGP - App</title>
		<!-- <link rel="stylesheet"
				href="<%=request.getContextPath()%>/bootstrap-3.3.7-
			dist/css/bootstrap.css"> -->
		<link rel="stylesheet"
			href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
		<script
			src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-6">
					<h1>Les Collaborateurs</h1>
				</div>
			</div>
			<div id="contenu">
				<form class="form-inline">
					<div class="row">
						<div class="col-xs-4" style="text-align: right;">Rechercher
							un nom ou un prénom qui commence par :</div>
						<div class="col-xs-4" style="text-align: center;">
							<div class="col-xs-8">
								<input name="recherche" type="text" placeholder="Recherche"
									class="form-control input-md" required="" style="width: 100%;">
							</div>
							<div class="col-xs-4">
								<button class="btn btn-primary">Rechercher</button>
							</div>
						</div>
						<div class="col-xs-4">
							<label class="checkbox-inline"> <input type="checkbox"
								name="collaborateursDesactives" value="1"> Voir les
								collaborateurs désactivés
							</label>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-4" style="text-align: right;">Filtrer par
							département :</div>
						<div class="col-xs-4" style="text-align: center;">
							<div class="col-xs-8">
								<select name="filtreDépartement" class="form-control"
									style="width: 100%;">
									<option value="1">Département 1</option>
									<option value="2">Département 2</option>
								</select>
							</div>
						</div>
					</div>
				</form>
	
				<div class="container">
					
					<%
						List<Collaborateur> collaborateurs = (List<Collaborateur>)request.getAttribute("listeCollabs");
						for (Collaborateur collaborateur : collaborateurs) {
					%>
					
					<div class="col-md-4">
						<form class="form-horizontal">
							<legend><%=collaborateur.getNom().toUpperCase() %> <%=collaborateur.getPrenom().toUpperCase() %></legend>
							<div class="col-xs-4">
								<img src="<%=collaborateur.getPhoto() %>" alt="<%=collaborateur.getNom().toUpperCase() %> <%=collaborateur.getPrenom().toUpperCase() %>" title="<%=collaborateur.getNom().toUpperCase() %> <%=collaborateur.getPrenom().toUpperCase() %>"
									class="img-responsive" />
							</div>
							<div class="col-xs-8">
								<div class="row">
									<div class="col-xs-6"
										style="text-align: right; white-space: nowrap;">
										<strong>Fonction :</strong>
									</div>
									<div class="col-xs-6">%function%</div>
								</div>
								<div class="row">
									<div class="col-xs-6"
										style="text-align: right; white-space: nowrap;">
										<strong>Département :</strong>
									</div>
									<div class="col-xs-6">%departement%</div>
								</div>
								<div class="row">
									<div class="col-xs-6"
										style="text-align: right; white-space: nowrap;">
										<strong>Email :</strong>
									</div>
									<div class="col-xs-6"><%=collaborateur.getEmailPro() %></div>
								</div>
								<div class="row">
									<div class="col-xs-6"
										style="text-align: right; white-space: nowrap;">
										<strong>Téléphone :</strong>
									</div>
									<div class="col-xs-6">%telephone%</div>
								</div>
								<div class="row" style="text-align: center;">
									<input type="submit" class="btn btn-primary" value="Editer"/>
								</div>
							</div>
						</form>
					</div>
					
					<%
						}
					%>
	
				</div>
			</div>
		</div>
	</body>
</html>