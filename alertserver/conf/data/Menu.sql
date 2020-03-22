

delete from public.menu_item;

INSERT INTO public.menu_item (id,int_status,changed_by,changed_on,created_by,created_on,accesslevel,activity_id,"external","label",main,menu_id,parent_id,preferred,report_id,screen_control_id,seq,icon_path,menu_cls,submenu_icon_path,access_level,external_access,job_role_id) VALUES 
(1,0,NULL,'2020-03-03 17:17:37.493',NULL,NULL,'','policyengine.responsecode',NULL,'Manage Response',false,'responsecode','policyengine',false,NULL,NULL,1,NULL,NULL,NULL,'CREATE',NULL,NULL)
,(2,0,NULL,'2020-03-03 17:12:52.727',NULL,NULL,'','policyengine.policy',NULL,'Manage Policy',false,'policy','policyengine',false,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL)
,(3,0,NULL,'2020-03-03 17:17:37.493',NULL,NULL,NULL,'policyengine.risk',NULL,'Manage Risk',NULL,'managerisk','riskmanagement',NULL,NULL,NULL,1,NULL,NULL,NULL,'CREATE',NULL,NULL)
,(4,0,NULL,'2020-03-03 17:17:37.494',NULL,NULL,'','policyengine.ruleset',NULL,'Manage RuleSet',false,'ruleset','policyengine',false,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL)
,(5,0,NULL,'2020-03-03 17:17:37.494',NULL,NULL,'','policyengine.rule',NULL,'Manage Rule',false,'rule','policyengine',false,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL)
,(6,0,NULL,'2020-03-03 17:17:36.806',NULL,'2020-03-03 17:17:36.806',NULL,'policyengine.mitigationcontrol',NULL,'Manage Mitigation',NULL,'managemitigation','riskmanagement',NULL,NULL,NULL,1,NULL,NULL,NULL,'CREATE',NULL,NULL)
,(7,NULL,NULL,NULL,NULL,NULL,'','setting.list',NULL,'Manage Master Data',false,'list','setting',false,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL)
,(8,NULL,NULL,NULL,NULL,NULL,'','setting.classdef',NULL,'Manage Rule Entity',false,'classdef','setting',false,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL)
,(9,0,NULL,'2020-03-03 17:51:38.188',NULL,'2020-03-03 17:51:38.188',NULL,'setting.role',NULL,'Manage Role',NULL,'managerole','admin',NULL,NULL,NULL,2,NULL,NULL,NULL,'CREATE',NULL,NULL)
,(10,0,NULL,NULL,NULL,NULL,NULL,'setting.user',false,'Manage User',false,'manageuser','admin',false,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL)
;
INSERT INTO public.menu_item (id,int_status,changed_by,changed_on,created_by,created_on,accesslevel,activity_id,"external","label",main,menu_id,parent_id,preferred,report_id,screen_control_id,seq,icon_path,menu_cls,submenu_icon_path,access_level,external_access,job_role_id) VALUES 
(11,0,NULL,'2020-03-03 17:17:37.492',NULL,'2020-03-03 17:08:47.927',NULL,NULL,NULL,'Risk Management',true,'riskmanagement',NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,'CREATE',NULL,1)
,(12,0,NULL,'2020-03-03 17:17:37.492',NULL,NULL,NULL,NULL,NULL,'Setting',true,'setting',NULL,NULL,NULL,NULL,4,'/resources/shared/images/menu01.jpg',NULL,NULL,NULL,NULL,1)
,(13,0,NULL,'2020-03-03 17:17:37.492',NULL,NULL,'',NULL,NULL,'Policy Engine',true,'policyengine',NULL,false,NULL,NULL,1,'/resources/shared/images/menu01.jpg',NULL,NULL,'CREATE',NULL,1)
,(14,0,NULL,'2020-03-03 17:51:38.117',NULL,'2020-03-03 17:51:38.117',NULL,NULL,NULL,'Admin',true,'admin',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,'CREATE',NULL,1)
;
-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- VALUES (1, 'inbox', true, null, 1, 'Inbox', true, '', 'user.inbox', null, null, null);


