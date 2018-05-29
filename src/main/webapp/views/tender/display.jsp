
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



	<spring:message code="tender.reference" />: <jstl:out value="${tender.reference}" /> <br/>
	<spring:message code="tender.title" />: <jstl:out value="${tender.title}" /> <br/>
	<spring:message code="tender.expedient" />: <jstl:out value="${tender.expedient}" /> <br/>
	<spring:message code="tender.estimatedAmount" />: <jstl:out value="${tender.estimatedAmount}" /> <br/>
	<spring:message code="tender.organism" />: <jstl:out value="${tender.organism}" /> <br/>
	<spring:message code="tender.bulletin" />: <jstl:out value="${tender.bulletin}" /> <br/>
	<spring:message code="tender.bulletinDate" />: <jstl:out value="${tender.bulletinDate}" /> <br/>
	<spring:message code="tender.openingDate" />: <jstl:out value="${tender.openingDate}" /> <br/>
	<spring:message code="tender.maxPresentationDate" />: <jstl:out value="${tender.maxPresentationDate}" /> <br/>
	<spring:message code="tender.executionTime" />: <jstl:out value="${tender.executionTime}" /> <br/>
	<spring:message code="tender.observations" />: <jstl:out value="${tender.observations}" /> <br/>
	<spring:message code="tender.informationPage" />: <jstl:out value="${tender.informationPage}" /> <br/>
	<spring:message code="tender.interest" />: <jstl:out value="${tender.interest}" /> <br/>
	<spring:message code="tender.interestComment" />: <jstl:out value="${tender.interestComment}" /> <br/><br/>
	
	<jstl:if test="${tender.administrative.id == actor.id}" >
		<acme:button url="tender/administrative/edit.do?tenderId=${tender.id}" text="tender.edit" css="formButton toLeft"/>
	</jstl:if>
	
	<acme:button url="tender/list.do" text="tender.back" css="formButton toLeft"/>
		

	<jstl:if test="${tender.offer != null}" >
		<br /><br />
		<b><spring:message code="tender.associated.offer" />: </b><br/>
		
		<spring:message code="offer.state" />: 
			<a href="offer/display.do?offerId=${tender.offer.id}" >
				<jstl:out value="${tender.offer.state}" /> <br/>
			</a>
			
		<jstl:if test="${tender.offer.presentationDate != null}" >
			<spring:message code="offer.presentationDate" />: <jstl:out value="${tender.offer.presentationDate}" /> <br/>
		</jstl:if>
		<spring:message code="offer.amount" />: <jstl:out value="${tender.offer.amount}" /> <br/>
		<spring:message code="offer.commercial" />: <a href="actor/display.do?actorId=${tender.offer.commercial.id}"><jstl:out value="${tender.offer.commercial.name} ${tender.offer.commercial.surname}" /></a> <br/>
		<spring:message code="offer.comision" />: <jstl:out value="${tender.offer.amount * (benefitsPercentage/100)}" /> <br/>
		<jstl:if test="${tender.offer.denialReason != null}" >
			<spring:message code="offer.denialReason" />: <jstl:out value="${tender.offer.denialReason}" /> <br/>
		</jstl:if>
	</jstl:if>

	<jstl:set var="fechaActual" value="<%=new java.util.Date()%>"/>
	<jstl:if test="${tender.offer == null && tender.maxPresentationDate lt fechaActual}" >
		<security:authorize access="hasRole('COMMERCIAL')"> 
			<acme:button url="offer/commercial/create.do?tenderId=${tender.id}" text="tender.create.offer" css="formButton toLeft"/>
		</security:authorize>
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
	<acme:column property="writingDate" title="comment.writingDate" />

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

<jstl:if test="${tender.administrative.id != actor.id}" >
	<br />
	<acme:button text="tender.comment.create" url="comment/commercial/create.do?tenderId=${tender.id}" css="formButton toLeft" />
</jstl:if>


