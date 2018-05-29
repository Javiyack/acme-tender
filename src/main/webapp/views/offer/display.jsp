
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



	<spring:message code="offer.tender.reference" />: <a href="tender/display.do?tenderId=${offer.tender.id}"><jstl:out value="${offer.tender.reference}" /></a> <br/>
	<spring:message code="offer.tender.title" />: <a href="tender/display.do?tenderId=${offer.tender.id}"><jstl:out value="${offer.tender.title}" /></a> <br/>


<jstl:choose>
		<jstl:when test="${offer.state eq 'CREATED' }">
			<spring:message code="offer.state.created"
				var="stateVar" />
		</jstl:when>
		<jstl:when test="${offer.state eq 'IN_DEVELOPMENT' }">
			<spring:message code="offer.state.indevelopment"
				var="stateVar" />
		</jstl:when>
		<jstl:when test="${offer.state eq 'ABANDONED' }">
			<spring:message code="offer.state.abandoned"
				var="stateVar" />
		</jstl:when>
		<jstl:when test="${offer.state eq 'PRESENTED' }">
			<spring:message code="offer.state.presented"
				var="stateVar" />
		</jstl:when>
		<jstl:when test="${offer.state eq 'WINNED' }">
			<spring:message code="offer.state.won"
				var="stateVar" />
		</jstl:when>
		<jstl:when test="${offer.state eq 'LOSED' }">
			<spring:message code="offer.state.lost"
				var="stateVar" />
		</jstl:when>
		<jstl:when test="${offer.state eq 'CHALLENGED' }">
			<spring:message code="offer.state.challenged"
				var="stateVar" />
		</jstl:when>
		<jstl:when test="${offer.state eq 'DENIED' }">
			<spring:message code="offer.state.denied"
				var="stateVar" />
		</jstl:when>
	</jstl:choose>
	<spring:message code="offer.state" />: <jstl:out value="${stateVar}" /> <br/>
	
	<jstl:if test="${offer.presentationDate != null}" >
		<spring:message code="offer.presentationDate" />: <jstl:out value="${offer.presentationDate}"  /> <br/>
	</jstl:if>
	<spring:message code="offer.amount" />: <jstl:out value="${offer.amount}" /> <br/>
	<spring:message code="offer.commercial" />: <a href="actor/display.do?actorId=${offer.commercial.id}"><jstl:out value="${offer.commercial.name} ${offer.commercial.surname}" /></a> <br/>
	<spring:message code="offer.comision" />: <jstl:out value="${offer.amount * (benefitsPercentage/100)}" /> <br/>
	<jstl:if test="${offer.denialReason != null}" >
		<spring:message code="offer.denialReason" />: <jstl:out value="${offer.denialReason}" /> <br/>
	</jstl:if>

<br/>
<jstl:if test="${offer.commercial.id == actor.id}" >
	<acme:button url="offer/commercial/edit.do?offerId=${offer.id}" text="offer.edit" css="formButton toLeft"/>
</jstl:if>

<jstl:if test="${offer.commercial.id == actor.id && offer.inDevelopment}" >
	<acme:button text="offer.createCollaborationRequest" url="collaborationRequest/commercial/create.do?offerId=${offer.id}" css="formButton toLeft" />
	<acme:button text="offer.createAdministrativeRequest" url="administrativeRequest/create.do?offerId=${offer.id}" css="formButton toLeft" />
</jstl:if>

<acme:button url="offer/list.do" text="offer.back" css="formButton toLeft"/>
<br/><br/>

<h5>
	<spring:message code="offer.administrative_acreditation" />
</h5>

