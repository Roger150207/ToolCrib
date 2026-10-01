USE ToolCribDB;
GO

CREATE TABLE Roles (
   IdRol INT IDENTITY(1,1),
   NombreRol NVARCHAR(50) NOT NULL,
   CONSTRAINT PK_Roles PRIMARY KEY (IdRol)
);


CREATE TABLE Usuarios (
   IdUsuario INT IDENTITY(1,1),
   NombreUsuario NVARCHAR(100) NOT NULL,
   ContraseñaHash NVARCHAR(60) NOT NULL,
   IdRol INT NOT NULL,
   CONSTRAINT PK_Usuarios PRIMARY KEY (IdUsuario),
   CONSTRAINT FK_Usuarios_Roles FOREIGN KEY (IdRol) REFERENCES Roles(IdRol)
);


CREATE TABLE Areas (
   IdArea INT IDENTITY(1,1),
   NombreArea NVARCHAR(100) NOT NULL,
   CONSTRAINT PK_Areas PRIMARY KEY (IdArea)
);


CREATE TABLE Empleados (
   IdEmpleado INT IDENTITY(1,1),
   NumEmpleado NVARCHAR(20) NOT NULL,
   Nombre NVARCHAR(100) NOT NULL,
   Turno INT NOT NULL,
   Activo BIT NOT NULL,
   IdArea INT NOT NULL,
   CONSTRAINT PK_Empleados PRIMARY KEY (IdEmpleado),
   CONSTRAINT UQ_Empleados_NumEmpleado UNIQUE (NumEmpleado),
   CONSTRAINT FK_Empleados_Areas FOREIGN KEY (IdArea) REFERENCES Areas(IdArea),
   CONSTRAINT CK_Empleados_Turno CHECK (Turno BETWEEN 1 AND 3)
);


CREATE TABLE Categorias (
   IdCategoria INT IDENTITY(1,1),
   NombreCategoria NVARCHAR(100) NOT NULL,
   CONSTRAINT PK_Categorias PRIMARY KEY (IdCategoria)
);


CREATE TABLE Proveedores (
   IdProveedor INT IDENTITY(1,1),
   NombreProveedor NVARCHAR(100) NOT NULL,
   Contacto NVARCHAR(100),
   CONSTRAINT PK_Proveedores PRIMARY KEY (IdProveedor)
);


CREATE TABLE Configuracion (
   IdConfiguracion INT IDENTITY(1,1),
   ClaveConfiguracion NVARCHAR(50) NOT NULL,
   ValorConfiguracion NVARCHAR(255) NOT NULL,
   CONSTRAINT PK_Configuracion PRIMARY KEY (IdConfiguracion),
   CONSTRAINT UQ_Configuracion_Clave UNIQUE (ClaveConfiguracion)
);

CREATE TABLE Articulos (
    IdArticulo INT IDENTITY(1,1),
    IdCategoria INT NOT NULL,
    Codigo NVARCHAR(50) NOT NULL,
    Nombre NVARCHAR(100) NOT NULL,
    Ubicacion NVARCHAR(100),
    Stock INT NOT NULL,
    StockMinimo INT NOT NULL,
    CONSTRAINT PK_Articulos PRIMARY KEY (IdArticulo),
    CONSTRAINT CK_Articulos_Stock CHECK (Stock >= 0),
    CONSTRAINT FK_Articulos_Categorias FOREIGN KEY (IdCategoria) REFERENCES Categorias(IdCategoria)
);

CREATE TABLE Prestamos (
    IdPrestamo INT IDENTITY(1,1),
    IdEmpleado INT NOT NULL,
    FechaPrestamo DATETIME2 NOT NULL, 
    FechaLimite DATE NOT NULL, 
    Estado NVARCHAR(20) NOT NULL,
    CONSTRAINT PK_Prestamos PRIMARY KEY (IdPrestamo),
    CONSTRAINT FK_Prestamos_Empleados FOREIGN KEY (IdEmpleado) REFERENCES Empleados(IdEmpleado)
);

