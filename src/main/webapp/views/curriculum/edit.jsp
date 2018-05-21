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

<form:form action="curriculum/edit.do"
	modelAttribute="curriculum">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="subSection"/>

	<acme:textbox code="curriculum.name" path="name" />
	<br />
	
	<acme:textbox code="curriculum.surname" path="surname" />
	<br />
	
	<acme:textbox code="curriculum.identificationNumber" path="identificationNumber" />
	<br />
	
	<acme:textbox code="curriculum.phone" path="phone" />
	<br />
	
	<acme:textbox code="curriculum.email" path="email" />
	<br />
	
	<acme:textbox code="curriculum.dateOfBirth" path="dateOfBirth" placeholder="dd/MM/yyyy"/>
	<br />
	
	<acme:textarea code="curriculum.text" path="text" />
	<br />
	
	<acme:textbox code="curriculum.minSalaryExpectation" path="minSalaryExpectation" />
	<br />

	<acme:submit name="save" code="curriculum.save" css="formButton toLeft" />&nbsp;
	
	<jstl:if test="${curriculum.id ne 0 }">
	<acme:submit name="delete" code="curriculum.delete" css="formButton toLeft" />&nbsp;
	</jstl:if>
	
    <acme:cancel url="curriculum/list.do?subSectionId=${curriculum.subSection.id }"
		code="curriculum.cancel" css="formButton toLeft" />
		
	

</form:form>