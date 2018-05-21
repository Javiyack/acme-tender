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

<display:table pagesize="5" class="displaytag" name="subSectionEvaluationCriterias" requestURI="subSectionEvaluationCriteria/commercial/list.do" id="row">

	<acme:column property="comments" title="subSectionEvaluationCriteria.comments" />
	<acme:column property="evaluationCriteria.title" title="subSectionEvaluationCriteria.evaluationCriteria" />
	
	<display:column>
		<div>
			<a href="subSectionEvaluationCriteria/commercial/edit.do?subSectionEvaluationCriteriaId=${row.id}">
				<spring:message code="subSectionEvaluationCriteria.edit" />
			</a>
		</div>
	</display:column>

</display:table>
<br/>
<br/>
<acme:button url="/subSectionEvaluationCriteria/commercial/create.do?subSectionId=${subSectionId}" text="subSectionEvaluationCriteria.create" css="formButton toLeft" />
<acme:cancel url="/offer/display.do?offerId=${offerId}" code="subSectionEvaluationCriteria.back" css="formButton toLeft" />
