
<%@page import="org.springframework.test.web.ModelAndViewAssert"%>
<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<b>
	<spring:message code="tender.reference" />: ${offer.tender.reference} <br/>
	<spring:message code="tender.title" />: ${offer.tender.title} <br/>
	<spring:message code="offer.state" />:${offer.state} <br/>
</b>
<br/><br/>

<h5>
	<spring:message code="offer.subSections" />
</h5>


