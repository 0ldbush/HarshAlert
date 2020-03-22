
DELETE FROM public.list_entries;

INSERT INTO public.list_entries (id,int_status,entry_code,entry_name,list_code, entry_type) VALUES 
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
INSERT INTO public.list_entries (id,int_status,entry_code,entry_name,list_code, entry_type) VALUES 
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
,(25,0,'CREATE','Create','ACCESSLEVEL', '')
,(26,0,'EDIT','Edit','ACCESSLEVEL', '')
,(27,0,'DELETE','Delete','ACCESSLEVEL', '')
;