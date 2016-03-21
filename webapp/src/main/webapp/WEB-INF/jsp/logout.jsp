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
				- Computer Database </a> <a href="?lang=en"
				class="navbar-brand btn btn-inverse btn-large pull-right">en</a> <a
				href="?lang=fr"
				class="navbar-brand btn btn-inverse btn-large pull-right">fr</a>
		</div>

	</header>

	<section id="main">
		<div class="container">
			<div class="info alert-info">
				<h1>You were succesfully logged out. Come back soon !</h1>
				<br />
			</div>
			<div>
				<a href="${pageContext.request.contextPath}/login"
					class="btn btn-block btn-lg btn-primary">log in <span
					class="glyphicon glyphicon-log-in"></span></a>
			</div>
		</div>
	</section>

	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>

</body>
</html>