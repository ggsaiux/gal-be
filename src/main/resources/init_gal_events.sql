-- GAL Gestiune Asociatii Locatari
-- init_gal_events.sql

--drop table gal.event;

-- gal.event -----------------------------------------------
create table gal.event
(
    id          serial not null
        constraint event_pk
            primary key,
    title        varchar not null,
    description varchar,
    image       bytea,
    date     varchar not null,
    inserted    date    not null,
    updated     date,
    id_stt      integer not null
);

comment on table gal.event is 'event';
alter table gal.event owner to postgres;