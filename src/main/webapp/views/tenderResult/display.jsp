
<%@page import="org.springframework.test.web.ModelAndViewAssert"%>
<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>



<fieldset>
	<legend>
		<spring:message code="tenderResult.data" />
	</legend>

	<acme:labelvalue code="tender.reference" value="${tenderResult.tender.reference}" />
	<acme:labelvalue code="tender.title" value="${tenderResult.tender.title}" />
	<acme:labelvalue code="tenderResult.tenderDate" value="${tenderResult.tenderDate}" isDate="true" />
	<acme:labelvalue code="tenderResult.description" value="${tenderResult.description}" />

	<br/>
	<jstl:if test="${tenderResult.tender.administrative.id == actor.id}">
		<acme:button text="tenderResult.edit" url="tenderResult/administrative/edit.do?tenderResultId=${tenderResult.id}" css="formButton toLeft" />
	</jstl:if>	
	<acme:cancel url="tender/list.do" code="tenderResult.back" css="formButton toLeft" />

</fieldset>

<fieldset>
	<legend>
		<spring:message code="tenderResult.companyResult" />
	</legend>

	<display:table class="displaytag" name="companyResults" id="row">

		<acme:column property="name" title="companyResult.name" />
		<acme:column property="position" title="companyResult.position" />
		
		<spring:message code="companyResult.economicalOffer" var="titleCompanyResultEconomicalOffer"/>
		<display:column title="${titleCompanyResultEconomicalOffer}" >
			<fmt:formatNumber value="${row.economicalOffer}" pattern="${formpriceformat}" var="varEconomicalOffer" />
			<div>
				${varEconomicalOffer} <spring:message code="currency.symbol" />
			</div>
		</display:column>			
		
		<acme:column property="score" title="companyResult.score" />
		<acme:column property="comments" title="companyResult.comments" />
		<acme:column property="state" title="companyResult.state" />

		<display:column>
			<a href="companyResult/display.do?companyResultId=${row.id}"> 
				<spring:message code="tenderResult.display" />
			</a>
		</display:column>
		

	</display:table>

	<jstl:if test="${tenderResult.tender.administrative.id == actor.id}">
		<acme:button text="tenderResult.companyResult.create" url="companyResult/administrative/create.do?tenderResultId=${tenderResult.id}" css="formButton toLeft" />
	</jstl:if>

</fieldset>

<fieldset>
	<legend>
		<spring:message code="tenderResult.data.files" />
	</legend>

	<display:table class="displaytag" name="files" id="row">

		<acme:column property="name" title="file.name" />
		<acme:column property="uploadDate" title="file.uploadDate" format="display.date.time.format" />

		<display:column>
			<a href="file/display.do?fileId=${row.id}"> 
				<spring:message code="file.display" />
			</a>
		</display:column>
	</display:table>

	<jstl:if test="${tenderResult.tender.administrative.id == actor.id}">
		<acme:button text="file.create" url="/file/create.do?id=${tenderResult.id}&type=tenderResult" css="formButton toLeft" />
	</jstl:if>

</fieldset>

