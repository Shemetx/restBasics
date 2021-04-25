alter table order_items
drop constraint order_items_ibfk_1;

alter table order_items
drop constraint order_items_ibfk_2;

alter table order_items
add foreign key (order_id) references user_order(id) on delete cascade  on update cascade;

alter table order_items
add foreign key (item_id) references gift_certificate(id) on delete cascade  on update cascade;