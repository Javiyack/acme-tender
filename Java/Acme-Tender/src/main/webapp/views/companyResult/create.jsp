<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="${requestURI}" modelAttribute="companyResult" method="post">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="tenderResult" />

	<acme:textbox code="companyResult.name" path="name" />
	<br />
	<acme:textbox code="companyResult.economicalOffer" path="economicalOffer" />
	<br />
	<acme:textbox code="companyResult.score" path="score" />
	<br />
	<acme:textbox code="companyResult.position" path="position" />
	<br />
	<acme:textbox code="companyResult.comments" path="comments" />
	<br />
	
<%-- 	<form:select id="state" path="state" css="formInput" class="formInput"> --%>
<%-- 		<form:options items="${states}" itemValue="state" /> --%>
<%-- 	</form:select> --%>
	
	<acme:textarea code="companyResult.state" path="state" placeholder="WINNER | LOSER | RECKLESS_OFFER"/>
	<br />

	<acme:submit name="save" code="companyResult.save" css="formButton toLeft" />&nbsp;
    <acme:cancel url="tenderResult/administrative/display.do?tenderId=${companyResult.tenderResult.tender.id}" code="companyResult.cancel" css="formButton toLeft" />
	<br />

</form:form>