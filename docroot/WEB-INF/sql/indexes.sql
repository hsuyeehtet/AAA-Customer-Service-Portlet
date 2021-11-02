create index IX_373C5882 on AAACustomerPortlet_Customer (groupId);
create index IX_3332238C on AAACustomerPortlet_Customer (uuid_);
create index IX_95CA711C on AAACustomerPortlet_Customer (uuid_, companyId);
create unique index IX_8A348E9E on AAACustomerPortlet_Customer (uuid_, groupId);

create index IX_55217802 on AAACustomerPortlet_Services (groupId);
create index IX_9ED9230C on AAACustomerPortlet_Services (uuid_);
create index IX_52F0C19C on AAACustomerPortlet_Services (uuid_, companyId);
create unique index IX_25B4FF1E on AAACustomerPortlet_Services (uuid_, groupId);