-- !Ups

CREATE TABLE aehsc.activity (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    description character varying(200),
    doc_number character varying(50),
    ext_id character varying(50),
    text character varying(50),
    bus_function character varying(255),
    bus_obj_cat character varying(255),
    default_mode character varying(255),
    display_only bit,
    follow_ons character varying(255),
    full_namespace character varying(255),
    help_text_url character varying(255),
    hidden bit,
    icon character varying(255),
    keywords character varying(255),
    mapped_namespace character varying(255),
    package_name character varying(255),
    setting_act bit,
    title character varying(255)
);

CREATE TABLE aehsc.api_service (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    api_service_code character varying(255) NOT NULL,
    clazz character varying(255),
    error_message_code character varying(255),
    from_queue_name character varying(255),
    group_name character varying(255),
    match_rule varchar(max),
    method character varying(255),
    name character varying(255) NOT NULL,
    seq integer,
    success_message_code character varying(255),
    to_queue_name character varying(255),
    type character varying(255) NOT NULL,
    web_service character varying(255)
);


CREATE TABLE aehsc.api_service_parameter (
    id bigint NOT NULL,
    constant_value character varying(255),
    field character varying(255) NOT NULL,
    field_type character varying(255) NOT NULL,
    multiple_values bit,
    seq integer NOT NULL,
    api_service_id bigint NOT NULL
);


CREATE TABLE aehsc.attachment (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    bus_obj_cat character varying(255),
    bus_obj_id bigint,
    ext_id character varying(50),
    text character varying(255),
    type character varying(50),
	mime_type  character varying(255),
    binary_resource_id bigint,
    shared bit,
    source_file character varying(255),
    thumbnail_id bigint
);

CREATE TABLE aehsc.attachment_type (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    description character varying(200),
    doc_number character varying(50),
    ext_id character varying(50),
    text character varying(50)
);


CREATE TABLE aehsc.binary_resource (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    file_data image,
    mime_type character varying(255),
    path character varying(255),
    size bigint,
    thumbnail_path character varying(255),
    title character varying(255),
    type character varying(255)
);

CREATE TABLE aehsc.class_def (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    description character varying(255),
    ext_id character varying(50),
    text character varying(255),
    type character varying(255),
    bus_obj_cat character varying(255),
    class_name character varying(255),
    field_in_parent character varying(255),
    is_rule_parent bit,
    doc_number_supported bit,
    editable bit,
    parent_bus_obj_cat character varying(255),
    ref_field character varying(255)
);

CREATE TABLE aehsc.config_setting (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    description character varying(255),
    ext_id character varying(50),
    text character varying(255),
    type character varying(255),
    group_name character varying(255),
    name character varying(255) NOT NULL,
    value character varying(255) NOT NULL
);


CREATE TABLE aehsc.currency (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    description character varying(255),
    ext_id character varying(50),
    text character varying(255),
    type character varying(255),
    country character varying(255),
    decimals integer,
    flagicon character varying(255),
    peggedcurrency character varying(255),
    symbol character varying(255)
);


CREATE TABLE aehsc.doc_number (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    doc_number character varying(255),
    fiscal_year integer,
    num_range_id character varying(255),
    range_reset_date datetime2
);

CREATE TABLE aehsc.doc_number_range (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    description character varying(200),
    doc_number character varying(50),
    ext_id character varying(50),
    text character varying(50),
    bus_obj_cat character varying(255),
    code_edit_allowed bit,
    company_range bit,
    duplicates_allowed bit,
    external_range_end character varying(255),
    external_range_format character varying(255),
    external_range_start character varying(255),
    fiscal_year_range bit,
    internal_range_end character varying(255),
    internal_range_format character varying(255),
    internal_range_start character varying(255),
    max_length integer,
    no_missed_numbers bit,
    remove_leading_zeros bit,
    user_input_extid character varying(255)
);

CREATE TABLE aehsc.doc_number_range_busobjtype (
	[doc_num_range_id] bigint NOT NULL,
	[bus_obj_type] [varchar](255) NULL
)

