create table if not exists user_role
(
    id   int auto_increment
        primary key,
    role varchar(30) not null unique
);

alter table user
    add column firstName varchar(255) not null,
    add column email varchar(255) not null,
    add column password varchar(255) not null,
    add column role_id int not null default 1,
    add foreign key (role_id) references user_role(id) on update cascade;

