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

import java.util.Date;
import java.util.List;

import com.hsuyeehtet.customerservice.ServicesNameException;
import com.hsuyeehtet.customerservice.ServicesPriceException;
import com.hsuyeehtet.customerservice.model.Services;
import com.hsuyeehtet.customerservice.service.base.ServicesLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

public class ServicesLocalServiceImpl extends ServicesLocalServiceBaseImpl {
	
	//Finder method
	public List<Services> getServicess (long groupId) throws SystemException{
		return servicesPersistence.findByServiceFinder(groupId);
	}
	
	//Finder Method with pagination
	public List<Services> getServicess (long groupId, int start, int end) throws SystemException{
		return servicesPersistence.findByServiceFinder(groupId, start, end);
	}
	
	//Validation method
	protected void validate (String servicesName, String servicesPrice) throws PortalException{
		if (Validator.isNull(servicesName)) 
			throw new ServicesNameException();
			
		if (Validator.isNull(servicesPrice)) 
			throw new ServicesPriceException();
		
	}
	
	//Add Service Method
	public Services addServices (long userId, String servicesName, String servicesPrice, String servicesPlans, ServiceContext serviceContext) throws PortalException, SystemException{
		//Scope Column(uuid, groupId, companyId)
		//User Column (userId, userName)
		//Audit Column (createDate, modifiedDate)
				
		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();
		long servicesId = counterLocalService.increment();
		
		Services services = servicesPersistence.create(servicesId);
		
		services.setUuid(serviceContext.getUuid());
		services.setGroupId(serviceContext.getScopeGroupId());
		services.setCompanyId(serviceContext.getCompanyId());
		
		services.setUserId(user.getUserId());
		services.setUserName(user.getFullName());
		
		services.setCreateDate(serviceContext.getCreateDate());
		services.setModifiedDate(serviceContext.getModifiedDate(now));
		
		services.setServicesName(servicesName);
		services.setServicesPrice(servicesPrice);
		services.setServicesPlans(servicesPlans);
		
		services.setExpandoBridgeAttributes(serviceContext);

		servicesPersistence.update(services);
		resourceLocalService.addResources(user.getCompanyId(), serviceContext.getScopeGroupId(), userId, Services.class.getName(), servicesId, false, true, true);
		
		return services;
		
	}
	
	//Update Service (servicesId which want to update or edit)
	public Services updateServices (long userId, long servicesId, String servicesName, String servicesPrice, String servicesPlans, ServiceContext serviceContext) throws PortalException, SystemException{
		//get current servicesId 
		Services services = servicesPersistence.findByPrimaryKey(servicesId);
		
		//set modified date
		services.setModifiedDate(serviceContext.getModifiedDate());
		
		//allows to edit or update servicesName, servicesPrice, servicesPlans
		services.setServicesName(servicesName);
		services.setServicesPrice(servicesPrice);
		services.setServicesPlans(servicesPlans);

		services.setExpandoBridgeAttributes(serviceContext);
		
		servicesPersistence.update(services);
		
		User user = userPersistence.findByPrimaryKey(userId);
		
		resourceLocalService.updateResources(user.getCompanyId(), serviceContext.getScopeGroupId(), Services.class.getName(), servicesId, serviceContext.getGroupPermissions(), serviceContext.getGuestPermissions());
				
		return services;
		
	}
	
	//delete Service
	public Services deleteServices (long userId, long servicesId, ServiceContext serviceContext) throws PortalException, SystemException{
		
	//Get servicesId which we want to delete
	Services services = getServices(servicesId);
	
	//Call deleteServices method by passing current pizzaId which want to delete
	services = deleteServices(servicesId);
	
	//return current service object
	resourceLocalService.deleteResource(serviceContext.getCompanyId(), Services.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, servicesId);
	
	return services;
		
	}
	
	public Services addServices (long userId, long groupId, String servicesName, String servicesPrice, String servicesPlans, ServiceContext serviceContext)throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}