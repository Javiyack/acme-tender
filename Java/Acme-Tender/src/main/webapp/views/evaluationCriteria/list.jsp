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


<h5><spring:message code="evaluationCriteria.tenderData" /></h5>
<b>
	<spring:message code="tender.reference" />: ${tender.reference} <br/>
	<spring:message code="tender.title" />: ${tender.title} <br/>
</b>

<br/>

<h5><spring:message code="evaluationCriteria.evaluationCriteria" /></h5>

<display:table pagesize="5" class="displaytag" name="evaluationCriterias" requestURI="evaluationCriteria/administrative/list.do" id="row">

	<acme:column property="title" title="evaluationCriteria.title" />
	<acme:column property="description" title="evaluationCriteria.description" />
	<acme:column property="maxScore" title="evaluationCriteria.maxScore" />

	<acme:column property="evaluationCriteriaType.name" title="evaluationCriteria.evaluationCriteriaType" />
	
	<display:column>
		<div>
			<a href="evaluationCriteria/administrative/edit.do?evaluationCriteriaId=${row.id}">
				<spring:message code="evaluationCriteria.edit" />
			</a>
		</div>
	</display:column>


</display:table>
<br/>
<br/>
<acme:button url="/evaluationCriteria/administrative/create.do?tenderId=${tender.id}" text="evaluationCriteria.create" css="formButton toLeft" />
<acme:cancel url="/tender/administrative/list.do" code="evaluationCriteria.back" css="formButton toLeft" />