-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- VALUES (6,  'RequestAccess', true, null, 6, ' Request Access',false, null, null, null, '/resources/shared/images/menu02.jpg',null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- Values (7, 'PhysicalAccess', false, 'RequestAccess',6, 'Physical Access', false, null, null, null, null,null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (8, 'RequestaNewBadge', false, 'PhysicalAccess', 7, 'Request a New Badge', false, '', null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (9, 'UploadOrUpdatePhoto', false, 'PhysicalAccess', 7, 'Upload or Update Photo', false, '', null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (10, 'PrintBadge', false, 'PhysicalAccess', 7, 'Print Badge', false, '', null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (11, 'ActivateBadge', false, 'PhysicalAccess', 7, 'Activate Badge', false, '', null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (12, 'RequestforReplacementBadge', false, 'PhysicalAccess', 7, 'Request for Replacement Badge' ,false, '', null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (13, 'DeactivateBadge', false, 'PhysicalAccess', 7, 'Deactivate Badge', false, '', null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (14, 'ModifyBadgeValidityDates', false, 'PhysicalAccess', 7, 'Modify Badge Validity Dates', false,'', null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (15, 'RequestBuildingOrSiteAccess', false, 'PhysicalAccess', 7, 'Request Building or Site Access', false,'', null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (16, 'RemoveBuildingOrSiteAccess', false, 'PhysicalAccess', 7, 'Remove Building or Site Access', false, '', null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (17, 'ViewUserDetailsDisplayInformation', false, 'PhysicalAccess', 7, 'View User Details', false ,'', null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (18, 'ModifyUserDetails', false, 'PhysicalAccess', 7, 'Modify User Details', false ,'', null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- Values (19, 'ApplicationAccess', false, 'RequestAccess', 6, 'Application Access', false, null, null, null, null,null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (20, 'RequestForApplicationAccess', false, 'ApplicationAccess', 19, 'Request for Application Access', false, '', null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (21, 'RequestOrRemoveAccessToAnApplication', false, 'ApplicationAccess', 19,'Remove Access to an Application', false, '',null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- Values (22, 'ViewRequestStatus', false, 'RequestAccess',6, 'View Request Status',false, null, null, null, null,null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- Values (23, 'ViewRequestStatusAll', false, 'RequestAccess',6, 'View Request Status ALL', false, ' ',null ,null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- VALUES (24,  'RoleManagement', true, null, 24, 'Role Management', false, null, null, null, '/resources/shared/images/menu03.jpg',null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- Values (25, 'CreateRequestForRM', false, 'RoleManagement', 24, 'Create Request for RM',false , null, null, null, null,null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (26, 'DisplayRole', false, 'CreateRequestForRM', 25, 'Display Role' ,false ,'', null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (27, 'CreateNewRole', false, 'CreateRequestForRM', 25, 'Create New Role' ,false ,'',null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (28, 'ManageRole', false, 'CreateRequestForRM', 25, 'Manage Role' ,false ,'', null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- Values (29, 'RoleMaintenance', false, 'RoleManagement',24, 'Role Maintenance', false, null, null, null, null,null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (30, 'CreateRequestForNewRole', false, 'RoleMaintenance', 29, 'Create Request for New Role' ,false, '', null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (31, 'CreateRequestForChangeRole', false, 'RoleMaintenance', 29, 'Create Request for Change Role', false ,'',null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- Values (32, 'Role-Decommission', false, 'RoleManagement',24, 'Role_Decommission', false, null, null, null, null,null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- Values (33, 'ManageOwners', false, 'RoleManagement',24, 'Manage Owners', false, null, null, null, null,null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (34, 'ManageOwners-Approvers-Certifiers', false, 'ManageOwners', 33, 'ManageOwnersApproversCertifiers', false ,'',null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- VALUES (35,  'TrainingAndCertifications', true, null, 35, ' Training n Certifications', false, null, null, null, '/resources/shared/images/menu04.jpg',null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- Values (36, 'Training', false, 'TrainingAndCertifications',35, 'Training', false, null, null, null, null,null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (37, 'CreateTraining', false, 'Training', 36, 'Create Training', false ,'',null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (38, 'DisplayTraining', false, 'Training', 36, 'Display Training', false ,'',null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (39, 'ModifyTraining', false, 'Training', 36, 'Modify Training', false ,'',null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- Values (40, 'PRA', false, 'TrainingAndCertifications', 35, 'PRA', false, null, null, null, null,null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (41, 'CreatePRA', false, 'PRA', 40, 'Create PRA', false ,'', null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (42, 'DisplayPRA', false, 'PRA', 40, 'Display PRA', false ,'', null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- values (43, 'ModifyPRA', false, 'PRA', 40, 'Modify PRA', false ,'',null, null, null, null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- VALUES (44,  'AccessReviews', true, null, 44, 'Access Reviews', false, null, null, null, '/resources/shared/images/menu05.jpg',null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- Values (45, 'CreateReview', false, 'AccessReviews', 44, 'Create Review', false, null, null, null, null,null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- Values (56, 'ExistingReview', false, 'AccessReviews', 44, 'Existing Review', false, null, null, null, null,null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- Values (46, 'ReviewsAdministration', false, 'AccessReviews',44, 'Reviews Administration', false, null, null, null, null,null);
-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- VALUES (54,  'Analytics', true, null, 54, 'Analytics', false, null, null, null, '/resources/shared/images/menu07.jpg',null);
/*
INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
VALUES (47,  'VisitorManagement', true, null, 47, 'Visitor Management', false, null, null, null, null,null);

INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
Values (48, 'Google', false, 'VisitorManagement', 47, 'Google', false, null, null, null, null,null);

INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
Values (49, 'Kiosk', false, 'VisitorManagement', 47, 'Google', false, null, null, null, null,null);
*/
-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- VALUES (50,  'Operations', true, null, 50, 'Operations', false, null, null, null, '/resources/shared/images/menu06.jpg',null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- Values (51, 'AreaAdmin', false, 'Operations', 50, 'Area Admin', false, null, null, null, null,null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- Values (52, 'BadgeAdmin', false, 'Operations', 50, 'Badge Admin', false, null, null, null, null,null);

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- Values (53, 'BulkImport-exports', false, 'Operations', 50, 'Bulk Import/exports', false, null, null, null, null,null);


/*INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
VALUES (55,  'Setup', true, null, 55, 'Setup', false, null, null, null, null,null);
*/

-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- VALUES (59, 'dashboard', false, 'Analytics', 1, 'Dashboard', false, '', 'analytics.dashboard', null, null, null);
-- INSERT INTO MENU_ITEM(ID, MENU_ID, MAIN, PARENT_ID, SEQ, LABEL, PREFERRED, ACCESS_LEVEL, ACTIVITY_ID, SCREEN_CONTROL_ID, REPORT_ID, EXTERNAL_ACCESS)
-- VALUES (60, 'reports', false,'Analytics' , 2, 'Reports', false, '', 'analytics.reports', null, null, null);
-- VALUES (58, 'reports', false,'Analytics' , 2, 'Reports', false, '', 'analytics.reports', null, null, null);



