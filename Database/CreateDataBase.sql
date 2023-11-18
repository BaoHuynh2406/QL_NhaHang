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
	Sex nvarchar(3) not null,
	birthday date not null,
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
	Price decimal(10,3) default 0
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
	Price decimal(10,3) default 0,
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
    Price DECIMAL(10, 3) not null,
    Photo nvarchar(50),
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
	Price DECIMAL(10,3),
	TotalPrice DECIMAL(13,3),
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
	TaxAmount DECIMAL(10,3),
	TotalAmount DECIMAL(10,3), --Tổng tiền không thuế
	IsPaid bit, -- trang thái thanh toán
    FOREIGN KEY (ID_Order) REFERENCES Orders(ID_Order),
	Foreign KEY (ID_Method) REFERENCES PaymentMethods(ID_Method),
	FOREIGN KEY (ID_Employee) REFERENCES Employees(ID_Employee),
	Foreign KEY (ID_Tax) REFERENCES Taxes(ID_Tax)
);