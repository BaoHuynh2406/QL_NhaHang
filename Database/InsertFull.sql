use restaurantData
SELECT * FROM Role
-- Thêm dữ liệu mẫu cho bảng Role
insert into Role VALUES ('MG', N'Quản Lý');
insert into Role VALUES ('SV', N'Phục vụ');
insert into Role VALUES ('CS', N'Thu ngân');
insert into Role VALUES ('KS', N'Bếp');
go

select * from Employees where ID_Employee = 1000%
select * FROM Employees where ID_Employee = 100000
insert into Employees (FullName, [Password], Email, ID_role,SEX, birthday, PhoneNumber) VALUES (N'Admin', 'admin123', 'huynhbaomts2004@gmail.com', 'MG',1,'2004-06-24','0388319013');
insert into Employees (FullName, [Password], Email, ID_role,SEX, birthday, PhoneNumber) VALUES (N'Bé Hoa', 'admin123', 'huynhbaomts2004@gmail.com', 'SV',1,'2004-06-24','0388319013');
insert into Employees (FullName, [Password], Email, ID_role,SEX, birthday, PhoneNumber) VALUES (N'Bé Bò', 'admin123', 'huynhbaomts2004@gmail.com', 'KS',1,'2004-06-24','0388319013');
insert into Employees (FullName, [Password], Email, ID_role,SEX, birthday, PhoneNumber) VALUES (N'Bé Bé', 'admin123', 'huynhbaomts2004@gmail.com', 'CS',1,'2004-06-24','0388319013');

update Employees set Password = '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9'

-- Thêm dữ liệu mẫu cho bảng Products
select * from Products
INSERT INTO Products (ID_product, Name, Quantity, Unit, Price) VALUES 
('P00001', N'Bánh gạo', 50, N'cái', 15000),
('P00002', N'Bia Sài Gòn', 100, 'lon', 13000),
('P00003', N'Thịt bò', 30, 'kg', 200000);

-- Thêm dữ liệu mẫu cho bảng PurchaseOrders
select * from PurchaseOrders
INSERT INTO PurchaseOrders (ID_PurchaseOrder,OrderDate, ID_Employee) VALUES 
(123,'2023-11-15', 100000),
(124,'2023-11-16', 100001),
(125, '2023-11-17', 100002);

-- Thêm dữ liệu mẫu cho bảng PurchaseOrdersDetail
INSERT INTO PurchaseOrdersDetail (ID_PurchaseOrder, ID_product, Quantity, Price) VALUES 
(123, 'P00001', 20, 9.99),
(124, 'P00002', 50, 4.49),
(125, 'P00003', 10, 18.00);

-- Thêm dữ liệu mẫu cho bảng MenuCategories
INSERT INTO MenuCategories (CategoryName) VALUES 
('Khai vị'),
('Món chính'),
('Tráng miệng');

-- Thêm dữ liệu mẫu cho bảng MenuItems
INSERT INTO MenuItems (ItemName, ID_Category, Price, Photo) VALUES 

-- khai vị
(N'Salad', 1, 99000, 'salad.jpg'),
(N'Bánh mỳ chấm sửa', 1, 15000, 'banhmy.jpg'),
(N'Canapé', 1, 99000, 'canapes.jpg'),
(N'Gỏi cuốn', 1, 49000, 'goicuon.jpg'),
(N'Chả giò', 1, 25000, 'chagio.jpg'),
(N'Caprese Salad', 1, 35000, 'bruschetta-caprese.jpg'),
(N'Humus và bánh pita', 1, 29000, 'Hummus.jpg'),
(N'Mì xào hải sản', 1, 39000, 'myxao.jpg'),
(N'Cơm cuộn Hàn Quốc (Gimbap)', 1, 25000, 'comcuon.jpg'),
(N'Món xôi cuộn gói lá chuối', 1, 15000, 'xoi.jpg'),
(N'Đậu hủ chiên', 1, 15000, 'dau-hu-chien-don.jpg'),

-- món chính
(N'Bạch tuột sốt cay', 2, 249000, 'bach_tuot.jpg'),
(N'Mực trứng chiên nước mắn', 2, 299000, 'muc-trung-chien-mam.jpg'),
(N'Gà hấp xả', 2, 350000, 'ga-hap-xa.jpg'),
(N'Lẩu bò', 2, 399000, 'lau-bo.jpg'),
(N'Lẩu hải sản', 2, 499000, 'lau-hai-san.jpg'),
(N'Nghiêu hấp thái', 2, 249000, 'ngheu-hap-thai.jpg'),
(N'Râu muống xào tỏi', 2, 119000, 'rao-muong-xao-toi.jpg'),
(N'Sò huyết rang me', 2, 139000, 'so-huyet-rang-me.jpg'),
(N'Sụn gà chiên mắn', 2, 239000, 'sun-ga-chien-mam.jpg'),
(N'Cơm chiên dương châu', 2, 119000, 'com-chien-duong-chau.jpg'),
(N'Bò sốt vang đỏ', 2, 249000, 'Bit-tet-ap-chao-sot-ruou-vang-do.jpg'),
(N'Sushi hải sản', 2, 229000, 'sushi.jpg'),
(N'Lasagna', 2, 349000, 'lasagna.jpg'),
(N'Pizza', 2, 449000, 'piza.jpg'),
(N'Bún chả', 2, 149000, 'buncha.jpg'),
(N'Phở bò', 2, 149000, 'pho-bo.jpg'),

