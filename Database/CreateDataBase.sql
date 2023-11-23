Drop database restaurantData;

Create database restaurantData;
go
use restaurantData;
go

-- Nhóm nhân sự

Create table Role(
	ID_role varchar(5) primary key,
	RoleName nvarchar(20)
);


Create table Employees(
	ID_Employee INT IDENTITY(100000, 1) primary key,
	FullName nvarchar(50) not null,
	Password varchar(500) not null,
	Sex bit not null, --thêm cột giới tính
	birthday date not null, -- thêm ngày tháng năm sinh
	PhoneNumber varchar(11) not null,
	Email varchar(50) not null,
	Address nvarchar(50),
	ID_role varchar(5),
	Photo nvarchar(50),
	foreign key (ID_role) REFERENCES Role(ID_role)
);

-- Hàng hóa trong kho

create table Products(
	ID_product varchar(6) primary key,
	Name nvarchar(32) not null,
	Quantity int default 0, --Số lượng
	Unit nvarchar(10) not null,
	Price int default 0
);

-- Nhóm nhập hàng

create table PurchaseOrders(
	ID_PurchaseOrder INT Primary key,
	OrderDate date not null,
	ID_Employee INT,
	Foreign key (ID_Employee) REFERENCES Employees(ID_Employee)
);


Create table PurchaseOrdersDetail(
	ID_POD INT identity(1,1) primary key,
	ID_PurchaseOrder INT,
	ID_product varchar(6),
	Quantity int default 0,
	Price int default 0,
	Foreign key (ID_PurchaseOrder) References PurchaseOrders(ID_PurchaseOrder),
	Foreign Key (ID_Product) References Products (ID_product)
);


-- Nhóm thực đơn

Create table MenuCategories(
	ID_Category INT identity(1,1) primary key,
	CategoryName nvarchar(20) not null
);

CREATE TABLE MenuItems (
    ID_Item INT identity(1,1) PRIMARY KEY,
    ItemName NVARCHAR(100) not null,
    ID_Category INT ,
    Price int not null,
    Photo nvarchar(50),
	IsAvailable BIT default 1,
	Foreign key (ID_Category) References MenuCategories(ID_Category)
);

Create table MenuItemDetail(
	ID_MIT INT identity(1,1) primary key,
	ID_Item INT,
	ID_Product varchar(6),
	Quantity INT,
	Foreign key (ID_Item) References MenuItems(ID_Item),
	Foreign key (ID_Product) References Products(ID_Product)
);


-- Nhóm Khu vực và bàn
Create table Areas(
	ID_Area INT identity(1,1) primary key,
	AreaName nvarchar(20)
);

Create table Tables(
	ID_Table int identity(1,1) primary key,
	TableName nvarchar(20),
	ID_Area int,
	IsOccupied BIT,
	Foreign key (ID_Area) references Areas(ID_Area)
);

-- Nhóm thanh toán

CREATE TABLE Orders(
	ID_Order INT IDENTITY(1,1) PRIMARY KEY,
	ID_Table INT,
	ID_Employee INT,
	OrderDate DATETIME,
	NumberOfGuests INT,
	IsPaid BIT,
	FOREIGN KEY (ID_Table) REFERENCES TABLES (ID_Table),
	Foreign KEY (ID_Employee) REFERENCES Employees(ID_Employee)
);


CREATE TABLE OrderDetail(
	ID_OrderDetail INT IDENTITY(1,1) PRIMARY KEY,
	ID_Order INT,
	ID_Item INT,
	Quantity INT,
	Price int,
	TotalPrice int,
	FOREIGN KEY (ID_Order) REFERENCES Orders(ID_Order),
	Foreign KEY (ID_Item) REFERENCES MenuItems(ID_Item)
);

-- Nhóm thanh toán

CREATE TABLE Taxes (
    ID_Tax INT IDENTITY(1,1) PRIMARY KEY,
    TaxName NVARCHAR(50),
    TaxRate DECIMAL(5, 2) -- Tỷ lệ thuế
);

CREATE TABLE PaymentMethods (
    ID_Method INT identity(1,1) PRIMARY KEY,
    MethodName NVARCHAR(50)
);

