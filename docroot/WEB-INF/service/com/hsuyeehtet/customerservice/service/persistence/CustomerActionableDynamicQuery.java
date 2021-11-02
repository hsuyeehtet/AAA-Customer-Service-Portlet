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

package com.hsuyeehtet.customerservice.service.persistence;

import com.hsuyeehtet.customerservice.model.Customer;
import com.hsuyeehtet.customerservice.service.CustomerLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author hsuyeehtet
 * @generated
 */
public abstract class CustomerActionableDynamicQuery
	extends BaseActionableDynamicQuery {
	public CustomerActionableDynamicQuery() throws SystemException {
		setBaseLocalService(CustomerLocalServiceUtil.getService());
		setClass(Customer.class);

		setClassLoader(com.hsuyeehtet.customerservice.service.ClpSerializer.class.getClassLoader());

		setPrimaryKeyPropertyName("customerId");
	}
}