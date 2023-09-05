-- GAL Gestiune Asociatii Locatari
-- init_gal_costs.sql

-- gal_cost.cost_repartition -----------------------------------------------
create table gal_cost.cost_repartition
(
    id   serial not null
        constraint cost_repartition_pk
            primary key,
    name varchar(40)
);
comment on table gal_cost.cost_repartition is 'repartition cost';
alter table gal_cost.cost_repartition owner to postgres;
create unique index cost_repartition__uindex on gal_cost.cost_repartition (name);

insert into gal_cost.cost_repartition (name) values('asso');
insert into gal_cost.cost_repartition (name) values('buildStair');
insert into gal_cost.cost_repartition (name) values('apartment');
insert into gal_cost.cost_repartition (name) values('m2');
insert into gal_cost.cost_repartition (name) values('tenants');
insert into gal_cost.cost_repartition (name) values('manual');

-- gal_cost.cost_type -----------------------------------------------
create table gal_cost.cost_type
(
    id   serial not null
        constraint cost_type_pk
            primary key,
    name varchar(40)
);
comment on table gal_cost.cost_type is 'type cost';
alter table gal_cost.cost_type owner to postgres;
create unique index cost_type__uindex on gal_cost.cost_type (name);

insert into gal_cost.cost_type (name) values('water');
insert into gal_cost.cost_type (name) values('clean');
insert into gal_cost.cost_type (name) values('light');
insert into gal_cost.cost_type (name) values('other');
insert into gal_cost.cost_type (name) values('garden');
insert into gal_cost.cost_type (name) values('administration');

-- gal_cost.cost_apart -----------------------------------------------
create table gal_cost.cost_apart
(
    id          serial not null
        constraint cost_apart_pk
            primary key,
    id_apart    integer not null
        constraint cost_apart_apart_id_fk
            references gal.apart,
    month       varchar(2) not null,
    year        varchar(4) not null,
    inserted    date not null,
    updated     date,
    id_cost_type     integer not null
        constraint cost_apart_cost_type_id_fk
            references gal_cost.cost_type,
    cost        numeric(10,2),
    id_cost_repartition     integer not null
        constraint cost_apart_cost_repartition_id_fk
            references gal_cost.cost_repartition
);
comment on table gal_cost.cost_apart is 'cost_apart level apart - month = 00 if cost division by year';
alter table gal_cost.cost_apart owner to postgres;
create unique index cost_apart__uindex on gal_cost.cost_apart (id_apart,month,year,id_cost_type);

-- gal_cost.cost_build_stair -----------------------------------------------
create table gal_cost.cost_build_stair
(
    id          serial not null
        constraint cost_build_stair_pk
            primary key,
    id_build_stair    integer not null
        constraint cost_build_stair_build_stair_id_fk
            references gal.build_stair,
    month       varchar(2) not null,
    year        varchar(4) not null,
    inserted    date not null,
    updated     date,
    id_cost_type     integer not null
        constraint cost_build_stair_cost_type_id_fk
            references gal_cost.cost_type,
    cost        numeric(10,2),
    id_cost_repartition     integer not null
        constraint cost_build_stair_cost_repartition_id_fk
            references gal_cost.cost_repartition
);
comment on table gal_cost.cost_build_stair is 'cost_build_stair level build_stair - month = 00 if cost division by year';
alter table gal_cost.cost_build_stair owner to postgres;
create unique index cost_build_stair__uindex on gal_cost.cost_build_stair (id_build_stair,month,year,id_cost_type);

-- gal_cost.cost_apart -----------------------------------------------
create table gal_cost.cost_asso
(
    id          serial not null
        constraint cost_asso_pk
            primary key,
    id_asso    integer not null
        constraint cost_asso_asso_id_fk
            references gal.asso,
    month       varchar(2) not null,
    year        varchar(4) not null,
    inserted    date not null,
    updated     date,
    id_cost_type     integer not null
        constraint cost_asso_cost_type_id_fk
            references gal_cost.cost_type,
    cost        numeric(10,2),
    id_cost_repartition     integer not null
        constraint cost_asso_cost_repartition_id_fk
            references gal_cost.cost_repartition
);
comment on table gal_cost.cost_asso is 'cost_asso level asso - month = 00 if cost division by year';
alter table gal_cost.cost_asso owner to postgres;
create unique index cost_asso__uindex on gal_cost.cost_asso (id_asso,month,year,id_cost_type);
