
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
	<legend><spring:message code="tender.data" /></legend>
	
	
	<acme:labelvalue code="tender.reference"    value="${tender.reference}" /> 
	<acme:labelvalue code="tender.title"    value="${tender.title}" /> 
	<acme:labelvalue code="tender.expedient"    value="${tender.expedient}" /> 
	<acme:labelvalue code="tender.estimatedAmount" value="${tender.estimatedAmount}" isCurrency="true"/>
	<acme:labelvalue code="tender.organism"    value="${tender.organism}" /> 
	<acme:labelvalue code="tender.bulletin"    value="${tender.bulletin}" /> 
	<acme:labelvalue code="tender.bulletinDate"    value="${tender.bulletinDate}" isDatetime="true" />
	<acme:labelvalue code="tender.openingDate"    value="${tender.openingDate}" isDatetime="true"/> 
	<acme:labelvalue code="tender.maxPresentationDate"    value="${tender.maxPresentationDate}" isDatetime="true" /> 
	<acme:labelvalue code="tender.executionTime"    value="${tender.executionTime}" /> 
	<acme:labelvalue code="tender.observations"    value="${tender.observations}" /> 
	<acme:labelvalue code="tender.informationPage" value="${tender.informationPage}" href="${tender.informationPage}" />
	<acme:labelvalue code="tender.interest"    value="${tender.interest}" /> 
	<acme:labelvalue code="tender.interestComment"    value="${tender.interestComment}" /> 
	<br/><br/>

	<jstl:if test="${tender.administrative.id == actor.id}" >
		<acme:button url="tender/administrative/edit.do?tenderId=${tender.id}" text="tender.edit" css="formButton toLeft"/>
	</jstl:if>
	
	<acme:button url="tender/list.do" text="tender.back" css="formButton toLeft"/>
	
	<jstl:if test="${tender.canCreateOffer}" >
		<security:authorize access="hasRole('COMMERCIAL')"> 
			<acme:button url="offer/commercial/create.do?tenderId=${tender.id}" text="tender.create.offer" css="formButton toLeft"/>
		</security:authorize>
	</jstl:if>	
</fieldset>
		

	<jstl:if test="${tender.offer != null && tender.offer.published}" >
		<fieldset>
			<legend><spring:message code="tender.associated.offer" /></legend>
	
				<acme:labelvalue code="offer.state" value="${tender.offer.state}" /> 
					
				<jstl:if test="${tender.offer.presentationDate != null}" >
					<acme:labelvalue code="offer.presentationDate" value="${tenderOfferPresentationDate}" isDatetime="true" />
				</jstl:if>
				<acme:labelvalue code="offer.amount" value="${tender.offer.amount}" isCurrency="true" /> 
				<acme:labelvalue code="offer.commercial" href="actor/display.do?actorId=${tender.offer.commercial.id}"  value="${tender.offer.commercial.name} ${tender.offer.commercial.surname}" />
				<acme:labelvalue code="offer.comision" value="${tender.offer.amount * (benefitsPercentage/100)}" isCurrency="true" /> 
				<jstl:if test="${tender.offer.denialReason != null && tender.offer.denialReason != ''}" >
					<acme:labelvalue code="offer.denialReason" value="${tender.offer.denialReason}" /> 
				</jstl:if>
		</fieldset>
	</jstl:if>



	<br/><br/>


<h5>
	<spring:message code="tender.evaluation.criteria" />
</h5>

<display:table class="displaytag" name="evaluationCriterias" id="row">
	
	<acme:column property="title" title="evaluationCriteria.title" />
	<acme:column property="description" title="evaluationCriteria.description" />
	<acme:column property="maxScore" title="evaluationCriteria.maxScore" />

	<acme:column property="evaluationCriteriaType.name" title="evaluationCriteria.evaluationCriteriaType" />
	
	<display:column>
		<div>
			<a href="evaluationCriteria/display.do?evaluationCriteriaId=${row.id}">
				<spring:message code="evaluationCriteria.display" />
			</a>
		</div>
	</display:column>

</display:table>

<jstl:if test="${tender.administrative.id == actor.id}" >
	<br /><br />
	<acme:button text="tender.evaluationCriteria.create" url="evaluationCriteria/administrative/create.do?tenderId=${tender.id}" css="formButton toLeft" />
</jstl:if>

<br /><br />

<h5>
	<spring:message code="tender.comments" />
</h5>

<display:table class="displaytag" name="comments"  id="row">

	<acme:column property="text" title="comment.text" />
	<acme:column property="writingDate" title="comment.writingDate" format="display.date.time.format"/>

	<spring:message code="comment.commercial" var="commentCommercial" />
	<display:column title="${commentCommercial}">
		<div>
			<a href="actor/display.do?actorId=${row.commercial.id}">
				<jstl:out value="${row.commercial.name} ${row.commercial.surname}" />
			</a> 
		</div>
	</display:column>
	
	<display:column>
		<div>
			<a href="answer/actor/list.do?commentId=${row.id}">
				<spring:message code="comment.answer.list" />
			</a>
		</div>
	</display:column>	
	

</display:table>
<br />

<security:authorize access="hasRole('COMMERCIAL')">
	<br />
	<acme:button text="tender.comment.create" url="comment/create.do?tenderId=${tender.id}" css="formButton toLeft" />
</security:authorize>


