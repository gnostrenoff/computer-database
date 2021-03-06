<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="uri" type="java.lang.String" description="uri"%>
<%@ attribute name="page" description="page index"%>
<%@ attribute name="number" description="number of element on the page"%>
<%@ attribute name="search" type="java.lang.String"
	description="search parameter"%>
<%@ attribute name="orderBy" type="java.lang.String"
	description="orderby parameter"%>
	<%@ attribute name="order" type="java.lang.String"
	description="order parameter"%>

<c:url value="${uri}" var="generatedUrl">
	<c:if test="${not empty search}">
		<c:param name="search" value="${search}" />
	</c:if>
	<c:if test="${not empty orderBy}">
		<c:param name="orderBy" value="${orderBy}" />
	</c:if>
	<c:if test="${not empty order}">
		<c:param name="order" value="${order}" />
	</c:if>
	<c:param name="index" value="${page}" />
	<c:param name="nbElements" value="${number}" />
</c:url>

<c:out value="${generatedUrl}" />