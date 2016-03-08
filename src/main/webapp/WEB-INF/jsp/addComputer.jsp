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
					<h1>Add Computer</h1>
					<form action="new" method="POST" id="addcomputer-form"
						name="addcomputer-form">
						<fieldset>
							<div class="form-group">
								<label for="computerName">Name *</label> <input type="text"
									class="form-control" id="computerName"
									placeholder="Computer name" name="name">
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									type="date" class="form-control" id="introduced"
									placeholder="YYYY-MM-DD" name="introduced">
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									type="date" class="form-control" id="discontinued"
									placeholder="YYYY-MM-DD" name="discontinued">
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="company-id" name="companyId">
									<option value=0>no company</option>
									<c:forEach items="${companies}" var="company">
										<option value="${company.id}">${company.id}-
											${company.name}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Add" class="btn btn-primary">
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
	<script src="resources/js/addComputer.js"></script>
</body>
</html>