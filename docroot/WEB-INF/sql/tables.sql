create table AAACustomerPortlet_Customer (
	uuid_ VARCHAR(75) null,
	customerId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	customerName VARCHAR(75) null,
	customerEmail VARCHAR(75) null,
	customerContact VARCHAR(75) null,
	address VARCHAR(75) null,
	township VARCHAR(75) null,
	state_ VARCHAR(75) null,
	payment VARCHAR(75) null,
	servicesId LONG,
	orderTime DATE null
);

create table AAACustomerPortlet_Services (
	uuid_ VARCHAR(75) null,
	servicesId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	servicesName VARCHAR(75) null,
	servicesPrice VARCHAR(75) null,
	servicesPlans VARCHAR(75) null
);