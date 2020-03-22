
delete from menu_item ;
delete from activity ;

INSERT INTO public.activity (id,int_status,changed_by,changed_on,created_by,created_on,description,ext_id,"text",bus_function,bus_obj_cat,default_mode,display_only,follow_ons,full_namespace,help_text_url,hidden,icon,keywords,mapped_namespace,setting_act,title,doc_number, package_name) VALUES 
(15,NULL,NULL,NULL,NULL,NULL,NULL,'setting.policytype','Policy Type',NULL,NULL,'update',false,NULL,'ae/setting/policytype',NULL,NULL,NULL,NULL,'ae.setting.policytype',false,NULL,NULL, 'setting')
,(16,NULL,NULL,NULL,NULL,NULL,NULL,'setting.policygroup','Policy Group',NULL,NULL,'update',false,NULL,'ae/setting/policygroup',NULL,NULL,NULL,NULL,'ae.setting.policygroup',false,NULL,NULL, 'setting')
,(1,NULL,NULL,NULL,NULL,NULL,NULL,'user.inbox','Inbox','','','update',false,'','ae/inbox','',NULL,NULL,'','ae.inbox',NULL,NULL,NULL, 'inbox')
,(2,NULL,NULL,NULL,NULL,NULL,NULL,'policyengine.policy','Policies','','','update',false,'','ae/policyengine/policy','',NULL,NULL,'','ae.policyengine.policy',NULL,NULL,NULL,'policyengine')
,(3,NULL,NULL,NULL,NULL,NULL,NULL,'policyengine.ruleset','Rule Set','','','update',false,'','ae/policyengine/ruleset','',NULL,NULL,'','ae.policyengine.ruleset',NULL,NULL,NULL,'policyengine')
,(4,NULL,NULL,NULL,NULL,NULL,NULL,'policyengine.rule','Rules','','','update',false,'','ae/policyengine/rule','',NULL,NULL,'','ae.policyengine.rule',NULL,NULL,NULL,'policyengine')
,(5,NULL,NULL,NULL,NULL,NULL,NULL,'analytics.dashboard','Dashboard','','','update',false,'','ae/dashboard','',NULL,NULL,'','ae.dashboard',NULL,NULL,NULL,'dashboard')
,(6,NULL,NULL,NULL,NULL,NULL,NULL,'analytics.reports','Reports','','','update',false,'','ae/reports','',NULL,NULL,'','ae.reports',NULL,NULL,NULL,'reports')
,(7,NULL,NULL,NULL,NULL,NULL,NULL,'policyengine.responsecode','Response Codes','','','update',false,'','ae/policyengine/responsecode','',NULL,NULL,'','ae.policyengine.responsecode',NULL,NULL,NULL,'policyengine')
,(8,NULL,NULL,NULL,NULL,NULL,NULL,'policyengine.risk','Risk','','','update',false,'','ae/policyengine/risk','',NULL,NULL,'','ae.policyengine.risk',NULL,NULL,NULL,'policyengine')
;
INSERT INTO public.activity (id,int_status,changed_by,changed_on,created_by,created_on,description,ext_id,"text",bus_function,bus_obj_cat,default_mode,display_only,follow_ons,full_namespace,help_text_url,hidden,icon,keywords,mapped_namespace,setting_act,title,doc_number, package_name) VALUES 
(9,NULL,NULL,NULL,NULL,NULL,NULL,'policyengine.mitigationcontrol','Mitigation Control','','','update',false,'','ae/policyengine/mitigationcontrol','',NULL,NULL,'','ae.policyengine.mitigationcontrol',NULL,NULL,NULL,'policyengine')
,(11,NULL,NULL,NULL,NULL,NULL,NULL,'setting.classdef','Class Definition',NULL,NULL,'update',false,NULL,'ae/setting/classdef',NULL,NULL,NULL,NULL,'ae.setting.classdef',false,NULL,NULL, 'setting')
,(12,NULL,NULL,NULL,NULL,NULL,NULL,'setting.list','List',NULL,NULL,'update',false,NULL,'platform/view/lists',NULL,NULL,NULL,NULL,'platform.view.lists',false,NULL,NULL, 'setting')
,(13,NULL,NULL,NULL,NULL,NULL,NULL,'setting.user','User',NULL,NULL,'update',false,NULL,'ae/setting/user',NULL,NULL,NULL,NULL,'ae.setting.user',false,NULL,NULL, 'setting')
,(14,NULL,NULL,NULL,NULL,NULL,NULL,'setting.role','Role',NULL,NULL,'update',false,NULL,'ae/setting/role',NULL,NULL,NULL,NULL,'ae.setting.role',false,NULL,NULL, 'setting')
;






