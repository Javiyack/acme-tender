<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<spring:message code="date.pattern" var="momentFormat" />
<fmt:formatDate value="${curriculum.dateOfBirth}" pattern="${momentFormat}" var="momentVar" />

	<spring:message code="curriculum.name" />: ${curriculum.name}  <br/>
	<spring:message code="curriculum.surname" />: ${curriculum.surname}  <br/>
	<spring:message code="curriculum.identificationNumber" />: ${curriculum.identificationNumber}  <br/>
	<spring:message code="curriculum.phone" />: ${curriculum.phone}  <br/>
	<spring:message code="curriculum.email" />: ${curriculum.email}  <br/>
	<spring:message code="curriculum.dateOfBirth" />: ${curriculum.dateOfBirth}  <br/>
	<spring:message code="curriculum.minSalaryExpectation" />: ${curriculum.minSalaryExpectation}  <br/>

	<spring:message code="curriculum.text" />: <br/>
		<div class="dashboard">
			${curriculum.text}
		</div>
<br>

<jstl:if test="${curriculum.subSection.commercial.id == actorId}" >
	<acme:button url="curriculum/edit.do?curriculumId=${curriculum.id}" text="curriculum.edit" css="formButton toLeft" />
</jstl:if>
<jstl:if test="${curriculum.subSection.commercial.id == actorId}" >
	<acme:button url="curriculum/delete.do?curriculumId=${curriculum.id}" text="curriculum.delete" css="formButton toLeft" />
</jstl:if>
<acme:button url="subSection/display.do?subSectionId=${curriculum.subSection.id}" text="curriculum.back" css="formButton toLeft" />


