-- Drop tables if they exist
drop table if exists ware_house_items cascade;
drop table if exists app_users cascade;
drop table if exists ware_house cascade;

-- Create the tables
create table ware_house
(
    id        SERIAL primary key,
    name      varchar(255) not null,
    latitude  float        not null,
    longitude float        not null
);

create table item
(
    id                     SERIAL primary key,
    name                   varchar(255) not null,
    create_Year            int          not null,
    created_At             timestamp    not null,
    quantity               int          not null,
    price                  float        not null,
    brand_from             smallint     not null
);

create table app_users
(
    id   SERIAL primary key,
    username varchar(255) not null,
    password varchar(255) not null
);