CREATE TABLE DetallePrestamo (
    IdPrestamo INT NOT NULL,
    IdArticulo INT NOT NULL,
    Cantidad INT NOT NULL,
    EstadoDevolucion NVARCHAR(50),
    CONSTRAINT PK_DetallePrestamo PRIMARY KEY (IdPrestamo, IdArticulo),
    CONSTRAINT FK_DetallePrestamo_Prestamos FOREIGN KEY (IdPrestamo) REFERENCES Prestamos(IdPrestamo),
    CONSTRAINT FK_DetallePrestamo_Articulos FOREIGN KEY (IdArticulo) REFERENCES Articulos(IdArticulo)
);

CREATE TABLE Salidas (
    IdSalida INT IDENTITY(1,1),
    IdEmpleado INT NOT NULL,
    IdArea INT NOT NULL,
    Fecha DATETIME2 NOT NULL, 
    Motivo NVARCHAR(255),
    CONSTRAINT PK_Salidas PRIMARY KEY (IdSalida),
    CONSTRAINT FK_Salidas_Empleados FOREIGN KEY (IdEmpleado) REFERENCES Empleados(IdEmpleado),
    CONSTRAINT FK_Salidas_Areas FOREIGN KEY (IdArea) REFERENCES Areas(IdArea)
);

CREATE TABLE DetalleSalida (
    IdSalida INT NOT NULL,
    IdArticulo INT NOT NULL,
    Cantidad INT NOT NULL,
    CONSTRAINT PK_DetalleSalida PRIMARY KEY (IdSalida, IdArticulo),
    CONSTRAINT FK_DetalleSalida_Salidas FOREIGN KEY (IdSalida) REFERENCES Salidas(IdSalida),
    CONSTRAINT FK_DetalleSalida_Articulos FOREIGN KEY (IdArticulo) REFERENCES Articulos(IdArticulo)
);

CREATE TABLE Entradas (
    IdEntrada INT IDENTITY(1,1),
    IdProveedor INT NOT NULL,
    Fecha DATETIME2 NOT NULL, 
    CONSTRAINT PK_Entradas PRIMARY KEY (IdEntrada),
    CONSTRAINT FK_Entradas_Proveedores FOREIGN KEY (IdProveedor) REFERENCES Proveedores(IdProveedor)
);

CREATE TABLE DetalleEntrada (
    IdEntrada INT NOT NULL,
    IdArticulo INT NOT NULL,
    Cantidad INT NOT NULL,
    CostoUnitario DECIMAL(18,2) NOT NULL,
    CONSTRAINT PK_DetalleEntrada PRIMARY KEY (IdEntrada, IdArticulo),
    CONSTRAINT FK_DetalleEntrada_Entradas FOREIGN KEY (IdEntrada) REFERENCES Entradas(IdEntrada),
    CONSTRAINT FK_DetalleEntrada_Articulos FOREIGN KEY (IdArticulo) REFERENCES Articulos(IdArticulo)
);

CREATE TABLE MovimientosInventario (
    IdMovimiento INT IDENTITY(1,1),
    IdArticulo INT NOT NULL,
    TipoMovimiento NVARCHAR(50) NOT NULL,
    Cantidad INT NOT NULL,
    Fecha DATETIME2 NOT NULL, 
    CONSTRAINT PK_MovimientosInventario PRIMARY KEY (IdMovimiento),
    CONSTRAINT CK_MovimientosInventario_Tipo CHECK (TipoMovimiento IN ('ENTRADA', 'PRESTAMO', 'DEVOLUCION', 'SALIDA', 'AJUSTE')),
    CONSTRAINT FK_MovimientosInventario_Articulos FOREIGN KEY (IdArticulo) REFERENCES Articulos(IdArticulo)
);

CREATE TABLE Bitacora (
    IdBitacora INT IDENTITY(1,1),
    IdUsuario INT NOT NULL,
    Accion NVARCHAR(255) NOT NULL,
    Fecha DATETIME2 NOT NULL, 
    CONSTRAINT PK_Bitacora PRIMARY KEY (IdBitacora),
    CONSTRAINT FK_Bitacora_Usuarios FOREIGN KEY (IdUsuario) REFERENCES Usuarios(IdUsuario)
);