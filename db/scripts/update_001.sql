create table if not exists post (
    id serial primary key,
    namePost text
);

create table if not exists candidate (
    id serial primary key,
    nameCandidate text
);

SELECT * FROM post;

SELECT * FROM candidate;