CREATE TABLE Invoices(
	ID_Invoice INT IDENTITY(1,1) PRIMARY KEY,
    ID_Order INT,
	ID_Method INT,
	ID_Employee INT,
	ID_Tax INT,
	InvoiceDate DATETIME,
	TaxAmount int,
	TotalAmount int, --Tổng tiền không thuế
	IsPaid bit, -- trang thái thanh toán
    FOREIGN KEY (ID_Order) REFERENCES Orders(ID_Order),
	Foreign KEY (ID_Method) REFERENCES PaymentMethods(ID_Method),
	FOREIGN KEY (ID_Employee) REFERENCES Employees(ID_Employee),
	Foreign KEY (ID_Tax) REFERENCES Taxes(ID_Tax)
);


-- -----------------------------------------------------------------

CREATE PROCEDURE UpdateAllTablesStatus
AS
BEGIN
    UPDATE Tables
    SET IsOccupied = 
        CASE 
            WHEN EXISTS (
                SELECT 1
                FROM Orders
                WHERE Orders.ID_Table = Tables.ID_Table
                AND Orders.IsPaid = 0
            ) THEN 1 -- Có đơn hàng chưa thanh toán: bàn có khách
            ELSE 0 -- Không có đơn hàng chưa thanh toán: bàn không khách
        END
END

EXEC UpdateAllTablesStatus;


CREATE PROCEDURE GetOccupiedTablesInfo
AS
BEGIN
    SELECT T.ID_Table, T.TableName, O.NumberOfGuests
    FROM Tables T
    INNER JOIN Orders O ON T.ID_Table = O.ID_Table
    WHERE O.IsPaid = 0;
END

EXEC GetOccupiedTablesInfo;

-- -----------
SELECT 
    T.ID_Table AS MaBan,
    T.TableName AS TenBan,
    O.NumberOfGuests AS SoKhach
FROM 
    Tables T
LEFT JOIN 
    (SELECT ID_Table, NumberOfGuests
     FROM Orders
     WHERE IsPaid = 0) O ON T.ID_Table = O.ID_Table




-- Cập nhật stored procedure để lấy thông tin bàn dựa trên mã khu vực

CREATE PROCEDURE GetTableSummary
    @AreaID INT = NULL
AS
BEGIN
    SELECT 
        T.ID_Table,
        T.TableName,
        CASE
            WHEN O.IsPaid = 0 THEN SUM(OD.TotalPrice)
            ELSE NULL
        END AS TotalAmount,
        CASE
            WHEN O.IsPaid = 0 THEN O.NumberOfGuests
            ELSE NULL
        END AS NumberOfGuests
    FROM 
        Tables T
    LEFT JOIN 
        Orders O ON T.ID_Table = O.ID_Table
    LEFT JOIN 
        OrderDetail OD ON O.ID_Order = OD.ID_Order
    WHERE
        (@AreaID IS NULL OR T.ID_Area = @AreaID) -- Lọc theo mã khu vực nếu được cung cấp
    GROUP BY 
        T.ID_Table, T.TableName, O.IsPaid, O.NumberOfGuests
END;


EXEC GetTableSummary 2;


-- Tạo trigger tự động cập nhật trạng thái của bàn
CREATE OR ALTER TRIGGER UpdateTableStatus
ON Orders
AFTER INSERT, UPDATE
AS
BEGIN
    DECLARE @TableID INT;

    -- Lấy ID của bàn từ hóa đơn được thêm hoặc cập nhật
    SELECT @TableID = ID_Table
    FROM inserted;

    -- Cập nhật trạng thái của bàn
    UPDATE Tables
    SET IsOccupied = CASE
                        WHEN EXISTS (
                            SELECT 1
                            FROM Orders O
                            WHERE O.ID_Table = @TableID AND O.IsPaid = 0
                        ) THEN 1 -- Bàn có hóa đơn chưa thanh toán, đặt trạng thái là có khách
                        ELSE 0 -- Không có hóa đơn chưa thanh toán, đặt trạng thái là không có khách
                    END
    WHERE ID_Table = @TableID;
END;