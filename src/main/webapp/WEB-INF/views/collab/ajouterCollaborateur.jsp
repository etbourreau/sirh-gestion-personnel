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
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6">
				<h1>Nouveau Collaborateur</h1>
			</div>
		</div>
		<div id="contenu">
			<form class="form-horizontal" method="post">
				<div class="form-group">
					<label class="col-md-3 control-label" for="nom">Nom</label>
					<div class="col-md-8">
						<input id="nom" name="nom" type="text"
							placeholder="Nom du collaborateur" class="form-control input-md"
							required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label" for="prenom">Prénom</label>
					<div class="col-md-8">
						<input id="prenom" name="prenom" type="text"
							placeholder="Prénom du collaborateur"
							class="form-control input-md" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label" for="dateNaissance">Date
						de naissance</label>
					<div class="col-md-8">
						<input id="dateNaissance" name="dateNaissance" type="text"
							placeholder="JJ-MM-YYYY" pattern="^[0-3]\d-[0-1]\d-\d{4}$"
							title="Merci de saisir une date au format JJ-MM-AAAA"
							class="form-control input-md" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label" for="adresse">Adresse</label>
					<div class="col-md-8">
						<textarea class="form-control" id="adresse" name="adresse"
							placeholder="Adresse du collaborateur" required></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label" for="numeroSS">Numéro de
						Sécurité Sociale</label>
					<div class="col-md-8">
						<input id="numeroSS" name="numeroSS" type="text"
							placeholder="Numéro de Sécurité Sociale du collaborateur"
							pattern="^[1-2][0-9]{14}$"
							title="Merci de renseigner un numéro de sécurité sociale valide"
							class="form-control input-md" required>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-11">
						<button class="btn btn-primary pull-right">Créer</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>