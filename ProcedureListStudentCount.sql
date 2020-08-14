create table students(
id int,
name varchar(30),
age int,
address varchar(30));

insert into students values('001','Nguyen Van A','21','HN');
insert into students values('002', 'Nguyen Van B','18','HT');
insert into students values('002', 'Nguyen Van C','25','HD');
insert into students values('002', 'Nguyen Van B','23','HT');
insert into students values('002', 'Nguyen Van A','20','HN');

DELIMITER $$
create procedure update_students1()
begin
declare done int default 0;
declare v_count int;
declare v_name nvarchar(255);
declare list_student cursor for select count(*), name from students group by name;
declare continute handler for not found set done = 1;
open list_student;
loop0: LOOP
fetch list_student into v_count, v_name;
if done = 1 then
leave loop0;
end if;
if v_count > 1 then
insert into test values (v_name,v_count);
end if;
end loop loop0;
close list_student;
end;
DELIMITER $$
call update_students1();