-- tráng miệng
(N'Kem', 3, 12000, 'kem.jpg'),
(N'Tiramisu', 3, 29000, 'Tiramisu.jpg'),
(N'Mochi', 3, 15000, 'mochi.jpg'),
(N'Bánh chuối Foster', 3, 19000, 'banhchuoi.jpg'),
(N'Pavlova', 3, 9000, 'banh-pavlova.jpg'),
(N'Cheesecake hương vị trái cây', 3, 18000, 'chessetraicay.jpg'),
(N'Bánh flan', 3, 15000, 'banh-flan.jpg'),
(N'Bánh Matcha', 3, 27000, 'BanhMatcha.jpg'),
(N'Tart táo', 3, 21000, 'Tarttao.jpg'),
(N'Bánh crepe', 3, 23000, 'banh-crepe.jpg');


-- Thêm dữ liệu mẫu cho bảng MenuItemDetail
INSERT INTO MenuItemDetail (ID_Item, ID_Product, Quantity) VALUES 
(1, 'P00001', 5),
(2, 'P00002', 3),
(3, 'P00003', 2);

-- Thêm dữ liệu mẫu cho bảng Areas
INSERT INTO Areas (AreaName) VALUES 
('Khu A'),
('Khu B'),
(N'Mang về');

insert into Tables (TableName, ID_Area, IsOccupied) values ('A01', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A02', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A03', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A04', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A05', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A06', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A07', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A08', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A09', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A10', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A11', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A12', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A14', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A15', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A16', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A17', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A18', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A19', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A20', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A21', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A22', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A23', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A24', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A25', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A26', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A27', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A28', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A23', 1, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('A23', 1, 0);


-- Khu B
insert into Tables (TableName, ID_Area, IsOccupied) values ('B01', 2,0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('B02', 2, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('B03', 2, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('B04', 2, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('B05', 2, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('B06', 2, 0);
insert into Tables (TableName, ID_Area, IsOccupied) values ('B07', 2, 0);

-- Mang về
insert into Tables (TableName, ID_Area, IsOccupied) values (N'Khách lẻ', 3, 0);


-- Thêm dữ liệu mẫu cho bảng Orders
INSERT INTO Orders (ID_Table, ID_Employee, OrderDate, NumberOfGuests, IsPaid) VALUES 
(1, 100000, '2023-11-15', 4, 1),
(2, 100001, '2023-11-16', 2, 0),
(3, 100002, '2023-11-17', 6, 0);
INSERT INTO Orders (ID_Table, ID_Employee, OrderDate, NumberOfGuests, IsPaid) VALUES 
(9, 100000, '2023-11-15', 4, 0),
(10, 100001, '2023-11-16', 10, 0),
(11, 100002, '2023-11-17', 6, 0);

-- Thêm dữ liệu mẫu cho bảng OrderDetail
INSERT INTO OrderDetail (ID_Order, ID_Item, Quantity, Price, TotalPrice) VALUES 
(4, 1, 2, 899, 17000),
(5, 2, 1, 199, 1599),
(6, 3, 4, 699, 2796);

-- Thêm dữ liệu mẫu cho bảng Taxes
INSERT INTO Taxes (TaxName, TaxRate) VALUES 
('VAT', 10.00),
('Sales Tax', 8.50),
('Service Tax', 5.00);

-- Thêm dữ liệu mẫu cho bảng PaymentMethods
INSERT INTO PaymentMethods (MethodName) VALUES 
('Cash'),
('Credit Card'),
('Online Payment');

-- Thêm dữ liệu mẫu cho bảng Invoices
INSERT INTO Invoices (ID_Order, ID_Method, ID_Employee, ID_Tax, InvoiceDate, TaxAmount, TotalAmount, IsPaid) VALUES 
(1, 1, 100000, 1, '2023-11-15', 1.798, 19.778, 1),
(2, 2, 100001, 2, '2023-11-16', 1.359, 18.349, 0),
(3, 3, 100002, 3, '2023-11-17', 1.398, 29.356, 0);
