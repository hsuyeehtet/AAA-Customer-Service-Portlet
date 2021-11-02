<%@page import="com.hsuyeehtet.customerservice.service.CustomerLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="/html/init.jsp" %>

<% 
	List<Services> servicess = ServicesLocalServiceUtil.getServicess(scopeGroupId);
	
	Customer customer = null;
	long customerId = ParamUtil.getLong(request, "customerId");
	if(customerId > 0){
		customer = CustomerLocalServiceUtil.getCustomer(customerId);
	}
%>

<h2>Customer Form</h2>
<h3>Please kindly add new customer</h3>

<portlet:renderURL var="viewRenderURL">
	<portlet:param name="mvcPath"  value="/html/customer/view.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL name="addCustomer" var="customerActionURL"></portlet:actionURL>

<aui:form action="<%=customerActionURL %>">
<aui:model-context bean="<%=customer %>" model="<%=Customer.class %>"></aui:model-context>
	<aui:fieldset>
	<aui:input name="customerId" type="hidden"></aui:input>
	<aui:input name="customerName" type="text"></aui:input>
	<aui:input name="customerEmail" type="text"></aui:input>
	<aui:input name="customerContact" type="text"></aui:input>
	<aui:input name="address" type="text"></aui:input>
	<aui:input name="state" type="text"></aui:input>
	<aui:input name="township" type="text"></aui:input>
	<aui:input name="payment" type="text"></aui:input>

	<aui:input name="orderTime"></aui:input>
	
	<aui:select name="servicesId" Lable="Services">
	
	<% 
		for(Services s: servicess){
			
	%>	
		<aui:option selected="<%= customer!=null && s.getServicesId()==customer.getServicesId()%>" value="<%= s.getServicesId() %>">
	
		<%= s.getServicesName() %>
			
		</aui:option>
	<% 
		}
	%>
		
	</aui:select>
	</aui:fieldset>
	<aui:button type="submit" value="SUBMIT"></aui:button>
	<aui:button onClick="<%=viewRenderURL.toString() %>" type="cancel" value="CANCEL"></aui:button>
	
</aui:form>