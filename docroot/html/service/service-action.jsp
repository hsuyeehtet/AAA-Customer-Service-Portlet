<%@page import="com.liferay.portal.security.permission.ActionKeys"%>
<%@ include file="/html/init.jsp" %>

<%  
	//Retrieve current row
	ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	//Get current row to service object
	Services services = (Services)row.getObject();
%>

<!-- Liferay Action Icon Menu -->
<liferay-ui:icon-menu>
	
	<c:if test='<%=ServicesPermission.contains(permissionChecker, services.getServicesId(), ActionKeys.DELETE)  %>'>

	<!-- Delete Action URL -->
	<portlet:actionURL name="deleteServices" var="deleteActionURL">
		<portlet:param name="servicesId" value="<%=String.valueOf(services.getServicesId()) %>"></portlet:param>
	</portlet:actionURL>

	<!-- Delete Action Button -->
	<liferay-ui:icon-delete url="<%= deleteActionURL.toString()%>"></liferay-ui:icon-delete>
	</c:if>
	
	<c:if test='<%=ServicesPermission.contains(permissionChecker, services.getServicesId(), ActionKeys.UPDATE) %>'>
	<!-- Edit Action Button -->
	<portlet:renderURL var="EditRenderURL">
		<portlet:param name="servicesId" value="<%=String.valueOf(services.getServicesId())%>"></portlet:param>
		<portlet:param name="mvcPath" value="/html/service/service.jsp"></portlet:param>
	</portlet:renderURL>
	
	<!-- Edit Action Button -->
	<liferay-ui:icon image="edit" message="Edit" url="<%=EditRenderURL.toString()%>"></liferay-ui:icon>
	</c:if>
	
	<c:if test='<%=ServicesPermission.contains(permissionChecker, services.getServicesId(), ActionKeys.PERMISSIONS) %>'>
	<!-- Permission Security Action URL -->
	<liferay-security:permissionsURL modelResource="<%= Services.class.getName()%>" modelResourceDescription="<%= services.getServicesName()%>" 
		resourcePrimKey="<%= String.valueOf(services.getServicesId())%>" var="servicSecurityURL">
	</liferay-security:permissionsURL>

	<!-- Permission Action Button -->
	<liferay-ui:icon image="permissions" url="<%=servicSecurityURL%>"></liferay-ui:icon>
	</c:if>
	
</liferay-ui:icon-menu>