CREATE TABLE aehsc.field_def (
    id bigint NOT NULL,
    class_ext_id character varying(255),
    do_not_copy bit,
    field_name character varying(255),
    is_base_class_field bit,
    is_dimension bit,
    is_list bit,
    is_measure bit,
    is_primitive bit,
    is_required bit,
    is_translated bit,
    is_unique bit,
    label character varying(255),
    no_history bit,
    reference_key character varying(255),
    tooltip character varying(255),
    type character varying(255),
    uri character varying(255),
    xtype character varying(255)
);


CREATE TABLE aehsc.job_role (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    description character varying(255),
    ext_id character varying(50),
    text character varying(255),
    type character varying(255),
    start_activity character varying(255)
);


CREATE TABLE aehsc.list_entries (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    entry_code character varying(255) NOT NULL,
    entry_name character varying(255),
    entry_type character varying(255),
    list_code character varying(255)
);

CREATE TABLE aehsc.lists (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    description character varying(255),
    code character varying(255) NOT NULL
);

CREATE TABLE aehsc.menu_item (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    access_level character varying(255),
    activity_id character varying(50),
    external_access bit,
    icon_path character varying(255),
    job_role_id bigint,
    label character varying(255),
    main bit,
    menu_cls character varying(255),
    menu_id character varying(255),
    parent_id character varying(255),
    preferred bit,
    report_id character varying(255),
    screen_control_id character varying(255),
    seq integer,
    submenu_icon_path character varying(255)
);

CREATE TABLE aehsc.message_button (
    id bigint NOT NULL,
    button_handler_api character varying(255),
    clickable_text character varying(255),
    clicked_text character varying(255),
    icon character varying(255),
    message_log_id bigint,
    is_repeat bit,
    seq integer,
    toggle bit
);

CREATE TABLE aehsc.message_button_field_values (
    message_log_button_id bigint NOT NULL,
    field_value character varying(255),
    field_name character varying(255) NOT NULL
);

CREATE TABLE aehsc.message_log (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    bus_obj_cat character varying(255),
    bus_obj_id bigint,
    ext_id character varying(50),
    text character varying(255),
    type character varying(255),
    admin_message bit,
    body varchar(max),
    conversation_id character varying(255),
    form bit,
    message_master_id character varying(255),
    priority character varying(255),
    subject character varying(255)
);

CREATE TABLE aehsc.message_recipient (
    id bigint NOT NULL,
    message_log_id bigint,
    read_on datetime2,
    responded_on datetime2,
    score_response integer,
    seq integer,
    user_id character varying(255)
);

CREATE TABLE aehsc.message_type (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    description character varying(200),
    doc_number character varying(50),
    ext_id character varying(50),
    text character varying(50)
);


CREATE TABLE aehsc.mitigation_control (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    description character varying(255),
    ext_id character varying(50),
    text character varying(255),
    type character varying(255),
    validity_days integer
);


CREATE TABLE aehsc.mitigation_control_owner (
    id bigint NOT NULL,
    mitigation_control_id bigint,
    owner_id character varying(50)
);

CREATE TABLE aehsc.mitigation_control_owner_group (
    id bigint NOT NULL,
    mitigation_control_id bigint,
    owner_id character varying(50)
);

CREATE TABLE aehsc.mitigation_control_type (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    description character varying(200),
    doc_number character varying(50),
    ext_id character varying(50),
    text character varying(50)
);


CREATE TABLE aehsc.policy (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    description character varying(255),
    ext_id character varying(50),
    text character varying(255),
    type character varying(255),
    enforcement character varying(255),
    owner bigint,
    policygroup character varying(255),
    priority integer
);


CREATE TABLE aehsc.policy_group (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    description character varying(200),
    doc_number character varying(50),
    ext_id character varying(50),
    text character varying(50)
);


CREATE TABLE aehsc.policy_rule_set_xref (
    policy_id bigint NOT NULL,
    rule_set_id bigint NOT NULL
);


CREATE TABLE aehsc.policy_type (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    description character varying(200),
    doc_number character varying(50),
    ext_id character varying(255),
    text character varying(50)
);


CREATE TABLE aehsc.response_code (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    description character varying(255),
    ext_id character varying(50),
    text character varying(255),
    type character varying(255),
    response_action character varying(255),
    response_action_type character varying(255)
);


