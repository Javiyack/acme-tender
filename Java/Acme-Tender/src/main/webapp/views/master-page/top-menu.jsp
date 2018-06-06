<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>


<spring:message var="profile" code="profile.editProfile" />
<spring:message var="signup" code="master.page.register" />
<spring:message var="login" code="master.page.login" />
<spring:message var="logout" code="master.page.logout" />
<spring:message var="register" code="master.page.register" />

<div class="center">
	<security:authorize access="isAnonymous()">
		<div class="topMenuElement toLeft">
			<!-- Login y register -->
			<a href="security/login.do" >
				<img src="images/power.ico" title="${login}" class="iconoenlace" />
			</a> <a href="actor/create.do"> <img
				src="images/key.ico" title="${register}" class="iconoenlace" />
			</a>
		</div>
	</security:authorize>

	<security:authorize access="isAuthenticated()">
		<div class="topMenuElement toLeft">

			<button onclick="window.location.replace('j_spring_security_logout');"
				class="menuButton toLeft"> <img src="images/power.ico" title="${logout}" class="iconoenlace" />
			</button>
		</div>
		<div class="topMenuElement toLeft">
			<button onclick="javascript: relativeredir('actor/edit.do');"
				title="${Profile}" class="menuButton toLeft"> <security:authentication property="principal.username" />
				<img src="images/profile.ico" title="${profile}" class="iconoenlace"/>
					 				
			</button>
		</div>
	</security:authorize>
	<div class="topMenuElement toRight">
		<a href="?language=en">
		<img src="images/uk.ico" class="iconoenlace" title="Change to english" /></a> 
		<a href="?language=es">
		<img src="images/spain.ico" class="iconoenlace" title="Cambiar a español" /></a>
	</div>
</div>