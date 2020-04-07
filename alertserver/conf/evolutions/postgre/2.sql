-- !Ups

INSERT INTO aehsc.activity (id,int_status,changed_by,changed_on,created_by,created_on,description,ext_id,"text",bus_function,bus_obj_cat,default_mode,display_only,follow_ons,full_namespace,help_text_url,hidden,icon,keywords,mapped_namespace,setting_act,title,doc_number) VALUES 
(2,0,NULL,NULL,NULL,NULL,NULL,'policyengine.policy','Policies','','','update','false','','ae/policyengine/policy','',NULL,NULL,'','ae.policyengine.policy',NULL,NULL,NULL)
,(3,0,NULL,NULL,NULL,NULL,NULL,'policyengine.ruleset','Rule Set','','','update','false','','ae/policyengine/ruleset','',NULL,NULL,'','ae.policyengine.ruleset',NULL,NULL,NULL)
,(4,0,NULL,NULL,NULL,NULL,NULL,'policyengine.rule','Rules','','','update','false','','ae/policyengine/rule','',NULL,NULL,'','ae.policyengine.rule',NULL,NULL,NULL)
,(7,0,NULL,NULL,NULL,NULL,NULL,'policyengine.responsecode','Response Codes','','','update','false','','ae/policyengine/responsecode','',NULL,NULL,'','ae.policyengine.responsecode',NULL,NULL,NULL)
,(8,0,NULL,NULL,NULL,NULL,NULL,'policyengine.risk','Risk','','','update','false','','ae/policyengine/risk','',NULL,NULL,'','ae.policyengine.risk',NULL,NULL,NULL)
;
INSERT INTO aehsc.activity (id,int_status,changed_by,changed_on,created_by,created_on,description,ext_id,"text",bus_function,bus_obj_cat,default_mode,display_only,follow_ons,full_namespace,help_text_url,hidden,icon,keywords,mapped_namespace,setting_act,title,doc_number) VALUES 
(9,0,NULL,NULL,NULL,NULL,NULL,'policyengine.mitigationcontrol','Mitigation Control','','','update','false','','ae/policyengine/mitigationcontrol','',NULL,NULL,'','ae.policyengine.mitigationcontrol',NULL,NULL,NULL)
,(11,0,NULL,NULL,NULL,NULL,NULL,'setting.classdef','Class Definition',NULL,NULL,'update','false',NULL,'platform/view/classdef',NULL,NULL,NULL,NULL,'platform.view.classdef','false',NULL,NULL)
,(12,0,NULL,NULL,NULL,NULL,NULL,'setting.list','List',NULL,NULL,'update','false',NULL,'platform/view/lists',NULL,NULL,NULL,NULL,'platform.view.lists','false',NULL,NULL)
,(13,0,NULL,NULL,NULL,NULL,NULL,'setting.user','User',NULL,NULL,'update','false',NULL,'ae/setting/user',NULL,NULL,NULL,NULL,'ae.setting.user','false',NULL,NULL)
,(14,0,NULL,NULL,NULL,NULL,NULL,'setting.role','Role',NULL,NULL,'update','false',NULL,'ae/setting/role',NULL,NULL,NULL,NULL,'ae.setting.role','false',NULL,NULL)
,(15,0,NULL,NULL,NULL,NULL,NULL,'setting.policytype','Policy Type',NULL,NULL,'update','false',NULL,'ae/setting/policytype',NULL,NULL,NULL,NULL,'ae.setting.policytype','false',NULL,NULL)
,(16,0,NULL,NULL,NULL,NULL,NULL,'setting.policygroup','Policy Group',NULL,NULL,'update','false',NULL,'ae/setting/policygroup',NULL,NULL,NULL,NULL,'ae.setting.policygroup','false',NULL,NULL)
,(17,0,NULL,NULL,NULL,NULL,NULL,'setting.docnumberrange','Document Number Range',NULL,NULL,'update','false',NULL,'ae/setting/docnumberrange',NULL,NULL,NULL,NULL,'ae.setting.docnumberrange','false',NULL,NULL)
;

