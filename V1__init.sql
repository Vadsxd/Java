drop table if exists "user" cascade;

create table "user"
(
    "user_id" bigserial,
    primary key ("user_id"),
    "etu_id"  bigint
);

drop table if exists "role" cascade;

create table "role"
(
    "role_id" bigserial,
    primary key ("role_id"),
    "first_name"  varchar(255) not null
);

drop table if exists "result_state" cascade;

create table "result_state"
(
    "result_state_id" bigserial,
    primary key ("result_state_id"),
    "state"  varchar(255) not null
);

drop table if exists "course_state" cascade;

create table "course_state"
(
    "course_state_id" bigserial,
    primary key ("course_state_id"),
    "state"  varchar(255) not null
);

drop table if exists "lesson_state" cascade;

create table "lesson_state"
(
    "state_id" bigserial,
    primary key ("state_id"),
    "name"  varchar(255) not null
);

drop table if exists "course_template" cascade;

create table "course_template"
(
    "course_template_id" bigserial,
    primary key ("course_template_id"),
    "title"  varchar(255) not null,
    "description" varchar(255) not null,
    "is_active" boolean
);

alter table if exists "block" drop constraint if exists "fk_template_id";
drop table if exists "block" cascade;

create table "block"
(
    "block_id" bigserial,
    primary key ("block_id"),
    "template_id"  bigint,
    constraint "fk_template_id" foreign key ("template_id") references "course_template"("course_template_id"),
    "description"  varchar(255) not null,
    "ordinal" bigint
);

alter table if exists "user_profile" drop constraint if exists "fk_user_id";
drop table if exists "user_profile" cascade;

create table "user_profile"
(
    "user_id"     bigint,
    primary key ("user_id"),
    constraint "fk_user_id" foreign key ("user_id") references "user"("user_id"),
    "first_name"  varchar(255) not null,
    "second_name" varchar(255) not null,
    "photo_url"   varchar(255) not null,
    "edu_group"   bigint,
    "about"       varchar(255) not null
);

alter table if exists "user_role" drop constraint if exists "fk_user_id";
alter table if exists "user_role" drop constraint if exists "fk_role_id";
drop table if exists "user_role" cascade;

create table "user_role"
(
    "user_id" bigint,
    constraint "fk_user_id" foreign key ("user_id") references "user"("user_id"),
    "role_id" bigint,
    constraint "fk_role_id" foreign key ("role_id") references "role"("role_id")
);

alter table if exists "course" drop constraint if exists "fk_template_id";
alter table if exists "course" drop constraint if exists "fk_state_id";
drop table if exists "course" cascade;

create table "course"
(
    "course_id" bigserial,
    primary key ("course_id"),
    "template_id" bigint,
    constraint "fk_template_id" foreign key ("template_id") references "course_template"("course_template_id"),
    "state_id" bigint,
    constraint "fk_state_id" foreign key ("state_id") references "course_state"("course_state_id"),
    "registration_start" timestamp with time zone,
    "registration_end" timestamp with time zone,
    "start_date" timestamp with time zone,
    "icon_url" varchar(255) not null,
    "is_hidden" boolean
);

alter table if exists "user_course" drop constraint if exists "fk_user_id";
alter table if exists "user_course" drop constraint if exists "fk_course_id";
alter table if exists "user_course" drop constraint if exists "fk_result_state_id";
drop table if exists "user_course" cascade;

create table "user_course"
(
    "user_course_id" bigserial,
    primary key ("user_course_id"),
    "user_id" bigint,
    constraint "fk_user_id" foreign key ("user_id") references "user"("user_id"),
    "course_id" bigint,
    constraint "fk_course_id" foreign key ("course_id") references "course"("course_id"),
    "result_state_id" bigint,
    constraint "fk_result_state_id" foreign key ("result_state_id") references "result_state"("result_state_id"),
    "started" date default CURRENT_DATE,
    "feedback" int8
);

alter table if exists "lesson" drop constraint if exists "fk_block_id";
drop table if exists "lesson" cascade;

create table "lesson"
(
    "lesson_id" bigserial,
    primary key ("lesson_id"),
    "title"  varchar(255) not null,
    "block_id" bigint,
    constraint "fk_block_id" foreign key ("block_id") references "block"("block_id"),
    "description"  varchar(255) not null,
    "opens_in_days" bigint,
    "theory_html"  varchar(255) not null,
    "task_html"  varchar(255) not null
);

alter table if exists "task" drop constraint if exists "fk_user_id";
drop table if exists "task" cascade;

create table "task"
(
    "task_id" bigserial,
    primary key ("task_id"),
    "lesson_id" bigint,
    constraint "fk_lesson_id" foreign key ("lesson_id") references "lesson"("lesson_id"),
    "ordinal" bigint,
    "info" bigint
);

alter table if exists "task_answer" drop constraint if exists "fk_task_id";
alter table if exists "task_answer" drop constraint if exists "fk_user_id";
drop table if exists "task_answer" cascade;

create table "task_answer"
(
    "answer_id" bigserial,
    primary key ("answer_id"),
    "answer"  varchar(255) not null,
    "task_id" bigint,
    constraint "fk_task_id" foreign key ("task_id") references "task"("task_id"),
    "user_id" bigint,
    constraint "fk_user_id" foreign key ("user_id") references "user"("user_id")
);

alter table if exists "lesson_result" drop constraint if exists "fk_user_id";
alter table if exists "lesson_result" drop constraint if exists "fk_lesson_id";
alter table if exists "lesson_result" drop constraint if exists "fk_state_id";
drop table if exists "lesson_result" cascade;

create table "lesson_result"
(
    "result_id" bigserial,
    primary key ("result_id"),
    "user_id" bigint,
    constraint "fk_user_id" foreign key ("user_id") references "user"("user_id"),
    "lesson_id" bigint,
    constraint "fk_lesson_id" foreign key ("lesson_id") references "lesson"("lesson_id"),
    "state_id" bigint,
    constraint "fk_state_id" foreign key ("state_id") references "lesson_state"("state_id"),
    "task_score" bigint,
    "speed_score" bigint,
    "completed_on" timestamp with time zone
);

alter table if exists "suggested_answers" drop constraint if exists "fk_task_id";
drop table if exists "suggested_answers" cascade;

create table "suggested_answers"
(
    "suggested_answers_id" bigserial,
    primary key ("suggested_answers_id"),
    "task_id" bigint,
    constraint "fk_task_id" foreign key ("task_id") references "task"("task_id"),
    "answer"  varchar(255) not null,
    "is_right" boolean
);