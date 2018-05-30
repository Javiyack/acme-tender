<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<fieldset>
	<legend><spring:message code="curriculum.data" /></legend>
	
	<acme:labelvalue code="curriculum.name" value="${curriculum.name}"/> 
	<acme:labelvalue code="curriculum.surname" value="${curriculum.surname}"/> 
	<acme:labelvalue code="curriculum.identificationNumber"  value="${curriculum.identificationNumber}" />
	<acme:labelvalue code="curriculum.phone"  value="${curriculum.phone}"/> 
	<acme:labelvalue code="curriculum.email"  value="${curriculum.email}" /> 
	<acme:labelvalue code="curriculum.dateOfBirth" value="${curriculum.dateOfBirth}" /> 
	<acme:labelvalue code="curriculum.minSalaryExpectation" value="${curriculum.minSalaryExpectation}" isCurrency="true"/> 
	<acme:labelvalue code="curriculum.text"  value="${curriculum.text}" /> 

	<br/>
	
	<jstl:if test="${curriculum.subSection.offer.inDevelopment && curriculum.subSection.commercial.id == actor.id}" >
		<acme:button url="curriculum/edit.do?curriculumId=${curriculum.id}" text="curriculum.edit" css="formButton toLeft" />
	</jstl:if>
	<acme:button url="subSection/display.do?subSectionId=${curriculum.subSection.id}" text="curriculum.back" css="formButton toLeft" />

</fieldset>

<fieldset>
	<legend><spring:message code="curriculum.data.files" /></legend>
	
	<display:table class="displaytag" name="files" id="row">
		
		<acme:column property="name" title="file.name" />
		<acme:column property="uploadDate" title="file.uploadDate" format="display.date.time.format"/>
		
		<display:column>
			<a href="file/display.do?fileId=${row.id}"> 
				<spring:message code="file.display" />
			</a>
		</display:column>
	</display:table>
	<br />
	<jstl:if test="${curriculum.subSection.offer.inDevelopment && curriculum.subSection.commercial.id == actor.id}" >
		<acme:button text="file.create" url="/file//create.do?id=${curriculum.id}&type=curriculum" css="formButton toLeft" />
	</jstl:if>

</fieldset>

