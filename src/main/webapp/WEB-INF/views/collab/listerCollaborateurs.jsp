<%@page import="dev.sgp.entite.Departement"%>
<%@page import="dev.sgp.entite.Collaborateur"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SGP - App</title>
<link rel="stylesheet"
	href="<c:url value='/bootstrap-3.3.7-dist/css/bootstrap.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/bootstrap-3.3.7-dist/js/bootstrap.min.css'/>">
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
								class="form-control input-md" required style="width: 100%;">
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
								<c:forEach var="listeDepartements"
									items="${listeDepartements}">
									<option value="${d.id}">${d.nom}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
			</form>

			<div class="container">

				<c:forEach var="collab" items="${listeCollabs}">

					<div class="col-md-4">
						<form class="form-horizontal" method="get" action="editer">
							<legend>${collab.nom}
								${collaborateur.prenom}</legend>
							<div class="col-xs-4">
								<img
									src="<c:url value='/assets/collaborateurs/${collab.photo}'/>"
									alt="${collab.nom} ${collab.prenom}"
									title="${collab.nom} ${collab.prenom}"
									class="img-responsive" />
							</div>
							<div class="col-xs-8">
								<div class="row">
									<div class="col-xs-6"
										style="text-align: right; white-space: nowrap;">
										<strong>Fonction :</strong>
									</div>
									<div class="col-xs-6">${collab.intitulePoste}</div>
								</div>
								<div class="row">
									<div class="col-xs-6"
										style="text-align: right; white-space: nowrap;">
										<strong>Département :</strong>
									</div>
									<div class="col-xs-6">${collab.departement.nom}</div>
								</div>
								<div class="row">
									<div class="col-xs-6"
										style="text-align: right; white-space: nowrap;">
										<strong>Email :</strong>
									</div>
									<div class="col-xs-6">${collab.emailPro}</div>
								</div>
								<div class="row">
									<div class="col-xs-6"
										style="text-align: right; white-space: nowrap;">
										<strong>Téléphone :</strong>
									</div>
									<div class="col-xs-6">%telephone%</div>
								</div>
								<div class="row" style="text-align: center;">
									<input type="hidden" name="id"
										value="${collab.matricule}" /> <input
										type="submit" class="btn btn-primary" value="Editer" />
								</div>
							</div>
						</form>
					</div>

				</c:forEach>

			</div>
		</div>
	</div>
</body>
</html>