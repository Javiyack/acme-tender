<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
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

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true" name="categories" requestURI="${requestUri}" id="row">

	<spring:message code="category.name" var="nameHeader" />
	<display:column title="${nameHeader}">

		<spring:url
			value="category/administrator/list.do?parentCategoryId=${row.id}"
			var="tenderByCategoryURL" />
		<a href="${tenderByCategoryURL}"><jstl:out value="${row.name}" /></a>

	</display:column>

	<spring:message code="category.parentCategory" var="columnHeader" />
	<display:column property="fatherCategory.name" title="${columnHeader}">
	</display:column>

	<spring:message code="category.edit" var="columnHeader" />
	<display:column title="${columnHeader}">
		<a href="category/administrator/edit.do?categoryId=${row.id}">
			<spring:message code="category.edit" />
		</a>
	</display:column>

</display:table>
<br/><br/>
<acme:button url="category/administrator/create.do" text="category.create" css="formButton toLeft"/>

<jstl:if test="${parent != null && parent.fatherCategory == null}" >
	<acme:button url="category/administrator/list.do" text="msg.back" css="formButton toLeft" />
</jstl:if>
<jstl:if test="${parent != null && parent.fatherCategory != null}" >
	<acme:button url="category/administrator/list.do?parentCategoryId=${parent.fatherCategory.id}" text="msg.back" css="formButton toLeft" />
</jstl:if>

