drop VIEW TIMELINE_UI;
CREATE VIEW TIMELINE_UI ( "id",
	 "eventtype",
	 "eventtypetext",
	 "busobjcat",
	 "busobjid",
	 "field",
	 "fieldname",
	 "fieldtooltip",
	 "oldvalue",
	 "newvalue",
	 "user_id",
	 "username",
	 "createdon",
	 "int_status" ) AS SELECT
	 
	timeline_chgs.id as  "id",
	 timeline_chgs.subType as "eventtype",
	 timeline_chgs.subType as "eventtypetext",
	 timeline.busObjCat as "busobjcat",
	 timeline.busObjID as "busobjid",
	 timeline_chgs.field as "field",
	 field_Def.label as "fieldname",
	 field_Def.tooltip as "fieldtooltip",
	 timeline_chgs.oldValue as "oldvalue",
	 timeline_chgs.newValue as "newvalue",
	 timeline.createdBy as "user_id",
	 users.first_name as "username",
	 timeline.createdOn as "createdon" ,
	 0 as int_status 
from timeline_chgs 
left outer join timeline on timeline.id = timeline_chgs.timelineid 
left join users on users.id = timeline.createdBy 
left join field_Def on field_def.fieldname = timeline_chgs.field 
order by timeline.createdOn desc ;