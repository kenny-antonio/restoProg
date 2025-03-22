create table dishOrder(
    id_dish_Order serial primary key,
    dish_name varchar(100) not null,
    quantity_to_order int default 0,
    dish_price float not null,
    id_order int not null references"order"(id_order)
);