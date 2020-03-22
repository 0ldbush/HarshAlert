-- POLICY TYPE
insert into policy_type
(id,int_status,version,description,ext_id,text)
 values('1',1,'1','Access Policy','ACCESS_POLICY_TYPE','ACCESS_POLICY_TYPE');
	
-- POLICY   
insert into policy
(id,int_status,version,description,enforcement,ext_id,policygroup,
 priority,text,type)
 values('1',1,'1','Access Policy Group',1,'ACCESS_POLICY_GROUP','ACCESS',1,
	   'Access Policy Group','ACCESS_POLICY_TYPE');
	   
-- RULESET
insert into rule_set
(id,int_status,version,description,ext_id,text)
 values('1',1,'1','SOD Rule Set','SOD','SOD Rule Set');
 
 insert into policy_rule_set_xref(policy_id,rule_set_id) values('1','1');
 
 insert into rule_set_rule_xref(rule_set_id,rule_id) values('1','1');
 insert into rule_set_rule_xref(rule_set_id,rule_id) values('1','2'); 
 -- RULES
 insert into rule(id,int_status,version,action,condition,condition_json,description,ext_id,namespace,priority,text) 
 values('1',1,'1',
 '$(ROLE_NOT_ALLOWED)',
 'Request.isSaveDraft == false && Request.rolesList.size() > 0 &&  ((description in Request.?rolesList) contains "PO_CREATE_ROLE")',
 'JSON',
 'Rule not allowed',
 'ROLE_NOT_ALLOWED',
 'SOD',
 '1',
 'Role not allowed ');
 
 insert into rule(id,int_status,version,action,condition,condition_json,description,ext_id,namespace,priority,text) values('2',1,'1','??ROLE_NOT_ASSIGNED??','Request.isSaveDraft == false && Request.rolesList.size() > 0 &&  ((description in Request.?rolesList) contains "PO_CREATE_ROLE")','JSON','Rule not allowed','ROLE_NOT_ASSIGNED','SOD','1','Role not allowed ');