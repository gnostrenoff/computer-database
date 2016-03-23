<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/dashboard"> Application
				- Computer Database </a>
			<div class="navbar-brand pull-right">
                <!-- Single button -->
                <div class="btn-group">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <spring:message code="dashboard.lang" /> <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="?lang=en"><spring:message code="dashboard.english" /></a></li>
                        <li><a href="?lang=fr"><spring:message code="dashboard.french" /></a></li>
                    </ul>
                </div>

				<a href="${pageContext.request.contextPath}/logout"
					class="btn btn-primary btn-danger"><span
					class="glyphicon glyphicon-log-out"></span> log out</a>
			</div>
		</div>

	</header>
	<section id="main">
		<div class="container">
			<div class="alert alert-info">
				<div class="row">
					<div class="col-xs-8 col-xs-offset-2 box">

						<form action="${pageContext.request.contextPath}/login"
							method="post" id="login-form" name="login-form">

							<spring:message code="login.username" var="username" />
							<spring:message code="login.password" var="password" />

							<h1>${message}</h1>

							<fieldset>
								<div class="form-group ">
									<label class="control-label requiredField" for="username">
										${username} <span class="asteriskField"> * </span>
									</label>
									<div class="input-group">
										<div class="input-group-addon">
											<i class="fa fa-user"> </i>
										</div>
										<input class="form-control" id="username" name="username"
											placeholder="${username}" type="text" />
									</div>
								</div>
								<div class="form-group ">
									<label class="control-label requiredField" for="password">
										${password} <span class="asteriskField"> * </span>
									</label>
									<div class="input-group">
										<div class="input-group-addon">
											<i class="fa fa-key"> </i>
										</div>
										<input class="form-control" type="password" id="password" name="password"
											placeholder="${password}" />
									</div>
								</div>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</fieldset>
							<div class="actions pull-right">
								<input type="submit"
									value="<spring:message
									code="login.login" />"
									class="btn btn-primary"></input> <a class="btn btn-default"><spring:message
										code="login.clear" /></a>
							</div>
						</form>
					</div>
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
</body>
</html>