CREATE TABLE aehsc.response_code_type (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    description character varying(200),
    doc_number character varying(50),
    ext_id character varying(50),
    text character varying(50)
);


CREATE TABLE aehsc.risk (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    description character varying(255),
    ext_id character varying(50),
    text character varying(255),
    type character varying(255),
    assign_default bit,
    currency character varying(255),
    impact numeric(19,2),
    severity character varying(255)
);


CREATE TABLE aehsc.risk_mitigation_control_xref (
    risk_id bigint NOT NULL,
    mitigation_id bigint NOT NULL
);


CREATE TABLE aehsc.risk_type (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    description character varying(200),
    doc_number character varying(50),
    ext_id character varying(50),
    text character varying(50)
);

CREATE TABLE aehsc.rule_response_code_xref (
    rule_id bigint NOT NULL,
    response_code_id bigint NOT NULL
);


CREATE TABLE aehsc.rule_risk_xref (
    rule_id bigint NOT NULL,
    risk_id bigint NOT NULL
);

CREATE TABLE aehsc.rule_set (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    description character varying(255),
    ext_id character varying(50),
    text character varying(255),
    type character varying(255)
);


CREATE TABLE aehsc.rule_set_rule_xref (
    rule_set_id bigint NOT NULL,
    rule_id bigint NOT NULL
);


CREATE TABLE aehsc.rule_set_type (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    description character varying(200),
    doc_number character varying(50),
    ext_id character varying(50),
    text character varying(50)
);


CREATE TABLE aehsc.rule_type (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    description character varying(200),
    doc_number character varying(50),
    ext_id character varying(50),
    text character varying(50)
);


CREATE TABLE aehsc.rules (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    description character varying(255),
    ext_id character varying(50),
    text character varying(255),
    type character varying(255),
    action character varying(2000),
    advancebuilder bit,
    condition character varying(2000),
    condition_json character varying(2000),
    enforcement character varying(255),
    entity_id character varying(50),
    namespace character varying(255),
    priority integer,
    response_code_id character varying(255),
    risk_id character varying(255)
);


CREATE TABLE aehsc.search_layout (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    description character varying(200),
    doc_number character varying(50),
    ext_id character varying(50),
    text character varying(50),
    activity_id character varying(50),
    details varchar(max),
    download_template character varying(255),
    expand_all bit,
    group_by character varying(255),
    preferred bit,
    rule_criteria varchar(max),
    shared bit,
    sort_by character varying(255),
    view_id character varying(255)
);


CREATE TABLE aehsc.timeline (
    id bigint NOT NULL,
    busobjcat character varying(255),
    busobjid bigint,
    createdby bigint,
    createdon datetime2,
    ipaddress character varying(255),
    ismobile bit,
    reason character varying(500),
    sessionid character varying(255),
    timelinetype character varying(255)
);


CREATE TABLE aehsc.timeline_chgs (
    id bigint NOT NULL,
    field character varying(255),
    newvalue character varying(255),
    oldvalue character varying(255),
    subdockey character varying(255),
    subtype character varying(255),
    timelineid bigint
);


CREATE TABLE aehsc.user_job_role (
    user_id bigint NOT NULL,
    job_role_id bigint NOT NULL
);


CREATE TABLE aehsc.users (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    date_format character varying(255),
    first_name character varying(255),
    keep_messages bit,
    last_name character varying(255),
    locale_code character varying(255),
    number_format character varying(255),
    password character varying(255),
    password_change_required bit,
    primary_email_address character varying(255),
    primary_phone character varying(255),
    reset_password bit,
    start_activity character varying(255),
    start_last_activity bit,
    time_format character varying(255),
    time_zone character varying(255),
    user_name character varying(255),
    user_type character varying(255)
);


CREATE TABLE aehsc.workflow (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2,
    created_by bigint,
    created_on datetime2,
    int_status integer,
    business_object_category character varying(255),
    business_object_id bigint,
    display_template bit,
    no_connections bit,
    owner_id bigint,
    sub_item_start character varying(255),
    template bit,
    text character varying(255),
    workflow_code character varying(255)
);


CREATE TABLE aehsc.workflow_bus_obj_types (
    workflow_id bigint NOT NULL,
    bus_obj_type character varying(255)
);


