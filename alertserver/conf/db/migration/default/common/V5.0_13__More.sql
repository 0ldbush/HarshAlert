--********************** Table ACTIVITY Changes starts here *******************************

UPDATE activity SET int_status = 0;

--********************** Table ACTIVITY Changes ends here *******************************

--********************** Table ListEntry Changes starts here *******************************

DELETE FROM public.list_entries WHERE list_code = 'ACCESSLEVEL';

INSERT INTO public.list_entries (id,entry_code,entry_name,list_code,entry_type,changed_by,changed_on,created_by,created_on,int_status) VALUES 
(24,'DISPLAY','Display','ACCESSLEVEL',NULL,NULL,NULL,NULL,NULL,NULL)
,(25,'UPDATE','Update','ACCESSLEVEL',NULL,NULL,NULL,NULL,NULL,NULL)
;

--********************** Table ListEntry Changes ends here *******************************

--********************** Table Menu_Item Changes starts here *******************************

update menu_item set access_level = 'UPDATE';
update menu_item set icon_path = '/resources/shared/images/menu02.svg' where id=11;
update menu_item set icon_path = '/resources/shared/images/menu04.svg' where id=12;
update menu_item set icon_path = '/resources/shared/images/menu01.svg' where id=13;
update menu_item set icon_path = '/resources/shared/images/menu03.svg' where id=14;

INSERT INTO public.menu_item (id,changed_by,changed_on,created_by,created_on,int_status,access_level,activity_id,external_access,icon_path,job_role_id,"label",main,menu_cls,menu_id,parent_id,preferred,report_id,screen_control_id,seq,submenu_icon_path) VALUES 
(16,NULL,'2020-03-30 14:22:53.120',NULL,'2020-03-30 14:22:53.120',0,'UPDATE','setting.policytype',NULL,NULL,NULL,'Manage Policy Type',NULL,NULL,'policytype','setting',NULL,NULL,NULL,NULL,NULL)
,(17,NULL,'2020-03-30 14:22:53.130',NULL,'2020-03-30 14:22:53.130',0,'UPDATE','setting.policygroup',NULL,NULL,NULL,'Manage Policy Group',NULL,NULL,'policygroup','setting',NULL,NULL,NULL,2,NULL)
;

--********************** Table Menu_Item Changes ends here *******************************

