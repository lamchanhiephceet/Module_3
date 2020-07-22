create table contacts
( contact_id int(11) not null auto_increment,
last_name varchar(30) not null,
first_name varchar(25),
birthday date,
constraint contacts_pk primary key (contact_id)
);
