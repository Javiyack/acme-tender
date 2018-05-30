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
	<form:hidden path="uploadDate" />
	<form:hidden path="mimeType" />

	<form:hidden path="curriculum" />
	<form:hidden path="tenderResult" />
	<form:hidden path="subSection" />
	<form:hidden path="tender" />

	<acme:textbox code="file.name" path="name" />
	<br />
	<input type="file" id="data" name="data" class="formInput"/>

	<acme:submit name="save" code="file.save" css="formButton toLeft" />&nbsp;
	
	<jstl:if test="${file.id != 0}" >
		<acme:submit name="delete" code="file.delete" css="formButton toLeft" />
	</jstl:if>

	<jstl:if test="${file.curriculum != null}" >
		<acme:button url="curriculum/display.do?curriculumId=${file.curriculum.id}" text="file.back" css="formButton toLeft" />
	</jstl:if>
	<jstl:if test="${file.subSection != null}" >
		<acme:button url="subSection/display.do?subSectionId=${file.subSection.id}" text="file.back" css="formButton toLeft" />
	</jstl:if>
	<jstl:if test="${file.tender != null}" >
		<acme:button url="tender/display.do?tenderId=${file.tender.id}" text="file.back" css="formButton toLeft" />
	</jstl:if>
	<jstl:if test="${file.tenderResult != null}" >
		<acme:button url="tenderResult/display.do?tenderResultId=${file.tenderResult.id}" text="file.back" css="formButton toLeft" />
	</jstl:if>

	<br />

</form:form>