INSERT INTO aehsc.class_def (id,int_status,bus_obj_cat,ext_id,field_in_parent,parent_bus_obj_cat,ref_field,"text",is_rule_parent,changed_by,changed_on,created_by,created_on,description,"type",class_name) VALUES 
(1,0,'Request','request',NULL,NULL,NULL,'Request','true',NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(2,0,'Role','role',NULL,NULL,NULL,'Role','false',NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(3,0,'Policy','policy',NULL,NULL,NULL,'Policy','false',NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(4,0,'Rule','rule',NULL,NULL,NULL,'Rule','false',NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(5,0,'ClassDef','classDef',NULL,NULL,NULL,'Class Definition','false',NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(6,0,'User','user',NULL,NULL,NULL,'User','false',NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(7,0,'PolicyType','policyType',NULL,NULL,NULL,'Policy Type','false',NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(8,0,'PolicyGroup','policyGroup',NULL,NULL,NULL,'Policy Group','false',NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(9,0,'ResponseCode','responseCode',NULL,NULL,NULL,'Response Code','false',NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(10,0,'MitigationControl','mitigationControl',NULL,NULL,NULL,'Mitigation Control','false',NULL,NULL,NULL,NULL,NULL,NULL,NULL)
;

INSERT INTO aehsc.class_def (id,int_status,bus_obj_cat,ext_id,field_in_parent,parent_bus_obj_cat,ref_field,"text",is_rule_parent,changed_by,changed_on,created_by,created_on,description,"type",class_name) VALUES 
(12,0,'RuleSet','ruleSet',NULL,NULL,NULL,'Rule Set','false',NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(14,0,'ListEntries','listEntries',NULL,NULL,NULL,'ListEntries','false',NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(15,0,'List','list',NULL,NULL,NULL,'List','false',NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(13,0,'Risk','risk',NULL,NULL,NULL,'Risk','false',NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(16,0,'DocNumberRange','docNumberRange',NULL,NULL,NULL,'Document Number Range','false',NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(11,0,'FieldDef','fieldDef',NULL,NULL,NULL,'Field Definition','false',NULL,NULL,NULL,NULL,NULL,NULL,NULL);

INSERT INTO aehsc.field_def (id,class_ext_id,do_not_copy,field_name,is_base_class_field,is_dimension,is_list,is_measure,is_primitive,is_required,is_translated,is_unique,"label",no_history,reference_key,tooltip,"type",xtype,uri) VALUES 
(1,'request','false','roleData','false','false','true','false','false','false','false','false','Role Data','false',NULL,NULL,'role',NULL,NULL)
,(2,'role','false','roleName','false','false','false','false','true','false','false','false','roleName/Role Name','false',NULL,NULL,NULL,NULL,'IdentityRoles')
,(3,'role','false','roleAction','false','false','false','false','true','false','false','false','roleAction','false',NULL,NULL,NULL,NULL,NULL)
,(4,'role','false','roleType','false','false','false','false','true','false','false','false','Role Type','false',NULL,NULL,NULL,NULL,NULL)
,(5,'role','false','description','false','false','false','false','true','false','false','false','Description','false',NULL,NULL,NULL,NULL,NULL)
,(7,'role','false','isJobRole','false','false','false','false','true','false','false','false','IsJobRole','false',NULL,NULL,NULL,NULL,NULL)
,(8,'role','false','country','false','false','false','false','true','false','false','false','Country','false',NULL,NULL,NULL,NULL,NULL)
,(9,'role','false','alias','false','false','false','false','true','false','false','false','Alias','false',NULL,NULL,NULL,NULL,NULL)
,(10,'role','false','criticality','false','false','false','false','true','false','false','false','Criticality','false',NULL,NULL,NULL,NULL,NULL)
,(11,'role','false','validity','false','false','false','false','true','false','false','false','Validity','false',NULL,NULL,NULL,NULL,NULL)
,(12,'role','false','longDescription','false','false','false','false','true','false','false','false','Long Description','false',NULL,NULL,NULL,NULL,NULL)
,(13,'role','false','userLocation','false','false','false','false','true','false','false','false','UserLocation','false',NULL,NULL,NULL,NULL,NULL)
,(14,'role','false','city','false','false','false','false','true','false','false','false','City','false',NULL,NULL,NULL,NULL,NULL)
,(15,'role','false','displayRoleName','false','false','false','false','true','false','false','false','Display Role Name','false',NULL,NULL,NULL,NULL,NULL)
,(16,'role','false','evaluationStatus','false','false','false','false','true','false','false','false','Evaluation Status','false',NULL,NULL,NULL,NULL,NULL)
;


INSERT INTO aehsc.lists (id,int_status,changed_by,changed_on,created_by,created_on,description,code) VALUES  
(1,0,NULL,NULL,NULL,NULL,'RESPONSEACTIONTYPES','RESPONSEACTIONTYPES')
,(2,0,NULL,NULL,NULL,NULL,'RESPONSEACTION_ALL','RESPONSEACTION_ALL')
,(3,0,1,NULL,1,NULL,'RULE OPERATOR','RULEOPERATOR')
,(5,0,1,NULL,1,NULL,'ENTITY STATUS','ENTITYSTATUS')
,(4,0,1,NULL,1,NULL,'LOGICAL OPERATOR','RULELOGICALOPERATOR')
,(6,0,NULL,NULL,NULL,NULL,'ROLE TYPES','ROLETYPE')
,(7,0,NULL,NULL,NULL,NULL,'SEVERITTY','SEVERITY')
,(8,0,NULL,NULL,NULL,NULL,'ENFORCEMENTTYPE','ENFORCEMENTTYPE')
,(9,0,1,NULL,1,NULL,'Access level on a screen as per assigned Role','ACCESSLEVEL')
;


INSERT INTO aehsc.list_entries (id,int_status,entry_code,entry_name,list_code, entry_type) VALUES 
(1,0,'ReturnMessage','Return Message','RESPONSEACTIONTYPES', '')
,(2,0,'DisplayMessage','Display Message','RESPONSEACTION_ALL', 'ReturnMessage')
,(3,0,'==','Equal','RULEOPERATOR', '')
,(4,0,'!=','Not Equal','RULEOPERATOR', '')
,(5,0,'<','Less Than','RULEOPERATOR', '')
,(6,0,'>','Greater Than','RULEOPERATOR', '')
,(7,0,'contains','Contains','RULEOPERATOR', '')
,(8,0,'0','Active','ENTITYSTATUS', '')
,(9,0,'9','InActive','ENTITYSTATUS', '')
,(10,0,'&&','AND','RULELOGICALOPERATOR', '')
;
INSERT INTO aehsc.list_entries (id,int_status,entry_code,entry_name,list_code, entry_type) VALUES 
(11,0,'||','OR','RULELOGICALOPERATOR', '')
,(12,0,'ANYTWO','ANY TWO','RULEOPERATOR', '')
,(13,0,'ANYTHREE','ANY THREE','RULEOPERATOR', '')
,(14,0,'ANYM','ANYM','RULEOPERATOR', '')
,(15,0,'REQUESTTYPE','Request Types','ROLETYPE', '')
,(16,0,'CERTTYPE','Certification Types','ROLETYPE', '')
,(17,0,'DoNothing','Do Nothing','RESPONSEACTION_ALL', 'NoAction')
,(18,0,'NoAction','No Action','RESPONSEACTIONTYPES', '')
,(19,0,'SEVERITY2','SEVERITY 2','SEVERITY', '')
,(20,0,'SEVERITY3','SEVERITY 3','SEVERITY', '')
,(21,0,'SEVERITY4','SEVERITY 4','SEVERITY', '')
,(22,0,'Preventive','Preventive','ENFORCEMENTTYPE', '')
,(23,0,'Suggestive','Suggestive','ENFORCEMENTTYPE', '')
,(24,0,'DISPLAY','Display','ACCESSLEVEL', '')
,(25,0,'UPDATE','Update','ACCESSLEVEL', '')
;


INSERT INTO aehsc.job_role (id,int_status,changed_by,changed_on,created_by,created_on,description,ext_id,"text","type",start_activity) VALUES 
(1,0,1,NULL,1,NULL,'ADMIN',NULL,'ADMIN',NULL,NULL)
;


INSERT INTO aehsc.menu_item (id,changed_by,changed_on,created_by,created_on,int_status,access_level,activity_id,external_access,icon_path,job_role_id,"label",main,menu_cls,menu_id,parent_id,preferred,report_id,screen_control_id,seq,submenu_icon_path) VALUES 
(1,NULL,NULL,NULL,NULL,0,'UPDATE','policyengine.responsecode',NULL,NULL,NULL,'Manage Response','false',NULL,'responsecode','policyengine','false',NULL,NULL,1,NULL)
,(2,NULL,NULL,NULL,NULL,0,'UPDATE','policyengine.policy',NULL,NULL,NULL,'Manage Policy','false',NULL,'policy','policyengine','false',NULL,NULL,1,NULL)
,(4,NULL,NULL,NULL,NULL,0,'UPDATE','policyengine.ruleset',NULL,NULL,NULL,'Manage RuleSet','false',NULL,'ruleset','policyengine','false',NULL,NULL,1,NULL)
,(5,NULL,NULL,NULL,NULL,0,'UPDATE','policyengine.rule',NULL,NULL,NULL,'Manage Rule','false',NULL,'rule','policyengine','false',NULL,NULL,1,NULL)
,(10,NULL,NULL,NULL,NULL,0,'UPDATE','setting.user',NULL,NULL,NULL,'Manage User','false',NULL,'manageuser','admin','false',NULL,NULL,1,NULL)
,(15,NULL,NULL,NULL,NULL,0,'UPDATE','setting.docnumberrange',NULL,NULL,NULL,'Document Number Range','false',NULL,'docnumberrange','setting','false',NULL,NULL,4,NULL)
,(11,NULL,NULL,NULL,NULL,0,'UPDATE',NULL,NULL,'/resources/shared/images/menu02.svg',1,'Risk Management','true',NULL,'riskmanagement',NULL,NULL,NULL,NULL,2,NULL)
,(12,NULL,NULL,NULL,NULL,0,'UPDATE',NULL,NULL,'/resources/shared/images/menu04.svg',1,'Setting','true',NULL,'setting',NULL,NULL,NULL,NULL,4,NULL)
,(13,NULL,NULL,NULL,NULL,0,'UPDATE',NULL,NULL,'/resources/shared/images/menu01.svg',1,'Policy Engine','true',NULL,'policyengine',NULL,'false',NULL,NULL,1,NULL)
,(14,NULL,NULL,NULL,NULL,0,'UPDATE',NULL,NULL,'/resources/shared/images/menu03.svg',1,'Admin','true',NULL,'admin',NULL,NULL,NULL,NULL,1,NULL)
;


INSERT INTO aehsc.menu_item (id,changed_by,changed_on,created_by,created_on,int_status,access_level,activity_id,external_access,icon_path,job_role_id,"label",main,menu_cls,menu_id,parent_id,preferred,report_id,screen_control_id,seq,submenu_icon_path) VALUES 
(7,NULL,NULL,NULL,NULL,0,'UPDATE','setting.list',NULL,NULL,NULL,'Manage List Entries','false',NULL,'list','setting','false',NULL,NULL,1,NULL)
,(8,NULL,NULL,NULL,NULL,0,'UPDATE','setting.classdef',NULL,NULL,NULL,'Manage Class Definition','false',NULL,'classdef','setting','false',NULL,NULL,1,NULL)
,(3,NULL,NULL,NULL,NULL,0,'UPDATE','policyengine.risk',NULL,NULL,NULL,'Manage Risk','false',NULL,'managerisk','riskmanagement',NULL,NULL,NULL,1,NULL)
,(6,NULL,NULL,NULL,NULL,0,'UPDATE','policyengine.mitigationcontrol',NULL,NULL,NULL,'Manage Mitigation','false',NULL,'managemitigation','riskmanagement',NULL,NULL,NULL,1,NULL)
,(9,NULL,NULL,NULL,NULL,0,'UPDATE','setting.role',NULL,NULL,NULL,'Manage Role','false',NULL,'managerole','admin',NULL,NULL,NULL,2,NULL)
,(16,NULL,NULL,NULL,NULL,0,'UPDATE','setting.policytype',NULL,NULL,NULL,'Manage Policy Type','false',NULL,'policytype','setting',NULL,NULL,NULL,NULL,NULL)
,(17,NULL,NULL,NULL,NULL,0,'UPDATE','setting.policygroup',NULL,NULL,NULL,'Manage Policy Group','false',NULL,'policygroup','setting',NULL,NULL,NULL,2,NULL)
;

INSERT INTO aehsc.users (id,changed_by,changed_on,created_by,created_on,int_status,date_format,first_name,keep_messages,last_name,locale_code,number_format,"password",password_change_required,primary_email_address,primary_phone,start_activity,start_last_activity,time_format,time_zone,user_name,user_type) VALUES (1,1,'2020-03-05 13:47:41.701',NULL,NULL,0,NULL,'admin',NULL,'user',NULL,NULL,'$2a$11$1g0y3RfUvfSemsgpsuHXjekV/.PS/Y3uJwjVYBcjtwc/EG9qVKq2u','false',NULL,NULL,NULL,NULL,NULL,NULL,'admin',NULL);

INSERT INTO aehsc.user_job_role (user_id,job_role_id) VALUES (1,1);

INSERT INTO aehsc.config_setting 
(id,int_status,changed_by,changed_on,created_by,created_on,name,ext_id,value,group_name,description) VALUES  
(1,0,NULL,NULL,NULL,NULL,'marked_for_deletion_expr','marked_for_deletion_expr','Request.rolesList[?(@.roleAction == "delete")]','SOD','roles list having action=delete is omitted')
;

INSERT INTO aehsc.policy_type (id,int_status,changed_by,changed_on,created_by,created_on,description,ext_id,"text",doc_number) VALUES 
(1,0,NULL,NULL,NULL,NULL,'Validation Policies','VALPOL','VALIDATION',NULL);

INSERT INTO aehsc.policy_group (id,changed_by,changed_on,created_by,created_on,int_status,description,doc_number,ext_id,"text") VALUES 
(1,NULL,NULL,NULL,NULL,0,'Policy Group for Access Requests',NULL,'PGRPRequests','Access Requests')
;

INSERT INTO aehsc.doc_number_range (id,changed_by,changed_on,created_by,created_on,int_status,description,doc_number,ext_id,"text",bus_obj_cat,code_edit_allowed,company_range,duplicates_allowed,external_range_end,external_range_format,external_range_start,fiscal_year_range,internal_range_end,internal_range_format,internal_range_start,max_length,no_missed_numbers,remove_leading_zeros,user_input_extid) VALUES 
(1,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,'ResponseCode','false','false','false',NULL,NULL,NULL,'false','RES-999999','RES-NNNNNN','RES-000001',11,'false','true','Never')
,(2,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,'MitigationControl','false','false','false',NULL,NULL,NULL,'false','MIT-999999','MIT-NNNNNN','MIT-000001',11,'false','true','Never')
,(3,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,'RuleSet','false','false','false',NULL,NULL,NULL,'false','RST-999999','RST-NNNNNN','RST-000001',11,'false','true','Never')
,(4,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,'Risk','false','false','false',NULL,NULL,NULL,'false','RSK-999999','RSK-NNNNNN','RSK-000001',11,'false','true','Never')
,(5,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,'Role','false','false','false',NULL,NULL,NULL,'false','ROL-999999','ROL-NNNNNN ','ROL-000001',11,'false','true','Never')
,(6,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,'Policy','false','false','false',NULL,NULL,NULL,'false','POL-999999','POL-NNNNNN','POL-000001',11,'false','true','Never')
,(7,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,'Rule','false','false','false',NULL,NULL,NULL,'false','RUL-999999','RUL-NNNNNN','RUL-000001',11,'false','true','Never')
,(8,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,'User','false','false','false',NULL,NULL,NULL,'false','USR-999999','USR-NNNNNN','USR-000001',11,'false','true','Never')
,(9,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL,NULL,'PolicyGroup','false','false','false',NULL,NULL,NULL,'false','PGR-999999','PGR-NNNNNN','PGR-000001',11,'false','true','Never')
;


INSERT INTO aehsc.currency (id,int_status,description,ext_id,"text",country,decimals,flagicon,peggedcurrency,symbol) VALUES 
(1,0,'USD','USD','USD','USA',2,'https://image.flaticon.com/icons/svg/197/197374.svg','pegged','$')
,(2,0,'INR','INR','INR','India',2,'https://image.flaticon.com/icons/svg/197/197419.svg','pegged','₹')
;


INSERT INTO aehsc.tenant (id,int_status,changed_by,changed_on,created_by,created_on,tenant_name,db_schema,"domain",persistence_unit_name,tenant_id) VALUES
(1,0,NULL,NULL,NULL,NULL,'alert','aehsc','alert','master',1);