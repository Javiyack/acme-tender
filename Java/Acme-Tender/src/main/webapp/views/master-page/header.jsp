<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>


<!-- Menu and banner usually + "$") -->
<div class="topnav" id="myTopnav">
	<security:authorize access="isAnonymous()">
		<div class="dropdown" style="float: left">
			<button class="dropbtn">
				<i class="fa fa-caret-down"></i>
			</button>
			<a href="security/login.do"><spring:message
					code="master.page.login" /></a>
			<div class="dropdown-content">
				<a href="user/register.do"> <spring:message
						code="master.page.userRegister" />
				</a> <a href="customer/register.do"> <spring:message
						code="master.page.customerRegister" />
				</a> <a href="agent/register.do"> <spring:message
						code="master.page.agentRegister" />
				</a>
			</div>

		</div>
	</security:authorize>

	
	<security:authorize access="isAuthenticated()">
		<div class="dropdown" style="float: left">
			<button class="dropbtn">
				<security:authentication property="principal.username" />
				<i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="j_spring_security_logout"> <spring:message
						code="master.page.logout" />
				</a> <a href="profile/actor/display.do"> <spring:message
						code="user.display" />
				</a>
			</div>

		</div>

	</security:authorize>
<a href="user/list.do"><spring:message code="master.page.users" /></a>


	<security:authorize access="hasRole('USER')">

		<div class="dropdown">
			<button class="dropbtn">
				<spring:message code="master.page.user.follow" />
				<i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="follow/user/list.do"><spring:message
						code="master.page.user.follow" /></a> <a
					href="follow/user/followeds.do"><spring:message
						code="master.page.user.list.followeds" /></a> <a
					href="follow/user/followers.do"><spring:message
						code="master.page.user.list.followers" /></a>
			</div>

		</div>
		<div class="dropdown">
			<button class="dropbtn">
				<spring:message code="master.page.chirp" />
				<i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="chirp/user/create.do"><spring:message
						code="master.page.user.create.chirp" /></a> <a
					href="chirp/user/written.do"><spring:message
						code="master.page.user.list.my.chirp" /></a> <a
					href="chirp/user/timeline.do"><spring:message
						code="master.page.user.timeline.chirp" /></a>


			</div>

		</div>

		<div class="dropdown">
			<button class="dropbtn">
				<spring:message code="volume.volumes" />
				<i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">


				<a href="volume/user/create.do"><spring:message
						code="master.page.user.volume.create" /></a> 
				<a href="volume/user/list.do"><spring:message
						code="master.page.user.volume.list" /></a>


			</div>

		</div>

		<div class="dropdown">
			<button class="dropbtn">
				<spring:message code="master.page.newspaper" />
				<i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">


				<a href="newspaper/user/create.do"><spring:message
						code="master.page.user.newspaper.create" /></a> <a
					href="newspaper/user/list.do"><spring:message
						code="master.page.user.newspaper.list" /></a>


			</div>

		</div>

		<div class="dropdown">
			<button class="dropbtn">
				<spring:message code="master.page.user.article" />
				<i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">

				<a href="article/user/create.do"><spring:message
						code="master.page.user.article.create" /></a> <a
					href="article/user/list.do"><spring:message
						code="master.page.user.article.list" /></a>
			</div>

		</div>

		
	</security:authorize>

	<security:authorize access="hasRole('ADMIN')">
		<div class="dropdown">
			<button class="dropbtn">
				<spring:message code="master.page.tabooWord" />
				<i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="tabooWord/administrator/create.do"> <spring:message
						code="master.page.tabooWord.create" />
				</a> <a href="tabooWord/administrator/list.do"> <spring:message
						code="master.page.tabooWord.list" />
				</a> <a href="tabooWord/administrator/listNewspapers.do"> <spring:message
						code="master.page.tabooWord.listNewspapers" />
				</a> <a href="tabooWord/administrator/listArticles.do"> <spring:message
						code="master.page.tabooWord.listArticles" />
				</a> <a href="tabooWord/administrator/listChirps.do"> <spring:message
						code="master.page.tabooWord.listChirps" />
				</a> <a href="tabooWord/administrator/listAdvertisement.do"> <spring:message
						code="master.page.tabooWord.listAdvertisement" />
				</a>
			</div>
		</div>

		<a href="dashboard/administrator/display.do"><spring:message
				code="dashboard" /></a>
	</security:authorize>

	<a href="article/search.do"> <spring:message
			code="master.page.searchArticles" />
	</a><a href="newspaper/search.do"> <spring:message
			code="master.page.searchNewspapers" />
	</a> <a href="newspaper/list.do"> <spring:message
			code="newspaper.newspapers" />
	</a> <a href="volume/list.do"> <spring:message code="volume.volumes" />
	</a>

	<security:authorize access="hasRole('CUSTOMER')">
		<div class="dropdown">
			<button class="dropbtn">
				<spring:message code="master.page.customer.subscriptions" />
				<i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">


				<a href="newspaper/customer/list.do"><spring:message
						code="master.page.customer.newspaper.privates" /></a> <a
					href="subscription/customer/list.do"><spring:message
						code="master.page.customer.subscription.list" /></a>
				
				<a href="vsubscription/customer/list.do"><spring:message
						code="master.page.customer.vsubscription.list" /></a> 


			</div>

		</div>
	</security:authorize>

	<security:authorize access="hasRole('AGENT')">
		<div class="dropdown">
			<button class="dropbtn">
				<spring:message code="master.page.agent.advertisements" />
				<i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">


				<a href="advertisement/agent/list.do"><spring:message
						code="master.page.agent.advertisements" /></a>
				<a href="newspaper/agent/listAdvertisement.do"><spring:message
						code="master.page.agent.nespaper.list.advertisements" /></a>
				<a href="newspaper/agent/list.do"><spring:message
						code="master.page.agent.nespaper.list" /></a> 

			</div>

		</div>
	</security:authorize>

	<security:authorize access="hasRole('ADMIN')">
		<a href="article/list.do"> <spring:message
				code="newspaper.Articles" />
		</a>
		<a href="newspaper/listAll.do"> <spring:message
				code="newspaper.newspapers" />
		</a>
		<a href="chirp/administrator/list.do"> <spring:message
				code="master.page.tabooWord.listChirps" />
		</a>
	</security:authorize>

	<security:authorize access="isAuthenticated()">
		<div class="dropdown">
			<button class="dropbtn">
				<spring:message code="master.page.messages" />
				<i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="myMessage/create.do"><spring:message
						code="master.page.newmessage" /> </a>
				<security:authorize access="hasRole('ADMIN')">
					<a href="myMessage/administrator/create.do"><spring:message
							code="master.page.broadcast" /></a>
				</security:authorize>
				<a href="folder/list.do"><spring:message
						code="master.page.myfolders" /></a>

			</div>





		</div>

	</security:authorize>

	<a href="#about">About</a> <a href="javascript:void(0);"
		style="font-size: 15px;" class="icon" onclick="dropDown()">&#9776;</a>
	<a style="float: left"
		href="${requestScope['javax.servlet.forward.request_uri']}
		<my:replaceParam name='language' value='en' />">en</a>

	<a style="float: left"
		href="${requestScope['javax.servlet.forward.request_uri']}
		<my:replaceParam name='language' value='es' />">es</a>

</div>
