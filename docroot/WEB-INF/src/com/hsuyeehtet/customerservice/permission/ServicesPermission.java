package com.hsuyeehtet.customerservice.permission;

import com.hsuyeehtet.customerservice.model.Services;
import com.hsuyeehtet.customerservice.service.ServicesLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

public class ServicesPermission {
	
	public static void check(PermissionChecker permissionChecker, long groupId, String  actionId) throws PortalException, SystemException{
		if(!contains(permissionChecker, groupId, actionId))
			throw new PrincipalException();
	}
	
	public static boolean contains (PermissionChecker permissionChecker, long servicesId, String actionId) throws PortalException, SystemException{
		Services services =ServicesLocalServiceUtil.getServices(servicesId);
		
		return permissionChecker.hasPermission(services.getGroupId(),Services.class.getName(), services.getServicesId() , actionId);
		
	}

}
