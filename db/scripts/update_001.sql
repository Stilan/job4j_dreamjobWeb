create table if not exists post (
    id serial primary key,
    namePost text
);


create table if not exists candidate (
    id serial primary key,
    nameCandidate text
);


create table if not exists users (
     id serial primary key,
     nameUser text,
     password text,
     email text
);



select * from candidate;
select *  from post;
select *  from users;