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
		<div class="dropdown" style="float: left">
			<button class="dropbtn" onclick="relativeRedir('security/login.do')">
				<spring:message code="master.page.login" />
			</button>			
			<div class="dropdown-content">
				<a href="actor/create.do"> 
					<spring:message code="master.page.register" />
				</a> 
			</div>
		</div>
	</security:authorize>
		
<!-- 	<a href="?language=en">en</a> -->
<!-- 	<a href="?language=es">es</a> -->
	<a href="${requestScope['javax.servlet.forward.request_uri']}
		<my:replaceParam name='language' value='en' />">
		en
	</a>

	<a href="${requestScope['javax.servlet.forward.request_uri']}
		<my:replaceParam name='language' value='es' />">
		es
	</a>
	
	<security:authorize access="isAuthenticated()">
		<div class="dropdown" style="float: left">
			<button class="dropbtn">
				<security:authentication property="principal.username" />
			</button>
			<div class="dropdown-content">
				<a href="j_spring_security_logout"> 
					<spring:message code="master.page.logout" />
				</a> 
				<a href="myMessage/create.do">
					<spring:message code="master.page.newmessage" /> 
				</a>
				<security:authorize access="hasRole('ADMIN')">
					<a href="myMessage/administrator/create.do">
						<spring:message code="master.page.broadcast" />
					</a>
				</security:authorize>
				<a href="folder/list.do"> 
					<spring:message code="master.page.myfolders" />
				</a> 
			</div>
		</div>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMINISTRATIVE')">
		<a href="tender/administrative/list.do">
			<spring:message code="master.page.my.tenders" />
		</a>
		
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<a href="tabooWord/administrator/list.do">
			<spring:message code="master.page.tabooWord" />
		</a>
		<a href="configuration/administrator/edit.do">
			<spring:message code="master.page.configuration" />
		</a>
		<a href="category/administrator/list.do">
			<spring:message code="master.page.administrator.categories" />
		</a>
	</security:authorize>
	
	
	<a href="actor/list.do"><spring:message code="master.page.actor.list" /></a>
	<a href="tender/list.do"><spring:message code="master.page.tender.list" /></a>
	<a href="#about">About</a>

</div>