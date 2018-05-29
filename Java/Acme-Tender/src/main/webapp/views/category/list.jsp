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


<spring:message code="category.parentCategory" />: 
	<jstl:if test="${parent != null}" >
		${parent.name} <br/><br/>
	</jstl:if>
	<jstl:if test="${parent == null}" >
		<spring:message code="category.without.father" /> <br/><br/>
	</jstl:if>
	

<display:table pagesize="5" class="displaytag" keepStatus="true" name="categories" requestURI="${requestUri}" id="row">

	<acme:column property="name" title="category.name"/>
	<acme:column property="description" title="category.description"/>

	<display:column>
		<a href="category/administrator/list.do?parentCategoryId=${row.id}">
			<spring:message code="category.childCategories" />
		</a>
	</display:column>

	<spring:message code="category.edit" var="columnHeader" />
	<display:column title="${columnHeader}">
		<a href="category/administrator/edit.do?categoryId=${row.id}">
			<spring:message code="category.edit" />
		</a>
	</display:column>

</display:table>
<br/><br/>


<jstl:if test="${parent != null}" >
	<acme:button url="category/administrator/create.do?parentCategoryId=${parent.id}" text="category.create" css="formButton toLeft"/>
</jstl:if>
<jstl:if test="${parent == null}" >
	<acme:button url="category/administrator/create.do" text="category.create" css="formButton toLeft"/>
</jstl:if>


<jstl:if test="${parent != null && parent.fatherCategory == null}" >
	<acme:button url="category/administrator/list.do" text="category.move.to.up.level" css="formButton toLeft" />
</jstl:if>
<jstl:if test="${parent != null && parent.fatherCategory != null}" >
	<acme:button url="category/administrator/list.do?parentCategoryId=${parent.fatherCategory.id}" text="category.move.to.up.level" css="formButton toLeft" />
</jstl:if>