CREATE TABLE aehsc.workflow_condition (
    id bigint NOT NULL,
    condition_api character varying(255),
    field_name character varying(255),
    is_or bit,
    operator character varying(255),
    value character varying(255),
    workflow_connection_id bigint
);


CREATE TABLE aehsc.workflow_connection (
    id bigint NOT NULL,
    preferred bit,
    response character varying(255),
    source character varying(255),
    target character varying(255),
    workflow_id bigint NOT NULL
);


CREATE TABLE aehsc.workflow_step (
    id bigint NOT NULL,
    action_message_code character varying(255),
    actual_end datetime2,
    actual_start datetime2,
    all_owners bit,
    bus_obj_cat_status character varying(255),
    color character varying(255),
    comment_required bit,
    completed_by bigint,
    current_close_count integer,
    is_end_step bit,
    entry_api character varying(255),
    exit_api character varying(255),
    form_message_id character varying(255),
    forward bit,
    forwarded_to bigint,
    from_date datetime2,
    is_generated bit,
    icon_id character varying(255),
    int_status integer,
    label character varying(255),
    lead_time integer,
    milestone character varying(255),
    milestone_description character varying(255),
    notification_message_code character varying(255),
    parallel_close_count integer,
    parallel_field character varying(255),
    parallel_id bigint,
    parallel_tasks character varying(255),
    planned_end datetime2,
    planned_start datetime2,
    previous_step character varying(255),
    prior_step_comments character varying(255),
    reminder_message_code character varying(255),
    num_repeat integer,
    return_step bit,
    seq integer,
    skip_previous bit,
    is_start_step bit,
    started_by bigint,
    system_status integer,
    text character varying(255),
    type character varying(255),
    workflow_step_code character varying(20),
    workflow_id bigint NOT NULL
);


CREATE TABLE aehsc.workflow_step_access_roles (
    workflow_step_id bigint NOT NULL,
    role character varying(255)
);


CREATE TABLE aehsc.workflow_step_attributes (
    workflow_step_id bigint NOT NULL,
    value character varying(255),
    name character varying(255) NOT NULL
);


CREATE TABLE aehsc.workflow_step_field_values (
    workflow_step_id bigint NOT NULL,
    field_value character varying(255),
    field_name character varying(255) NOT NULL
);




CREATE TABLE aehsc.workflow_step_messages (
    workflow_step_id bigint NOT NULL,
    message_id bigint
);



CREATE TABLE aehsc.workflow_step_notification_recipients (
    workflow_step_id bigint NOT NULL,
    notification_recipient character varying(255)
);
 
CREATE TABLE aehsc.workflow_step_reminder_recipients (
    workflow_step_id bigint NOT NULL,
    reminder_recipient character varying(255)
);


CREATE TABLE aehsc.workflow_step_users (
    workflow_step_id bigint NOT NULL,
    user_name character varying(255)
);


CREATE TABLE aehsc.workflow_step_workflow_recipient_xref (
    id bigint NOT NULL,
    seq integer,
    workflow_step_recipient_id bigint NOT NULL,
    workflow_step_id bigint NOT NULL
);


CREATE TABLE aehsc.app_log (
    id bigint NOT NULL,
    changed_by bigint NULL,
    changed_on datetime2 NULL,
    created_by bigint NULL,
    created_on datetime2 NULL,
    int_status int NULL,
    bus_obj_cat varchar(255) NULL,
    bus_obj_id bigint NULL,
    ext_id varchar(50) NULL,
    "text" varchar(255) NULL,
    "type" varchar(255) NULL,
    admin_message bit NULL,
    body varchar(max) NULL,
    conversation_id varchar(255) NULL,
    form bit NULL,
    message_master_id varchar(255) NULL,
    priority varchar(255) NULL,
    subject varchar(255) NULL,
    stage varchar(255) NULL
);


CREATE TABLE aehsc.tenant (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on datetime2 NULL,
    created_by bigint,
    created_on datetime2 NULL,
    int_status int,
    db_schema character varying(255),
    domain character varying(255),
    persistence_unit_name character varying(255),
    tenant_id bigint,
    tenant_name character varying(255)
);

