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

	<jstl:if test="${row.interest == 'UNDEFINED'}" >
		<spring:message var="rowCss" text="white" />
	</jstl:if>
	<jstl:if test="${row.interest == 'LOW'}" >
		<spring:message var="rowCss" text="orange" />
	</jstl:if>
	<jstl:if test="${row.interest == 'MEDIUM'}" >
		<spring:message var="rowCss" text="lightgreen" />
	</jstl:if>
	<jstl:if test="${row.interest == 'HIGH'}" >
		<spring:message var="rowCss" text="green" />
	</jstl:if>


	<acme:column property="reference" title="tender.reference" style="background-color: ${rowCss}"  />
	<acme:column property="title" title="tender.title" style="background-color: ${rowCss}" />
	<acme:column property="maxPresentationDate" title="tender.maxPresentationDate" style="background-color: ${rowCss}" />
	
	<spring:message code="tender.comision" var="varTenderComision"/>
	<display:column title="${varTenderComision}" style="background-color: ${rowCss}" >
		<div>
			${row.estimatedAmount * (benefitsPercentaje/100)}
		</div>
	</display:column>	
	
	<spring:message code="tender.administrative" var="tenderAdministrative" />
	<display:column title="${tenderAdministrative}" style="background-color: white">
		<div>
			<jstl:if test="${row.administrative.id == actor.id}" >
				<spring:message code="offer.mine" />
			</jstl:if>
			<jstl:if test="${row.administrative.id != actor.id}" >
				<a href="actor/display.do?actorId=${row.administrative.id}">
					${row.administrative.name} ${row.administrative.surname}
				</a>
			</jstl:if>
		</div>
	</display:column>
	
	<display:column style="background-color: white">
		<div>
			<a href="tender/display.do?tenderId=${row.id}">
				<spring:message code="tender.display" />
			</a>
		</div>
	</display:column>
	
	<jstl:if test="${myTender}"  >
	<display:column style="background-color: white">
		<div>
			<a href="tenderResult/administrative/display.do?tenderId=${row.id}">
				<spring:message code="tender.tenderResult.display" />
			</a>
		</div>
	</display:column>
	</jstl:if>
	
	<jstl:if test="${anonymous}" >
	<display:column style="background-color: white">
		<div>
			<a href="tenderResult/display.do?tenderId=${row.id}">
				<spring:message code="tender.tenderResult.display" />
			</a>
		</div>
	</display:column>
	</jstl:if>

</display:table>
<br/><br/>

<security:authorize access="hasRole('ADMINISTRATIVE')">
	<acme:button url="tender/create.do" text="tender.new" css="formButton toLeft" />
</security:authorize>
