
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

<div class="seccion">

	<acme:textbox code="actor.name" path="actor.name" css="formInput" />
	<br />

	<acme:textbox code="actor.surname" path="actor.surname" css="formInput" />
	<br />

	<acme:textbox code="actor.email" path="actor.email" css="formInput" />
	<br />

	<acme:textbox code="actor.phone" path="actor.phone" css="formInput" />
	<br />

	<acme:textbox code="actor.address" path="actor.address" css="formInput" />
	<br />
</div>
<br />
	<acme:cancel url="/actor/list.do" code="actor.cancel" css="formButton toLeft" />





