-- GAL Gestiune Asociatii Locatari
-- init_gal_base.sql

drop table gal.multi_lng;
drop table gal.entman;
drop table gal.user;
drop table gal.rule;
drop table gal.stt;
drop table gal.type;
drop table gal.lng;
drop table gal.role;
drop table gal.user_role;




-- gal.entman -----------------------------------------------
create table if not exists gal.entman
(
    id        serial
        constraint entman_pk
            primary key,
    table_name varchar

);
comment on table gal.entman is 'table names to map multi_lng';
create unique index entman__uindex on gal.entman (table_name);


-- gal.type ----------------------------------------------
create table gal.type
(
    id    serial  not null
        constraint type_pk
            primary key ,
    name varchar not null
);
comment on table gal.type is 'type of state';
alter table gal.type owner to postgres;
create unique index type_name__uindex on gal.type (name);

insert into gal.type(name) values('general');
insert into gal.type(name) values('user');
insert into gal.type(name) values('aso');
insert into gal.type(name) values('rule');

-- gal.stt ----------------------------------------------
create table gal.stt
(
    id    serial  not null
        constraint stt_pk
            primary key ,
    name varchar not null,
    id_type integer not null
        constraint stt_type_id_fk
            references gal.type
);
comment on table gal.stt is 'state';
alter table gal.stt owner to postgres;
create unique index stt_name__uindex on gal.stt (name);

insert into gal.stt(name, id_type) values('active', 1);
insert into gal.stt(name, id_type) values('inactive', 1);
insert into gal.stt(name, id_type) values('deleted', 1);

-- gal.lng -----------------------------------------------
create table gal.lng
(
    id    serial  not null
        constraint lng_pk
            primary key,
    abbrv varchar not null,
    name varchar not null,
    image bytea
);
comment on table gal.lng is 'language';
alter table gal.lng owner to postgres;
create unique index lng_name__uindex on gal.lng (name);

insert into gal.lng(abbrv, name) values('en', 'english');
insert into gal.lng(abbrv, name) values('de', 'deutsch');
insert into gal.lng(abbrv, name) values('ro', 'romana');

-- gal.multi_lng -----------------------------------------------
create table if not exists gal.multi_lng
(
    id        serial
        constraint multi_lng_pk
            primary key,
    base_name varchar,
    lng_name  varchar,
    id_entman integer not null
        constraint multi_lng_entman_id_fk
            references gal.entman,
    id_lng integer not null
        constraint multi_lng_lng_id_fk
            references gal.lng
);
comment on table gal.multi_lng is 'map english name to other language';
create unique index multi_lng__uindex on gal.multi_lng (base_name, id_entman, id_lng);

-- gal.role -----------------------------------------------
create table gal.role
(
    id          serial  not null
        constraint role_pk
            primary key,
    name       varchar not null,
    description varchar
);
comment on table gal.role is 'role';
alter table gal.role owner to postgres;
create unique index role_name__uindex on gal.role (name);

insert into gal.role(name, description) values('ADMIN_SYS', 'System Administrator');
insert into gal.role(name, description) values('ADMIN_ASO', 'Association Administrator');
insert into gal.role(name, description) values('LOCATAR', 'Locatar');
insert into gal.role(name, description) values('USER', 'User');
insert into gal.role(name, description) values('PROP', 'Proprietar');

-- gal.rule -----------------------------------------------
create table gal.rule
(
    id          serial  not null
        constraint rule_pk
            primary key,
    name       varchar not null,
    description varchar
);
comment on table gal.rule is 'rule';
alter table gal.rule owner to postgres;
create unique index rule_name__uindex on gal.rule (name);

-- gal.user -----------------------------------------------
create table gal.user
(
    id          serial  not null
        constraint user_pk
            primary key,
    username       varchar not null,
    password       varchar not null,
    email          varchar, --not null,
    inserted    date    not null,
    updated     date,
    id_lng      integer not null
        constraint user_lng_id_fk
            references gal.lng,
    id_stt      integer not null
        constraint user_stt_id_fk
            references gal.stt
);
comment on table gal.user is 'user';
alter table gal.user owner to postgres;
create unique index user_username__uindex on gal.user (username);
create unique index user_email__uindex on gal.user (email);

-- gal.user_role -----------------------------------------------
create table gal.user_role
(
    id          serial  not null
        constraint user_role_pk
            primary key,
    id_user     integer not null
        constraint user_role_user_id_fk
            references gal.user,
    id_role     integer not null
        constraint user_role_role_id_fk
            references gal.role,
    inserted    date    not null,
    updated     date,
    id_stt      integer not null
        constraint user_role_stt_id_fk
            references gal.stt
);
comment on table gal.user_role is 'user role map';
alter table gal.user_role owner to postgres;
create unique index user_role__uindex on gal.user_role (id_user, id_role);

