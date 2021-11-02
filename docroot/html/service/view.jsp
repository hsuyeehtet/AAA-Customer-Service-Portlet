<%@page import="com.liferay.portal.security.permission.ActionKeys"%>
<%@ include file="/html/init.jsp" %>

<h3>VIEW SERVICE</h3>

<portlet:renderURL var="servicesRenderURL">
	<portlet:param name="mvcPath" value="/html/service/service.jsp"></portlet:param>
</portlet:renderURL>

<c:if test='<%=ServicesModelPermission.contains(permissionChecker, scopeGroupId, "ADD_SERVICES")  %>'>
	<aui:button name="addServices" onClick="<%=servicesRenderURL.toString() %>" value="ADD SERVICES"></aui:button>
</c:if>

<liferay-ui:search-container> 
	<liferay-ui:search-container-results results="<%= ServicesLocalServiceUtil.getServicess(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd()) %>">
	</liferay-ui:search-container-results>
	
	<liferay-ui:search-container-row className="Services" modelVar="services">
	
	<liferay-ui:search-container-column-text name="servicesName"></liferay-ui:search-container-column-text>
	<liferay-ui:search-container-column-text name="servicesPrice"></liferay-ui:search-container-column-text>
	<liferay-ui:search-container-column-text name="servicesPlans"></liferay-ui:search-container-column-text>
	
<liferay-ui:search-container-column-jsp align="right" path="/html/service/service-action.jsp"></liferay-ui:search-container-column-jsp>
	
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator></liferay-ui:search-iterator>
</liferay-ui:search-container>