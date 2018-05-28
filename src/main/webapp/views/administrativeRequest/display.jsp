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
<fmt:formatDate value="${administrativeRequest.maxAcceptanceDate}" pattern="${dateFormat}" var="maxAcceptanceDateVar" />
<fmt:formatDate value="${administrativeRequest.maxDeliveryDate}" pattern="${dateFormat}" var="maxDeliveryDateVar" />

	<spring:message code="administrativeRequest.tender.reference" />: <jstl:out value="${administrativeRequest.offer.tender.reference}"/>  <br/>
	
	<spring:message code="administrativeRequest.tender.title" />: <jstl:out value="${administrativeRequest.offer.tender.title} "/> <br/>
	
	<jstl:choose>
	<jstl:when test="${principal eq administrativeRequest.offer.commercial }">
		<spring:message code="administrativeRequest.receiver" />: <jstl:out value="${administrativeRequest.administrative.name} ${administrativeRequest.administrative.surname} "/> <br/>
	</jstl:when>
	<jstl:when test="${principal eq administrativeRequest.administrative }">
		<spring:message code="administrativeRequest.sender" />: <jstl:out value="${administrativeRequest.offer.commercial.name} ${administrativeRequest.offer.commercial.surname}"/> <br/>
	</jstl:when>
	</jstl:choose>
	
	
	<spring:message code="administrativeRequest.subSectionTitle" />: <jstl:out value="${administrativeRequest.subSectionTitle} "/> <br/>
	
	<spring:message code="administrativeRequest.subSectionDescription" />: <div class="dashboard"><jstl:out value="${administrativeRequest.subSectionDescription}"/></div> <br/>
	
	<spring:message code="administrativeRequest.maxAcceptanceDate" />: <jstl:out value="${maxAcceptanceDateVar}"/><br/>
	
	<spring:message code="administrativeRequest.maxDeliveryDate" />: <jstl:out value="${maxDeliveryDateVar}"/><br/>
	
	<jstl:choose>
		<jstl:when test="${administrativeRequest.accepted eq true }">
			<spring:message code="administrativeRequest.state.accepted"
				var="acceptedVar" />
		</jstl:when>
		<jstl:when test="${administrativeRequest.accepted eq false }">
			<spring:message code="administrativeRequest.state.rejected"
				var="acceptedVar" />
		</jstl:when>
		<jstl:otherwise>
			<spring:message code="administrativeRequest.state.withoutReply"
				var="acceptedVar" />
		</jstl:otherwise>
	</jstl:choose> 
	<spring:message code="administrativeRequest.state" />: <jstl:out value="${acceptedVar}"/><br/>
	
	<jstl:if test="${not empty administrativeRequest.rejectedReason }">
			<spring:message code="administrativeRequest.rejectedReason" />: <div class="dashboard"><jstl:out value="${administrativeRequest.rejectedReason}"/></div> <br/>
	</jstl:if>
	<br/>
	
	 <jstl:choose>
	 <jstl:when test="${principal eq administrativeRequest.offer.commercial }">
	  <acme:cancel url="administrativeRequest/listSent.do" code="administrativeRequest.back" css="formButton toLeft" />
	 </jstl:when>
	 <jstl:when test="${principal eq administrativeRequest.administrative }">
	  <acme:cancel url="administrativeRequest/listReceived.do" code="administrativeRequest.back" css="formButton toLeft" />
	</jstl:when>
	 </jstl:choose>
	
	
	<jstl:if test="${principal eq administrativeRequest.administrative && administrativeRequest.accepted == null }">
		<acme:button url="administrativeRequest/reject.do?requestId=${administrativeRequest.id }" text="administrativeRequest.reject" css="formButton toLeft"/>
		<acme:button url="administrativeRequest/accept.do?requestId=${administrativeRequest.id }" text="administrativeRequest.accept" css="formButton toLeft"/>
	</jstl:if>

