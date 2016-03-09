<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link
	href="${pageContext.request.contextPath}/resources/css/font-awesome.css"
	rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/resources/css/main.css"
	rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a> <a href="?lang=en"
				class="navbar-brand btn btn-inverse btn-large pull-right">EN</a> <a
				href="?lang=fr"
				class="navbar-brand btn btn-inverse btn-large pull-right">FR</a>
		</div>

	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>
						<spring:message code="common.new" />
					</h1>
					<sf:form action="${pageContext.request.contextPath}/new"
						method="post" id="addcomputer-form" name="addcomputer-form"
						modelAttribute="computerDto">

						<!-- get value for placeholders -->
						<spring:message code="common.name" var="name" />
						<spring:message code="common.datePattern" var="datePattern" />

						<fieldset>
							<div class="form-group">
								<label for="computerName"><spring:message
										code="common.name" /> *</label>
								<sf:input type="text" path="name" cssClass="form-control"
									id="computerName" placeholder="${name}"></sf:input>
								<sf:errors path="name" cssclass="error"></sf:errors>
							</div>
							<div class="form-group">
								<label for="introduced"><spring:message
										code="common.introduced" /></label>
								<sf:input type="date" path="introduced" cssClass="form-control"
									id="introduced" placeholder="${datePattern}"></sf:input>
								<sf:errors path="introduced" cssclass="error"></sf:errors>
							</div>
							<div class="form-group">
								<label for="discontinued"><spring:message
										code="common.discontinued" /></label>
								<sf:input type="date" path="discontinued"
									cssClass="form-control" id="discontinued"
									placeholder="${datePattern}"></sf:input>
								<sf:errors path="discontinued" cssclass="error"></sf:errors>
							</div>
							<div class="form-group">
								<label for="companyId"><spring:message
										code="common.company" /></label>
								<sf:select path="companyId" cssClass="form-control"
									id="company-id">
									<option value=0><spring:message
											code="common.defaultCompanyName" /></option>
									<c:forEach items="${companies}" var="company">
										<option value="${company.id}">${company.id}-
											${company.name}</option>
									</c:forEach>
									<sf:errors path="companyId" cssClass="error"></sf:errors>
								</sf:select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit"
								value="<spring:message
									code="common.save" />"
								class="btn btn-primary"></input> <a href="dashboard"
								class="btn btn-default"><spring:message code="common.cancel" /></a>
						</div>
					</sf:form>
					<sf:errors path="computerDto" cssclass="error"></sf:errors>
				</div>
			</div>
		</div>
	</section>
	<script
		src="${pageContext.request.contextPath}/resources/js/lib/jquery-1.11.1.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/lib/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/lib/jquery.validate.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/lib/jquery.validate.additional.js"></script>
	<!-- <script src="resources/js/addComputer.js"></script> -->
</body>
</html>