package com.hsuyeehtet.customerservice;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.hsuyeehtet.customerservice.model.Services;
import com.hsuyeehtet.customerservice.service.ServicesLocalServiceUtil;

/**
 * Portlet implementation class ServicePortlet
 */
public class ServicePortlet extends MVCPortlet {

	public void addServices(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException {
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Services.class.getName(), actionRequest);
	
		String servicesName = ParamUtil.getString(actionRequest, "servicesName");
		String servicesPrice = ParamUtil.getString(actionRequest, "servicesPrice");
		String servicesPlans = ParamUtil.getString(actionRequest, "servicesPlans");
		
		//ServicesId which want to edit (from service.jsp)
		long servicesId = ParamUtil.getLong(actionRequest, "servicesId");
		
		if (servicesId > 0) {
			try {
				ServicesLocalServiceUtil.updateServices(serviceContext.getUserId(), servicesId, servicesName, servicesPrice, servicesPlans, serviceContext);
				System.out.println(servicesId + " " + " is updated successfully");
			} catch (Exception e) {
				e.printStackTrace();
				actionResponse.setRenderParameter("mvcPath", "/html/service/service.jsp");
			}
		} 
		
		else {
			try {
				ServicesLocalServiceUtil.addServices(serviceContext.getUserId(), servicesName, servicesPrice, servicesPlans, serviceContext);
				System.out.println("Services is added successfully");
			} catch (Exception e) {
				e.printStackTrace();
				actionResponse.setRenderParameter("mvcPath", "/html/service/service.jsp");
			
			}	
		}
}

	public void deleteServices(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException {
		long servicesId = ParamUtil.getLong(actionRequest, "servicesId");
		
		try {
			ServicesLocalServiceUtil.deleteServices(servicesId);
			System.out.println(servicesId + " " + " is deleted ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
 


