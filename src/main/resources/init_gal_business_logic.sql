-- GAL Gestiune Asociatii Locatari
-- init_gal_business_logic.sql

--drop table gal.aso;
--drop table gal.build_stair;
--drop table gal.apartment;
--drop table gal.aso_build_stair;
--drop table gal.build_stair_apartment;
--drop table gal.user_apartment;

-- gal.association -----------------------------------------------
create table gal.aso
(
    id          serial not null
        constraint aso_pk
            primary key,
    name        varchar not null,
    description varchar,
    address     varchar not null,
    inserted    date    not null,
    updated     date,
    id_stt      integer not null
);

comment on table gal.aso is 'association';
alter table gal.aso owner to postgres;
create unique index aso_name__uindex on gal.aso (name);


-- gal.build_stair -----------------------------------------------
create table gal.build_stair
(
    id        serial not null
        constraint build_stair_pk
            primary key,
    number   integer not null,
    stair    varchar not null,
    address  varchar not null,
    inserted date    not null,
    updated  date,
    id_stt   integer not null,
    name     integer
);

comment on table gal.aso is 'build and stair';
alter table gal.build_stair owner to postgres;
create unique index build_stair__uindex on gal.build_stair (number, stair, address);


-- gal.apartment -----------------------------------------------
create table gal.apartment
(
    id             serial not null
        constraint apartment_pk
            primary key,
    number         varchar not null,
    last_name      varchar not null,
    first_name     varchar not null,
    tenants_number integer not null,
    m2             double precision,
    inserted       date not null,
    updated        date,
    id_stt         integer not null
);

comment on table gal.aso is 'apartment';
comment on column gal.apartment.m2 is 'square metre';
alter table gal.apartment owner to postgres;

-- gal.aso_build_stair -----------------------------------------------
create table gal.aso_build_stair
(
    id          serial  not null
        constraint aso_build_stair_pk
            primary key,
    id_aso     integer not null
        constraint aso_build_stair_aso_id_fk
            references gal.aso,
    id_build_stair     integer not null
        constraint aso_build_stair_build_stair_id_fk
            references gal.build_stair,
    inserted    date    not null,
    updated     date,
    id_stt      integer not null
        constraint aso_build_stair_stt_id_fk
            references gal.stt
);
comment on table gal.aso_build_stair is 'association build stair map';
alter table gal.aso_build_stair owner to postgres;
create unique index aso_build_stair__uindex on gal.aso_build_stair (id_aso, id_build_stair);

-- gal.build_stair_apartment -----------------------------------------------
create table gal.build_stair_apartment
(
    id          serial  not null
        constraint build_stair_apartment_pk
            primary key,
    id_build_stair     integer not null
        constraint build_stair_apartment_build_stair_id_fk
            references gal.build_stair,
    id_apartment     integer not null
        constraint build_stair_apartment_apartment_id_fk
            references gal.apartment,
    inserted    date    not null,
    updated     date,
    id_stt      integer not null
        constraint build_stair_apartment_stt_id_fk
            references gal.stt
);
comment on table gal.build_stair_apartment is 'build stair apartment map';
alter table gal.build_stair_apartment owner to postgres;
create unique index build_stair_apartment__uindex on gal.build_stair_apartment (id_build_stair, id_apartment);

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
            references gal.apartment,
    inserted    date    not null,
    updated     date,
    id_stt      integer not null
        constraint user_apartment_stt_id_fk
            references gal.stt
);
comment on table gal.user_apartment is 'user apartment map';
alter table gal.user_apartment owner to postgres;
create unique index user_apartment__uindex on gal.user_apartment (id_user, id_apartment);