<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="resources/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="resources/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id : ${computer.id}</div>
					<h1>Edit Computer</h1>

					<form action="edit" method="POST" id="editcomputer-form" name="editcomputer-form">
						<input type="hidden" value="${computer.id}" name="id" />
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" id="computerName" name="name"
									value="${computer.name}">
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									type="date" class="form-control" id="introduced" name="introduced"
									placeholder="${computer.introduced}">
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									type="date" class="form-control" id="discontinued" name="discontinued"
									placeholder="${computer.discontinued}">
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="companyId">
									<option value="${computer.companyId}">${computer.companyId} - ${computer.companyName}</option>
									<c:forEach items="${companies}" var="company">
										<option value="${company.id}">${company.id}-
											${company.name}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary">
							or <a href="dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<script src="resources/js/lib/jquery-1.11.1.js"></script>
	<script src="resources/js/lib/bootstrap.min.js"></script>
	<script src="resources/js/lib/jquery.validate.js"></script>
	<script src="resources/js/lib/jquery.validate.additional.js"></script>
	<script src="resources/js/editComputer.js"></script>
</body>
</html>