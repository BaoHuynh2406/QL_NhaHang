
-- Employee
select * from Employees
INSERT INTO Employees (FullName, Password, PhoneNumber, Email, Address, ID_role, Photo)
	VALUES (N'John Doe', 'hashedpassword123', '12345678901', 'john.doe@email.com', N'123 Main St', 'ADMIN', 'john_doe.jpg');
UPDATE Employees SET 
	FullName = 'Huỳnh Thiên Bảo', Password = 'baobao123', PhoneNumber = '0987654321',
	Email = 'thienbao11@gmail.com', Address = 'TP.HCM', ID_role = 'ADMIN', Photo = 'bao.jpg' WHERE ID_Employee = '100005'
DELETE FROM Employees WHERE ID_Employee = '100004'
SELECT * FROM Employees WHERE ID_Employee = '100005'

-- Role
INSERT INTO Role (ID_role, RoleName) VALUES ('ADMIN', N'Quản trị viên'),
											('WAIT', N'Nhân viên phục vụ'),
											('CASH', N'Thủ quỹ');
UPDATE Role set RoleName = N'Quản Lý' WHERE ID_role = 'ADMIN'
DELETE FROM Role WHERE ID_role = 'ADIM'
SELECT * FROM Role WHERE ID_role = 'ADMIN';

-- Product
INSERT INTO Products (ID_product, Name, Quantity, Unit, Price) VALUES ('P001', N'Product 1', '100', N'pcs', '10.99');
UPDATE Products SET Name = N'Thịt', Quantity = '110', Unit = N'kg', Price = '10.110' Where ID_product = 'P001'
DELETE FROM Products WHERE ID_product = 'P003'
SELECT *  FROM Products WHERE ID_product = 'P001'

-- PurchaseOrders
INSERT INTO PurchaseOrders (ID_PurchaseOrder, OrderDate, ID_Employee) VALUES ('1', '2023-11-15', '100005'); 
UPDATE PurchaseOrders SET OrderDate = '2023-11-20', ID_Employee = '100005' WHERE ID_PurchaseOrder = '1'
DELETE FROM PurchaseOrders WHERE ID_PurchaseOrder = '1'
SELECT * FROM PurchaseOrders WHERE ID_PurchaseOrder = '1'

-- PurchaseOrdersDetail
INSERT INTO PurchaseOrdersDetail (ID_PurchaseOrder, ID_product, Quantity, Price) VALUES (1, 'P001', 10, 100.50);  
UPDATE PurchaseOrdersDetail SET ID_PurchaseOrder = '1', ID_product = 'P001', Quantity = '20', Price = '100.200' WHERE ID_POD = '2'
DELETE FROM PurchaseOrdersDetail WHERE ID_POD = '1'
SELECT *  FROM PurchaseOrdersDetail WHERE ID_POD = '2'

-- MenuCategories
INSERT INTO MenuCategories (CategoryName) VALUES (N'Drink')     -- Danh mục thức uống
UPDATE MenuCategories SET CategoryName = 'abc' WHERE ID_Category = '1'
DELETE FROM MenuCategories WHERE ID_Category = '1'
SELECT *  FROM MenuCategories WHERE ID_Category = '2'

-- MenuItems
SELECT * FROM MenuItems WHERE ID_Item = '3'
INSERT INTO MenuItems (ItemName, ID_Category, Price, Photo) VALUES (N'Cappuccino', '2', '4.99', 'cappuccino.jpg')
    -- Thêm các dòng khác nếu cần
UPDATE MenuItems SET ItemName = 'Cacao', ID_Category = '2', Price = '5.00', Photo = 'cacao.jpg'  WHERE ID_Item = '2'
DELETE FROM MenuItems WHERE ID_Item = '2'

-- MenuItemDetail
INSERT INTO MenuItemDetail (ID_Item, ID_Product, Quantity) VALUES ('3', 'P001', '2') 
UPDATE MenuItemDetail SET ID_Item = '3', ID_Product = 'P001', Quantity = '5' WHERE ID_MIT= '2'
DELETE FROM MenuItemDetail WHERE ID_MIT = '2'
SELECT * FROM MenuItemDetail WHERE ID_MIT = '2'

