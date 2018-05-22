<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="colaborationRequest/commercial/edit.do" modelAttribute="colaborationRequest">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="Accepted" />
	<form:hidden path="rejectedReason" />
	<form:hidden path="offer" />

	
	<form:label path="section"><spring:message code="colaborationRequest.section"/></form:label>
	<form:select path="section">
	<form:option label="TECHNICAL_OFFER" value="TECHNICAL_OFFER"/>
	<form:option label="ECONOMICAL_OFFER" value="ECONOMICAL_OFFER"/>
	</form:select>
	<br />
	<br />
	
	
	<acme:textbox code="colaborationRequest.subSectionTitle" path="subSectionTitle" />
	<br />
	
	<acme:textarea code="colaborationRequest.subSectionDescription" path="subSectionDescription" />
	<br />
	
	<acme:textbox code="colaborationRequest.benefitsPercentage" path="benefitsPercentage" />
	<br />
	
	<acme:textarea code="colaborationRequest.requirements" path="requirements"/>
	<br />
	
	<acme:textbox code="colaborationRequest.numberOfPages" path="numberOfPages" />
	<br />
	
	<acme:textbox code="colaborationRequest.maxAcceptanceDate" path="maxAcceptanceDate" placeholder="dd/MM/yyyy"/>
	<br />
	
	<acme:textbox code="colaborationRequest.maxDeliveryDate" path="maxDeliveryDate" placeholder="dd/MM/yyyy"/>
	<br />
	
	<acme:select code="colaborationRequest.commercial" itemLabel="email" items="${commercials }" path="commercial"/>
	<br />
	

	<acme:submit name="save" code="colaborationRequest.save" css="formButton toLeft" />&nbsp;
	
    <acme:cancel url="/" code="colaborationRequest.cancel" css="formButton toLeft" />
		
</form:form>