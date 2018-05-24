<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>
<%@page import="org.apache.commons.lang.time.DateUtils"%>
<%@page import="org.hibernate.engine.spi.RowSelection"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<display:table pagesize="5" class="displaytag" name="offers" requestURI="${requestUri}" id="row">

	<acme:column property="tender.reference" title="offer.tender.reference" />
	<acme:column property="tender.title" title="offer.tender.title" />
	<acme:column property="state" title="subSection.offer.state" />
	<acme:column property="amount" title="offer.amount" />

	<spring:message code="offer.commercial" var="offerCommercial" />
	<display:column title="${offerCommercial}">
		<div>
			<jstl:if test="${row.commercial.id == actorId}" >
				<spring:message code="offer.mine" />
			</jstl:if>
			<jstl:if test="${row.commercial.id != actorId}" >
				<a href="actor/display.do?actorId=${row.commercial.id}">
					${row.commercial.name} ${row.commercial.surname}
				</a>
			</jstl:if>
		</div>
	</display:column>

	<spring:message code="offer.comision" var="varOfferComision"/>
	<display:column title="${varOfferComision}">
		<div>
			${row.amount * (benefitsPercentaje/100)}
		</div>
	</display:column>

	<display:column>
		<div>
			<a href="offer/display.do?offerId=${row.id}">
				<spring:message code="offer.display" />
			</a>
		</div>
	</display:column>


</display:table>

