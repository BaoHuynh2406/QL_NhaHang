
-- Dữ liệu mẫu
select * from Role

insert into Role VALUES ('MG', N'Quản Lý');
insert into Role VALUES ('SV', N'Phục vụ');
insert into Role VALUES ('CS', N'Thu ngân');
insert into Role VALUES ('KS', N'Bếp');
go


select * FROM Employees
insert into Employees (FullName, [Password], Email, ID_role) VALUES (N'Admin BaoHuynh', 'admin123', 'huynhbaomts2004@gmail.com', 'MG');
