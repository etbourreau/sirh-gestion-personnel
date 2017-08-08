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
	href="<c:url value='/res/bootstrap-3.3.7-dist/css/bootstrap.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/res/bootstrap-3.3.7-dist/js/bootstrap.min.js'/>">
<style>
@media ( max-width : 991px) {
	.form-label {
		margin-top: 10px;
		text-align: center;
	}
	.collab-infos {
		text-align: center;
	}
	.collab-infos .row {
		font-size: 15px;
	}
}

@media ( min-width : 992px) {
	.form-label {
		padding-top: 7px;
		text-align: right;
		font-size: 12px;
	}
	.collab-infos {
		padding-left: 20px;
	}
	.collab-infos .row {
		font-size: 12px;
		white-space: nowrap;
	}
}

@media ( min-width : 1200px) {
	.form-label {
		font-size: 14px;
	}
}

.collab-infos .row .edit-btn {
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<h1>Les Collaborateurs</h1>
			</div>
		</div>
		<form class="row form-horizontal" style="margin-bottom: 15px;">
			<div class="col-xs-12 col-md-8">
				<div class="row">
					<div class="form-label col-md-6">Rechercher un nom ou un
						prénom qui commence par :</div>
					<div class="col-md-6">
						<input class="form-control input-md"
							name="nom"
							value="${nom}"
							type="text"
							placeholder="Recherche"
							style="width: 100%;">
					</div>
				</div>
				<div class="row">
					<div class="form-label col-md-6">Filtrer par département :</div>
					<div class="col-md-6">
						<select class="form-control" name="departement"
							style="width: 100%;">
							<option value="0">Tous les départements</option>
							<c:forEach var="d" items="${listeDepartements}">
								<option value="${d.id}" <c:if test="${ departement==d.id }">selected</c:if>>${d.nom}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-md-4">
				<div class="row">
					<div class="form-label col-xs-6">
						<button class="btn btn-primary" style="width: 100%;">
							Rechercher</button>
					</div>
					<div class="form-label col-xs-6" style="text-align: left;">
						<label class="checkbox-inline">
							<input type="checkbox"
							name="inactifs"
							value="1"
							<c:if test="${inactifs}">checked</c:if>/>
							Afficher les
							désactivés
						</label>
					</div>
				</div>
			</div>
		</form>
		<div class="row">
			<c:forEach var="collab" items="${listeCollabs}">
				<div class="col-md-4">
					<div class="row"
						style="font-size: 20px;
							font-weight: bold;
							white-space: nowrap;
							<c:if test="${!collab.actif}">color: red;</c:if>">
						${collab.nom} ${collab.prenom}</div>
					<div class="row">
						<div class="row col-xs-4">
							<img
								src="<c:if test="${collab.photo eq 'default.jpg'}"><c:url value='/assets/collaborateurs/'/></c:if>${collab.photo}"
								alt="${collab.nom} ${collab.prenom}"
								title="${collab.nom} ${collab.prenom}" class="img-responsive" />
						</div>
						<div class="collab-infos col-xs-8">
							<div class="row">
								<strong>Fonction:</strong> ${collab.intitulePoste}
							</div>
							<div class="row">
								<strong>Département:</strong> ${collab.departement.nom}
							</div>
							<div class="row">
								<strong>Email:</strong> ${collab.emailPro}
							</div>
							<div class="row">
								<strong>Téléphone:</strong> ${collab.tel}
							</div>
							<div class="row text-center">
								<a class="btn btn-primary" href="<c:url value='/collaborateurs/editer?matricule=${collab.matricule}'/>">
									Éditer
								</a>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>