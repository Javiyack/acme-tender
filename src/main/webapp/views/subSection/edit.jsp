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

<form:form action="${requestURI}" modelAttribute="subSection" method="post">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="offer" />
	<form:hidden path="commercial" />
	<form:hidden path="administrative" />

	
	<acme:textbox code="subSection.title" path="title" />
	<acme:textbox code="subSection.section" path="section" />
	<acme:textbox code="subSection.order" path="subsectionOrder" />
	<acme:textarea code="subSection.shortDescription" path="shortDescription" />
	<acme:textarea code="subSection.text" path="body" />
	<acme:textarea code="subSection.comments" path="comments" />
	
	<br />

	<acme:submit name="save" code="subSection.save" css="formButton toLeft" />
	
	<jstl:if test="${subSection.commercial.id == actorId && subSection.id != 0}" >
		<acme:submit name="delete" code="subSection.delete" css="formButton toLeft" />
	</jstl:if>
	<jstl:if test="${subSection.administrative.id == actorId && subSection.id != 0}" >
		<acme:submit name="delete" code="subSection.delete" css="formButton toLeft" />
	</jstl:if>
	
    <acme:cancel url="offer/display.do?offerId=${subSection.offer.id}" code="subSection.cancel" css="formButton toLeft" />
	<br />

</form:form>