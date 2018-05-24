<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>
<%@page import="org.apache.commons.lang.time.DateUtils"%>
<%@page import="org.hibernate.engine.spi.RowSelection"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<display:table pagesize="5" class="displaytag" name="tenders" requestURI="${requestUri}" id="row">

	<spring:message code="tender.reference" var="tenderReference" />
	<acme:column property="reference" title="tender.reference" />

	<spring:message code="tender.title" var="tenderTitle" />
	<acme:column property="title" title="tender.title" />
	
	<spring:message code="tender.expedient" var="tenderExpedient" />
	<acme:column property="expedient" title="tender.expedient" />
	
	<spring:message code="tender.organism" var="tenderOrganism" />
	<acme:column property="organism" title="tender.organism" />
	
	<spring:message code="tender.bulletin" var="tenderBulletin" />
	<acme:column property="bulletin" title="tender.bulletin" />
	
	<spring:message code="tender.observations" var="tenderObservations" />
	<acme:column property="observations" title="tender.observations" />
	
	<spring:message code="tender.informationPage" var="tenderInformationPage" />
	<acme:column property="informationPage" title="tender.informationPage" />
	
	<spring:message code="tender.interestComment" var="tenderInterestComment" />
	<acme:column property="interestComment" title="tender.interestComment" />

	<security:authorize access="hasRole('ADMIN')">
	<display:column>
		<div>
			<a href="tender/administrator/delete.do?tenderId=${row.id}">
				<spring:message code="tender.delete" />
			</a>
		</div>
	</display:column>
	</security:authorize>

</display:table>
