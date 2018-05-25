
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div class="dashboard">

	<h5><spring:message code="dashboard.level"></spring:message> C</h5>
	<br>

	<!-- Dashboard C - 1. Tenders por administrative. -->
	<h3>
		<spring:message code="dashboard.administrative.tenders" />
	</h3>
	<display:table class="displaytag" name="c1Datos" id="row">
		<spring:message code="dashboard.administrative" var="nameAdministratives" />
		<display:column title="${nameAdministratives}">
			<jstl:out value="${row[1]}" />
		</display:column>
		<spring:message code="dashboard.number.tender" var="numberTender" />
		<display:column title="${numberTender}">
			<jstl:out value="${row[2]}" />
		</display:column>
	</display:table>

	
</div>

