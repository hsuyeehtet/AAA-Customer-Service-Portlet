/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.hsuyeehtet.customerservice.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.hsuyeehtet.customerservice.AddressException;
import com.hsuyeehtet.customerservice.CustomerNameException;
import com.hsuyeehtet.customerservice.model.Customer;
import com.hsuyeehtet.customerservice.service.base.CustomerLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

public class CustomerLocalServiceImpl extends CustomerLocalServiceBaseImpl {
	
	//Finder Method
	public List<Customer> getCustomers (long groupId) throws SystemException {
		return customerPersistence.findByCustomerFinder(groupId);
	}

	//FinderMethod with pagination
	public List<Customer> getCustomers (long groupId, int start, int end) throws SystemException{
		return customerPersistence.findByCustomerFinder(groupId, start, end);
	}
	
	//Validation
	protected void validate (String customerName, String address) throws PortalException{
		if (Validator.isNull(customerName)) 
			throw new CustomerNameException();
		
		if (Validator.isNull(address)) 
			throw new AddressException();
	}
	
		  
	//Add Customer
	public Customer addCustomer(long userId, String customerName, String customerEmail, String customerContact, String address, String state, String township, String payment, int month, int day, int year, int hour, int minute, long servicesId, ServiceContext serviceContext) throws PortalException, SystemException{
		//Scope Column(uuid, groupId, companyId)
		//User Column (userId, userName)
		//Audit Column (createDate, modifiedDate)
		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();
		
		long customerId = counterLocalService.increment();
		Customer customer = customerPersistence.create(customerId);
		
		customer.setUuid(serviceContext.getUuid());
		customer.setGroupId(serviceContext.getScopeGroupId());
		customer.setCompanyId(serviceContext.getCompanyId());
		
		customer.setUserId(user.getUserId());
		customer.setUserName(user.getFullName());
		
		customer.setCreateDate(serviceContext.getCreateDate(now));
		customer.setModifiedDate(serviceContext.getModifiedDate(now));
		
		customer.setCustomerName(customerName);
		customer.setCustomerEmail(customerEmail);
		customer.setCustomerContact(customerContact);
		customer.setAddress(address);
		customer.setState(state);
		customer.setTownship(township);
		customer.setPayment(payment);
		
		//Create calendar object to initialize current time zone
		Calendar dateCal = CalendarFactoryUtil.getCalendar(user.getTimeZone());
		dateCal.set(year, month, day, hour, minute);
				
		//Create date object to get Calender date
		Date calDate = dateCal.getTime();
				
				
		customer.setOrderTime(calDate);
		
		customer.setServicesId(servicesId);
		
		customer.setExpandoBridgeAttributes(serviceContext);
		
		customerPersistence.update(customer);
		
		return customer;
		
	}
	
	//Update Customer
	public Customer updateCustomer (long userId, long customerId, String customerName, String customerEmail, String customerContact, String address, String state, String township, String payment, int month, int day, int year, int hour, int minute, long servicesId, ServiceContext serviceContext) throws PortalException, SystemException{
		Customer customer = customerPersistence.findByPrimaryKey(customerId);
		
		Date now = new Date();
		User user = userPersistence.findByPrimaryKey(userId);
		
		customer.setModifiedDate(serviceContext.getModifiedDate(now));
		
		customer.setCustomerName(customerName);
		customer.setCustomerEmail(customerEmail);
		customer.setCustomerContact(customerContact);
		customer.setAddress(address);
		customer.setState(state);
		customer.setTownship(township);
		customer.setPayment(payment);
		
		//Create calendar object to initialize current time zone
		Calendar dateCal = CalendarFactoryUtil.getCalendar(user.getTimeZone());
		dateCal.set(year, month, day, hour, minute);
				
		//Create date object to get Calender date
		Date calDate = dateCal.getTime();
			
		customer.setOrderTime(calDate);
		
		customer.setServicesId(servicesId);
		
		customer.setExpandoBridgeAttributes(serviceContext);
		
		customerPersistence.update(customer);
		
		return customer;
	}
	
	//delete Customer
	public Customer deleteCustomer (long customerId) throws PortalException, SystemException{
		Customer customer = getCustomer(customerId);
		customer = deleteCustomer(customer);
		return null;
	}	
}