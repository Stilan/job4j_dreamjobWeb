


create table if not exists city (
     id serial primary key,
     nameCity text
);

create table if not exists candidate (
    id serial primary key,
    nameCandidate text,
    created timestamp,
    city_id serial references city(id)
);

create table if not exists post (
    id serial primary key,
    namePost text,
    created timestamp
);



create table if not exists users (
     id serial primary key,
     nameUser text,
     password text,
     email text
);






insert into city(nameCity) values ('Москва');
insert into city(nameCity) values ('Санкт-Петербург');
select * from candidate;
select *  from post;
select *  from users;
select * from city;
