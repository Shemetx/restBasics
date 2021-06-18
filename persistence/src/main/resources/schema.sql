create table if not exists gift_certificate
(
    id               int auto_increment
        primary key,
    name             varchar(100)                        not null,
    description      varchar(255)                        not null,
    price            float                               not null,
    create_date      timestamp default CURRENT_TIMESTAMP not null,
    last_update_date timestamp default CURRENT_TIMESTAMP not null,
    duration         int                                 not null,
    constraint name
        unique (name)
);

create table if not exists tag
(
    id   int auto_increment
        primary key,
    name varchar(100) not null unique
);
create table if not exists  user
(
    id         int auto_increment
        primary key,
    email      varchar(255) not null,
    password   varchar(255) not null,
    first_name varchar(255) null,
    username   varchar(255) not null,
    constraint email
        unique (email),
    constraint username
        unique (username)
);
create table if not exists user_role
(
    id   int auto_increment
        primary key,
    role varchar(30) not null,
    constraint role
        unique (role)
);

create table if not exists users_roles
(
    user_id int not null,
    role_id int not null,
        foreign key (user_id) references user (id)
            on update cascade on delete cascade,
        foreign key (role_id) references user_role (id)
            on update cascade on delete cascade
);

create table if not exists certificates_tags
(
    cert_id int not null,
    tag_id  int not null,
    constraint certificates_tags_ibfk_1
        foreign key (cert_id) references gift_certificate (id)
            on update cascade on delete cascade,
    constraint certificates_tags_ibfk_2
        foreign key (tag_id) references tag (id)
            on update cascade on delete cascade
);
