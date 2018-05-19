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

<display:table pagesize="5" class="displaytag" name="comments" requestURI="comment/list.do" id="row">

	<spring:message code="comment.writingDate" var="commentWritingDate" />
	<acme:column property="writingDate" title="comment.writingDate" />

	<spring:message code="comment.text" var="commentText" />
	<acme:column property="text" title="comment.text" />
	
	<security:authorize access="isAuthenticated()">
	<display:column>
		<div>
			<a href="answer/actor/create.do?commentId=${row.id}">
				<spring:message code="comment.answer.create" />
			</a>
		</div>
	</display:column>
	
	<display:column>
		<div>
			<a href="answer/actor/list.do?commentId=${row.id}">
				<spring:message code="comment.answer.list" />
			</a>
		</div>
	</display:column>
	</security:authorize>

</display:table>

<acme:cancel url="/tender/list.do" code="comment.back" css="formButton toLeft" />
