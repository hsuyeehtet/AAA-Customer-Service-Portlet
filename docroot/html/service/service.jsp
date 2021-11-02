<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file= "/html/init.jsp"%>

<% 
	//When rendering to the service.jsp, get current servicesId which want to edit
	long servicesId = ParamUtil.getLong(renderRequest, "servicesId");
	//create services object	
	Services services = null;
	//Check servicesId, if 0 it is request to add sservice, or >0 it is requested to edit service
	if(servicesId > 0){
		services = ServicesLocalServiceUtil.getServices(servicesId);
	}
%>

<h2>Service Form</h2>
<h3>Please kindly add new Services</h3>

<portlet:actionURL name="addServices" var="serviceActionURL"></portlet:actionURL>

<portlet:renderURL var="viewRenderURL">
	<portlet:param name="mvcPath" value="/html/service/view.jsp"></portlet:param>
</portlet:renderURL>

<aui:form action="<%= serviceActionURL %>">
 
<!-- Updated service is displaying in the form -->
<aui:model-context bean="<%= services %>" model="<%= Services.class %>">
</aui:model-context>

	<aui:input name="servicesId" type="hidden"></aui:input>
	<aui:input name="servicesName" type="text"></aui:input>
	<aui:input name="servicesPrice" type="text"></aui:input>
	<aui:input name="servicesPlans" type="text"></aui:input>

	<aui:button type="submit" value="SUBMIT"></aui:button>
	<aui:button onClick="<%= viewRenderURL.toString()  %>" type="cancel" value="CANCEL"></aui:button>

</aui:form>