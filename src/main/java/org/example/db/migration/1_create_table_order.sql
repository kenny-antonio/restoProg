create table "order"(
    id_order serial primary key,
    table_number char(7) not null check(table_number in ('TABLE_1','TABLE_2','TABLE_3')),
    amount_paid float default 0,
    amount_due float default 0,
    customer_arrival_date_time timestamp default current_timestamp,
    status_payment char(7) check (status_payment in ('PAID' ,'UNPAID')) default 'UNPAID'
);