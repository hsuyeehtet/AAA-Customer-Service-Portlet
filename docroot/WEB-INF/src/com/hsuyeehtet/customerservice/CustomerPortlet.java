package com.hsuyeehtet.customerservice;

import com.hsuyeehtet.customerservice.model.Customer;
import com.hsuyeehtet.customerservice.service.CustomerLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.util.Calendar;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * Portlet implementation class CustomerPortlet
 */
public class CustomerPortlet extends MVCPortlet {

	public void addCustomer(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Customer.class.getName(), actionRequest);
	
		String customerName = ParamUtil.getString(actionRequest, "customerName");
		String customerEmail = ParamUtil.getString(actionRequest, "customerEmail");
		String customerContact = ParamUtil.getString(actionRequest, "customerContact");
		String address = ParamUtil.getString(actionRequest, "address");
		String state = ParamUtil.getString(actionRequest, "state");
		String township = ParamUtil.getString(actionRequest, "township");
		String payment = ParamUtil.getString(actionRequest, "payment");
		long customerId = ParamUtil.getLong(actionRequest, "customerId");
		long servicesId = ParamUtil.getLong(actionRequest, "servicesId");
		
		int year = ParamUtil.getInteger(actionRequest, "orderTimeYear");
		int month = ParamUtil.getInteger(actionRequest, "orderTimeMonth");
		int day = ParamUtil.getInteger(actionRequest, "orderTimeDay");
		int hour = ParamUtil.getInteger(actionRequest, "orderTimeHour");
		int minute = ParamUtil.getInteger(actionRequest, "orderTimeMinute");
		int amPm = ParamUtil.getInteger(actionRequest, "orderTimeAmPm");
		
		if(amPm == Calendar.PM){
			hour +=12;
		}
		
		if (customerId > 0) {
			try {
				CustomerLocalServiceUtil.updateCustomer(serviceContext.getUserId(), customerId, customerName, customerEmail, customerContact, address, state, township, payment, month, day, year, hour, minute, servicesId, serviceContext);
				System.out.println( customerId+ " " + "is updated Successfully ");
			} catch (Exception e) {
				e.printStackTrace();
				actionResponse.setRenderParameter("mvcPath", "/html/customer/customer.jsp");
			}
		}
		
		else {
			try {
				CustomerLocalServiceUtil.addCustomer(serviceContext.getUserId(), customerName, customerEmail, customerContact, address, state, township, payment, month, day, year, hour, minute, servicesId, serviceContext);
				System.out.println("Customer is added to the database");
			} catch (Exception e) {
				e.printStackTrace();
				actionResponse.setRenderParameter("mvcPath", "/html/customer/customer.jsp");
			}
		}
	}

	public void deleteCustomer(ActionRequest actionRequest, ActionResponse actionResponse) throws PortalException, SystemException {
	long customerId = ParamUtil.getLong(actionRequest, "customerId");
	
	try {
		CustomerLocalServiceUtil.deleteCustomer(customerId);
		System.out.println(customerId + " " + " is deleted successfully");
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
}
