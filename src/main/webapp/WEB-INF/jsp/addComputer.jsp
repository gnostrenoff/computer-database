<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="${pageContext.request.contextPath}/resources/css/font-awesome.css" rel="stylesheet"
	media="screen">
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" media="screen">
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
					<sf:form action="${pageContext.request.contextPath}/new" method="post"
						id="addcomputer-form" name="addcomputer-form"
						modelAttribute="computerDto">
						<fieldset>
							<div class="form-group">
								<label for="computerName">Name *</label>
								<sf:input type="text" path="name" cssClass="form-control"
									id="computerName" placeholder="computer name"></sf:input>
								<sf:errors path="name" cssclass="error"></sf:errors>
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label>
								<sf:input type="date" path="introduced" cssClass="form-control"
									id="introduced" placeholder="YYYY-MM-DD"></sf:input>
								<sf:errors path="introduced" cssclass="error"></sf:errors>
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label>
								<sf:input type="date" path="discontinued"
									cssClass="form-control" id="discontinued"
									placeholder="YYYY-MM-DD"></sf:input>
								<sf:errors path="discontinued" cssclass="error"></sf:errors>
							</div>
							<div class="form-group">
								<label for="companyId">Company</label>
								<sf:select path="companyId" cssClass="form-control"
									id="company-id">
									<option value=0>no company</option>
									<c:forEach items="${companies}" var="company">
										<option value="${company.id}">${company.id}-
											${company.name}</option>
									</c:forEach>
									<sf:errors path="companyId" cssClass="error"></sf:errors>
								</sf:select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Add" class="btn btn-primary"></input>
							or <a href="dashboard" class="btn btn-default">Cancel</a>
						</div>
					</sf:form>
					<sf:errors path="computerDto" cssclass="error"></sf:errors>
				</div>
			</div>
		</div>
	</section>
	<script src="${pageContext.request.contextPath}/resources/js/lib/jquery-1.11.1.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/lib/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/lib/jquery.validate.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/lib/jquery.validate.additional.js"></script>
	<!-- <script src="resources/js/addComputer.js"></script> -->
</body>
</html>