
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div class="dashboard">

	<h5><spring:message code="dashboard.level"></spring:message> C</h5>
	<br>

	<!-- Dashboard C - 1. Tenders por administrative. -->
	<h3>
		<spring:message code="dashboard.administrative.tenders.administrative" />
	</h3>
	<display:table class="displaytag" name="c1Datos" id="row">
		<spring:message code="dashboard.administrative" var="nameAdministratives" />
		<display:column title="${nameAdministratives}">
			<jstl:out value="${row[1]}" />
		</display:column>
		<spring:message code="dashboard.number.tender" var="numberTender" />
		<display:column title="${numberTender}">
			<jstl:out value="${row[2]}" />
		</display:column>
	</display:table>
	
	
	<!-- Dashboard C - 2. Tenders by interest level. -->
	<h3>
		<spring:message code="dashboard.administrative.tenders.interest" />
	</h3>
	
	<display:table class="displaytag" name="c2Datos" id="row">
		<spring:message code="dashboard.interest.undefined" var="interestUndefined" />
		<display:column title="${interestUndefined}">
			<jstl:out value="${row[0]}" />
		</display:column>
		
		<spring:message code="dashboard.interest.high" var="interestHigh" />
		<display:column title="${interestHigh}">
			<jstl:out value="${row[1]}" />
		</display:column>
		
		<spring:message code="dashboard.interest.medium" var="interestMedium" />
		<display:column title="${interestMedium}">
			<jstl:out value="${row[2]}" />
		</display:column>
		
		<spring:message code="dashboard.interest.low" var="interestLow" />
		<display:column title="${interestLow}">
			<jstl:out value="${row[3]}" />
		</display:column>
		
	</display:table>
	
	<!-- Dashboard C - 3. Offers by state. -->
	<h3>
		<spring:message code="dashboard.administrative.offers.state" />
	</h3>
	
	<display:table class="displaytag" name="c3Datos" id="row">
		<spring:message code="dashboard.state.created" var="stateCreated" />
		<display:column title="${stateCreated}">
			<jstl:out value="${row[0]}" />
		</display:column>
		
		<spring:message code="dashboard.state.inDevelopment" var="stateInDevelopment" />
		<display:column title="${stateInDevelopment}">
			<jstl:out value="${row[1]}" />
		</display:column>
		
		<spring:message code="dashboard.state.abandoned" var="stateAbandoned" />
		<display:column title="${stateAbandoned}">
			<jstl:out value="${row[2]}" />
		</display:column>
		
		<spring:message code="dashboard.state.presented" var="statePresented" />
		<display:column title="${statePresented}">
			<jstl:out value="${row[3]}" />
		</display:column>
		
		<spring:message code="dashboard.state.winned" var="stateWinned" />
		<display:column title="${stateWinned}">
			<jstl:out value="${row[4]}" />
		</display:column>
		
		<spring:message code="dashboard.state.lossed" var="stateLossed" />
		<display:column title="${stateLossed}">
			<jstl:out value="${row[5]}" />
		</display:column>
		
		<spring:message code="dashboard.state.challenged" var="stateChallenged" />
		<display:column title="${stateChallenged}">
			<jstl:out value="${row[6]}" />
		</display:column>
		
		<spring:message code="dashboard.state.denied" var="stateDenied" />
		<display:column title="${stateDenied}">
			<jstl:out value="${row[7]}" />
		</display:column>
		
	</display:table>
	
	
	<!-- Dashboard C - 4. Offers by State and Executive. -->
	<h3>
		<spring:message code="dashboard.administrative.offers.state.executive" />
	</h3>
	
	<display:table class="displaytag" name="c4Datos" id="row">
		
		<spring:message code="dashboard.executive.name" var="executiveName" />
		<display:column title="${executiveName}">
			<jstl:out value="${row[0]}" />
		</display:column>
		
		<spring:message code="dashboard.state.created" var="stateCreated" />
		<display:column title="${stateCreated}">
			<jstl:out value="${row[1]}" />
		</display:column>
		
		<spring:message code="dashboard.state.inDevelopment" var="stateInDevelopment" />
		<display:column title="${stateInDevelopment}">
			<jstl:out value="${row[2]}" />
		</display:column>
		
		<spring:message code="dashboard.state.abandoned" var="stateAbandoned" />
		<display:column title="${stateAbandoned}">
			<jstl:out value="${row[3]}" />
		</display:column>
		
		<spring:message code="dashboard.state.presented" var="statePresented" />
		<display:column title="${statePresented}">
			<jstl:out value="${row[4]}" />
		</display:column>
		
		<spring:message code="dashboard.state.winned" var="stateWinned" />
		<display:column title="${stateWinned}">
			<jstl:out value="${row[5]}" />
		</display:column>
		
		<spring:message code="dashboard.state.lossed" var="stateLossed" />
		<display:column title="${stateLossed}">
			<jstl:out value="${row[6]}" />
		</display:column>
		
		<spring:message code="dashboard.state.challenged" var="stateChallenged" />
		<display:column title="${stateChallenged}">
			<jstl:out value="${row[7]}" />
		</display:column>
		
		<spring:message code="dashboard.state.denied" var="stateDenied" />
		<display:column title="${stateDenied}">
			<jstl:out value="${row[8]}" />
		</display:column>
		
	</display:table>

	<!-- Dashboard C - 5. Offers by state ratio. -->
	<h3>
		<spring:message code="dashboard.administrative.offers.state.ratio" />
	</h3>
	
	<display:table class="displaytag" name="c5Datos" id="row">
		<spring:message code="dashboard.state.created" var="stateCreated" />
		<display:column title="${stateCreated}">
			<jstl:out value="${row[0]}" />
		</display:column>
		
		<spring:message code="dashboard.state.inDevelopment" var="stateInDevelopment" />
		<display:column title="${stateInDevelopment}">
			<jstl:out value="${row[1]}" />
		</display:column>
		
		<spring:message code="dashboard.state.abandoned" var="stateAbandoned" />
		<display:column title="${stateAbandoned}">
			<jstl:out value="${row[2]}" />
		</display:column>
		
		<spring:message code="dashboard.state.presented" var="statePresented" />
		<display:column title="${statePresented}">
			<jstl:out value="${row[3]}" />
		</display:column>
		
		<spring:message code="dashboard.state.winned" var="stateWinned" />
		<display:column title="${stateWinned}">
			<jstl:out value="${row[4]}" />
		</display:column>
		
		<spring:message code="dashboard.state.lossed" var="stateLossed" />
		<display:column title="${stateLossed}">
			<jstl:out value="${row[5]}" />
		</display:column>
		
		<spring:message code="dashboard.state.challenged" var="stateChallenged" />
		<display:column title="${stateChallenged}">
			<jstl:out value="${row[6]}" />
		</display:column>
		
		<spring:message code="dashboard.state.denied" var="stateDenied" />
		<display:column title="${stateDenied}">
			<jstl:out value="${row[7]}" />
		</display:column>
		
	</display:table>
	
	<!-- Dashboard C - 6. Offers by State and Executive Ratio. -->
	<h3>
		<spring:message code="dashboard.administrative.offers.state.executive.ratio" />
	</h3>
	
	<display:table class="displaytag" name="c6Datos" id="row">
		
		<spring:message code="dashboard.executive.name" var="executiveName" />
		<display:column title="${executiveName}">
			<jstl:out value="${row[0]}" />
		</display:column>
		
		<spring:message code="dashboard.state.created" var="stateCreated" />
		<display:column title="${stateCreated}">
			<jstl:out value="${row[1]}" />
		</display:column>
		
		<spring:message code="dashboard.state.inDevelopment" var="stateInDevelopment" />
		<display:column title="${stateInDevelopment}">
			<jstl:out value="${row[2]}" />
		</display:column>
		
		<spring:message code="dashboard.state.abandoned" var="stateAbandoned" />
		<display:column title="${stateAbandoned}">
			<jstl:out value="${row[3]}" />
		</display:column>
		
		<spring:message code="dashboard.state.presented" var="statePresented" />
		<display:column title="${statePresented}">
			<jstl:out value="${row[4]}" />
		</display:column>
		
		<spring:message code="dashboard.state.winned" var="stateWinned" />
		<display:column title="${stateWinned}">
			<jstl:out value="${row[5]}" />
		</display:column>
		
		<spring:message code="dashboard.state.lossed" var="stateLossed" />
		<display:column title="${stateLossed}">
			<jstl:out value="${row[6]}" />
		</display:column>
		
		<spring:message code="dashboard.state.challenged" var="stateChallenged" />
		<display:column title="${stateChallenged}">
			<jstl:out value="${row[7]}" />
		</display:column>
		
		<spring:message code="dashboard.state.denied" var="stateDenied" />
		<display:column title="${stateDenied}">
			<jstl:out value="${row[8]}" />
		</display:column>
		
	</display:table>
	
</div>

