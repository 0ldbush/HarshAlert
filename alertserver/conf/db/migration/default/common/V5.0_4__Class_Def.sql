INSERT INTO class_def (id,int_status,bus_obj_cat,ext_id,field_in_parent,parent_bus_obj_cat,ref_field,"text",is_rule_parent,changed_by,changed_on,created_by,created_on,description,"type",class_name) VALUES 
(1,0,'Request','request',NULL,NULL,NULL,'Request',true,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(2,0,'Role','role',NULL,NULL,NULL,'Role',false,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(3,0,'Policy','policy',NULL,NULL,NULL,'Policy',false,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(4,0,'Rule','rule',NULL,NULL,NULL,'Rule',false,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(5,0,'ClassDef','classDef',NULL,NULL,NULL,'Class Definition',false,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(6,0,'User','user',NULL,NULL,NULL,'User',false,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(7,0,'PolicyType','policyType',NULL,NULL,NULL,'Policy Type',false,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(8,0,'PolicyGroup','policyGroup',NULL,NULL,NULL,'Policy Group',false,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(9,0,'ResponseCode','responseCode',NULL,NULL,NULL,'Response Code',false,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(10,0,'MitigationControl','mitigationControl',NULL,NULL,NULL,'Mitigation Control',false,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
;
INSERT INTO class_def (id,int_status,bus_obj_cat,ext_id,field_in_parent,parent_bus_obj_cat,ref_field,"text",is_rule_parent,changed_by,changed_on,created_by,created_on,description,"type",class_name) VALUES 
(12,0,'RuleSet','ruleSet',NULL,NULL,NULL,'Rule Set',false,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(14,0,'ListEntries','listEntries',NULL,NULL,NULL,'ListEntries',false,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(15,0,'List','list',NULL,NULL,NULL,'List',false,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(13,0,'Risk','risk',NULL,NULL,NULL,'Risk',false,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(16,0,'DocNumberRange','docNumberRange',NULL,NULL,NULL,'Document Number Range',false,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,(11,0,'FieldDef','fieldDef',NULL,NULL,NULL,'Field Definition',false,NULL,NULL,NULL,NULL,NULL,NULL,NULL)
;