CREATE VIEW [aehsc].[TIMELINE_UI] ( "id",
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
	 
	timeline_chgs.id as "id",
	 timeline_chgs.subType as "eventtype",
	 timeline_chgs.subType as "eventtypetext",
	 timeline.busObjCat as "busobjcat",
	 timeline.busObjID as "busobjid",
	 timeline_chgs.field as "field",
	 field_def.label as "fieldname",
	 field_def.tooltip as "fieldtooltip",
	 timeline_chgs.oldValue as "oldvalue",
	 timeline_chgs.newValue as "newvalue",
	 timeline.createdBy as "user_id",
	 users.first_name as "username",
	 timeline.createdOn as "createdon" ,
	 0 as int_status 
from aehsc.timeline_chgs as timeline_chgs
left outer join aehsc.timeline as timeline on timeline.id = timeline_chgs.timelineid 
left join aehsc.users as  users on users.id = timeline.createdBy 
left join aehsc.field_def as field_def on field_def.field_name = timeline_chgs.field ;


ALTER TABLE aehsc.activity
    ADD CONSTRAINT activity_pkey PRIMARY KEY (id);


ALTER TABLE aehsc.api_service_parameter
    ADD CONSTRAINT api_service_parameter_pkey PRIMARY KEY (id);



ALTER TABLE aehsc.api_service
    ADD CONSTRAINT api_service_pkey PRIMARY KEY (id);



ALTER TABLE aehsc.attachment
    ADD CONSTRAINT attachment_pkey PRIMARY KEY (id);



ALTER TABLE aehsc.attachment_type
    ADD CONSTRAINT attachment_type_pkey PRIMARY KEY (id);


ALTER TABLE aehsc.binary_resource
    ADD CONSTRAINT binary_resource_pkey PRIMARY KEY (id);



ALTER TABLE aehsc.class_def
    ADD CONSTRAINT class_def_pkey PRIMARY KEY (id);


ALTER TABLE aehsc.config_setting
    ADD CONSTRAINT config_setting_pkey PRIMARY KEY (id);



ALTER TABLE aehsc.currency
    ADD CONSTRAINT currency_pkey PRIMARY KEY (id);


ALTER TABLE aehsc.doc_number
    ADD CONSTRAINT doc_number_pkey PRIMARY KEY (id);


ALTER TABLE aehsc.doc_number_range
    ADD CONSTRAINT doc_number_range_pkey PRIMARY KEY (id);


ALTER TABLE aehsc.field_def
    ADD CONSTRAINT field_def_pkey PRIMARY KEY (id);



ALTER TABLE aehsc.job_role
    ADD CONSTRAINT job_role_pkey PRIMARY KEY (id);


ALTER TABLE aehsc.list_entries
    ADD CONSTRAINT list_entries_pkey PRIMARY KEY (id);


ALTER TABLE aehsc.lists
    ADD CONSTRAINT lists_pkey PRIMARY KEY (id);



ALTER TABLE aehsc.menu_item
    ADD CONSTRAINT menu_item_pkey PRIMARY KEY (id);


ALTER TABLE aehsc.message_button_field_values
    ADD CONSTRAINT message_button_field_values_pkey PRIMARY KEY (message_log_button_id, field_name);


ALTER TABLE aehsc.message_button
    ADD CONSTRAINT message_button_pkey PRIMARY KEY (id);


ALTER TABLE aehsc.message_log
    ADD CONSTRAINT message_log_pkey PRIMARY KEY (id);


ALTER TABLE aehsc.message_recipient
    ADD CONSTRAINT message_recipient_pkey PRIMARY KEY (id);


ALTER TABLE aehsc.message_type
    ADD CONSTRAINT message_type_pkey PRIMARY KEY (id);



ALTER TABLE aehsc.mitigation_control_owner_group
    ADD CONSTRAINT mitigation_control_owner_group_pkey PRIMARY KEY (id);



ALTER TABLE aehsc.mitigation_control_owner
    ADD CONSTRAINT mitigation_control_owner_pkey PRIMARY KEY (id);


ALTER TABLE aehsc.mitigation_control
    ADD CONSTRAINT mitigation_control_pkey PRIMARY KEY (id);


ALTER TABLE aehsc.mitigation_control_type
    ADD CONSTRAINT mitigation_control_type_pkey PRIMARY KEY (id);



ALTER TABLE aehsc.policy_group
    ADD CONSTRAINT policy_group_pkey PRIMARY KEY (id);


ALTER TABLE aehsc.policy
    ADD CONSTRAINT policy_pkey PRIMARY KEY (id);




ALTER TABLE aehsc.policy_type
    ADD CONSTRAINT policy_type_pkey PRIMARY KEY (id);


ALTER TABLE aehsc.response_code
    ADD CONSTRAINT response_code_pkey PRIMARY KEY (id);



ALTER TABLE aehsc.response_code_type
    ADD CONSTRAINT response_code_type_pkey PRIMARY KEY (id);



ALTER TABLE aehsc.risk
    ADD CONSTRAINT risk_pkey PRIMARY KEY (id);



ALTER TABLE aehsc.risk_type
    ADD CONSTRAINT risk_type_pkey PRIMARY KEY (id);




ALTER TABLE aehsc.rule_set
    ADD CONSTRAINT rule_set_pkey PRIMARY KEY (id);



ALTER TABLE aehsc.rule_set_type
    ADD CONSTRAINT rule_set_type_pkey PRIMARY KEY (id);


ALTER TABLE aehsc.rule_type
    ADD CONSTRAINT rule_type_pkey PRIMARY KEY (id);



ALTER TABLE aehsc.rules
    ADD CONSTRAINT rules_pkey PRIMARY KEY (id);



ALTER TABLE aehsc.search_layout
    ADD CONSTRAINT search_layout_pkey PRIMARY KEY (id);


ALTER TABLE aehsc.timeline_chgs
    ADD CONSTRAINT timeline_chgs_pkey PRIMARY KEY (id);


ALTER TABLE aehsc.timeline
    ADD CONSTRAINT timeline_pkey PRIMARY KEY (id);

ALTER TABLE aehsc.activity
    ADD CONSTRAINT uk_activity UNIQUE (ext_id);




ALTER TABLE aehsc.users
    ADD CONSTRAINT uk_user UNIQUE (user_name);



ALTER TABLE aehsc.attachment_type
    ADD CONSTRAINT uk_attachment_type UNIQUE (ext_id);




ALTER TABLE aehsc.list_entries
    ADD CONSTRAINT uk_list_entries UNIQUE (entry_code);



ALTER TABLE aehsc.lists
    ADD CONSTRAINT uk_list UNIQUE (code);



ALTER TABLE aehsc.class_def
    ADD CONSTRAINT uk_class_def UNIQUE (ext_id);


ALTER TABLE aehsc.menu_item
    ADD CONSTRAINT uk_menu_item UNIQUE (menu_id);



ALTER TABLE aehsc.policy_type
    ADD CONSTRAINT uk_policy_type UNIQUE (ext_id);



ALTER TABLE aehsc.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);



