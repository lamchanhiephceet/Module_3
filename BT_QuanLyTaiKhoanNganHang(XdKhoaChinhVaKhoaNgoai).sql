create table customers(
customer_id int primary key, 
fullname varchar(50),
address varchar(50),
email varchar(50),
phone varchar(50)
);
create table accounts(
account_number int primary key,
foreign key(account_number) references customers(customer_id),
account_type varchar(50),
date1 date,
balance int );
create table transactions(
tran_number int primary key,
 foreign key(tran_number) references accounts(account_number),
account_number int,
date2 date,
amounts int,
descriptions varchar(50));
drop table customers, accounts,transactions;