-- Areas
INSERT INTO Areas (AreaName) VALUES (N'Area A')   -- Khu vực A
UPDATE Areas SET AreaName = 'B001' WHERE ID_Area = '4'
DELETE FROM Areas WHERE ID_Area = '4'
SELECT * FROM Areas WHERE ID_Area = '1'

-- Tables
INSERT INTO Tables (TableName, ID_Area, IsOccupied) VALUES (N'Table 1', 1, 0)   -- Bàn 1 ở khu vực 1, chưa được sử dụng
UPDATE Tables SET TableName = 'TABLE', ID_Area = '1', IsOccupied = '2' WHERE ID_Table = '1'
DELETE FROM Tables WHERE ID_Table = '4'
SELECT * FROM Tables WHERE ID_Table = '1'

-- Orders
INSERT INTO Orders (ID_Table, ID_Employee, OrderDate, NumberOfGuests, IsPaid)
	VALUES ('1', '100005', GETDATE(), '4', '0')  -- Đặt bàn ở bàn 1, do nhân viên 100001, vào ngày hiện tại, có 4 khách, chưa thanh toán
UPDATE Orders SET ID_Table = '1', ID_Employee = '100005', OrderDate = '10-10-2023', NumberOfGuests = '5', IsPaid = '1' WHERE ID_Order = '4'
DELETE FROM Orders WHERE ID_Order = '1'
SELECT * FROM Orders WHERE ID_Order = '1'

-- OrderDetail
INSERT INTO OrderDetail (ID_Order, ID_Item, Quantity, Price, TotalPrice) VALUES ('4', '3', '1', '10.99', '21.01')   -- Chi tiết đơn hàng 1, món ăn 1, số lượng 2, giá 10.99, tổng giá 21.98
UPDATE OrderDetail SET ID_Order = '4', ID_Item = '3', Quantity = '5', Price = '11.001', TotalPrice = '22.002' WHERE ID_OrderDetail = '4'
DELETE FROM OrderDetail WHERE ID_OrderDetail = '4'
SELECT * FROM OrderDetail WHERE ID_OrderDetail = '4'

-- Taxes
INSERT INTO Taxes (TaxName, TaxRate) VALUES (N'VAT', 10.00),      -- Thuế VAT với tỷ lệ 10%
	 (N'Service Tax', 5.50); -- Thuế dịch vụ với tỷ lệ 5.50%
    -- Thêm các dòng khác nếu cần
UPDATE Taxes SET TaxName = 'GPA', TaxRate = '20.00' WHERE ID_Tax = '1'
DELETE FROM Taxes WHERE ID_Tax = '2'
SELECT * FROM Taxes WHERE ID_Tax = '2'

-- PaymentMethods
INSERT INTO PaymentMethods (MethodName) VALUES (N'Cash'),         -- Thanh toán bằng tiền mặt
												(N'Credit Card'),  -- Thanh toán bằng thẻ tín dụng
												(N'Debit Card'),   -- Thanh toán bằng thẻ ghi nợ
												(N'Online Payment');-- Thanh toán trực tuyến
    -- Thêm các dòng khác nếu cần
UPDATE PaymentMethods SET MethodName = 'BANK' WHERE ID_Method = '4'
DELETE FROM PaymentMethods WHERE ID_Method = '3'
SELECT * FROM PaymentMethods WHERE ID_Method = '1'

-- Invoices
INSERT INTO Invoices (ID_Order, ID_Method, ID_Employee, ID_Tax, InvoiceDate, TaxAmount, TotalAmount, IsPaid) VALUES ('4', '1', '100005', '1', GETDATE(), '2.11', '60.00', '2')  -- Hóa đơn cho đơn hàng 1, thanh toán bằng tiền mặt, do nhân viên 100001, có thuế 2.10, tổng cộng 50.00, chưa thanh toán
UPDATE Invoices SET ID_Order = '4', ID_Method = '1', ID_Employee = '100005', ID_Tax = '1', InvoiceDate = '2004-11-17', TaxAmount = '10.100', TotalAmount = '50.000', IsPaid = '0' WHERE ID_Invoice = '1'
DELETE FROM Invoices WHERE ID_Invoice = '1'
SELECT * FROM Invoices WHERE ID_Invoice = '1'
