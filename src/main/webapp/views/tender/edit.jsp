<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${requestUri}" modelAttribute="tender" method="post">
<!-- pattern="yyyy-MM-dd" -->
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<h5>
		<acme:textbox code="tender.reference" path="reference" css="formInput" />
		<br />
	</h5>
	<acme:textbox code="tender.title" path="title" css="formInput" />
	<br />
	<acme:textbox code="tender.expedient" path="expedient" css="formInput" />
	<br />
	<acme:textbox code="tender.estimatedAmount" path="estimatedAmount" css="formInput" />
	<br />
	<acme:textbox code="tender.organism" path="organism" css="formInput" />
	<br />
	<acme:textbox code="tender.bulletin" path="bulletin" css="formInput" />
	<br />
	<acme:textbox code="tender.bulletinDate" path="bulletinDate" css="formInput" />
	<br />
	<acme:textbox code="tender.openingDate" path="openingDate" css="formInput" />
	<br />
	<acme:textbox code="tender.maxPresentationDate" path="maxPresentationDate" css="formInput" />
	<br />
	<acme:textbox code="tender.executionTime" path="executionTime" css="formInput" />
	<br />
	<acme:textbox code="tender.observations" path="observations" css="formInput" />
	<br />
	<acme:textbox code="tender.informationPage" path="informationPage" css="formInput" />
	<br />
	<acme:textbox code="tender.interest" path="interest" css="formInput" />
	<br />
	<acme:textbox code="tender.interestComment" path="interestComment" css="formInput" />
	<br />
	
	<acme:submit name="save" code="tenderResult.save" css="formButton toLeft" />&nbsp;
    <acme:cancel url="tender/administrative/list.do" code="tenderResult.cancel" css="formButton toLeft" />
	<br />

</form:form>