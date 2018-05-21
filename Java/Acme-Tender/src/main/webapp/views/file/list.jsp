
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table pagesize="5" class="displaytag" name="files" requestURI="file/list.do" id="row">

	<acme:column property="name" title="file.name" />

	<display:column>
		<div>
			<a href="file/edit.do?fileId=${row.id}"> 
				<spring:message code="file.edit" />
			</a>
		</div>
	</display:column>

</display:table>

<a href="file/create.do?id=${parentId}&type=${parentType}"> 
	<spring:message code="file.create" />
</a>