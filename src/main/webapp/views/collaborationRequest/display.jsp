<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<spring:message code="date.pattern" var="dateFormat" />
<fmt:formatDate value="${collaborationRequest.maxAcceptanceDate}" pattern="${dateFormat}" var="maxAcceptanceDateVar" />
<fmt:formatDate value="${collaborationRequest.maxDeliveryDate}" pattern="${dateFormat}" var="maxDeliveryDateVar" />

	<spring:message code="collaborationRequest.tender.reference" />: <jstl:out value="${collaborationRequest.offer.tender.reference}"/>  <br/>
	
	<spring:message code="collaborationRequest.tender.title" />: <jstl:out value="${collaborationRequest.offer.tender.title} "/> <br/>
	
	<jstl:choose>
	<jstl:when test="${principal eq collaborationRequest.offer.commercial }">
		<spring:message code="collaborationRequest.receiver" />: <jstl:out value="${collaborationRequest.commercial.name} ${collaborationRequest.commercial.surname} "/> <br/>
	</jstl:when>
	<jstl:when test="${principal eq collaborationRequest.commercial }">
		<spring:message code="collaborationRequest.sender" />: <jstl:out value="${collaborationRequest.offer.commercial.name} ${collaborationRequest.offer.commercial.surname}"/> <br/>
	</jstl:when>
	</jstl:choose>
	
	<spring:message code="collaborationRequest.section" />: <jstl:out value="${collaborationRequest.section} "/> <br/>
	
	<spring:message code="collaborationRequest.subSectionTitle" />: <jstl:out value="${collaborationRequest.subSectionTitle} "/> <br/>
	
	<spring:message code="collaborationRequest.subSectionDescription" />: <div class="dashboard"><jstl:out value="${collaborationRequest.subSectionDescription}"/></div> <br/>
	
	<spring:message code="collaborationRequest.benefitsPercentage" />:<jstl:out value="${collaborationRequest.benefitsPercentage} "/>  <br/>
	
	<spring:message code="collaborationRequest.requirements" />: <div class="dashboard"><jstl:out value="${collaborationRequest.requirements}"/></div> <br/>
	
	<spring:message code="collaborationRequest.numberOfPages" />: <jstl:out value="${collaborationRequest.numberOfPages}"/><br/>
	
	<spring:message code="collaborationRequest.maxAcceptanceDate" />: <jstl:out value="${maxAcceptanceDateVar}"/><br/>
	
	<spring:message code="collaborationRequest.maxDeliveryDate" />: <jstl:out value="${maxDeliveryDateVar}"/><br/>
	
	<jstl:choose>
		<jstl:when test="${collaborationRequest.accepted eq true }">
			<spring:message code="collaborationRequest.state.accepted"
				var="acceptedVar" />
		</jstl:when>
		<jstl:when test="${collaborationRequest.accepted eq false }">
			<spring:message code="collaborationRequest.state.rejected"
				var="acceptedVar" />
		</jstl:when>
		<jstl:otherwise>
			<spring:message code="collaborationRequest.state.withoutReply"
				var="acceptedVar" />
		</jstl:otherwise>
	</jstl:choose> 
	<spring:message code="collaborationRequest.state" />: <jstl:out value="${acceptedVar}"/><br/>
	
	<jstl:if test="${not empty collaborationRequest.rejectedReason }">
			<spring:message code="collaborationRequest.rejectedReason" />: <div class="dashboard"><jstl:out value="${collaborationRequest.rejectedReason}"/></div> <br/>
	</jstl:if>
	<br/>
	
	 <jstl:choose>
	 <jstl:when test="${principal eq collaborationRequest.offer.commercial }">
	  <acme:cancel url="collaborationRequest/commercial/listSent.do" code="collaborationRequest.back" css="formButton toLeft" />
	 </jstl:when>
	 <jstl:when test="${principal eq collaborationRequest.commercial }">
	  <acme:cancel url="collaborationRequest/commercial/listReceived.do" code="collaborationRequest.back" css="formButton toLeft" />
	</jstl:when>
	 </jstl:choose>
	
	
	<jstl:if test="${principal eq collaborationRequest.commercial && collaborationRequest.accepted == null }">
		<acme:button url="collaborationRequest/commercial/reject.do?collaborationRequestId=${collaborationRequest.id }" text="collaborationRequest.reject" css="formButton toLeft"/>
	</jstl:if>
	
	
	
	
	
	

	

<%-- <jstl:if test="${curriculum.subSection.commercial.id == actorId}" >
	<acme:button url="curriculum/edit.do?curriculumId=${curriculum.id}" text="curriculum.edit" css="formButton toLeft" />
</jstl:if>
<jstl:if test="${curriculum.subSection.commercial.id == actorId}" >
	<acme:button url="curriculum/delete.do?curriculumId=${curriculum.id}" text="curriculum.delete" css="formButton toLeft" />
</jstl:if>
<acme:button url="subSection/display.do?subSectionId=${curriculum.subSection.id}" text="curriculum.back" css="formButton toLeft" />
 --%>

