<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>

<!-- Menu and banner usually + "$") -->
<div class="topnav" id="myTopnav">
	
	<security:authorize access="isAnonymous()">
		<!-- Login y register -->
		<a href="security/login.do" style="float: left">
			<spring:message code="master.page.login" />
		</a>		
		<a href="actor/create.do" style="float: left"> 
			<spring:message code="master.page.register" />
		</a> 
	</security:authorize>
		

	
	<security:authorize access="hasRole('ADMINISTRATIVE')">
		<div class="dropdown" style="float: left">
			<button class="dropbtn">
				<security:authentication property="principal.username" />
			</button>
			<div class="dropdown-content">
			
				<!-- Concursos -->
				<a><spring:message code="master.page.tenders" /></a> 
				<a href="tender/list.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.tenders.list" /></i>
				</a>
				<a href="tender/search.do"> 
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.tenders.search" /></i>
				</a>
				<a href="tender/listMyTenders.do"> 
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.my.tenders" /></i>
				</a>				
				<hr />
				
				<!-- Ofertas -->
				<a><spring:message code="master.page.offers" /></a> 
				<a href="offer/list.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.offers.list" /></i>
				</a>
				<a href="offer/search.do"> 
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.offers.search" /></i>
				</a>
				<a href="offer/administrative/listOffersByCollaboration.do"> 
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.my.offers.collaboration" /></i>
				</a>				
				<hr />
				
				<!-- Solicitudes administrativas -->
				<a><spring:message code="master.page.administrative.requests" /></a> 
				<a href="administrativeRequest/administrative/list.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.administrative.requests.received" /></i>
				</a>	
				<hr />				
				
				<!-- Tipos de criterios de valoración -->
				<a href="evaluationCriteriaType/administrative/list.do"><spring:message code="master.page.evaluation.criteria.types" /></a> 
				<hr />				
				
				<!-- Mensajeria -->
				<a><spring:message code="master.page.messages" /></a> 
				<a href="myMessage/create.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.newmessage" /></i>
				</a>
				<a href="folder/list.do"> 
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.myfolders" /></i>
				</a>
				<hr />

				<!-- Users y profile -->
				<a><spring:message code="master.page.users" /></a> 
				<a href="actor/list.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.actor.list" /></i>
				</a>
				<a href="actor/edit.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.profile" /></i>
				</a>				
				<hr />					
				
				<!-- Logout -->
				<a href="j_spring_security_logout"> 
					<spring:message code="master.page.logout" />
				</a> 				 
			</div>
		</div>
	</security:authorize>
	

	<security:authorize access="hasRole('COMMERCIAL')">
		<div class="dropdown" style="float: left">
			<button class="dropbtn">
				<security:authentication property="principal.username" />
			</button>
			<div class="dropdown-content">
			
				<!-- Concursos -->
				<a><spring:message code="master.page.tenders" /></a> 
				<a href="tender/list.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.tenders.list" /></i>
				</a>
				<a href="tender/search.do"> 
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.tenders.search" /></i>
				</a>	
				<hr />
				
				<!-- Ofertas -->
				<a><spring:message code="master.page.offers" /></a> 
				<a href="offer/list.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.offers.list" /></i>
				</a>
				<a href="offer/search.do"> 
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.offers.search" /></i>
				</a>
				<a href="offer/commercial/listOffersByPropietary.do"> 
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.my.offers" /></i>
				</a>				
				<a href="offer/commercial/listOffersByCollaboration.do"> 
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.my.offers.collaboration" /></i>
				</a>				
				<hr />
				
				<!-- Solicitudes de colaboración -->
				<a><spring:message code="master.page.collaboration.requests" /></a> 
				<a href="collaborationRequest/commercial/listReceived.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.collaboration.requests.received" /></i>
				</a>
				<a href="collaborationRequest/commercial/listSended.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.collaboration.requests.sended" /></i>
				</a>	
				<hr />					
				
				<!-- Solicitudes administrativas -->
				<a><spring:message code="master.page.administrative.requests" /></a> 
				<a href="administrativeRequest/commercial/list.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.administrative.requests.sended" /></i>
				</a>	
				<hr />					
				
				<!-- Mensajeria -->
				<a><spring:message code="master.page.messages" /></a> 
				<a href="myMessage/create.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.newmessage" /></i>
				</a>
				<a href="folder/list.do"> 
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.myfolders" /></i>
				</a>
				<hr />
				
				<!-- Users y profile -->
				<a><spring:message code="master.page.users" /></a> 
				<a href="actor/list.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.actor.list" /></i>
				</a>
				<a href="actor/edit.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.profile" /></i>
				</a>				
				<hr />					
				
				<!-- Logout -->
				<a href="j_spring_security_logout"> 
					<spring:message code="master.page.logout" />
				</a> 				 
			</div>
		</div>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<div class="dropdown" style="float: left">
			<button class="dropbtn">
				<security:authentication property="principal.username" />
			</button>
			<div class="dropdown-content">
			
				<!-- Concursos -->
				<a><spring:message code="master.page.tenders" /></a> 
				<a href="tender/list.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.tenders.list" /></i>
				</a>
				<a href="tender/search.do"> 
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.tenders.search" /></i>
				</a>	
				<hr />
				
				<!-- Ofertas -->
				<a><spring:message code="master.page.offers" /></a> 
				<a href="offer/list.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.offers.list" /></i>
				</a>
				<a href="offer/search.do"> 
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.offers.search" /></i>
				</a>			
				<hr />
				
				<!-- Categorías de concurso -->
				<a href="category/administrator/list.do"><spring:message code="master.page.tender.categories" /></a> 		
				<hr />
				
				<!-- Palabras tabu -->
				<a><spring:message code="master.page.tabooWords" /></a> 
				<a href="tabooWord/administrator/list.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.tabooWords.list" /></i>
				</a>
				<a href="tabooWord/administrator/listTenders.do"> 
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.tabooWords.tenders" /></i>
				</a>		
				<a href="tabooWord/administrator/listOffers.do"> 
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.tabooWords.offers" /></i>
				</a>						
				<hr />	
				
				<!-- Configuración -->
				<a href="configuration/administrator/edit.do"><spring:message code="master.page.configuration" /></a> 		
				<hr />							
				
				<!-- Mensajeria -->
				<a><spring:message code="master.page.messages" /></a> 
				<a href="myMessage/create.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.newmessage" /></i>
				</a>
				<a href="myMessage/administrator/create.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.broadcast" /></i>
				</a>				
				<a href="folder/list.do"> 
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.myfolders" /></i>
				</a>
				<hr />
				
				<!-- Users y profile -->
				<a><spring:message code="master.page.users" /></a> 
				<a href="actor/list.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.actor.list" /></i>
				</a>
				<a href="actor/edit.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.profile" /></i>
				</a>				
				<hr />				
				
				<!-- Logout -->
				<a href="j_spring_security_logout"> 
					<spring:message code="master.page.logout" />
				</a> 				 
			</div>
		</div>
	</security:authorize>	
	

	<security:authorize access="hasRole('EXECUTIVE')">
		<div class="dropdown" style="float: left">
			<button class="dropbtn">
				<security:authentication property="principal.username" />
			</button>
			<div class="dropdown-content">
			
				<!-- Concursos -->
				<a><spring:message code="master.page.tenders" /></a> 
				<a href="tender/list.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.tenders.list" /></i>
				</a>
				<a href="tender/search.do"> 
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.tenders.search" /></i>
				</a>	
				<hr />
				
				<!-- Ofertas -->
				<a><spring:message code="master.page.offers" /></a> 
				<a href="offer/list.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.offers.list" /></i>
				</a>
				<a href="offer/listNotPublished.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.offers.list.notpublished" /></i>
				</a>				
				<a href="offer/search.do"> 
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.offers.search" /></i>
				</a>			
				<hr />
				
				<!-- Mensajeria -->
				<a><spring:message code="master.page.messages" /></a> 
				<a href="myMessage/create.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.newmessage" /></i>
				</a>	
				<a href="folder/list.do"> 
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.myfolders" /></i>
				</a>
				<hr />
				
				<!-- Users y profile -->
				<a><spring:message code="master.page.users" /></a> 
				<a href="actor/list.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.actor.list" /></i>
				</a>
				<a href="actor/edit.do">
					&nbsp;&nbsp;&nbsp;<i><spring:message code="master.page.profile" /></i>
				</a>				
				<hr />				
				
				<!-- Logout -->
				<a href="j_spring_security_logout"> 
					<spring:message code="master.page.logout" />
				</a> 				 
			</div>
		</div>
	</security:authorize>	
	
	
	<!-- Lenguaje -->
	<!-- 	<a href="?language=en">en</a> -->
	<!-- 	<a href="?language=es">es</a> -->
	<a href="${requestScope['javax.servlet.forward.request_uri']} <my:replaceParam name='language' value='en' />">
		en
	</a>

	<a href="${requestScope['javax.servlet.forward.request_uri']} <my:replaceParam name='language' value='es' />">
		es
	</a>	
	
	<a href="/">
		<spring:message code="master.page.about" />
	</a>	

</div>