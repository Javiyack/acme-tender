
<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<script>
	var check = function() {
		document.getElementById('matching').style.display = 'none';
		document.getElementById('notMatching').style.display = 'none';
		
		if (document.getElementById('password').value != "" &&
				document.getElementById('password').value == document.getElementById('confirm_password').value) {
			document.getElementById('matching').style.color = 'green';
		    document.getElementById('matching').style.display = 'inline';
		    document.getElementById("save").disabled = false;
		    document.getElementById("save").className = "formButton toLeft";
		} else {
		    document.getElementById('notMatching').style.color = 'red';
		    document.getElementById('notMatching').style.display = 'inline';
			document.getElementById("save").disabled = true;
			document.getElementById("save").className = "formButton toLeft disabled";
		}
		return result;
	};
</script>

<div class="form">

	<form:form action="${requestUri}" modelAttribute="registerForm">
		<form:hidden path="id" />		
		
		<div class="seccion">
			<acme:textbox code="actor.name" path="name" css="formInput" />
			<br />
			<acme:textbox code="actor.surname" path="surname" css="formInput" />
			<br />
			<acme:textbox code="actor.email" path="email" css="formInput" />
			<br />
			<acme:textbox code="actor.phone" path="phone" css="formInput" />
			<br />
			<acme:textbox code="actor.address" path="address" css="formInput" />
			<br />
		</div>
		
		<div class="seccion">
			<div>
				<form:label path="${path}"><spring:message code="actor.authority.selection"/>
				</form:label>
				
				<form:select id="Authority" path="Authority" css="formInput" class="formInput">
					<form:options items="${permisos}" itemValue="authority" />
				</form:select>
				<form:errors path="${path}" cssClass="error" />
			</div>
			<br>			
			<acme:textbox code="actor.username" path="userName" css="formInput" />
			<br />
			<spring:message code="actor.password"/>
			<form:password path="password" name="password" id="password" class="formInput" /> 
			<form:errors path="password" cssClass="error" />
			<br />
			
			<spring:message code="actor.password.repeat"/>
			<input class="formInput" type="password" id="confirm_password" name="confirm_password" onkeyup="check();">
			<div class="error">
				<span id='matching' style="display:none;">
					<spring:message code="actor.password.matching" />
				</span>
				<span id='notMatching' style="display:none;" >
					<spring:message code="actor.password.missmatching"/>
				</span>
			</div>
		</div>

		<security:authorize access="isAnonymous()">
			<p class="terminos">
				<spring:message code="term.registration" />
			</p>
			<br />
	</security:authorize>

		<input type="submit" name="save" id="save"
			value='<spring:message code="actor.save"/>' class="formButton toLeft disabled" disabled/>&nbsp;
	<input type="button" name="cancel"
			value='<spring:message code="actor.cancel" />'
			onclick="javascript: relativeRedir('/');" class="formButton toLeft"/>
		<br />

	</form:form>	

</div>
