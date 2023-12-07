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



-- Tạo bảng để lưu trữ thông tin về loại hàng
CREATE TABLE ProductCategories (
    ID_Categories INT identity(1,1) PRIMARY KEY,
    CategoryName NVARCHAR(50) NOT NULL
);


-- Hàng hóa trong kho
CREATE TABLE Products (
    ID_product VARCHAR(6) PRIMARY KEY,
    ID_Categories INT,
    Name NVARCHAR(32) NOT NULL,
    Quantity FLOAT DEFAULT 0, -- Số lượng
    Unit NVARCHAR(10) NOT NULL,
    Price INT DEFAULT 0,
    FOREIGN KEY (ID_Categories) REFERENCES ProductCategories(ID_Categories)
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
	Quantity FLOAT default 0,
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
	IsLock BIT default 0
	Foreign key (ID_Category) References MenuCategories(ID_Category)
);

Create table MenuItemDetail(
	ID_MIT INT identity(1,1) primary key,
	ID_Item INT,
	ID_Product varchar(6),
	Quantity FLOAT,
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
-- PROC Chức năng cập nhật bàn thành có khách khi có đơn hàng chưa thanh toán
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


--  stored procedure để lấy thông tin bàn dựa trên mã khu vực

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


EXEC GetTableSummary 1;




-- proc truy xuất ra ds món mà bàn x đang gọi 
-- đầu vào mã bàn -> DS(ID món, tên món, số lượng, giá)
Create PROCEDURE GetItemsByTableID
    @TableID INT
AS
BEGIN
    SELECT od.ID_Item AS 'ID_Item',
           mi.ItemName AS 'ItemName',
           mi.Price AS 'Price',
           od.Quantity AS 'Quantity',
		   o.ID_Order AS 'ID'
    FROM OrderDetail od
    INNER JOIN MenuItems mi ON od.ID_Item = mi.ID_Item
    INNER JOIN Orders o ON od.ID_Order = o.ID_Order
    WHERE o.ID_Table = @TableID
      AND o.IsPaid = 0;
END;

exec GetItemsByTableID 2


-- Tự động xóa đơn hàng không có chi tiết đơn hàng
CREATE PROCEDURE DeleteOrdersWithoutDetails
AS
BEGIN
    SET NOCOUNT ON;

    DELETE FROM Orders
    WHERE ID_Order NOT IN (SELECT DISTINCT ID_Order FROM OrderDetail);
END

EXEC DeleteOrdersWithoutDetails;




-- Tạo stored procedure để cập nhật trạng thái của các món trong menu


CREATE PROCEDURE UpdateMenuItemsAvailability
AS
BEGIN
    -- Cập nhật các món trong menu
    UPDATE MenuItems
    SET IsAvailable = CASE
                            -- Kiểm tra món nào có liên kết với sản phẩm trong kho
                            WHEN EXISTS (SELECT 1
                                         FROM MenuItemDetail MID
                                         INNER JOIN Products P ON MID.ID_Product = P.ID_product
                                         WHERE MID.ID_Item = MenuItems.ID_Item
                                         AND (P.Quantity < MID.Quantity OR P.Quantity IS NULL)) THEN 0 -- Món hết hàng
                            ELSE 1 -- Món còn hàng
                      END;

    -- Kiểm tra món nào cần được đánh dấu là hết hàng hoặc còn hàng dựa trên mặt hàng trong kho
    DECLARE @OutOfStockItems TABLE (ID_Item INT);

    INSERT INTO @OutOfStockItems (ID_Item)
    SELECT MID.ID_Item
    FROM MenuItemDetail MID
    LEFT JOIN Products P ON MID.ID_Product = P.ID_product
    GROUP BY MID.ID_Item
    HAVING SUM(CASE WHEN P.Quantity < MID.Quantity OR P.Quantity IS NULL THEN 1 ELSE 0 END) > 0;

    -- Cập nhật trạng thái của các món
    UPDATE MenuItems
    SET IsAvailable = 0 -- Món hết hàng
    WHERE ID_Item IN (SELECT ID_Item FROM @OutOfStockItems);

    UPDATE MenuItems
    SET IsAvailable = 1 -- Món còn hàng
    WHERE ID_Item NOT IN (SELECT ID_Item FROM @OutOfStockItems);
END;



-- kiễm tra số lượng tối đa
DECLARE @MenuItemID INT = 1; -- ID của món ăn cần kiểm tra
DECLARE @MaxAvailableQuantity FLOAT; -- Số lượng tối đa có thể đáp ứng được

EXEC CheckMenuItemAvailabilityDetailed @MenuItemID, @MaxAvailableQuantity OUTPUT;

SELECT @MaxAvailableQuantity AS MaxAvailableQuantity;

CREATE PROCEDURE CheckMenuItemAvailabilityDetailed
    @MenuItemID INT,
    @RequestedQuantity FLOAT OUTPUT
AS
BEGIN
    DECLARE @AvailableQuantity FLOAT;

    -- Khởi tạo giá trị mặc định cho số lượng có sẵn và số lượng yêu cầu
    SET @AvailableQuantity = -1; -- Mặc định -1 nếu không có sản phẩm nào liên kết

    -- Kiểm tra xem món có liên kết với sản phẩm trong kho hay không
    IF EXISTS (
        SELECT 1
        FROM MenuItemDetail MID
        INNER JOIN Products P ON MID.ID_Product = P.ID_product
        WHERE MID.ID_Item = @MenuItemID
    )
    BEGIN
        -- Tính toán số lượng có sẵn và số lượng yêu cầu
        SELECT @AvailableQuantity = MIN(P.Quantity / MID.Quantity)
        FROM MenuItemDetail MID
        INNER JOIN Products P ON MID.ID_Product = P.ID_product
        WHERE MID.ID_Item = @MenuItemID
            AND P.Quantity IS NOT NULL
            AND P.Quantity >= MID.Quantity;

        -- Trả về kết quả dựa trên số lượng có sẵn và số lượng yêu cầu
        IF @AvailableQuantity >= @RequestedQuantity
            SET @RequestedQuantity = CAST(@RequestedQuantity AS INT);
        ELSE
            SET @RequestedQuantity = 0;
    END
    ELSE
    BEGIN
        -- Món không có liên kết với sản phẩm trong kho
        SET @RequestedQuantity = -1;
    END
END;



-- Proc Doanh Thu theo khoảng thời gian
EXEC GetInvoiceDetailsByDateTimeRange '2023-12-07', 0, 24

CREATE PROCEDURE GetInvoiceDetailsByDateTimeRange
    @InputDate DATE,
    @StartHour INT,
    @EndHour INT
AS
BEGIN
    SELECT Tables.TableName, Invoices.ID_Invoice, CONVERT(DATE, InvoiceDate) AS InvoiceDate,
           CONVERT(TIME, InvoiceDate) AS InvoiceTime, Invoices.TotalAmount
    FROM Invoices
    INNER JOIN Orders ON Orders.ID_Order = Invoices.ID_Invoice
    INNER JOIN Tables ON Tables.ID_Table = Orders.ID_Table
    WHERE CONVERT(DATE, InvoiceDate) = @InputDate
          AND DATEPART(HOUR, InvoiceDate) >= @StartHour
          AND DATEPART(HOUR, InvoiceDate) < @EndHour
          AND Invoices.isPaid = 1
END