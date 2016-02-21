<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="uri" type="java.lang.String" description="uri"%>
<%@ attribute name="styleclass" type="java.lang.String"
	description="class for style"%>
<%@ attribute name="page" description="page index"%>
<%@ attribute name="number" description="number of element on the page"%>
<%@ attribute name="search" type="java.lang.String" description="search parameter"%>

<c:url value="${uri}" var="generatedUrl">
	<c:if test="${not empty search}">
		<c:param name="search" value="${search}" />
	</c:if>
	<c:param name="pageIndex" value="${page}" />
	<c:param name="nbElementPerPage" value="${number}" />
</c:url>

<c:out value="${generatedUrl}" />