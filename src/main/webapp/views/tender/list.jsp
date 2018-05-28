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
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<display:table pagesize="5" class="displaytag" name="tenders" requestURI="${requestUri}" id="row">

	<acme:column property="reference" title="tender.reference" />
	<acme:column property="title" title="tender.title" />
	<acme:column property="maxPresentationDate" title="tender.maxPresentationDate" />
	
	<spring:message code="tender.associated.offer" var="tenderOffer"/>
	<display:column title="${tenderOffer}">
		<jstl:if test="${tender.offer != null}" >
			<div>
				<a href="offer/display.do?tenderId=${row.id}">
					<jstl:out value="${tender.offer.commercial.name} ${tender.offer.commercial.surname}" />
				</a>
			</div>
		</jstl:if>
		<jstl:if test="${tender.offer == null}" >
			<security:authorize access="hasRole('COMMERCIAL')"> 
				<div>
					<a href="offer/create.do?tenderId=${row.id}">
						<spring:message code="tender.create.offer" />
					</a>
				</div>
			</security:authorize>
		</jstl:if>		
	</display:column>
	
	<display:column>
		<div>
			<a href="tender/display.do?tenderId=${row.id}">
				<spring:message code="tender.display" />
			</a>
		</div>
	</display:column>
	
	<display:column>
		<div>
			<a href="tenderResult/display.do?tenderId=${row.id}">
				<spring:message code="tender.tenderResult.display" />
			</a>
		</div>
	</display:column>

</display:table>
<br/><br/>

<security:authorize access="hasRole('ADMINISTRATIVE')">
	<acme:button url="tender/create.do" text="tender.new" css="formButton toLeft" />
</security:authorize>
