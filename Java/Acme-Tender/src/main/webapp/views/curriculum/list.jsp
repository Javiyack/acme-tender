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

<spring:message code="curriculum.header" var="headerVar"/>
<h1><jstl:out value="${headerVar } ${subSection.title }"/></h1>

<display:table pagesize="10" class="displaytag" name="curriculums"
	requestURI="curriculum/list.do" id="row">
	
	

	<acme:column property="name" title="curriculum.name" sortable="true" />

	<acme:column property="surname" title="curriculum.surname"
		sortable="true" />

	<display:column>

		<a
			href="curriculum/display.do?curriculumId=<jstl:out value="${row.id}"/>"><spring:message
				code="curriculum.show" /></a>

	</display:column>

	<jstl:if
		test="${principal eq subSection.commercial}">
		<display:column>

			<a
				href="curriculum/edit.do?curriculumId=<jstl:out value="${row.id}"/>"><spring:message
					code="curriculum.edit" /></a>

		</display:column>
	</jstl:if>

</display:table>


<jstl:if
	test="${principal eq subSection.commercial }">
	<a
		href="curriculum/create.do?subSectionId=<jstl:out value="${subSection.id}"/>"><spring:message
			code="curriculum.add" /></a>&nbsp;
</jstl:if>