<%@ include file="/html/init.jsp" %>

<% 
	//Retrieve current row
	ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	//Get current row to service object
	Customer customer = (Customer) row.getObject();
%>

<!-- Delete Action URL -->
<portlet:actionURL name="deleteCustomer" var="deleteCustomerActionURL">
	<portlet:param name="customerId" value="<%=String.valueOf(customer.getCustomerId()) %>"></portlet:param>
</portlet:actionURL>

<portlet:renderURL var="EditCustomerRenderURL">
	<portlet:param name="customerId" value="<%=String.valueOf(customer.getCustomerId())%>"></portlet:param>
	<portlet:param name="mvcPath" value="/html/customer/custumer.jsp"></portlet:param>
</portlet:renderURL>

<!-- Permission Security URL -->
<liferay-security:permissionsURL modelResource="<%=Customer.class.getName() %>" 
modelResourceDescription="<%=customer.getCustomerName() %>"
resourcePrimKey="<%=String.valueOf(customer.getCustomerName()) %>" var="permissionSecurityURL">
</liferay-security:permissionsURL>

<!-- Liferay Action Icon Menu -->
<liferay-ui:icon-menu>
	<!-- Delete Action Button -->
	<liferay-ui:icon-delete url="<%=deleteCustomerActionURL.toString()%>"></liferay-ui:icon-delete>
	
	<!-- Edit Action Button -->
	<liferay-ui:icon image="edit" message="Edit" url="<%=EditCustomerRenderURL.toString()%>"></liferay-ui:icon>
	
	<!-- Permission Action Button -->
	<liferay-ui:icon image="permissions" url="<%=permissionSecurityURL %>"></liferay-ui:icon>
	
</liferay-ui:icon-menu>