
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


<b>
	<spring:message code="offer.tender.reference" />: <a href="tender/display.do?tenderId=${subSection.offer.tender.id}"><jstl:out value="${subSection.offer.tender.reference}" /></a> <br/>
	<spring:message code="offer.tender.title" />: <a href="tender/display.do?tenderId=${subSection.offer.tender.id}"><jstl:out value="${subSection.offer.tender.title}" /></a> <br/>
	<spring:message code="subSection.offer.state" />: <jstl:out value="${subSection.offer.state}" /> <br/>
</b>
<br/>

<h5>
	<spring:message code="subSection.data" />
</h5>

<spring:message code="subSection.title" />: <jstl:out value="${subSection.title}" /> <br/>
<spring:message code="subSection.section" />: <jstl:out value="${subSection.section}" /> <br/>
<spring:message code="subSection.order" />: <jstl:out value="${subSection.subsectionOrder}" /> <br/>
<spring:message code="subSection.shortDescription" />: <jstl:out value="${subSection.shortDescription}" /> <br/>
<spring:message code="subSection.text" />: <jstl:out value="${subSection.body}" /> <br/>
<spring:message code="subSection.lastReviewDate" />: <jstl:out value="${subSection.lastReviewDate}" /> <br/>
<spring:message code="subSection.comments" />: <jstl:out value="${subSection.comments}" /> <br/>

<br />

<jstl:if test="${subSection.offer.inDevelopment && subSection.commercial.id == actorId}" >
	<acme:button text="subSection.edit" url="/subSection/commercial/edit.do?subSectionId=${subSection.id}" css="formButton toLeft" />
</jstl:if>
<jstl:if test="${subSection.offer.inDevelopment && subSection.administrative.id == actorId}" >
	<acme:button text="subSection.edit" url="/subSection/administrative/edit.do?subSectionId=${subSection.id}" css="formButton toLeft" />
</jstl:if>
<acme:button text="subSection.back" url="/offer/display.do?offerId=${subSection.offer.id}" css="formButton toLeft" />



<jstl:if test="${subSection.section != 'ADMINISTRATIVE_ACREDITATION'}" >
	<br/><br/>
	<h5>
		<spring:message code="subSection.data.curriculums" />
	</h5>
	
	<display:table class="displaytag" name="curriculums" id="row">
		
		<acme:column property="name" title="curriculum.name" />
		<acme:column property="surname" title="curriculum.surname" />
		<acme:column property="dateOfBirth" title="curriculum.dateOfBirth" />
		<acme:column property="minSalaryExpectation" title="curriculum.minSalaryExpectation" />
	
		<display:column>
			<div>
				<a href="curriculum/display.do?curriculumId=${row.id}"> 
					<spring:message code="curriculum.display" />
				</a>
			</div>
		</display:column>
			
	</display:table>
	<br /><br/>
	<jstl:if test="${subSection.offer.inDevelopment && subSection.commercial.id == actorId}" >
		<acme:button text="curriculum.create" url="/curriculum/create.do?subSectionId=${subSection.id}" css="formButton toLeft" />
	</jstl:if>
</jstl:if>



<br/><br/>
<h5>
	<spring:message code="subSection.data.files" />
</h5>

<display:table class="displaytag" name="files" id="row">
	
	<acme:column property="name" title="file.name" />
	<acme:column property="uploadDate" title="file.uploadDate" />
	
	<display:column>
		<div>
			<a href="file/download.do?fileId=${row.id}"> 
				<spring:message code="file.download" />
			</a>
		</div>
	</display:column>
</display:table>
<br /><br/>
<jstl:if test="${subSection.offer.inDevelopment && subSection.commercial.id == actorId}" >
	<acme:button text="file.create" url="/file/commercial/create.do?id=${subSection.id}&type=subSection" css="formButton toLeft" />
</jstl:if>
<jstl:if test="${subSection.offer.inDevelopment && subSection.administrative.id == actorId}" >
	<acme:button text="file.create" url="/file/administrative/create.do?id=${subSection.id}&type=subSection" css="formButton toLeft" />
</jstl:if>





<br/><br/>
<h5>
	<spring:message code="subSection.data.evaluationCriterias" />
</h5>

<display:table class="displaytag" name="subSectionEvaluationCriterias" id="row">
	
	<acme:column property="evaluationCriteria.title" title="evaluationCriteria.title" />
	<acme:column property="evaluationCriteria.maxScore" title="evaluationCriteria.maxScore" />
	<acme:column property="comments" title="subSectionEvaluationCriteria.comments" />
	
	<display:column>
		<div>
			<a href="subSectionEvaluationCriteria/display.do?subSectionEvaluationCriteriaId=${row.id}"> 
				<spring:message code="subSectionEvaluationCriteria.display" />
			</a>
		</div>
	</display:column>	
	
</display:table>
<br /><br/>
<jstl:if test="${subSection.offer.inDevelopment && subSection.commercial.id == actorId && tenderHasEvaluationCriterias}" >
	<acme:button text="subSectionEvaluationCriteria.create" url="/subSectionEvaluationCriteria/commercial/create.do?subSectionId=${subSection.id}" css="formButton toLeft" />
</jstl:if>
<jstl:if test="${subSection.offer.inDevelopment && subSection.commercial.id == actorId && !tenderHasEvaluationCriterias}" >
	<spring:message code="subSection.cannot.add.subSectionEvaluationCriterias" />
</jstl:if>



