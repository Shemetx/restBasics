create table if not exists users_roles
(
    user_id int not null,
    role_id int not null,
    foreign key (user_id) references user (id)
        on update cascade on delete cascade,
    foreign key (role_id) references user_role (id)
        on update cascade on delete cascade
);
