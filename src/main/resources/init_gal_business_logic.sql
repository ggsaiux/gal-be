-- GAL Gestiune Asociatii Locatari
-- init_gal_business_logic.sql

--drop table gal.user_apartment;
--drop table gal.build_stair_apartment;
--drop table gal.asso_build_stair;
-- -- drop table gal.build_stair_apartment;
--drop table gal.apart;
--drop table gal.asso;
--drop table gal.build_stair;
--drop table gal.district;


-- gal.association -----------------------------------------------
create table gal.asso
(
    id          serial not null
        constraint asso_pk
            primary key,
    name        varchar not null,
    description varchar,
    address     varchar not null,
    inserted    date    not null,
    updated     date,
    id_stt      integer not null
);

comment on table gal.asso is 'association';
alter table gal.asso owner to postgres;
create unique index asso_name__uindex on gal.asso (name);

-- gal.province -----------------------------------------------
create table gal.province
(
    id          serial not null
        constraint province_pk
            primary key,
    name        varchar not null,
    abbrv       varchar not null
);

comment on table gal.province is 'province';
alter table gal.province owner to postgres;
create unique index province_name__uindex on gal.province(name);

-- gal.city -----------------------------------------------
create table gal.city
(
    id          serial not null
        constraint city_pk
            primary key,
    name        varchar not null,
    id_province    integer not null
        constraint city_province_id_fk
            references gal.province
);

comment on table gal.city is 'city';
alter table gal.city owner to postgres;
create unique index city_name_id_province__uindex on gal.city (name, id_province);


-- gal.district -----------------------------------------------
create table gal.district
(
    id          serial not null
        constraint district_pk
            primary key,
    name        varchar not null,
    id_city    integer not null
        constraint district_city_id_fk
            references gal.city
);

comment on table gal.district is 'district';
alter table gal.district owner to postgres;
create unique index district_name_id_city__uindex on gal.district (name, id_city);

-- gal.build_stair -----------------------------------------------
create table gal.build_stair
(
    id        serial not null
        constraint build_stair_pk
            primary key,
    number   integer not null,
    name    varchar,
    address  varchar not null,
    inserted date    not null,
    updated  date,
    id_district integer not null
        constraint build_stair_district_id_fk
            references gal.district
);

comment on table gal.build_stair is 'build and stair';
alter table gal.build_stair owner to postgres;
create unique index build_stair_district__uindex on gal.build_stair (number, stair, id_district);

-- gal.apart -----------------------------------------------
create table gal.apart
(
    id             serial not null
        constraint apart_pk
            primary key,
    number         integer not null,
    last_name      varchar not null,
    first_name     varchar not null,
    tenants_number integer not null,
    m2             double precision,
    id_build_stair  integer not null
        constraint apart_build_stair_id_fk
            references gal.build_stair,
    inserted       date not null,
    updated        date
);

comment on table gal.apart is 'apartments';
comment on column gal.apart.m2 is 'square metre';
alter table gal.apart owner to postgres;

-- gal.aso_build_stair -----------------------------------------------
create table gal.asso_build_stair
(
    id          serial  not null
        constraint asso_build_stair_pk
            primary key,
    id_aso     integer not null
        constraint asso_build_stair_aso_id_fk
            references gal.asso,
    id_build_stair     integer not null
        constraint asso_build_stair_build_stair_id_fk
            references gal.build_stair,
    inserted    date    not null,
    updated     date,
    id_stt      integer not null
        constraint asso_build_stair_stt_id_fk
            references gal.stt
);
comment on table gal.asso_build_stair is 'association build stair map';
alter table gal.asso_build_stair owner to postgres;
create unique index asso_build_stair__uindex on gal.asso_build_stair (id_asso, id_build_stair);

-- gal.build_stair_apartment -----------------------------------------------
/*create table gal.build_stair_apart
(
    id          serial  not null
        constraint build_stair_apart_pk
            primary key,
    id_build_stair     integer not null
        constraint build_stair_apart_build_stair_id_fk
            references gal.build_stair,
    id_apart     integer not null
        constraint build_stair_apart_apart_id_fk
            references gal.apart,
    inserted    date    not null,
    updated     date,
    id_stt      integer not null
        constraint build_stair_apart_stt_id_fk
            references gal.stt
);
comment on table gal.build_stair_apart is 'build stair apart map';
alter table gal.build_stair_apart owner to postgres;
create unique index build_stair_apart__uindex on gal.build_stair_apart (id_build_stair, id_apart);
*/

-- gal.user_apartment -----------------------------------------------
create table gal.user_apartment
(
    id          serial  not null
        constraint user_apartment_pk
            primary key,
    id_user     integer not null
        constraint user_apartment_user_id_fk
            references gal.user,
    id_apartment     integer not null
        constraint user_apartment_apartment_id_fk
            references gal.apart,
    inserted    date    not null,
    updated     date,
    id_stt      integer not null
        constraint user_apartment_stt_id_fk
            references gal.stt
);
comment on table gal.user_apartment is 'user apartment map';
alter table gal.user_apartment owner to postgres;
create unique index user_apartment__uindex on gal.user_apartment (id_user, id_apartment);

-- gal.user_asso -----------------------------------------------
create table gal.user_asso
(
    id          serial  not null
        constraint user_asso_pk
            primary key,
    id_user     integer not null
        constraint user_asso_user_id_fk
            references gal.user,
    id_asso     integer not null
        constraint user_asso_asso_id_fk
            references gal.asso,
    inserted    date    not null,
    updated     date,
    id_stt      integer not null
        constraint user_asso_stt_id_fk
            references gal.stt
);
comment on table gal.user_asso is 'user association map';
alter table gal.user_asso owner to postgres;
create unique index user_asso__uindex on gal.user_asso (id_user, id_asso);


