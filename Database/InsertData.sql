use restaurantData;
-- Dữ liệu mẫu
select * from Role

insert into Role VALUES ('MG', N'Quản Lý');
insert into Role VALUES ('SV', N'Phục vụ');
insert into Role VALUES ('CS', N'Thu ngân');
insert into Role VALUES ('KS', N'Bếp');
go


select * FROM Employees
insert into Employees (FullName, [Password], Email, ID_role) VALUES (N'Admin BaoHuynh', 'admin123', 'huynhbaomts2004@gmail.com', 'MG');

update Employees set Password = '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9' where Password = 'admin123'