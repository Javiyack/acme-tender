<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
  
 
<div class="brand">
	<img src="${banner}" style="max-width: 300px;" />
</div>


<div class="menu-list">
 
	<ul id="menu-content" class="menu-content collapse out">

               
		<security:authorize access="hasRole('ADMINISTRATIVE')">
				
			<!-- Concursos -->
			<li data-toggle="collapse" data-target="#tenders" class="collapsed">
				<a><i class="fa fa-balance-scale fa-lg"></i><span class="texto-menu"><spring:message code="master.page.tenders" /></span><span class="arrow"></span></a>
			</li>
			<ul class="sub-menu collapse" id="tenders">
				<li><a href="tender/list.do">
					<span class="texto-submenu"><spring:message code="master.page.tenders.list" /></span>
				</a></li>
				<li><a href="tender/search.do"> 
					<span class="texto-submenu"><spring:message code="master.page.tenders.search" /></span>
				</a></li>
				<li><a href="tender/administrative/list.do"> 
					<span class="texto-submenu"><spring:message code="master.page.my.tenders" /></span>
				</a></li>
				<li><a href="tender/administrative/create.do"> 
					<span class="texto-submenu"><spring:message code="master.page.tenders.create" /></span>
				</a></li>		
			</ul>
							
			<!-- Ofertas -->
			<li data-toggle="collapse" data-target="#offers" class="collapsed">				
				<a><i class="fa fa-money fa-lg"></i><span class="texto-menu"><spring:message code="master.page.offers" /></span><span class="arrow"></span></a>
			</li>
			<ul class="sub-menu collapse" id="offers">
				<li><a href="offer/list.do">
					<span class="texto-submenu"><spring:message code="master.page.offers.list" /></span>
				</a></li>
				<li><a href="offer/search.do"> 
					<span class="texto-submenu"><spring:message code="master.page.offers.search" /></span>
				</a></li>
				<li><a href="offer/administrative/listOffersByCollaboration.do"> 
					<span class="texto-submenu"><spring:message code="master.page.my.offers.collaboration" /></span>
				</a></li>				
			</ul>
			
			<!-- Solicitudes administrativas -->
			<li data-toggle="collapse" data-target="#administrativeRequests" class="collapsed">
				<a><i class="fa fa-copy fa-lg"></i><span class="texto-menu"><spring:message code="master.page.administrative.requests" /></span><span class="arrow"></span></a>
			</li>
			<ul class="sub-menu collapse" id="administrativeRequests">				
				<li><a href="administrativeRequest/listReceived.do">
					<span class="texto-submenu"><spring:message code="master.page.administrative.requests.received" /></span>
				</a></li>
			</ul>				
			
			<!-- Tipos de criterios de valoración -->
			<li data-toggle="collapse" data-target="#evaluationCriteriaTypes" class="collapsed">
				<a href="evaluationCriteriaType/administrative/list.do"><i class="fa fa-list fa-lg"></i><span class="texto-menu"><spring:message code="master.page.evaluation.criteria.types" /></span></a>
			</li>
			
			<!-- Mensajeria -->
			<li data-toggle="collapse" data-target="#messages" class="collapsed">
				<a><i class="fa fa-envelope fa-lg"></i><span class="texto-menu"><spring:message code="master.page.messages" /></span><span class="arrow"></span></a>
				</li>
			<ul class="sub-menu collapse" id="messages">								 
				<li><a href="myMessage/create.do">
					<span class="texto-submenu"><spring:message code="master.page.newmessage" /></span>
				</a></li>
				<li><a href="folder/list.do"> 
					<span class="texto-submenu"><spring:message code="master.page.myfolders" /></span>
				</a></li>
			</ul>
			
			<!-- Users y profile -->
			<li data-toggle="collapse" data-target="#users" class="collapsed">
				<a><i class="fa fa-users fa-lg"></i><span class="texto-menu"><spring:message code="master.page.users" /></span><span class="arrow"></span></a>
			</li>
			<ul class="sub-menu collapse" id="users">				
				<li><a href="actor/list.do">
					<span class="texto-submenu"><spring:message code="master.page.actor.list" /></span>
				</a></li>
				<li><a href="actor/edit.do">
					<span class="texto-submenu"><spring:message code="master.page.profile" /></span>
				</a></li>					
			</ul>
		</security:authorize>            
		
		
		<security:authorize access="hasRole('COMMERCIAL')">
				
			<!-- Concursos -->
			<li data-toggle="collapse" data-target="#tenders" class="collapsed">
				<a><i class="fa fa-balance-scale fa-lg"></i><span class="texto-menu"><spring:message code="master.page.tenders" /></span><span class="arrow"></span></a>
			</li>
			<ul class="sub-menu collapse" id="tenders">
				<li><a href="tender/list.do">
					<span class="texto-submenu"><spring:message code="master.page.tenders.list" /></span>
				</a></li>
				<li><a href="tender/search.do"> 
					<span class="texto-submenu"><spring:message code="master.page.tenders.search" /></span>
				</a></li>
			</ul>
							
			<!-- Ofertas -->
			<li data-toggle="collapse" data-target="#offers" class="collapsed">				
				<a><i class="fa fa-money fa-lg"></i><span class="texto-menu"><spring:message code="master.page.offers" /></span><span class="arrow"></span></a>
			</li>
			<ul class="sub-menu collapse" id="offers">
				<li><a href="offer/list.do">
					<span class="texto-submenu"><spring:message code="master.page.offers.list" /></span>
				</a></li>
				<li><a href="offer/search.do"> 
					<span class="texto-submenu"><spring:message code="master.page.offers.search" /></span>
				</a></li>
				<li><a href="offer/commercial/listOffersByPropietary.do"> 
					<span class="texto-submenu"><spring:message code="master.page.my.offers" /></span>
				</a></li>
				<li><a href="offer/commercial/listOffersByCollaboration.do"> 
					<span class="texto-submenu"><spring:message code="master.page.my.offers.collaboration" /></span>
				</a></li>				
			</ul>

			<!-- Solicitudes de colaboración -->
			<li data-toggle="collapse" data-target="#collaborationRequests" class="collapsed">
				<a><i class="fa fa-credit-card fa-lg"></i><span class="texto-menu"><spring:message code="master.page.collaboration.requests" /></span><span class="arrow"></span></a>
			</li>
			<ul class="sub-menu collapse" id="collaborationRequests">				
				<li><a href="collaborationRequest/commercial/listReceived.do">
					<span class="texto-submenu"><spring:message code="master.page.collaboration.requests.received" /></span>
				</a></li>
				<li><a href="collaborationRequest/commercial/listSent.do">
					<span class="texto-submenu"><spring:message code="master.page.collaboration.requests.sended" /></span>
				</a></li>
				
			</ul>				

			<!-- Solicitudes administrativas -->
			<li data-toggle="collapse" data-target="#administrativeRequests" class="collapsed">
				<a><i class="fa fa-copy fa-lg"></i><span class="texto-menu"><spring:message code="master.page.administrative.requests" /></span><span class="arrow"></span></a>
			</li>
			<ul class="sub-menu collapse" id="administrativeRequests">				
				<li><a href="administrativeRequest/listSent.do">
					<span class="texto-submenu"><spring:message code="master.page.administrative.requests.sended" /></span>
				</a></li>
			</ul>				
			
			<!-- Mensajeria -->
			<li data-toggle="collapse" data-target="#messages" class="collapsed">
				<a><i class="fa fa-envelope fa-lg"></i><span class="texto-menu"><spring:message code="master.page.messages" /></span><span class="arrow"></span></a>
				</li>
			<ul class="sub-menu collapse" id="messages">								 
				<li><a href="myMessage/create.do">
					<span class="texto-submenu"><spring:message code="master.page.newmessage" /></span>
				</a></li>
				<li><a href="folder/list.do"> 
					<span class="texto-submenu"><spring:message code="master.page.myfolders" /></span>
				</a></li>
			</ul>
			
			<!-- Users y profile -->
			<li data-toggle="collapse" data-target="#users" class="collapsed">
				<a><i class="fa fa-users fa-lg"></i><span class="texto-menu"><spring:message code="master.page.users" /></span><span class="arrow"></span></a>
			</li>
			<ul class="sub-menu collapse" id="users">				
				<li><a href="actor/list.do">
					<span class="texto-submenu"><spring:message code="master.page.actor.list" /></span>
				</a></li>
				<li><a href="actor/edit.do">
					<span class="texto-submenu"><spring:message code="master.page.profile" /></span>
				</a></li>					
			</ul>
		</security:authorize>    
		
		
		<security:authorize access="hasRole('ADMIN')">
				
			<!-- Concursos -->
			<li data-toggle="collapse" data-target="#tenders" class="collapsed">
				<a><i class="fa fa-balance-scale fa-lg"></i><span class="texto-menu"><spring:message code="master.page.tenders" /></span><span class="arrow"></span></a>
			</li>
			<ul class="sub-menu collapse" id="tenders">
				<li><a href="tender/list.do">
					<span class="texto-submenu"><spring:message code="master.page.tenders.list" /></span>
				</a></li>
				<li><a href="tender/search.do"> 
					<span class="texto-submenu"><spring:message code="master.page.tenders.search" /></span>
				</a></li>
			</ul>
							
			<!-- Ofertas -->
			<li data-toggle="collapse" data-target="#offers" class="collapsed">				
				<a><i class="fa fa-money fa-lg"></i><span class="texto-menu"><spring:message code="master.page.offers" /></span><span class="arrow"></span></a>
			</li>
			<ul class="sub-menu collapse" id="offers">
				<li><a href="offer/list.do">
					<span class="texto-submenu"><spring:message code="master.page.offers.list" /></span>
				</a></li>
				<li><a href="offer/administrator/listNotPublished.do"> 
					<span class="texto-submenu"><spring:message code="master.page.offers.list.notpublished" /></span>
				</a></li>								
				<li><a href="offer/search.do"> 
					<span class="texto-submenu"><spring:message code="master.page.offers.search" /></span>
				</a></li>
			</ul>
			
			<!-- Categorias de concurso -->
			<li data-toggle="collapse" data-target="#categories" class="collapsed">
				<a href="category/administrator/list.do"><i class="fa fa-list fa-lg"></i><span class="texto-menu"><spring:message code="master.page.tender.categories" /></span></a>
			</li>
			
			
			<!-- Palabras tabu -->
			<li data-toggle="collapse" data-target="#taboo" class="collapsed">
				<a><i class="fa fa-ban fa-lg"></i><span class="texto-menu"><spring:message code="master.page.tabooWords" /></span><span class="arrow"></span></a>
			</li>
			<ul class="sub-menu collapse" id="taboo">
				<li><a href="tabooWord/administrator/list.do">
					<span class="texto-submenu"><spring:message code="master.page.tabooWords.list" /></span>
				</a></li>
				<li><a href="tender/administrator/listWithTabooWord.do"> 
					<span class="texto-submenu"><spring:message code="master.page.tabooWords.tenders" /></span>
				</a></li>								
				<li><a href="offer/administrator/listWithTabooWord.do"> 
					<span class="texto-submenu"><spring:message code="master.page.tabooWords.offers" /></span>
				</a></li>
			</ul>	
			
			<!-- Config -->
			<li data-toggle="collapse" data-target="#config" class="collapsed">
				<a href="configuration/administrator/edit.do"><i class="fa fa-dashboard fa-lg"></i><span class="texto-menu"></span><spring:message code="master.page.configuration" /></a>
			</li>					
			
			<!-- Mensajeria -->
			<li data-toggle="collapse" data-target="#messages" class="collapsed">
				<a><i class="fa fa-envelope fa-lg"></i><span class="texto-menu"><spring:message code="master.page.messages" /></span><span class="arrow"></span></a>
				</li>
			<ul class="sub-menu collapse" id="messages">								 
				<li><a href="myMessage/create.do">
					<span class="texto-submenu"><spring:message code="master.page.newmessage" /></span>
				</a></li>
				<li><a href="myMessage/administrator/create.do">
					<span class="texto-submenu"><spring:message code="master.page.broadcast" /></span>
				</a></li>
				<li><a href="folder/list.do"> 
					<span class="texto-submenu"><spring:message code="master.page.myfolders" /></span>
				</a></li>
			</ul>
			
			<!-- Users y profile -->
			<li data-toggle="collapse" data-target="#users" class="collapsed">
				<a><i class="fa fa-users fa-lg"></i><span class="texto-menu"><spring:message code="master.page.users" /></span><span class="arrow"></span></a>
			</li>
			<ul class="sub-menu collapse" id="users">				
				<li><a href="actor/list.do">
					<span class="texto-submenu"><spring:message code="master.page.actor.list" /></span>
				</a></li>
				<li><a href="actor/edit.do">
					<span class="texto-submenu"><spring:message code="master.page.profile" /></span>
				</a></li>					
			</ul>
		</security:authorize>    
		
		
		
		<security:authorize access="hasRole('EXECUTIVE')">
				
			<!-- Concursos -->
			<li data-toggle="collapse" data-target="#tenders" class="collapsed">
				<a><i class="fa fa-balance-scale fa-lg"></i><span class="texto-menu"><spring:message code="master.page.tenders" /></span><span class="arrow"></span></a>
			</li>
			<ul class="sub-menu collapse" id="tenders">
				<li><a href="tender/list.do">
					<span class="texto-submenu"><spring:message code="master.page.tenders.list" /></span>
				</a></li>
				<li><a href="tender/search.do"> 
					<span class="texto-submenu"><spring:message code="master.page.tenders.search" /></span>
				</a></li>
			</ul>
							
			<!-- Ofertas -->
			<li data-toggle="collapse" data-target="#offers" class="collapsed">				
				<a><i class="fa fa-money fa-lg"></i><span class="texto-menu"><spring:message code="master.page.offers" /></span><span class="arrow"></span></a>
			</li>
			<ul class="sub-menu collapse" id="offers">
				<li><a href="offer/list.do">
					<span class="texto-submenu"><spring:message code="master.page.offers.list" /></span>
				</a></li>
				<li><a href="offer/executive/listNotPublished.do"> 
					<span class="texto-submenu"><spring:message code="master.page.offers.list.notpublished" /></span>
				</a></li>								
				<li><a href="offer/search.do"> 
					<span class="texto-submenu"><spring:message code="master.page.offers.search" /></span>
				</a></li>
			</ul>
			
			<!-- Mensajeria -->
			<li data-toggle="collapse" data-target="#messages" class="collapsed">
				<a><i class="fa fa-envelope fa-lg"></i><span class="texto-menu"><spring:message code="master.page.messages" /></span><span class="arrow"></span></a>
				</li>
			<ul class="sub-menu collapse" id="messages">								 
				<li><a href="myMessage/create.do">
					<span class="texto-submenu"><spring:message code="master.page.newmessage" /></span>
				</a></li>
				<li><a href="folder/list.do"> 
					<span class="texto-submenu"><spring:message code="master.page.myfolders" /></span>
				</a></li>
			</ul>
			
			<!-- Users y profile -->
			<li data-toggle="collapse" data-target="#users" class="collapsed">
				<a><i class="fa fa-users fa-lg"></i><span class="texto-menu"><spring:message code="master.page.users" /></span><span class="arrow"></span></a>
			</li>
			<ul class="sub-menu collapse" id="users">				
				<li><a href="actor/list.do">
					<span class="texto-submenu"><spring:message code="master.page.actor.list" /></span>
				</a></li>
				<li><a href="actor/edit.do">
					<span class="texto-submenu"><spring:message code="master.page.profile" /></span>
				</a></li>					
			</ul>
			
			<!-- Dashboard -->
			<li data-toggle="collapse" data-target="#dashboard" class="collapsed">
				<a href="dashboard/executive/display.do"><i class="fa fa-dashboard fa-lg"></i><span class="texto-menu"><spring:message code="master.page.dashboard" /></span></a>
			</li>			
		</security:authorize>    		
           
          
	</ul>
</div>