<display:table class="displaytag" name="subSectionsAcreditation" id="row">
	
	<acme:column property="title" title="subSection.title" style="width:50%"/>
	<acme:column property="subsectionOrder" title="subSection.order" />
	
	<spring:message code="offer.subSection.property" var="offerSubSectionProperty" />
	<display:column title="${offerSubSectionProperty}">
		<div>
			<jstl:if test="${row.commercial.id != null && row.commercial.id == actor.id}" >
				<spring:message code="offer.subSection.mine" />
			</jstl:if>
			<jstl:if test="${row.administrative.id != null && row.administrative.id == actor.id}" >
				<spring:message code="offer.subSection.mine" />
			</jstl:if>			
			<jstl:if test="${row.commercial.id != null && row.commercial.id != actor.id}" >
				<a href="actor/display.do?actorId=${row.commercial.id}">
					${row.commercial.name} ${row.commercial.surname}
				</a>
			</jstl:if>
			<jstl:if test="${row.administrative.id != null && row.administrative.id != actor.id}" >
				<a href="actor/display.do?actorId=${row.administrative.id}">
					${row.administrative.name} ${row.administrative.surname}
				</a>
			</jstl:if>
		</div>
	</display:column>

	<acme:column property="lastReviewDate" title="subSection.lastReviewDate" format="display.date.time.format"/>

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

	<acme:column property="title" title="subSection.title" style="width:50%"/>
	<acme:column property="subsectionOrder" title="subSection.order" />
	
	<spring:message code="offer.subSection.property" var="offerSubSectionProperty" />
	<display:column title="${offerSubSectionProperty}">
		<div>
			<jstl:if test="${row.commercial.id != null && row.commercial.id == actor.id}" >
				<spring:message code="offer.subSection.mine" />
			</jstl:if>
			<jstl:if test="${row.administrative.id != null && row.administrative.id == actor.id}" >
				<spring:message code="offer.subSection.mine" />
			</jstl:if>			
			<jstl:if test="${row.commercial.id != null && row.commercial.id != actor.id}" >
				<a href="actor/display.do?actorId=${row.commercial.id}">
					${row.commercial.name} ${row.commercial.surname}
				</a>
			</jstl:if>
			<jstl:if test="${row.administrative.id != null && row.administrative.id != actor.id}" >
				<a href="actor/display.do?actorId=${row.administrative.id}">
					${row.administrative.name} ${row.administrative.surname}
				</a>
			</jstl:if>
		</div>
	</display:column>	
	
	<acme:column property="lastReviewDate" title="subSection.lastReviewDate" format="display.date.time.format"/>

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

	<acme:column property="title" title="subSection.title" style="width:50%"/>
	<acme:column property="subsectionOrder" title="subSection.order" />
	
	<spring:message code="offer.subSection.property" var="offerSubSectionProperty" />
	<display:column title="${offerSubSectionProperty}">
		<div>
			<jstl:if test="${row.commercial.id != null && row.commercial.id == actor.id}" >
				<spring:message code="offer.subSection.mine" />
			</jstl:if>
			<jstl:if test="${row.administrative.id != null && row.administrative.id == actor.id}" >
				<spring:message code="offer.subSection.mine" />
			</jstl:if>			
			<jstl:if test="${row.commercial.id != null && row.commercial.id != actor.id}" >
				<a href="actor/display.do?actorId=${row.commercial.id}">
					${row.commercial.name} ${row.commercial.surname}
				</a>
			</jstl:if>
			<jstl:if test="${row.administrative.id != null && row.administrative.id != actor.id}" >
				<a href="actor/display.do?actorId=${row.administrative.id}">
					${row.administrative.name} ${row.administrative.surname}
				</a>
			</jstl:if>
		</div>
	</display:column>	
	
	<acme:column property="lastReviewDate" title="subSection.lastReviewDate" format="display.date.time.format"/>

	<display:column>
		<div>
			<a href="subSection/display.do?subSectionId=${row.id}"> 
				<spring:message code="subSection.display" />
			</a>
		</div>
	</display:column>
</display:table>
<br /><br />

<security:authorize access="hasRole('COMMERCIAL')">
	<jstl:if test="${offer.inDevelopment && offer.commercial.id == actor.id}" >
		<acme:button text="subSection.create" url="subSection/commercial/create.do?offerId=${offer.id}" css="formButton toLeft" />
	</jstl:if>
</security:authorize>