ALTER TABLE aehsc.workflow_condition
    ADD CONSTRAINT workflow_condition_pkey PRIMARY KEY (id);


ALTER TABLE aehsc.workflow_connection
    ADD CONSTRAINT workflow_connection_pkey PRIMARY KEY (id);



ALTER TABLE aehsc.workflow
    ADD CONSTRAINT workflow_pkey PRIMARY KEY (id);



ALTER TABLE aehsc.workflow_step_attributes
    ADD CONSTRAINT workflow_step_attributes_pkey PRIMARY KEY (workflow_step_id, name);



ALTER TABLE aehsc.workflow_step_field_values
    ADD CONSTRAINT workflow_step_field_values_pkey PRIMARY KEY (workflow_step_id, field_name);



ALTER TABLE aehsc.workflow_step
    ADD CONSTRAINT workflow_step_pkey PRIMARY KEY (id);



ALTER TABLE aehsc.workflow_step_workflow_recipient_xref
    ADD CONSTRAINT workflow_step_workflow_recipient_xref_pkey PRIMARY KEY (id);



ALTER TABLE aehsc.workflow_step_access_roles
    ADD CONSTRAINT fk_workflow_step_access_roles__workflow_step FOREIGN KEY (workflow_step_id)  REFERENCES aehsc.workflow_step(id);



