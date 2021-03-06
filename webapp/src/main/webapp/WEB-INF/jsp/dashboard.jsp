<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
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
			<h1 id="homeTitle">${page.nbTotalComputers}
				<spring:message code="dashboard.count" />
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<spring:message code="dashboard.searchValue" var="searchValue" />
					<spring:message code="dashboard.searchButton" var="searchButton" />
					<form id="searchForm"
						action="${pageContext.request.contextPath}/dashboard" method="GET"
						class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="${searchValue}" /> <input
							type="hidden" name="nbElementPerPage" value="${page.nbElements}">
						<input type="hidden" name="pageIndex" value="${page.index}">
						<input type="submit" id="searchsubmit" value="${searchButton}"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer"
						href="${pageContext.request.contextPath}/computer/admin/new"><spring:message
							code="common.new" /></a> <a class="btn btn-default"
						id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message
							code="dashboard.edit" /></a>
				</div>
			</div>
		</div>

		<form id="deleteForm"
			action="${pageContext.request.contextPath}/computer/delete"
			method="POST">
			<input type="hidden" name="selection" value=""> <input
				type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><a
							href="<t:link uri="${uri}" page="${page.index}" number="${page.nbElements}" search="${page.search}" orderBy="name" order="${page.orderBy == 'name' && page.order == 'ASC' ? 'DESC' : 'ASC'}"/>"><spring:message
									code="common.name" /></a></th>
						<th><a
							href="<t:link uri="${uri}" page="${page.index}" number="${page.nbElements}" search="${page.search}" orderBy="introduced" order="${page.orderBy == 'introduced' && page.order == 'ASC' ? 'DESC' : 'ASC'}"/>"><spring:message
									code="common.introduced" /></a></th>
						<!-- Table header for Discontinued Date -->
						<th><a
							href="<t:link uri="${uri}" page="${page.index}" number="${page.nbElements}" search="${page.search}" orderBy="discontinued" order="${page.orderBy == 'discontinued' && page.order == 'ASC' ? 'DESC' : 'ASC'}"/>"><spring:message
									code="common.discontinued" /></a></th>
						<!-- Table header for Company -->
						<th><a
							href="<t:link uri="${uri}" page="${page.index}" number="${page.nbElements}" search="${page.search}" orderBy="company.name" order="${page.orderBy == 'company.name' && page.order == 'ASC' ? 'DESC' : 'ASC'}"/>"><spring:message
									code="common.company" /></a></th>
					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${page.computerList}" var="computer">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}"></td>
							<td><a
								href="${pageContext.request.contextPath}/computer/admin/edit/${computer.id}"
								onclick=""><c:out value="${computer.name}" /></a></td>
							<td><c:out value="${computer.introduced}" /></td>
							<td><c:out value="${computer.discontinued}" /></td>
							<td><c:out value="${computer.companyName}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<t:pagination uri="dashboard" page="${page}" />
		</div>
	</footer>
	<script
		src="${pageContext.request.contextPath}/resources/js/lib/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/lib/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/lib/jquery.validate.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>
</body>
</html>