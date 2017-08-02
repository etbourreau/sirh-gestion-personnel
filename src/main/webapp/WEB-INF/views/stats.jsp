<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SGP - Stats</title>
<link rel="stylesheet"
	href="<c:url value='/bootstrap-3.3.7-dist/css/bootstrap.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/bootstrap-3.3.7-dist/js/bootstrap.min.css'/>">
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6">
				<h1>Statistiques</h1>
			</div>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Chemin</th>
					<th>Nombre de visites</th>
					<th>Min (ms)</th>
					<th>Max (ms)</th>
					<th>Moyenne (ms)</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="visite" items="${listeVisites}">
					<tr>
						<td>${ visite.key }</td>
						<td>${ visite.value.getCount() }</td>
						<td>${ visite.value.getMin() }</td>
						<td>${ visite.value.getMax() }</td>
						<td>${ visite.value.getAverage() }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>