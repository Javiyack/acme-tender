<%--
 * textbox.tag
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ tag language="java" body-content="empty" %>

<%-- Taglibs --%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<%-- Attributes --%>

<%@ attribute name="path" required="true" %>
<%@ attribute name="code" required="true" %>

<%@ attribute name="readonly" required="false" %>
<%@ attribute name="value" required="false" %>
<%@ attribute name="css" required="false" %>

<jstl:if test="${readonly == null}">
    <jstl:set var="readonly" value="false"/>
</jstl:if>

<jstl:if test="${value == null}">
    <jstl:set var="value" value=""/>
</jstl:if>


<%-- Definition --%>

<div>
    <form:label path="${path}">
        <spring:message code="${code}"/>
    </form:label>
    <form:input path="${path}" class="${css}" readonly="${readonly}" value="${value}"/>
    <form:errors path="${path}" cssClass="error"/>
</div>
