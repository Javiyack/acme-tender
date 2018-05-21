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


<spring:message code="date.pattern" var="momentFormat" />
<fmt:formatDate value="${curriculum.dateOfBirth}" pattern="${momentFormat}"
	var="momentVar" />

<ul style="list-style-type: disc">

	<li><b><spring:message code="curriculum.name"></spring:message>:</b>
		<jstl:out value="${curriculum.name}" /></li>

	<li><b><spring:message code="curriculum.surname"></spring:message>:</b>
		<jstl:out value="${curriculum.surname}" /></li>

	<li><b><spring:message code="curriculum.identificationNumber"></spring:message>:</b>
		<jstl:out value="${curriculum.identificationNumber}" /></li>


	<li><b><spring:message code="curriculum.phone"></spring:message>:</b>
		<jstl:out value="${curriculum.phone}" /></li>

	<li><b><spring:message code="curriculum.email"></spring:message>:</b>
		<jstl:out value="${curriculum.email}" /></li>

	<li><b><spring:message code="curriculum.dateOfBirth"></spring:message>:</b>
		<jstl:out value="${momentVar}" /></li>

	<li><b><spring:message code="curriculum.minSalaryExpectation"></spring:message>:</b>
		<jstl:out value="${curriculum.minSalaryExpectation}" /></li>
</ul>


<spring:message code="curriculum.text" var="textVar"/>
<jstl:out value="${ textVar}"/>
<div class="dashboard">
	<jstl:out value="${curriculum.text}" />
</div>
<br>


<input type="button" name="back"
	value="<spring:message code="curriculum.back" />"
	onclick="javascript: relativeRedir('curriculum/list.do?subSectionId=${curriculum.subSection.id}')" class ="formButton toLeft" >



