create table suppliers
( supplier_id int(11) not null auto_increment,
supplier_name varchar(50) not null,
account_rep varchar(30) not null default 'TBD',
constraint  suppliers_pk primary key (supplier_id)
);