ALTER TABLE aehsc.policy
    ADD CONSTRAINT fk_policy__policy_type FOREIGN KEY (type)  REFERENCES aehsc.policy_type(ext_id);



ALTER TABLE aehsc.risk_mitigation_control_xref
    ADD CONSTRAINT fk_risk_mitigation_control_xref__mitigation_control FOREIGN KEY (mitigation_id)  REFERENCES aehsc.mitigation_control(id);




ALTER TABLE aehsc.message_button_field_values
    ADD CONSTRAINT fk_message_button_field_values__message_button FOREIGN KEY (message_log_button_id)  REFERENCES aehsc.message_button(id);


ALTER TABLE aehsc.menu_item
    ADD CONSTRAINT fk_menu_item__job_role FOREIGN KEY (job_role_id)  REFERENCES aehsc.job_role(id);



ALTER TABLE aehsc.user_job_role
    ADD CONSTRAINT fk_user_job_role__job_role FOREIGN KEY (job_role_id)  REFERENCES aehsc.job_role(id);



ALTER TABLE aehsc.workflow_bus_obj_types
    ADD CONSTRAINT fk_workflow_bus_obj_types__workflow FOREIGN KEY (workflow_id)  REFERENCES aehsc.workflow(id);



ALTER TABLE aehsc.rule_risk_xref
    ADD CONSTRAINT fk_rule_risk_xref__rules FOREIGN KEY (rule_id)  REFERENCES aehsc.rules(id);


ALTER TABLE aehsc.timeline_chgs
    ADD CONSTRAINT fk_timeline_chgs__timeline FOREIGN KEY (timelineid)  REFERENCES aehsc.timeline(id);



ALTER TABLE aehsc.workflow_step_attributes
    ADD CONSTRAINT fk_workflow_step_attributes__workflow_step FOREIGN KEY (workflow_step_id)  REFERENCES aehsc.workflow_step(id);


ALTER TABLE aehsc.policy_rule_set_xref
    ADD CONSTRAINT fk_policy_rule_set_xref__rule_set FOREIGN KEY (rule_set_id)  REFERENCES aehsc.rule_set(id);



ALTER TABLE aehsc.message_recipient
    ADD CONSTRAINT fk_message_recipient__message_log FOREIGN KEY (message_log_id)  REFERENCES aehsc.message_log(id);



ALTER TABLE aehsc.response_code
    ADD CONSTRAINT fk_response_code_type__list_entries FOREIGN KEY (response_action_type)  REFERENCES aehsc.list_entries(entry_code);

ALTER TABLE aehsc.response_code
    ADD CONSTRAINT fk_response_code__list_entries FOREIGN KEY (response_action)  REFERENCES aehsc.list_entries(entry_code);

ALTER TABLE aehsc.rule_response_code_xref
    ADD CONSTRAINT fk_rule_response_code_xref__rules FOREIGN KEY (rule_id)  REFERENCES aehsc.rules(id);



ALTER TABLE aehsc.api_service_parameter
    ADD CONSTRAINT fk_api_service_parameter__api_service FOREIGN KEY (api_service_id)  REFERENCES aehsc.api_service(id);



ALTER TABLE aehsc.workflow_step
    ADD CONSTRAINT fk_workflow_step__workflow FOREIGN KEY (workflow_id)  REFERENCES aehsc.workflow(id);


ALTER TABLE aehsc.rule_response_code_xref
    ADD CONSTRAINT fk_rule_response_code_xref__response_code FOREIGN KEY (response_code_id)  REFERENCES aehsc.response_code(id);



ALTER TABLE aehsc.workflow_step_users
    ADD CONSTRAINT fk_workflow_step_users__workflow_step FOREIGN KEY (workflow_step_id)  REFERENCES aehsc.workflow_step(id);



ALTER TABLE aehsc.workflow_step_notification_recipients
    ADD CONSTRAINT fk_workflow_step_notification_recipients__workflow_step FOREIGN KEY (workflow_step_id)  REFERENCES aehsc.workflow_step(id);



ALTER TABLE aehsc.rule_set_rule_xref
    ADD CONSTRAINT fk_rule_set_rule_xref__rules FOREIGN KEY (rule_id)  REFERENCES aehsc.rules(id);



