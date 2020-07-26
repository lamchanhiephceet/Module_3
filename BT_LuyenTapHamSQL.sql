select * from Students where TEN like "%Huong%";
select sum(SOTIEN) as Tong_So_Tien_Ten_Huong from Students
where TEN like "%Huong%";
select distinct TEN from Students;