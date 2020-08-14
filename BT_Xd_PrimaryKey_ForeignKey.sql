
create table customers(
id_customer int auto_increment primary key,
name varchar(50),
address varchar(50),
email varchar(50),
phone varchar(30));
create table accounts(
id_account int auto_increment primary key,
type_account varchar(50),
date_account date,
balance varchar(50),
id_customer int,
foreign key(id_customer) references customers(id_customer)
);
create table transactions(
id_trans int auto_increment primary key,
id_account varchar(50),
date_trans date,
amounts varchar(50),
description varchar(50),
account_id int,
foreign key(account_id) references accounts(id_account)
);
