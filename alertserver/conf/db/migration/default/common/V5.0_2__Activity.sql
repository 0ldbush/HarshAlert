

INSERT INTO activity (id,int_status,changed_by,changed_on,created_by,created_on,description,ext_id,"text",bus_function,bus_obj_cat,default_mode,display_only,follow_ons,full_namespace,help_text_url,hidden,icon,keywords,mapped_namespace,setting_act,title,doc_number) VALUES 
-- (1,0,NULL,NULL,NULL,NULL,NULL,'user.inbox','Inbox','','','update',false,'','ae/inbox','',NULL,NULL,'','ae.inbox',NULL,NULL,NULL),
(2,0,NULL,NULL,NULL,NULL,NULL,'policyengine.policy','Policies','','','update',false,'','ae/policyengine/policy','',NULL,NULL,'','ae.policyengine.policy',NULL,NULL,NULL)
,(3,0,NULL,NULL,NULL,NULL,NULL,'policyengine.ruleset','Rule Set','','','update',false,'','ae/policyengine/ruleset','',NULL,NULL,'','ae.policyengine.ruleset',NULL,NULL,NULL)
,(4,0,NULL,NULL,NULL,NULL,NULL,'policyengine.rule','Rules','','','update',false,'','ae/policyengine/rule','',NULL,NULL,'','ae.policyengine.rule',NULL,NULL,NULL)
-- ,(5,0,NULL,NULL,NULL,NULL,NULL,'analytics.dashboard','Dashboard','','','update',false,'','ae/dashboard','',NULL,NULL,'','ae.dashboard',NULL,NULL,NULL)
-- ,(6,0,NULL,NULL,NULL,NULL,NULL,'analytics.reports','Reports','','','update',false,'','ae/reports','',NULL,NULL,'','ae.reports',NULL,NULL,NULL)
,(7,0,NULL,NULL,NULL,NULL,NULL,'policyengine.responsecode','Response Codes','','','update',false,'','ae/policyengine/responsecode','',NULL,NULL,'','ae.policyengine.responsecode',NULL,NULL,NULL)
,(8,0,NULL,NULL,NULL,NULL,NULL,'policyengine.risk','Risk','','','update',false,'','ae/policyengine/risk','',NULL,NULL,'','ae.policyengine.risk',NULL,NULL,NULL)
;
INSERT INTO activity (id,int_status,changed_by,changed_on,created_by,created_on,description,ext_id,"text",bus_function,bus_obj_cat,default_mode,display_only,follow_ons,full_namespace,help_text_url,hidden,icon,keywords,mapped_namespace,setting_act,title,doc_number) VALUES 
(9,0,NULL,NULL,NULL,NULL,NULL,'policyengine.mitigationcontrol','Mitigation Control','','','update',false,'','ae/policyengine/mitigationcontrol','',NULL,NULL,'','ae.policyengine.mitigationcontrol',NULL,NULL,NULL)
,(11,0,NULL,NULL,NULL,NULL,NULL,'setting.classdef','Class Definition',NULL,NULL,'update',false,NULL,'ae/setting/classdef',NULL,NULL,NULL,NULL,'ae.setting.classdef',false,NULL,NULL)
,(12,0,NULL,NULL,NULL,NULL,NULL,'setting.list','List',NULL,NULL,'update',false,NULL,'platform/view/lists',NULL,NULL,NULL,NULL,'platform.view.lists',false,NULL,NULL)
,(13,0,NULL,NULL,NULL,NULL,NULL,'setting.user','User',NULL,NULL,'update',false,NULL,'ae/setting/user',NULL,NULL,NULL,NULL,'ae.setting.user',false,NULL,NULL)
,(14,0,NULL,NULL,NULL,NULL,NULL,'setting.role','Role',NULL,NULL,'update',false,NULL,'ae/setting/role',NULL,NULL,NULL,NULL,'ae.setting.role',false,NULL,NULL)
,(15,0,NULL,NULL,NULL,NULL,NULL,'setting.policytype','Policy Type',NULL,NULL,'update',false,NULL,'ae/setting/policytype',NULL,NULL,NULL,NULL,'ae.setting.policytype',false,NULL,NULL)
,(16,0,NULL,NULL,NULL,NULL,NULL,'setting.policygroup','Policy Group',NULL,NULL,'update',false,NULL,'ae/setting/policygroup',NULL,NULL,NULL,NULL,'ae.setting.policygroup',false,NULL,NULL)
,(17,0,NULL,NULL,NULL,NULL,NULL,'setting.docnumberrange','Document Number Range',NULL,NULL,'update',false,NULL,'ae/setting/docnumberrange',NULL,NULL,NULL,NULL,'ae.setting.docnumberrange',false,NULL,NULL)
;




