drop database if exists constricted_constrictor;
create database constricted_constrictor;
use constricted_constrictor;

drop table if exists user;
drop table if exists high_scores;

create table user
(
    id int auto_increment,
    username VARCHAR(32) not null,
    password VARCHAR(32) not null,
    email VARCHAR(32) not null,
    constraint user_pk
        primary key (id)
);

create table high_scores
(
    id int auto_increment,
    score int null,
    user_id int null,
    constraint high_scores_pk
        primary key (id),
    constraint high_scores_user_id_fk
        foreign key (user_id) references user (id)
);