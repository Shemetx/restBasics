create index user_id_index
        on user_order(user_id);

create index tag_name_index
        on tag(name);

create index order_id_index
        on order_items(order_id);

create index item_id_index
        on order_items(item_id);
