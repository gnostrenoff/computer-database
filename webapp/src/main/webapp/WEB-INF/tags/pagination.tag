<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ attribute name="page" type="com.gnostrenoff.cdb.dto.PageDto"
	description="object containing needed parameters"%>
<%@ attribute name="uri" type="java.lang.String" description="uri"%>

<ul class="pagination">

	<!-- link to previous page or first -->
	<c:if test="${page.index > 1}">
		<li><a
			href="<t:link uri="${uri}" page="1" number="${page.nbElements}" search="${page.search}" orderBy="${page.orderBy}" order="${page.order}"/>"><spring:message
							code="page.first" /></a></li>
		<li><a
			href="<t:link uri="${uri}" page="${page.index - 1}" number="${page.nbElements}" search="${page.search}" orderBy="${page.orderBy}" order="${page.order}"/>"><spring:message
							code="page.previous" /></a></li>
	</c:if>
	<c:if test="${page.index == 1}">
		<li class="disabled active"><a href=""><spring:message
							code="page.first" /></a></li>
		<li class="disabled active"><a href=""><spring:message
							code="page.previous" /></a></li>
	</c:if>

	<!-- link to available pages -->
	<c:forEach var="i" begin="${page.pageStart}" end="${page.pageEnd}">
		<c:choose>
			<c:when test="${page.index == i}">
				<li class="disabled active"><a href="">${i}</a></li>
			</c:when>
			<c:when test="${page.index != i}">
				<li><a
					href="<t:link uri="${uri}" page="${i}" number="${page.nbElements}" search="${page.search}" orderBy="${page.orderBy}" order="${page.order}"/>">${i}</a></li>
			</c:when>
		</c:choose>
	</c:forEach>

	<!-- link to next page and last page -->
	<c:if test="${page.index < page.nbTotalPages}">
		<li><a
			href="<t:link uri="${uri}" page="${page.index + 1}" number="${page.nbElements}" search="${page.search}" orderBy="${page.orderBy}" order="${page.order}"/>"><spring:message
							code="page.next" /></a></li>
		<li><a
			href="<t:link uri="${uri}" page="${page.nbTotalPages}" number="${page.nbElements}" search="${page.search}" orderBy="${page.orderBy}" order="${page.order}"/>"><spring:message
							code="page.last" /></a></li>
	</c:if>
	<c:if test="${page.index == page.nbTotalPages}">
		<li class="disabled active"><a href=""><spring:message
							code="page.next" /></a></li>
		<li class="disabled active"><a href=""><spring:message
							code="page.last" /></a></li>
	</c:if>

</ul>


<div class="btn-group btn-group-sm pull-right" role="group">

	<a class="btn default-btn"
		href="<t:link uri="${uri}" page="1" number="10" search="${page.search}" orderBy="${page.orderBy}" order="${page.order}"/>">10</a>
	<a class="btn default-btn"
		href="<t:link uri="${uri}" page="1" number="50" search="${page.search}" orderBy="${page.orderBy}" order="${page.order}"/>">50</a>
	<a class="btn default-btn"
		href="<t:link uri="${uri}" page="1" number="100" search="${page.search}" orderBy="${page.orderBy}" order="${page.order}"/>">100</a>

</div>




