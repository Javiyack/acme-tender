
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


<b>
	<spring:message code="tender.reference" />: ${tenderResult.tender.reference} <br/>
	<spring:message code="tender.title" />:${tenderResult.tender.title} <br/>
	<spring:message code="tenderResult.tenderDate" />:
		<fmt:formatDate value="${tenderResult.tenderDate}" pattern="yyyy-MM-dd" var="tenderDate" />
		<jstl:out value="${tenderDate}"/>  <br/>
	<spring:message code="tenderResult.description" />: ${tenderResult.description} <br/>
</b>

<br/>
<security:authorize access="hasRole('ADMINISTRATIVE')">
	<jstl:if test="${tenderResult.id == null}">
		<acme:button text="tenderResult.create"
		url="tenderResult/administrative/create.do?tenderId=${tenderId}"
		css="formButton toLeft" />
	</jstl:if>
	
	<jstl:if test="${tenderResult.id != null}">
		<acme:button text="tenderResult.delete" url="tenderResult/administrative/delete.do?tenderResultId=${tenderResult.id}" css="formButton toLeft" />
	</jstl:if>
</security:authorize>

<acme:cancel url="tender/administrative/list.do" code="tenderResult.back" css="formButton toLeft" />

<br/><br/><br/>


<h5>
	<spring:message code="tenderResult.companyResult" />
</h5>

<display:table pagesize="5" class="displaytag" name="companyResults" requestURI="tenderResult/administrative/display.do" id="row">

	<spring:message code="companyResult.name" var="companyResultsName" />
	<acme:column property="name" title="companyResult.name" />

	<spring:message code="companyResult.position" var="companyResultsPosition" />
	<acme:column property="position" title="companyResult.position" />
		
		<jstl:if test="${companyResultCreate}">
			<display:column>
				<div>
					<a href="companyResult/administrative/delete.do?companyResultId=${row.id}">
						<spring:message code="tenderResult.delete" />
					</a>
				</div>
			</display:column>
		</jstl:if>
	
	<spring:message code="companyResult.economicalOffer" var="companyResultsEconomicalOffer" />
	<acme:column property="economicalOffer" title="companyResult.economicalOffer" />
	
	<spring:message code="companyResult.score" var="companyResultsScore" />
	<acme:column property="score" title="companyResult.score" />
	
	<spring:message code="companyResult.comments" var="companyResultsComments" />
	<acme:column property="comments" title="companyResult.comments" />
	
	<spring:message code="companyResult.state" var="companyResultsState" />
	<acme:column property="state" title="companyResult.state" />
	
	<display:column>
		<div>
			<a href="tenderResult/administrative/edit.do?tenderResultId=${row.id}"> 
				<spring:message code="tenderResult.edit" />
			</a>
		</div>
	</display:column>

</display:table>
<br />

<security:authorize access="hasRole('ADMINISTRATIVE')">
	<jstl:if test="${companyResultCreate}">
		<acme:button text="tenderResult.companyResult.create" url="companyResult/administrative/create.do?tenderResultId=${tenderResult.id}" css="formButton toLeft" />
	</jstl:if>
</security:authorize>
