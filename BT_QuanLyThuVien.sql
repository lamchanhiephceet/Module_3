CREATE DATABASE BT_UngDungQuanLyThuVien;
create table books(
idBook int primary key,
nameBook varchar(30),
publisher varchar(30),
author varchar(15),
yearPublish varchar(10),
priceBook int,
imageBook varchar(15),
catNumber int,
foreign key(catNumber) references category(idCat)
);
create table category(
idCat int primary key,
1_sciences varchar(15),
2_sociology varchar(15),
3_techonology varchar(15),
4_economics varchar(15),
5_others varchar(15)
);
create table BorrowOrder(
idBorrow int primary key,
quantityBorrow int,
dateBorrow date,
dateReturn date,
studentNumber varchar(15),
foreign key(studentNumber) references students(student_number)
);
create table students(
student_number varchar(15) primary key,
student_name varchar(50),
address varchar(500),
email varchar(50),
image varchar(50)
);
create table inforBorrow(
id_Borrow int,
foreign key(id_Borrow) references BorrowOrder(idBorrow),
id_Book int,
foreign key(id_Book) references books(idBook)
);


