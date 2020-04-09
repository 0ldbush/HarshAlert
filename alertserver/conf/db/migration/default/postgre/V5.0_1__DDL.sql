
--
-- Name: activity; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE activity (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    description character varying(200),
    doc_number character varying(50),
    ext_id character varying(50),
    text character varying(50),
    bus_function character varying(255),
    bus_obj_cat character varying(255),
    default_mode character varying(255),
    display_only boolean,
    follow_ons character varying(255),
    full_namespace character varying(255),
    help_text_url character varying(255),
    hidden boolean,
    icon character varying(255),
    keywords character varying(255),
    mapped_namespace character varying(255),
    package_name character varying(255),
    setting_act boolean,
    title character varying(255)
);

--
-- Name: api_service; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE api_service (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    api_service_code character varying(255) NOT NULL,
    clazz character varying(255),
    error_message_code character varying(255),
    from_queue_name character varying(255),
    group_name character varying(255),
    match_rule text,
    method character varying(255),
    name character varying(255) NOT NULL,
    seq integer,
    success_message_code character varying(255),
    to_queue_name character varying(255),
    type character varying(255) NOT NULL,
    web_service character varying(255)
);


--
-- Name: api_service_parameter; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE api_service_parameter (
    id bigint NOT NULL,
    constant_value character varying(255),
    field character varying(255) NOT NULL,
    field_type character varying(255) NOT NULL,
    multiple_values boolean,
    seq integer NOT NULL,
    api_service_id bigint NOT NULL
);


--
-- Name: attachment; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE attachment (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    bus_obj_cat character varying(255),
    bus_obj_id bigint,
    ext_id character varying(50),
    text character varying(255),
    type character varying(255),
	mime_type  character varying(255),
    binary_resource_id bigint,
    shared boolean,
    source_file character varying(255),
    thumbnail_id bigint
);


--
-- Name: attachment_type; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE attachment_type (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    description character varying(200),
    doc_number character varying(50),
    ext_id character varying(50),
    text character varying(50)
);


--
-- Name: binary_resource; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE binary_resource (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    file_data oid,
    mime_type character varying(255),
    path character varying(255),
    size bigint,
    thumbnail_path character varying(255),
    title character varying(255),
    type character varying(255)
);


--
-- Name: class_def; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE class_def (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    description character varying(255),
    ext_id character varying(50),
    text character varying(255),
    type character varying(255),
    bus_obj_cat character varying(255),
    class_name character varying(255),
    field_in_parent character varying(255),
    is_rule_parent boolean,
    editable boolean,
    doc_number_supported boolean,
    parent_bus_obj_cat character varying(255),
    ref_field character varying(255)
);


--
-- Name: config_setting; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE config_setting (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    description character varying(255),
    ext_id character varying(50),
    text character varying(255),
    type character varying(255),
    group_name character varying(255),
    name character varying(255) NOT NULL,
    value character varying(255) NOT NULL
);


