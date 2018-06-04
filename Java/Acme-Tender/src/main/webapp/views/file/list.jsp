<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<jstl:if test="${modelName == null}" >
		<jstl:set value="files" var="modelName"/>
</jstl:if>

<fieldset>
	<legend><spring:message code="subSection.data.files" /></legend>
	<form:form action="${requestUri}" method="GET">
		<spring:message code="pagination.size" />
		<input hidden="true" name="word" value="${word}">
		<input type="number" name="pageSize" min="1" max="100" value="${pageSize}">
  		<input type="submit" value=">" >
</form:form>
<display:table class="displaytag" name="${modelName}" id="row">
			
		<spring:message code="file.name" var="columTitle" />
		<display:column title="${columTitle}">
			<a href="file/download.do?fileId=${row.id}"> 
				<jstl:out value="${row.name}"/>
			</a>
		</display:column>
		
		<spring:message code="file.size" var="columTitle" />
		<display:column title="${columTitle}">
			<jstl:set var="sizeKB" value="${row.size / (1024)}"></jstl:set>
			<fmt:formatNumber type = 'number' maxFractionDigits = '3' value = '${sizeKB}' />
			KB
		</display:column>
		
		<acme:column property="uploadDate" title="file.uploadDate" format="display.date.time.format"/>
		<acme:column property="mimeType" title="file.type"/>
		<acme:column property="comment" title="subSection.comments" format="display.date.time.format"/>
		
		<display:column >
			<a href="file/delte.do?fileId=${row.id}"> 
				<button><spring:message code="file.delete"/></button>
			</a>
		</display:column>
		
		<display:column>
			<a href="file/edit.do?fileId=${row.id}"> 
				<button><spring:message code="file.edit"/></button>
			</a>
		</display:column>
				

	<jstl:if test="${subSection.offer.inDevelopment && (subSection.commercial.id == actorId || subSection.administrative.id == actorId)}" >
		<acme:button text="file.create" url="/file/create.do?id=${subSection.id}&type=subSection" css="formButton toLeft" />
	</jstl:if>
</display:table>
</fieldset>
	
	

