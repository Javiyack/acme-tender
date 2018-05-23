<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="collaborationRequest/commercial/edit.do" modelAttribute="collaborationRequest">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="Accepted" />
	<form:hidden path="rejectedReason" />
	<form:hidden path="offer" />

	
	<form:label path="section"><spring:message code="collaborationRequest.section"/></form:label>
	<form:select path="section">
	<form:option label="TECHNICAL_OFFER" value="TECHNICAL_OFFER"/>
	<form:option label="ECONOMICAL_OFFER" value="ECONOMICAL_OFFER"/>
	</form:select>
	<br />
	<br />
	
	
	<acme:textbox code="collaborationRequest.subSectionTitle" path="subSectionTitle" />
	<br />
	
	<acme:textarea code="collaborationRequest.subSectionDescription" path="subSectionDescription" />
	<br />
	
	<acme:textbox code="collaborationRequest.benefitsPercentage" path="benefitsPercentage" />
	<br />
	
	<acme:textarea code="collaborationRequest.requirements" path="requirements"/>
	<br />
	
	<acme:textbox code="collaborationRequest.numberOfPages" path="numberOfPages" />
	<br />
	
	<acme:textbox code="collaborationRequest.maxAcceptanceDate" path="maxAcceptanceDate" placeholder="dd/MM/yyyy"/>
	<br />
	
	<acme:textbox code="collaborationRequest.maxDeliveryDate" path="maxDeliveryDate" placeholder="dd/MM/yyyy"/>
	<br />
	
	<acme:select code="collaborationRequest.commercial" itemLabel="email" items="${commercials }" path="commercial"/>
	<br />
	

	<acme:submit name="save" code="collaborationRequest.save" css="formButton toLeft" />&nbsp;
	
    <acme:cancel url="/" code="collaborationRequest.cancel" css="formButton toLeft" />
		
</form:form>