<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="file/edit.do" modelAttribute="file">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<acme:textbox code="file.name" path="name" />
	<br />

	<acme:submit name="save" code="file.save" css="formButton toLeft" />&nbsp;
    <acme:cancel url="/file/list.do" code="file.cancel" css="formButton toLeft" />
	<br />

</form:form>