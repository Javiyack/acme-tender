
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
	<spring:message code="offer.tender.reference" />: ${offer.tender.reference} <br/>
	<spring:message code="offer.tender.title" />: ${offer.tender.title} <br/>
	<spring:message code="offer.state" />: ${offer.state} <br/>
	<jstl:if test="${offer.presentationDate != null}" >
		<spring:message code="offer.presentationDate" />: ${offer.presentationDate} <br/>
	</jstl:if>
	<spring:message code="offer.amount" />: ${offer.amount} <br/>
	<jstl:if test="${offer.denialReason != null}" >
		<spring:message code="offer.denialReason" />: ${offer.denialReason} <br/>
	</jstl:if>
</b>
<br/><br/>

<h5>
	<spring:message code="offer.subSections" />
</h5>

<display:table pagesize="5" class="displaytag" name="subSections" requestURI="offer/display.do?offerId=${offer.id}" id="row">

	<acme:column property="title" title="subSection.title" />
	<acme:column property="section" title="subSection.section" />
	<acme:column property="subsectionOrder" title="subSection.order" />
	<acme:column property="lastReviewDate" title="subSection.lastReviewDate" />

	<display:column>
		<div>
			<a href="subSection/display.do?subSectionId=${row.id}"> 
				<spring:message code="subSection.display" />
			</a>
		</div>
	</display:column>
	
	<display:column>
		<div>
			<a href="subSection/edit.do?subSectionId=${row.id}"> 
				<spring:message code="subSection.edit" />
			</a>
		</div>
	</display:column>
	
	<display:column>
		<div>
			<a href="curriculum/list.do?subSectionId=${row.id}"> 
				<spring:message code="offer.subSection.curriculums" />
			</a>
		</div>
	</display:column>
	
	<display:column>
		<div>
			<a href="file/list.do?id=${row.id}&type=subSection"> 
				<spring:message code="offer.subSection.files" />
			</a>
		</div>
	</display:column>
	
	<display:column>
		<div>
			<a href="subSectionEvaluationCriteria/commercial/list.do?subSectionId=${row.id}"> 
				<spring:message code="offer.subSection.subSectionEvaluationCriterias" />
			</a>
		</div>
	</display:column>
		
</display:table>
<br />

<security:authorize access="hasRole('COMMERCIAL')">
	<acme:button text="subSection.create" url="subSection/commercial/create.do?offerId=${offer.id}" css="formButton toLeft" />
</security:authorize>


