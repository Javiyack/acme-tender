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

	
	<acme:column property="name" title="actor.name" />
	<acme:column property="surname" title="actor.surname" />
	<acme:column property="address" title="actor.address" />
	<acme:column property="email" title="actor.email" />
	<acme:column property="phone" title="actor.phone" />
	<acme:column property="userAccount.authorities[0]" title="actor.authority" sortable="true"/>

	<security:authorize access="hasRole('ADMIN')">
		<spring:message code="actor.state" var="varState" />
		<display:column title="${varState}">
			<jstl:if test="${row.userAccount.active}">
				<spring:message code="actor.is.active" />
				<a href="actor/administrator/activeOrDeactivate.do?actorId=${row.id}">
					(<spring:message code="actor.deactivate" />)
				</a>
			</jstl:if>
			<jstl:if test="${!row.userAccount.active}">
				<spring:message code="actor.is.not.active" />
				<a href="actor/administrator/activeOrDeactivate.do?actorId=${row.id}">			
					(<spring:message code="actor.activate" />)
				</a>
			</jstl:if>
		</display:column>
	</security:authorize>

	<display:column>
		<a href="actor/display.do?actorId=${row.id}"> 
			<spring:message code="actor.showProfile" />
		</a>
	</display:column>
	
	
</display:table>
