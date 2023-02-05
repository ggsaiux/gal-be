-- GAL Gestiune Asociatii Locatari
-- init_gal.sql

drop table gal.user;
drop table gal.rule;
drop table gal.stt;
drop table gal.aso;
drop table gal.lng;
drop table gal.role;


-- gal.stt ----------------------------------------------
create table gal.stt
(
    id    serial  not null
        constraint stt_pk
            primary key ,
    name varchar not null
);
comment on table gal.stt is 'state';
alter table gal.stt owner to postgres;
create unique index stt_name__uindex on gal.stt (name);

insert into gal.stt(id, name) values(1, 'active');
insert into gal.stt(id, name) values(2, 'inactive');

-- gal.grp ----------------------------------------------
create table gal.aso
(
    id          serial  not null
        constraint aso_pk
            primary key,
    name      varchar not null,
    description varchar
);
comment on table gal.aso is 'asociation';
alter table gal.aso owner to postgres;
create unique index aso_name__uindex on gal.aso (name);


-- gal.lng -----------------------------------------------
create table gal.lng
(
    id    serial  not null
        constraint lng_pk
            primary key,
    abrv varchar not null,
    name varchar not null,
    image bytea
);
comment on table gal.lng is 'language';
alter table gal.lng owner to postgres;
create unique index lng_name__uindex on gal.lng (name);

insert into gal.lng(id, abbrv, name) values(1, 'en', 'english');
insert into gal.lng(id, abbrv, name) values(2, 'de', 'deutsch');
insert into gal.lng(id, abbrv, name) values(0, 'ro', 'romana');

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

insert into gal.role(id, name, description) values(1, 'ADMIN_SYS', 'System Administrator');
insert into gal.role(id, name, description) values(2, 'ADMIN_ASO', 'Association Administrator');
insert into gal.role(id, name, description) values(3, 'LOCATAR', 'Locatar');
insert into gal.role(id, name, description) values(3, 'USER', 'User');

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
    email          varchar not null,
    inserted    date    not null,
    updated     date,
    id_role     integer not null
        constraint user_role_id_fk
            references gal.role,
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
    id_stt      integer not null
        constraint user_stt_id_fk
            references gal.stt
);
comment on table gal.user_role is 'user role map';
alter table gal.user_role owner to postgres;
create unique index user_role__uindex on gal.user_role (id_user, id_role);




