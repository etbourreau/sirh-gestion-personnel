<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SGP - Stats</title>
<link rel="stylesheet"
	href="<c:url value='/res/bootstrap-3.3.7-dist/css/bootstrap.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/res/bootstrap-3.3.7-dist/js/bootstrap.min.js'/>">
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6">
				<h1>Activités depuis le démarrage de l'application</h1>
			</div>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Date/Heure</th>
					<th>Libellé</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="action" items="${listeActivites}">
					<tr>
						<td>${ action.dateHeure }</td>
						<td>${ action.type.msg} - matricule : ${action.matricule }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>