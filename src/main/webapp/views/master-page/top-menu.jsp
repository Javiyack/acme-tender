<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>


<div class="center">
	<security:authorize access="isAnonymous()">
		<div style="float: left; margin-left: 30px; vertical-align: middle; height: 100%">
			<!-- Login y register -->
			<a href="security/login.do" data-toggle="tooltip" title="Login" style="float: left; padding-right:10px;" >
				<i class="fa fa-sign-in fa-2x"></i>
			</a>
			<a href="actor/create.do" data-toggle="tooltip" title="Register" style="float: left; padding-right:10px;" > 
				<i class="fa fa-user-plus fa-2x"></i>
			</a> 
		</div>
	</security:authorize>
	
	<security:authorize access="isAuthenticated()">
		<div style="float: left; margin-left: 30px; vertical-align: middle; height: 100%">

			<a href="j_spring_security_logout" data-toggle="tooltip" title="Logout" style="float: left; padding-right:10px;"> 
				<i class="fa fa-sign-out fa-2x"></i>
			</a>
		</div>
	</security:authorize>	

	<div style="float: right; margin-right: 50px; vertical-align: middle; height: 100%">
		<security:authorize access="isAuthenticated()">
			<a href="actor/edit.do" data-toggle="tooltip" title="Profile">
				<i class="fa fa-user fa-2x"></i></a>
				<span class="nombre-usuario"><security:authentication property="principal.username" />
			</span>
			
		</security:authorize>	
		<a href="?language=es" ><img src="images/es.png" height="30px"/></a>
		<a href="?language=en" ><img src="images/en.png" height="30px"/></a> 			
	</div>
</div>