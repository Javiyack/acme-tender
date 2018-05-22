
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
	<spring:message code="offer.comision" />: ${offer.amount * (benefitsPercentage/100)} <br/>
	<jstl:if test="${offer.denialReason != null}" >
		<spring:message code="offer.denialReason" />: ${offer.denialReason} <br/>
	</jstl:if>
</b>
<br/>
<jstl:if test="${offer.commercial.id == actorId}" >
	<acme:button url="offer/commercial/edit.do?offerId=${offer.id}" text="offer.edit" css="formButton toLeft"/>
</jstl:if>

<acme:button url="offer/list.do" text="offer.back" css="formButton toLeft"/>
<br/><br/>

<h5>
	<spring:message code="offer.administrative_acreditation" />
</h5>

<display:table class="displaytag" name="subSectionsAcreditation" id="row">
	
	<acme:column property="title" title="subSection.title" />
	<acme:column property="subsectionOrder" title="subSection.order" />
	<acme:column property="lastReviewDate" title="subSection.lastReviewDate" />

	<display:column>
		<div>
			<a href="subSection/display.do?subSectionId=${row.id}"> 
				<spring:message code="subSection.display" />
			</a>
		</div>
	</display:column>
		
</display:table>
<br />

<h5>
	<spring:message code="offer.technical_offer" />
</h5>

<display:table class="displaytag" name="subSectionsTechnical"  id="row">

	<acme:column property="title" title="subSection.title" />
	<acme:column property="subsectionOrder" title="subSection.order" />
	<acme:column property="lastReviewDate" title="subSection.lastReviewDate" />

	<display:column>
		<div>
			<a href="subSection/display.do?subSectionId=${row.id}"> 
				<spring:message code="subSection.display" />
			</a>
		</div>
	</display:column>

</display:table>
<br />

<h5>
	<spring:message code="offer.economical_offer" />
</h5>

<display:table class="displaytag" name="subSectionsEconomical" id="row">

	<acme:column property="title" title="subSection.title"  />
	<acme:column property="subsectionOrder" title="subSection.order" />
	<acme:column property="lastReviewDate" title="subSection.lastReviewDate" />

	<display:column>
		<div>
			<a href="subSection/display.do?subSectionId=${row.id}"> 
				<spring:message code="subSection.display" />
			</a>
		</div>
	</display:column>
</display:table>
<br />

<security:authorize access="hasRole('COMMERCIAL')">
	<acme:button text="subSection.create" url="subSection/commercial/create.do?offerId=${offer.id}" css="formButton toLeft" />
</security:authorize>


