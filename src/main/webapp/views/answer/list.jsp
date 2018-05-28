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



<b><spring:message code="answer.comment" /></b><br/>

	<spring:message code="comment.text" />: <jstl:out value="${comment.text}"/>  <br/>
	<spring:message code="comment.writingDate" />: <jstl:out value="${comment.writingDate}"/>  <br/>

<br/>
<display:table pagesize="5" class="displaytag" name="answers" requestURI="answer/list.do" id="row">

	<acme:column property="writingDate" title="answer.writingDate" />
	<acme:column property="text" title="answer.text" />

</display:table>
<br /> <br />
<acme:button url="answer/actor/create.do?commentId=${comment.id}" text="comment.answer.create" css="formButton toLeft" />
<acme:cancel url="tender/display.do?tenderId=${tenderId}" code="comment.back" css="formButton toLeft" />
