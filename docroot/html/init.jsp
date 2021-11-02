<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<portlet:defineObjects></portlet:defineObjects>
<theme:defineObjects></theme:defineObjects>


<%-- Model or Entity --%>
<%@page import="com.hsuyeehtet.customerservice.model.Customer" %>
<%@page import="com.hsuyeehtet.customerservice.model.Services" %>

<%-- Service Layer --%>
<%@page import="com.hsuyeehtet.customerservice.service.impl.ServicesLocalServiceImpl" %>
<%@page import="com.hsuyeehtet.customerservice.service.impl.CustomerLocalServiceImpl" %>
<%@page import="com.hsuyeehtet.customerservice.service.ServicesLocalServiceUtil" %>
<%@page import="com.hsuyeehtet.customerservice.service.CustomerLocalServiceUtil" %>

<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>

<%-- Action UPDATE, DELETE --%>
<%@page import="com.liferay.portal.kernel.dao.search.SearchEntry" %>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@page import="com.hsuyeehtet.customerservice.util.WebKeys" %>

<!-- Action Security -->
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>

<!-- Liferay c.tld -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Permission Helper Classes -->
<%@page import="com.hsuyeehtet.customerservice.permission.ServicesModelPermission" %>
<%@page import="com.hsuyeehtet.customerservice.permission.ServicesPermission" %>

