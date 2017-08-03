<%@page import="dev.sgp.entite.Departement"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="dev.sgp.entite.Collaborateur"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
.accordeon {
	border: 1px solid black;
	transition: 0.4s;
}

.accordeon:hover {
	cursor: pointer;
	color: white;
	background-color: #448bc8;
}

.accordeon.active {
	color: white;
	background-color: #448bc8;
}

.bloc {
	padding: 0 10px;
	max-height: 0;
	overflow: hidden;
	transition: max-height 0.3s ease-out;
}
</style>
</head>
<body>

	<div class="container-fluid">

		<div class="col-xs-4" style="padding-top: 10%;">
			<img src="<c:url value='/assets/collaborateurs/${collab.photo}'/>"
				alt="${collab.nom} ${collab.prenom}"
				title="${collab.nom} ${collab.prenom}" class="img-responsive" />
		</div>

		<div class="col-xs-8" style="padding-top: 5%;">
			<form class="form-horizontal" method="post">
				<input type="hidden" name="matricule" value="${ collab.matricule }"/>
				<div class="row">
					<div class="col-xs-10">
						<h1>${fn:toUpperCase(collab.nom)}
							${fn:toUpperCase(collab.prenom)} - ${collab.matricule}</h1>
					</div>
					<div class="col-xs-2" style="margin-top: 20px;">
						<label class="checkbox-inline"> <input type="checkbox"
							name="desactiver" value="1"
							<c:if test="${collab.actif == false}">checked</c:if>>
							Désactiver
						</label>
					</div>
				</div>

				<div class="row accordeon col-xs-12">
					<h4>
						<strong>Identité</strong>
					</h4>
				</div>
				<div class="row bloc col-xs-12 form-horizontal">
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-4 control-label" for="nom">Nom</label>
						<div class="col-md-4">
							<input id="nom" name="nom" type="text" value="${collab.nom}"
								class="form-control input-md" disabled>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-4 control-label" for="prenom">Prénom</label>
						<div class="col-md-4">
							<input id="prenom" name="prenom" type="text"
								value="${collab.prenom}" class="form-control input-md" disabled>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-4 control-label" for="datenaissance">Date
							de naissance</label>
						<div class="col-md-4">
							<input id="datenaissance" name="datenaissance" type="text"
								value="${collab.dateNaissance}" class="form-control input-md"
								disabled>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-4 control-label" for="adresse">Adresse</label>
						<div class="col-md-4">
							<textarea class="form-control" id="adresse" name="adresse"
								placeholder="Adresse du collaborateur" required>${collab.getAdresse()}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-4 control-label" for="numeroSS">Numéro
							de Sécurité Sociale</label>
						<div class="col-md-4">
							<input id="numeroSS" name="numeroSS" type="text"
								value="${collab.getNumeroSS()}" class="form-control input-md"
								disabled>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-4 control-label" for="telephone">Téléphone</label>
						<div class="col-md-4">
							<input id="telephone" name="telephone" value="${collab.tel}"
								pattern="^(?:0|\(?\+33\)?\s?|0033\s?)[1-79](?:[\.\-\s]?\d\d){4}$"
								title="Merci de renseigner un numéro de téléphone valide"
								type="text" placeholder="Téléphone du collaborateur"
								class="form-control input-md" required>
						</div>
					</div>
				</div>

				<div class="row accordeon col-xs-12">
					<h4>
						<strong>Poste</strong>
					</h4>
				</div>
				<div class="row bloc col-xs-12 form-horizontal">
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-4 control-label" for="poste">Intitulé
							du poste</label>
						<div class="col-md-4">
							<input id="poste" name="intitulePoste" type="text"
								placeholder="Poste" class="form-control input-md" value="${collab.intitulePoste}" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-4 control-label" for="departement">Département</label>
						<div class="col-md-4">
							<select id="departement" name="departement" class="form-control">
								<c:forEach var="d" items="${listeDepartements}">
									<option value="${d.id}"
										<c:if test="${d.id == collab.departement.id}"> selected</c:if>>
										${d.nom}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>

				<div class="row accordeon col-xs-12">
					<h4>
						<strong>Coordonnées Bancaires</strong>
					</h4>
				</div>
				<div class="row bloc col-xs-12 form-horizontal">
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-4 control-label" for="banque">Banque</label>
						<div class="col-md-4">
							<input id="banque" name="banque" type="text"
								placeholder="Banque du collaborateur"
								class="form-control input-md" value="${collab.banque}" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-4 control-label" for="bic">BIC</label>
						<div class="col-md-4">
							<input id="bic" name="bic" type="text"
								placeholder="BIC du collaborateur" class="form-control input-md" value="${collab.bic}"
								required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-4 control-label" for="iban">IBAN</label>
						<div class="col-md-4">
							<input id="iban" name="iban" type="text"
								placeholder="IBAN du collaborateur"
								class="form-control input-md" value="${collab.iban}" required>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-xs-12" style="text-align: right; margin-top: 10px;">
						<input type="submit" class="btn btn-primary" value="Sauvegarder" />
					</div>
				</div>
			</form>
		</div>

	</div>

	<script>
		var acc = document.getElementsByClassName("accordeon");
		for (var i = 0; i < acc.length; i++) {
			acc[i].onclick = function() {
				this.classList.toggle("active");
				var bloc = this.nextElementSibling;
				if (bloc.style.maxHeight) {
					bloc.style.maxHeight = null;
					bloc.style.border = "none";
				} else {
					bloc.style.maxHeight = bloc.scrollHeight + "px";
					bloc.style.border = "1px black solid";
				}
			}
		}
	</script>
</body>
</html>