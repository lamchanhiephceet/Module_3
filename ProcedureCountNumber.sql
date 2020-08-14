drop procedure if exists find_number;
delimiter $$
create procedure find_number(in number int, out message varchar(255))
begin
case number
when 0 then set message = 'Khong';
when 1 then set message = 'Mot';
else set message = 'Khong co';
end case;

end $$
delimiter $$;

call find_number(1,@message);
select @message;