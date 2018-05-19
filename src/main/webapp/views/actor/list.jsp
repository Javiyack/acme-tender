<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table pagesize="5" class="displaytag" name="actors" requestURI="${requestUri}" id="row">

	<spring:message code="actor.name" var="actorName" />
	<acme:column property="name" title="actor.name" />

	<spring:message code="actor.surname" var="actorSurname" />
	<acme:column property="surname" title="actor.surname" />

	<spring:message code="actor.address" var="actorAddress" />
	<acme:column property="address" title="actor.address" />

	<spring:message code="actor.email" var="actorEmail" />
	<acme:column property="email" title="actor.email" />

	<spring:message code="actor.phone" var="actorPhone" />
	<acme:column property="phone" title="actor.phone" />
	
	<display:column>
			<div>
				<a href="actor/display.do?actorId=${row.id}"> 
					<spring:message code="actor.showProfile" />
				</a>
			</div>
	</display:column>
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="actor/administrator/activeOrDesactive.do?actorId=${row.id}">
				<button class = "btn">
					<jstl:if test="${row.userAccount.active}">
						<spring:message code="actor.deactivate" />
					</jstl:if>
					<jstl:if test="${!row.userAccount.active}">
						<spring:message code="actor.activate" />
					</jstl:if>
				</button>
			</a>
		</display:column>
	</security:authorize>
	
</display:table>
