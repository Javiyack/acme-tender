<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


	
<acme:labelvalue code="file.name" value="${file.name}"/> 
<acme:labelvalue code="file.uploadDate" value="${file.uploadDate}" isDatetime="true"/> 

<br/>

<jstl:if test="${file.curriculum != null && file.curriculum.subSection.commercial.id == actor.id}" >
	<acme:button url="file/edit.do?fileId=${file.id}" text="file.edit" css="formButton toLeft" />
</jstl:if>
<jstl:if test="${file.subSection != null && file.subSection.commercial.id == actor.id}" >
	<acme:button url="file/edit.do?fileId=${file.id}" text="file.edit" css="formButton toLeft" />
</jstl:if>
<jstl:if test="${file.tender != null && file.tender.administrative.id == actor.id}" >
	<acme:button url="file/edit.do?fileId=${file.id}" text="file.edit" css="formButton toLeft" />
</jstl:if>
<jstl:if test="${file.tenderResult != null && file.tenderResult.tender.administrative.id == actor.id}" >
	<acme:button url="file/edit.do?fileId=${file.id}" text="file.edit" css="formButton toLeft" />
</jstl:if>



<jstl:if test="${file.curriculum != null}" >
	<acme:button url="curriculum/display.do?curriculumId=${file.curriculum.id}" text="file.back" css="formButton toLeft" />
</jstl:if>
<jstl:if test="${file.subSection != null}" >
	<acme:button url="subSection/display.do?subSectionId=${file.subSection.id}" text="file.back" css="formButton toLeft" />
</jstl:if>
<jstl:if test="${file.tender != null}" >
	<acme:button url="tender/display.do?tenderId=${file.tender.id}" text="file.back" css="formButton toLeft" />
</jstl:if>
<jstl:if test="${file.tenderResult != null}" >
	<acme:button url="tenderResult/display.do?tenderResultId=${file.tenderResult.id}" text="file.back" css="formButton toLeft" />
</jstl:if>

&nbsp;


	
		