ALTER TABLE aehsc.risk_mitigation_control_xref
    ADD CONSTRAINT fk_risk_mitigation_control_xref__risk FOREIGN KEY (risk_id)  REFERENCES aehsc.risk(id);



ALTER TABLE aehsc.mitigation_control_owner_group
    ADD CONSTRAINT fk_mitigation_control_owner_group__mitigation_control FOREIGN KEY (mitigation_control_id)  REFERENCES aehsc.mitigation_control(id);



ALTER TABLE aehsc.rules
    ADD CONSTRAINT fk_rules__class_def FOREIGN KEY (entity_id)  REFERENCES aehsc.class_def(ext_id);



ALTER TABLE aehsc.workflow_condition
    ADD CONSTRAINT fk_workflow_condition__workflow_connection FOREIGN KEY (workflow_connection_id)  REFERENCES aehsc.workflow_connection(id);


ALTER TABLE aehsc.workflow_step_workflow_recipient_xref
    ADD CONSTRAINT fk_workflow_step_workflow_recipient_xref__workflow_step FOREIGN KEY (workflow_step_id)  REFERENCES aehsc.workflow_step(id);



ALTER TABLE aehsc.user_job_role
    ADD CONSTRAINT fk_user_job_role__users FOREIGN KEY (user_id)  REFERENCES aehsc.users(id);



ALTER TABLE aehsc.rule_risk_xref
    ADD CONSTRAINT fk_rule_risk_xref__risk FOREIGN KEY (risk_id)  REFERENCES aehsc.risk(id);



ALTER TABLE aehsc.menu_item
    ADD CONSTRAINT fk_menu_item__activity FOREIGN KEY (activity_id)  REFERENCES aehsc.activity(ext_id);



ALTER TABLE aehsc.workflow_connection
    ADD CONSTRAINT fk_workflow_connection__workflow FOREIGN KEY (workflow_id)  REFERENCES aehsc.workflow(id);


ALTER TABLE aehsc.workflow_step_reminder_recipients
    ADD CONSTRAINT fk_workflow_step_reminder_recipients__workflow_step FOREIGN KEY (workflow_step_id)  REFERENCES aehsc.workflow_step(id);



ALTER TABLE aehsc.workflow_step_messages
    ADD CONSTRAINT fk_workflow_step_messages__workflow_step FOREIGN KEY (workflow_step_id)  REFERENCES aehsc.workflow_step(id);



ALTER TABLE aehsc.mitigation_control_owner
    ADD CONSTRAINT fk_mitigation_control_owner__mitigation_control FOREIGN KEY (mitigation_control_id)  REFERENCES aehsc.mitigation_control(id);



ALTER TABLE aehsc.workflow_step_field_values
    ADD CONSTRAINT fk_workflow_step_field_values__workflow_step FOREIGN KEY (workflow_step_id)  REFERENCES aehsc.workflow_step(id);



ALTER TABLE aehsc.policy
    ADD CONSTRAINT fk_policy__users FOREIGN KEY (owner)  REFERENCES aehsc.users(id);




ALTER TABLE aehsc.rule_set_rule_xref
    ADD CONSTRAINT fk_rule_set_rule_xref__rule_set FOREIGN KEY (rule_set_id)  REFERENCES aehsc.rule_set(id);



ALTER TABLE aehsc.policy_rule_set_xref
    ADD CONSTRAINT fk_policy_rule_set_xref__policy FOREIGN KEY (policy_id)  REFERENCES aehsc.policy(id);



ALTER TABLE aehsc.attachment
    ADD CONSTRAINT fk_attachment__attachment_type FOREIGN KEY (type)  REFERENCES aehsc.attachment_type(ext_id);


ALTER TABLE aehsc.app_log
    ADD CONSTRAINT app_log_pkey PRIMARY KEY (id);



ALTER TABLE aehsc.doc_number_range_busobjtype
    ADD CONSTRAINT fk_doc_number_range_busobjtype__doc_number_range FOREIGN KEY (doc_num_range_id)  REFERENCES aehsc.doc_number_range(id);


ALTER TABLE aehsc.tenant
    ADD CONSTRAINT tenant_pkey PRIMARY KEY (id);