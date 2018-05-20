
<%@page import="org.springframework.test.web.ModelAndViewAssert"%>
<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div>

	<div>
		
		<br/> <br/>
		<div class="toRight">
			<spring:message code="tenderResult.tenderDate" />
			:
			<fmt:formatDate value="${tenderResult.tenderDate}"
				pattern="yyyy-MM-dd" var="tenderDate" />
			<jstl:out value="${tenderDate}"/>
		</div>
		<h5>
			<jstl:out value="${tenderResult.description}" />
		</h5>
	</div>

	<h4>
		<spring:message code="tenderResult.companyResult" />
	</h4>
	
	<display:table pagesize="5" class="displaytag" name="companyResults" id="row">

		<spring:message code="companyResult.name" var="companyResultsName" />
		<acme:column property="name" title="companyResult.name" />

		<spring:message code="companyResult.position" var="companyResultsPosition" />
		<acme:column property="position" title="companyResult.position" />
		
		<spring:message code="companyResult.economicalOffer" var="companyResultsEconomicalOffer" />
		<acme:column property="economicalOffer" title="companyResult.economicalOffer" />
		
		<spring:message code="companyResult.score" var="companyResultsScore" />
		<acme:column property="score" title="companyResult.score" />
		
		<spring:message code="companyResult.comments" var="companyResultsComments" />
		<acme:column property="comments" title="companyResult.comments" />
		
		<spring:message code="companyResult.state" var="companyResultsState" />
		<acme:column property="state" title="companyResult.state" />
	
	</display:table>
	<br />
	
	<security:authorize access="hasRole('ADMINISTRATIVE')">
		<jstl:if test="${companyResultCreate}">
			<acme:button text="tenderResult.companyResult.create" url="companyResult/administrative/create.do?tenderResultId=${tenderResult.id}" css="formButton toLeft" />
		</jstl:if>
	</security:authorize>
	<br /> <br />
	
	
	<security:authorize access="hasRole('ADMINISTRATIVE')">
		<jstl:if test="${tenderResult.id == null}">
			<acme:button text="tenderResult.create"
			url="tenderResult/administrative/create.do?tenderId=${tenderId}"
			css="formButton toLeft" />
		</jstl:if>
		
		<jstl:if test="${tenderResult.id != null}">
			<acme:button text="tenderResult.delete"
				url="tenderResult/administrative/delete.do?tenderResultId=${tenderResult.id}"
				css="formButton toLeft" />
		</jstl:if>
	</security:authorize>
	<acme:cancel url="tender/administrative/list.do" code="tenderResult.cancel" css="formButton toLeft" />
	
</div>
<br />
<br />