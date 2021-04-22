create table user
(
    id int auto_increment primary key
);


create table user_order
(
    id            int auto_increment primary key,
    user_id int,
    cost          float                               not null,
    purchase_time timestamp default CURRENT_TIMESTAMP not null,
    foreign key (user_id) references user(id)
);

create table order_items (
    order_id int not null,
    item_id int not null,
    foreign key (order_id) references user_order(id),
    foreign key (item_id) references gift_certificate(id)
)