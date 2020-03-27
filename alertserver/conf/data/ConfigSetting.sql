DELETE FROM public.Config_Setting;

INSERT INTO public.config_setting 
(id,int_status,changed_by,changed_on,created_by,created_on,name,ext_id,value,group_name,description) VALUES  
(1,0,NULL,NULL,NULL,NULL,'marked_for_deletion_expr','marked_for_deletion_expr','Request.rolesList[?(@.roleAction == "delete")]','SOD','roles list having action=delete is omitted')
;