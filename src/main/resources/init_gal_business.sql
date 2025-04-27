-- GAL Gestiune Asociatii Locatari
-- init_gal_business.sql

--drop table gal.user_apartment;
--drop table gal.build_stair_apartment;
-- -- drop table gal.asso_build_stair;
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
    id_stt      integer not null,
    id_district integer not null
        constraint asso_district_id_fk
            references gal.district,
    id_city     integer not null
        constraint asso_city_id_fk
            references gal.city,
    id_province integer not null
        constraint asso_province_id_fk
            references gal.province
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
create unique index province_name__uindex on gal.province (name);

insert into gal.province(id, name, abbrv) values(1, 'Brașov', 'BV');
insert into gal.province(id, name, abbrv) values(2, 'Sibiu', 'SB');
insert into gal.province(id, name, abbrv) values(3, 'Cluj', 'CJ');
insert into gal.province(id, name, abbrv) values(4, 'Covasna', 'CV');
insert into gal.province(id, name, abbrv) values(5, 'Prahova', 'PH');
insert into gal.province(id, name, abbrv) values(6, 'Vâlcea', 'VL');
insert into gal.province(id, name, abbrv) values(7, 'București', 'B');
insert into gal.province(id, name, abbrv) values(8, 'Buzău', 'BZ');

-- gal.city -----------------------------------------------
create table gal.city
(
    id          serial not null
        constraint city_pk
            primary key,
    name        varchar not null,
    id_province    integer not null
        constraint city_province_id_fk
            references gal.province,
    postal_code varchar(6)
);

comment on table gal.city is 'city';
alter table gal.city owner to postgres;
create unique index city_name_id_province__uindex on gal.city (name, id_province);

insert into gal.city(id, name, id_province) values(1, 'Brașov', 1);
insert into gal.city(id, name, id_province) values(2, 'Codlea', 1);
insert into gal.city(id, name, id_province) values(3, 'Făgăraș', 1);
insert into gal.city(id, name, id_province) values(4, 'Rupea', 1);
insert into gal.city(id, name, id_province) values(5, 'Zărnești', 1);


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
create unique index district_name_id_city__uindex on gal.district (name, id_city)

insert into gal.district(id, name, id_city) values(1, 'Civic', 1);
insert into gal.district(id, name, id_city) values(2, 'Măgura', 1);
insert into gal.district(id, name, id_city) values(3, '9 Mai', 1);
insert into gal.district(id, name, id_city) values(4, 'Colorom', 1);
insert into gal.district(id, name, id_city) values(5, 'Codlea Nord', 1);


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
    id_asso  integer not null
        constraint build_stair_asso_id_fk
            references gal.build_stair
);

comment on table gal.build_stair is 'build and stair';
alter table gal.build_stair owner to postgres;
create unique index build_stair_district__uindex on gal.build_stair (number, stair);

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
    updated        date,
    id_build_stair integer not null
        constraint apart_build_stair_id_fk
            references gal.apart
);

comment on table gal.apart is 'apartments';
comment on column gal.apart.m2 is 'square metre';
alter table gal.apart owner to postgres;

-- gal.asso_build_stair -----------------------------------------------
/*
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