--
-- Name: currency; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE currency (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
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


--
-- Name: doc_number; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE doc_number (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    doc_number character varying(255),
    fiscal_year integer,
    num_range_id character varying(255),
    range_reset_date timestamp without time zone
);


--
-- Name: doc_number_range; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE doc_number_range (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    description character varying(200),
    doc_number character varying(50),
    ext_id character varying(50),
    text character varying(50),
    bus_obj_cat character varying(255),
    code_edit_allowed boolean,
    company_range boolean,
    duplicates_allowed boolean,
    external_range_end character varying(255),
    external_range_format character varying(255),
    external_range_start character varying(255),
    fiscal_year_range boolean,
    internal_range_end character varying(255),
    internal_range_format character varying(255),
    internal_range_start character varying(255),
    max_length integer,
    no_missed_numbers boolean,
    remove_leading_zeros boolean,
    user_input_extid character varying(255)
);



--
-- Name: doc_number_range_busobjtype; Type: TABLE; Schema: ; Owner: -
--
CREATE TABLE doc_number_range_busobjtype (
	doc_num_range_id bigint NOT NULL,
	bus_obj_type varchar(255) NULL
);



--
-- Name: field_def; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE field_def (
    id bigint NOT NULL,
    class_ext_id character varying(255),
    do_not_copy boolean,
    field_name character varying(255),
    is_base_class_field boolean,
    is_dimension boolean,
    is_list boolean,
    is_measure boolean,
    is_primitive boolean,
    is_required boolean,
    is_translated boolean,
    is_unique boolean,
    label character varying(255),
    no_history boolean,
    reference_key character varying(255),
    tooltip character varying(255),
    type character varying(255),
    uri character varying(255),
    xtype character varying(255)
);


--
-- Name: job_role; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE job_role (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    description character varying(255),
    ext_id character varying(50),
    text character varying(255),
    type character varying(255),
    start_activity character varying(255)
);


--
-- Name: list_entries; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE list_entries (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    entry_code character varying(255) NOT NULL,
    entry_name character varying(255),
    entry_type character varying(255),
    list_code character varying(255)
);


--
-- Name: lists; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE lists (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    description character varying(255),
    code character varying(255) NOT NULL
);


--
-- Name: menu_item; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE menu_item (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    access_level character varying(255),
    activity_id character varying(255),
    external_access boolean,
    icon_path character varying(255),
    job_role_id bigint,
    label character varying(255),
    main boolean,
    menu_cls character varying(255),
    menu_id character varying(255),
    parent_id character varying(255),
    preferred boolean,
    report_id character varying(255),
    screen_control_id character varying(255),
    seq integer,
    submenu_icon_path character varying(255)
);


--
-- Name: message_button; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE message_button (
    id bigint NOT NULL,
    button_handler_api character varying(255),
    clickable_text character varying(255),
    clicked_text character varying(255),
    icon character varying(255),
    message_log_id bigint,
    is_repeat boolean,
    seq integer,
    toggle boolean
);


--
-- Name: message_button_field_values; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE message_button_field_values (
    message_log_button_id bigint NOT NULL,
    field_value character varying(255),
    field_name character varying(255) NOT NULL
);


--
-- Name: message_log; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE message_log (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    bus_obj_cat character varying(255),
    bus_obj_id bigint,
    ext_id character varying(50),
    text character varying(255),
    type character varying(255),
    admin_message boolean,
    body text,
    conversation_id character varying(255),
    form boolean,
    message_master_id character varying(255),
    priority character varying(255),
    subject character varying(255)
);


--
-- Name: message_recipient; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE message_recipient (
    id bigint NOT NULL,
    message_log_id bigint,
    read_on timestamp without time zone,
    responded_on timestamp without time zone,
    score_response integer,
    seq integer,
    user_id character varying(255)
);


--
-- Name: message_type; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE message_type (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    description character varying(200),
    doc_number character varying(50),
    ext_id character varying(50),
    text character varying(50)
);


--
-- Name: mitigation_control; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE mitigation_control (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    description character varying(255),
    ext_id character varying(50),
    text character varying(255),
    type character varying(255),
    validity_days integer
);


--
-- Name: mitigation_control_owner; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE mitigation_control_owner (
    id bigint NOT NULL,
    mitigation_control_id bigint,
    owner_id character varying(50)
);


--
-- Name: mitigation_control_owner_group; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE mitigation_control_owner_group (
    id bigint NOT NULL,
    mitigation_control_id bigint,
    owner_id character varying(50)
);


--
-- Name: mitigation_control_type; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE mitigation_control_type (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    description character varying(200),
    doc_number character varying(50),
    ext_id character varying(50),
    text character varying(50)
);


--
-- Name: policy; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE policy (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
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


--
-- Name: policy_group; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE policy_group (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    description character varying(200),
    doc_number character varying(50),
    ext_id character varying(50),
    text character varying(50)
);


--
-- Name: policy_rule_set_xref; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE policy_rule_set_xref (
    policy_id bigint NOT NULL,
    rule_set_id bigint NOT NULL
);


--
-- Name: policy_type; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE policy_type (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    description character varying(200),
    doc_number character varying(50),
    ext_id character varying(50),
    text character varying(50)
);


--
-- Name: response_code; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE response_code (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    description character varying(255),
    ext_id character varying(50),
    text character varying(255),
    type character varying(255),
    response_action character varying(255),
    response_action_type character varying(255)
);


--
-- Name: response_code_type; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE response_code_type (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    description character varying(200),
    doc_number character varying(50),
    ext_id character varying(50),
    text character varying(50)
);


--
-- Name: risk; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE risk (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    description character varying(255),
    ext_id character varying(50),
    text character varying(255),
    type character varying(255),
    assign_default boolean,
    currency character varying(255),
    impact numeric(19,2),
    severity character varying(255)
);


--
-- Name: risk_mitigation_control_xref; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE risk_mitigation_control_xref (
    risk_id bigint NOT NULL,
    mitigation_id bigint NOT NULL
);


--
-- Name: risk_type; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE risk_type (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    description character varying(200),
    doc_number character varying(50),
    ext_id character varying(50),
    text character varying(50)
);


--
-- Name: rule_response_code_xref; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE rule_response_code_xref (
    rule_id bigint NOT NULL,
    response_code_id bigint NOT NULL
);


--
-- Name: rule_risk_xref; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE rule_risk_xref (
    rule_id bigint NOT NULL,
    risk_id bigint NOT NULL
);


--
-- Name: rule_set; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE rule_set (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    description character varying(255),
    ext_id character varying(50),
    text character varying(255),
    type character varying(255)
);


--
-- Name: rule_set_rule_xref; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE rule_set_rule_xref (
    rule_set_id bigint NOT NULL,
    rule_id bigint NOT NULL
);


--
-- Name: rule_set_type; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE rule_set_type (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    description character varying(200),
    doc_number character varying(50),
    ext_id character varying(50),
    text character varying(50)
);


--
-- Name: rule_type; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE rule_type (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    description character varying(200),
    doc_number character varying(50),
    ext_id character varying(50),
    text character varying(50)
);


--
-- Name: rules; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE rules (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    description character varying(255),
    ext_id character varying(50),
    text character varying(255),
    type character varying(255),
    action character varying(2000),
    advancebuilder boolean,
    condition character varying(2000),
    condition_json character varying(2000),
    enforcement character varying(255),
    entity_id character varying(255),
    namespace character varying(255),
    priority integer,
    response_code_id character varying(255),
    risk_id character varying(255)
);


--
-- Name: search_layout; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE search_layout (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    description character varying(200),
    doc_number character varying(50),
    ext_id character varying(50),
    text character varying(50),
    activity_id character varying(255),
    details text,
    download_template character varying(255),
    expand_all boolean,
    group_by character varying(255),
    preferred boolean,
    rule_criteria text,
    shared boolean,
    sort_by character varying(255),
    view_id character varying(255)
);


--
-- Name: tenant; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE tenant (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    db_schema character varying(255),
    domain character varying(255),
    persistence_unit_name character varying(255),
    tenant_id bigint,
    tenant_name character varying(255)
);


--
-- Name: timeline; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE timeline (
    id bigint NOT NULL,
    busobjcat character varying(255),
    busobjid bigint,
    createdby bigint,
    createdon timestamp without time zone,
    ipaddress character varying(255),
    ismobile boolean,
    reason character varying(500),
    sessionid character varying(255),
    timelinetype character varying(255)
);


--
-- Name: timeline_chgs; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE timeline_chgs (
    id bigint NOT NULL,
    field character varying(255),
    newvalue character varying(255),
    oldvalue character varying(255),
    subdockey character varying(255),
    subtype character varying(255),
    timelineid bigint
);

--
-- Name: user_job_role; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE user_job_role (
    user_id bigint NOT NULL,
    job_role_id bigint NOT NULL
);


--
-- Name: users; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE users (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    date_format character varying(255),
    first_name character varying(255),
    keep_messages boolean,
    last_name character varying(255),
    locale_code character varying(255),
    number_format character varying(255),
    password character varying(255),
    password_change_required boolean,
    primary_email_address character varying(255),
    primary_phone character varying(255),
    reset_password boolean,
    start_activity character varying(255),
    start_last_activity boolean,
    time_format character varying(255),
    time_zone character varying(255),
    user_name character varying(255),
    user_type character varying(255)
);


--
-- Name: workflow; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE workflow (
    id bigint NOT NULL,
    changed_by bigint,
    changed_on timestamp without time zone,
    created_by bigint,
    created_on timestamp without time zone,
    int_status integer,
    business_object_category character varying(255),
    business_object_id bigint,
    display_template boolean,
    no_connections boolean,
    owner_id bigint,
    sub_item_start character varying(255),
    template boolean,
    text character varying(255),
    workflow_code character varying(255)
);


--
-- Name: workflow_bus_obj_types; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE workflow_bus_obj_types (
    workflow_id bigint NOT NULL,
    bus_obj_type character varying(255)
);


--
-- Name: workflow_condition; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE workflow_condition (
    id bigint NOT NULL,
    condition_api character varying(255),
    field_name character varying(255),
    is_or boolean,
    operator character varying(255),
    value character varying(255),
    workflow_connection_id bigint
);


--
-- Name: workflow_connection; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE workflow_connection (
    id bigint NOT NULL,
    preferred boolean,
    response character varying(255),
    source character varying(255),
    target character varying(255),
    workflow_id bigint NOT NULL
);


--
-- Name: workflow_step; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE workflow_step (
    id bigint NOT NULL,
    action_message_code character varying(255),
    actual_end timestamp without time zone,
    actual_start timestamp without time zone,
    all_owners boolean,
    bus_obj_cat_status character varying(255),
    color character varying(255),
    comment_required boolean,
    completed_by bigint,
    current_close_count integer,
    is_end_step boolean,
    entry_api character varying(255),
    exit_api character varying(255),
    form_message_id character varying(255),
    forward boolean,
    forwarded_to bigint,
    from_date timestamp without time zone,
    is_generated boolean,
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
    planned_end timestamp without time zone,
    planned_start timestamp without time zone,
    previous_step character varying(255),
    prior_step_comments character varying(255),
    reminder_message_code character varying(255),
    num_repeat integer,
    return_step boolean,
    seq integer,
    skip_previous boolean,
    is_start_step boolean,
    started_by bigint,
    system_status integer,
    text character varying(255),
    type character varying(255),
    workflow_step_code character varying(20),
    workflow_id bigint NOT NULL
);


--
-- Name: workflow_step_access_roles; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE workflow_step_access_roles (
    workflow_step_id bigint NOT NULL,
    role character varying(255)
);


--
-- Name: workflow_step_attributes; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE workflow_step_attributes (
    workflow_step_id bigint NOT NULL,
    value character varying(255),
    name character varying(255) NOT NULL
);


--
-- Name: workflow_step_field_values; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE workflow_step_field_values (
    workflow_step_id bigint NOT NULL,
    field_value character varying(255),
    field_name character varying(255) NOT NULL
);


--
-- Name: workflow_step_messages; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE workflow_step_messages (
    workflow_step_id bigint NOT NULL,
    message_id bigint
);


--
-- Name: workflow_step_notification_recipients; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE workflow_step_notification_recipients (
    workflow_step_id bigint NOT NULL,
    notification_recipient character varying(255)
);


--
-- Name: workflow_step_reminder_recipients; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE workflow_step_reminder_recipients (
    workflow_step_id bigint NOT NULL,
    reminder_recipient character varying(255)
);


--
-- Name: workflow_step_users; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE workflow_step_users (
    workflow_step_id bigint NOT NULL,
    user_name character varying(255)
);


--
-- Name: workflow_step_workflow_recipient_xref; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE workflow_step_workflow_recipient_xref (
    id bigint NOT NULL,
    seq integer,
    workflow_step_recipient_id bigint NOT NULL,
    workflow_step_id bigint NOT NULL
);

--
-- Name: app_log; Type: TABLE; Schema: ; Owner: -
--

CREATE TABLE app_log (
    id int8 NOT NULL,
    changed_by int8 NULL,
    changed_on timestamp NULL,
    created_by int8 NULL,
    created_on timestamp NULL,
    int_status int4 NULL,
    bus_obj_cat varchar(255) NULL,
    bus_obj_id int8 NULL,
    ext_id varchar(50) NULL,
    "text" varchar(255) NULL,
    "type" varchar(255) NULL,
    admin_message bool NULL,
    body text NULL,
    conversation_id varchar(255) NULL,
    form bool NULL,
    message_master_id varchar(255) NULL,
    priority varchar(255) NULL,
    subject varchar(255) NULL,
    stage varchar(255) NULL
);


--
-- Name: timeline_ui; Type: VIEW; Schema: ; Owner: -
--

CREATE VIEW timeline_ui
 AS
 SELECT concat(timeline.id, timeline_chgs.id) AS id,
    timeline_chgs.subtype AS eventtype,
    timeline_chgs.subtype AS eventtypetext,
    timeline.busobjcat,
    timeline.busobjid,
    timeline_chgs.field,
    field_def.label AS fieldname,
    field_def.tooltip AS fieldtooltip,
    timeline_chgs.oldvalue,
    timeline_chgs.newvalue,
    timeline.createdby AS "user",
    users.first_name AS username,
    timeline.createdon,
    0 AS int_status
   FROM timeline_chgs
     LEFT JOIN timeline ON timeline.id = timeline_chgs.timelineid
     LEFT JOIN users ON users.id = timeline.createdby
     LEFT JOIN field_def ON field_def.field_name::text = timeline_chgs.field::text
  ORDER BY timeline.createdon DESC;


--
-- Name: activity activity_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY activity
    ADD CONSTRAINT activity_pkey PRIMARY KEY (id);


--
-- Name: api_service_parameter api_service_parameter_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY api_service_parameter
    ADD CONSTRAINT api_service_parameter_pkey PRIMARY KEY (id);


--
-- Name: api_service api_service_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY api_service
    ADD CONSTRAINT api_service_pkey PRIMARY KEY (id);


--
-- Name: attachment attachment_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY attachment
    ADD CONSTRAINT attachment_pkey PRIMARY KEY (id);


--
-- Name: attachment_type attachment_type_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY attachment_type
    ADD CONSTRAINT attachment_type_pkey PRIMARY KEY (id);


--
-- Name: binary_resource binary_resource_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY binary_resource
    ADD CONSTRAINT binary_resource_pkey PRIMARY KEY (id);


--
-- Name: class_def class_def_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY class_def
    ADD CONSTRAINT class_def_pkey PRIMARY KEY (id);


--
-- Name: config_setting config_setting_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY config_setting
    ADD CONSTRAINT config_setting_pkey PRIMARY KEY (id);


--
-- Name: currency currency_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY currency
    ADD CONSTRAINT currency_pkey PRIMARY KEY (id);


--
-- Name: doc_number doc_number_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY doc_number
    ADD CONSTRAINT doc_number_pkey PRIMARY KEY (id);


--
-- Name: doc_number_range doc_number_range_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY doc_number_range
    ADD CONSTRAINT doc_number_range_pkey PRIMARY KEY (id);


--
-- Name: field_def field_def_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY field_def
    ADD CONSTRAINT field_def_pkey PRIMARY KEY (id);


--
-- Name: job_role job_role_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY job_role
    ADD CONSTRAINT job_role_pkey PRIMARY KEY (id);


--
-- Name: list_entries list_entries_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY list_entries
    ADD CONSTRAINT list_entries_pkey PRIMARY KEY (id);


--
-- Name: lists lists_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY lists
    ADD CONSTRAINT lists_pkey PRIMARY KEY (id);


--
-- Name: menu_item menu_item_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY menu_item
    ADD CONSTRAINT menu_item_pkey PRIMARY KEY (id);


--
-- Name: message_button_field_values message_button_field_values_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY message_button_field_values
    ADD CONSTRAINT message_button_field_values_pkey PRIMARY KEY (message_log_button_id, field_name);


--
-- Name: message_button message_button_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY message_button
    ADD CONSTRAINT message_button_pkey PRIMARY KEY (id);


--
-- Name: message_log message_log_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY message_log
    ADD CONSTRAINT message_log_pkey PRIMARY KEY (id);


--
-- Name: message_recipient message_recipient_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY message_recipient
    ADD CONSTRAINT message_recipient_pkey PRIMARY KEY (id);


--
-- Name: message_type message_type_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY message_type
    ADD CONSTRAINT message_type_pkey PRIMARY KEY (id);


--
-- Name: mitigation_control_owner_group mitigation_control_owner_group_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY mitigation_control_owner_group
    ADD CONSTRAINT mitigation_control_owner_group_pkey PRIMARY KEY (id);


--
-- Name: mitigation_control_owner mitigation_control_owner_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY mitigation_control_owner
    ADD CONSTRAINT mitigation_control_owner_pkey PRIMARY KEY (id);


--
-- Name: mitigation_control mitigation_control_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY mitigation_control
    ADD CONSTRAINT mitigation_control_pkey PRIMARY KEY (id);


--
-- Name: mitigation_control_type mitigation_control_type_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY mitigation_control_type
    ADD CONSTRAINT mitigation_control_type_pkey PRIMARY KEY (id);


--
-- Name: policy_group policy_group_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY policy_group
    ADD CONSTRAINT policy_group_pkey PRIMARY KEY (id);


--
-- Name: policy policy_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY policy
    ADD CONSTRAINT policy_pkey PRIMARY KEY (id);


--
-- Name: policy_type policy_type_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY policy_type
    ADD CONSTRAINT policy_type_pkey PRIMARY KEY (id);


--
-- Name: response_code response_code_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY response_code
    ADD CONSTRAINT response_code_pkey PRIMARY KEY (id);


--
-- Name: response_code_type response_code_type_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY response_code_type
    ADD CONSTRAINT response_code_type_pkey PRIMARY KEY (id);


--
-- Name: risk risk_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY risk
    ADD CONSTRAINT risk_pkey PRIMARY KEY (id);


--
-- Name: risk_type risk_type_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY risk_type
    ADD CONSTRAINT risk_type_pkey PRIMARY KEY (id);



--
-- Name: rule_set rule_set_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY rule_set
    ADD CONSTRAINT rule_set_pkey PRIMARY KEY (id);


--
-- Name: rule_set_type rule_set_type_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY rule_set_type
    ADD CONSTRAINT rule_set_type_pkey PRIMARY KEY (id);


--
-- Name: rule_type rule_type_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY rule_type
    ADD CONSTRAINT rule_type_pkey PRIMARY KEY (id);


--
-- Name: rules rules_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY rules
    ADD CONSTRAINT rules_pkey PRIMARY KEY (id);


--
-- Name: search_layout search_layout_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY search_layout
    ADD CONSTRAINT search_layout_pkey PRIMARY KEY (id);


--
-- Name: tenant tenant_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY tenant
    ADD CONSTRAINT tenant_pkey PRIMARY KEY (id);


--
-- Name: timeline_chgs timeline_chgs_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY timeline_chgs
    ADD CONSTRAINT timeline_chgs_pkey PRIMARY KEY (id);


--
-- Name: timeline timeline_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY timeline
    ADD CONSTRAINT timeline_pkey PRIMARY KEY (id);


--
-- Name: timeline_ui timeline_ui_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

-- ALTER TABLE ONLY timeline_ui
--     ADD CONSTRAINT timeline_ui_pkey PRIMARY KEY (id);


--
-- Name: activity uk_1e7fkj3jgq2ym0gtvnxi3b2a2; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY activity
    ADD CONSTRAINT uk_1e7fkj3jgq2ym0gtvnxi3b2a2 UNIQUE (ext_id);


--
-- Name: users uk_21q8fvry4wix31petp1awxsx9; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY users
    ADD CONSTRAINT uk_21q8fvry4wix31petp1awxsx9 UNIQUE (user_name);


--
-- Name: attachment_type uk_6rc3rlr4j084lhjk3mp16muc4; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY attachment_type
    ADD CONSTRAINT uk_6rc3rlr4j084lhjk3mp16muc4 UNIQUE (ext_id);


--
-- Name: list_entries uk_7tpstil635r334mqp4xr3jmgf; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY list_entries
    ADD CONSTRAINT uk_7tpstil635r334mqp4xr3jmgf UNIQUE (entry_code);


--
-- Name: lists uk_9nicjhhih88icyjmu5jr6dshj; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY lists
    ADD CONSTRAINT uk_9nicjhhih88icyjmu5jr6dshj UNIQUE (code);


--
-- Name: class_def uk_h111dp3e1aevwnboian96k27a; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY class_def
    ADD CONSTRAINT uk_h111dp3e1aevwnboian96k27a UNIQUE (ext_id);


--
-- Name: menu_item uk_k7as28qhnxxx6ltpb2g8jef2o; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY menu_item
    ADD CONSTRAINT uk_k7as28qhnxxx6ltpb2g8jef2o UNIQUE (menu_id);


--
-- Name: policy_type uk_mox268grnu01vam0y4fupmhul; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY policy_type
    ADD CONSTRAINT uk_mox268grnu01vam0y4fupmhul UNIQUE (ext_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: workflow_condition workflow_condition_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY workflow_condition
    ADD CONSTRAINT workflow_condition_pkey PRIMARY KEY (id);


--
-- Name: workflow_connection workflow_connection_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY workflow_connection
    ADD CONSTRAINT workflow_connection_pkey PRIMARY KEY (id);


--
-- Name: workflow workflow_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY workflow
    ADD CONSTRAINT workflow_pkey PRIMARY KEY (id);


--
-- Name: workflow_step_attributes workflow_step_attributes_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY workflow_step_attributes
    ADD CONSTRAINT workflow_step_attributes_pkey PRIMARY KEY (workflow_step_id, name);


--
-- Name: workflow_step_field_values workflow_step_field_values_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY workflow_step_field_values
    ADD CONSTRAINT workflow_step_field_values_pkey PRIMARY KEY (workflow_step_id, field_name);


--
-- Name: workflow_step workflow_step_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY workflow_step
    ADD CONSTRAINT workflow_step_pkey PRIMARY KEY (id);


--
-- Name: workflow_step_workflow_recipient_xref workflow_step_workflow_recipient_xref_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY workflow_step_workflow_recipient_xref
    ADD CONSTRAINT workflow_step_workflow_recipient_xref_pkey PRIMARY KEY (id);


--
-- Name: workflow_step_access_roles fk1dqv85s6r7328ajtmixn99c5k; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY workflow_step_access_roles
    ADD CONSTRAINT fk1dqv85s6r7328ajtmixn99c5k FOREIGN KEY (workflow_step_id) REFERENCES workflow_step(id);


--
-- Name: policy fk1le4d71fhh2jb84iiqc5o5899; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY policy
    ADD CONSTRAINT fk1le4d71fhh2jb84iiqc5o5899 FOREIGN KEY (type) REFERENCES policy_type(ext_id);


--
-- Name: risk_mitigation_control_xref fk2duiqa5iryo9mq6wi5ri4cqdu; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY risk_mitigation_control_xref
    ADD CONSTRAINT fk2duiqa5iryo9mq6wi5ri4cqdu FOREIGN KEY (mitigation_id) REFERENCES mitigation_control(id);


--
-- Name: message_button_field_values fk377qv54odjoalxrg267llaws; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY message_button_field_values
    ADD CONSTRAINT fk377qv54odjoalxrg267llaws FOREIGN KEY (message_log_button_id) REFERENCES message_button(id);


--
-- Name: menu_item fk3f6ynjoawkoe0rr2q0v0xsy7h; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY menu_item
    ADD CONSTRAINT fk3f6ynjoawkoe0rr2q0v0xsy7h FOREIGN KEY (job_role_id) REFERENCES job_role(id);


--
-- Name: user_job_role fk3h4x6nfeqycyrtoceomrcqk1y; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY user_job_role
    ADD CONSTRAINT fk3h4x6nfeqycyrtoceomrcqk1y FOREIGN KEY (job_role_id) REFERENCES job_role(id);


--
-- Name: workflow_bus_obj_types fk3ijb3af8tr28vryow4d6xkemf; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY workflow_bus_obj_types
    ADD CONSTRAINT fk3ijb3af8tr28vryow4d6xkemf FOREIGN KEY (workflow_id) REFERENCES workflow(id);


--
-- Name: rule_risk_xref fk4d7qh7c7fhd981s4cxtro4ogv; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY rule_risk_xref
    ADD CONSTRAINT fk4d7qh7c7fhd981s4cxtro4ogv FOREIGN KEY (rule_id) REFERENCES rules(id);


--
-- Name: timeline_chgs fk4nv1rl4uwpn0ww19y6lmh79mx; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY timeline_chgs
    ADD CONSTRAINT fk4nv1rl4uwpn0ww19y6lmh79mx FOREIGN KEY (timelineid) REFERENCES timeline(id);


--
-- Name: workflow_step_attributes fk6d9d4nqdqolwfvfyqapyl8goa; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY workflow_step_attributes
    ADD CONSTRAINT fk6d9d4nqdqolwfvfyqapyl8goa FOREIGN KEY (workflow_step_id) REFERENCES workflow_step(id);


--
-- Name: policy_rule_set_xref fk6t9l52ks3d9r4y22r60nkmd0y; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY policy_rule_set_xref
    ADD CONSTRAINT fk6t9l52ks3d9r4y22r60nkmd0y FOREIGN KEY (rule_set_id) REFERENCES rule_set(id);


--
-- Name: message_recipient fk7v27y3d7ox4vl0oftmdj20e1l; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY message_recipient
    ADD CONSTRAINT fk7v27y3d7ox4vl0oftmdj20e1l FOREIGN KEY (message_log_id) REFERENCES message_log(id);


--
-- Name: response_code fk89euh1rdtxq41my34sa35x7uj; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY response_code
    ADD CONSTRAINT fk89euh1rdtxq41my34sa35x7uj FOREIGN KEY (response_action_type) REFERENCES list_entries(entry_code);


--
-- Name: rule_response_code_xref fk8cnfq9x8xn6re8dcdjeip770a; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY rule_response_code_xref
    ADD CONSTRAINT fk8cnfq9x8xn6re8dcdjeip770a FOREIGN KEY (rule_id) REFERENCES rules(id);


--
-- Name: api_service_parameter fka2h8ip6hxrvlw369ihtmdnahg; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY api_service_parameter
    ADD CONSTRAINT fka2h8ip6hxrvlw369ihtmdnahg FOREIGN KEY (api_service_id) REFERENCES api_service(id);


--
-- Name: workflow_step fka6kn9yc75rnu6juvae4yn9jmi; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY workflow_step
    ADD CONSTRAINT fka6kn9yc75rnu6juvae4yn9jmi FOREIGN KEY (workflow_id) REFERENCES workflow(id);


--
-- Name: rule_response_code_xref fkapcaih2tb2iocbe17mj2jwv5v; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY rule_response_code_xref
    ADD CONSTRAINT fkapcaih2tb2iocbe17mj2jwv5v FOREIGN KEY (response_code_id) REFERENCES response_code(id);


--
-- Name: workflow_step_users fkayqr2ynt78o2ehkxa3erg8q04; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY workflow_step_users
    ADD CONSTRAINT fkayqr2ynt78o2ehkxa3erg8q04 FOREIGN KEY (workflow_step_id) REFERENCES workflow_step(id);


--
-- Name: workflow_step_notification_recipients fkchvl74cx8f9i6e49fnufywke5; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY workflow_step_notification_recipients
    ADD CONSTRAINT fkchvl74cx8f9i6e49fnufywke5 FOREIGN KEY (workflow_step_id) REFERENCES workflow_step(id);


--
-- Name: rule_set_rule_xref fkdn5bdc6rpuq7nqp0xvsiwvm7c; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY rule_set_rule_xref
    ADD CONSTRAINT fkdn5bdc6rpuq7nqp0xvsiwvm7c FOREIGN KEY (rule_id) REFERENCES rules(id);


--
-- Name: risk_mitigation_control_xref fke0ea5v25um4q8lajn3cmb0tkf; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY risk_mitigation_control_xref
    ADD CONSTRAINT fke0ea5v25um4q8lajn3cmb0tkf FOREIGN KEY (risk_id) REFERENCES risk(id);


--
-- Name: mitigation_control_owner_group fke8ncq2oosmiuc5artd42r5jun; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY mitigation_control_owner_group
    ADD CONSTRAINT fke8ncq2oosmiuc5artd42r5jun FOREIGN KEY (mitigation_control_id) REFERENCES mitigation_control(id);


--
-- Name: rules fkfuiifvapypy640e199tcslku; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY rules
    ADD CONSTRAINT fkfuiifvapypy640e199tcslku FOREIGN KEY (entity_id) REFERENCES class_def(ext_id);


--
-- Name: response_code fkg0k8xvxlc75rx0q7qbkoq9p58; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY response_code
    ADD CONSTRAINT fkg0k8xvxlc75rx0q7qbkoq9p58 FOREIGN KEY (response_action) REFERENCES list_entries(entry_code);


--
-- Name: workflow_condition fkg0xd0i7n7k7dav9c7sg2fbfv1; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY workflow_condition
    ADD CONSTRAINT fkg0xd0i7n7k7dav9c7sg2fbfv1 FOREIGN KEY (workflow_connection_id) REFERENCES workflow_connection(id);


--
-- Name: workflow_step_workflow_recipient_xref fkg99xo6wdx4kuseafxed7dna0g; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY workflow_step_workflow_recipient_xref
    ADD CONSTRAINT fkg99xo6wdx4kuseafxed7dna0g FOREIGN KEY (workflow_step_id) REFERENCES workflow_step(id);


--
-- Name: user_job_role fkgyelwok4ao2yff7wky3g3pp72; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY user_job_role
    ADD CONSTRAINT fkgyelwok4ao2yff7wky3g3pp72 FOREIGN KEY (user_id) REFERENCES users(id);


--
-- Name: rule_risk_xref fkh2ngql718po13l4nc4vaoqtb6; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY rule_risk_xref
    ADD CONSTRAINT fkh2ngql718po13l4nc4vaoqtb6 FOREIGN KEY (risk_id) REFERENCES risk(id);


--
-- Name: menu_item fkh956pnxs322msjtemtgve8qoj; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY menu_item
    ADD CONSTRAINT fkh956pnxs322msjtemtgve8qoj FOREIGN KEY (activity_id) REFERENCES activity(ext_id);


--
-- Name: workflow_connection fkhas7kt4xylnvayj5uneqhppi3; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY workflow_connection
    ADD CONSTRAINT fkhas7kt4xylnvayj5uneqhppi3 FOREIGN KEY (workflow_id) REFERENCES workflow(id);


--
-- Name: workflow_step_reminder_recipients fkjk03gft4gsrj6edrwstqmu3lu; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY workflow_step_reminder_recipients
    ADD CONSTRAINT fkjk03gft4gsrj6edrwstqmu3lu FOREIGN KEY (workflow_step_id) REFERENCES workflow_step(id);


--
-- Name: workflow_step_messages fknvy89i9afwneupor6ch9qdmpr; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY workflow_step_messages
    ADD CONSTRAINT fknvy89i9afwneupor6ch9qdmpr FOREIGN KEY (workflow_step_id) REFERENCES workflow_step(id);


--
-- Name: mitigation_control_owner fkoaayhmx74ud1hfqslu309a0ll; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY mitigation_control_owner
    ADD CONSTRAINT fkoaayhmx74ud1hfqslu309a0ll FOREIGN KEY (mitigation_control_id) REFERENCES mitigation_control(id);


--
-- Name: workflow_step_field_values fkpig68ui1gh48hq8fotyobwfxf; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY workflow_step_field_values
    ADD CONSTRAINT fkpig68ui1gh48hq8fotyobwfxf FOREIGN KEY (workflow_step_id) REFERENCES workflow_step(id);


--
-- Name: policy fkrcakc1advm60cshh9id2jws95; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY policy
    ADD CONSTRAINT fkrcakc1advm60cshh9id2jws95 FOREIGN KEY (owner) REFERENCES users(id);


--
-- Name: rule_set_rule_xref fkstk5xymi66pqqqk28s38m6ox1; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY rule_set_rule_xref
    ADD CONSTRAINT fkstk5xymi66pqqqk28s38m6ox1 FOREIGN KEY (rule_set_id) REFERENCES rule_set(id);


--
-- Name: policy_rule_set_xref fksun6f8ix3ceuikqaes61wbg78; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY policy_rule_set_xref
    ADD CONSTRAINT fksun6f8ix3ceuikqaes61wbg78 FOREIGN KEY (policy_id) REFERENCES policy(id);


--
-- Name: attachment fksxlgw8vqpwi8oo5yk0e0dysod; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY attachment
    ADD CONSTRAINT fksxlgw8vqpwi8oo5yk0e0dysod FOREIGN KEY (type) REFERENCES attachment_type(ext_id);

--
-- Name: app_log app_log_pkey; Type: CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY app_log
    ADD CONSTRAINT app_log_pkey PRIMARY KEY (id);


--
-- Name: doc_number_range_busobjtype FKcqgwg7s6sir1tmw9lt9i91m0s; Type: FK CONSTRAINT; Schema: ; Owner: -
--

ALTER TABLE ONLY doc_number_range_busobjtype
    ADD CONSTRAINT FKcqgwg7s6sir1tmw9lt9i91m0s FOREIGN KEY (doc_num_range_id) REFERENCES doc_number_range(id);