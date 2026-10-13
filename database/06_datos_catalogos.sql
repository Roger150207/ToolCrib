USE ToolCribDB;
GO

-- Rogelio Saldaña
-- Catalogos base

-- Tabla padres
-- Roles de trabajadores
INSERT INTO Roles (NombreRol) VALUES 
('Administrador'),
('Almacenista'),
('Consulta');

-- Areas de trabajo 
INSERT INTO Areas (NombreArea) VALUES 
('Mantenimiento Mecánico'),
('Ensamble'),
('Pintura'),
('Control de Calidad');

-- Categorias 
INSERT INTO Categorias (NombreCategoria) VALUES 
('Herramienta'),
('Refacción'),
('Consumible');

-- Provedores 
INSERT INTO Proveedores (NombreProveedor, Contacto) VALUES 
('Herramientas Industriales del Potosí', 'ventas@hipotosi.mx'),
('Ferretera Central de México', 'contacto@ferreteracentral.com');

INSERT INTO Configuracion (ClaveConfiguracion, ValorConfiguracion) VALUES 
('DIAS_PLAZO_PRESTAMO', '7'),
('NOTIFICAR_STOCK_MINIMO', 'SI');

-- Tablas hijas 

-- Usuarios unidos con roles
INSERT INTO Usuarios (NombreUsuario, ContraseñaHash, IdRol) VALUES 
(
'admin', 
'hash_seguro_admin_2026', 
    (SELECT IdRol FROM Roles WHERE NombreRol = 'Administrador')
),
(
'almacen_t1', 
'hash_seguro_almacen_2026', 
    (SELECT IdRol FROM Roles WHERE NombreRol = 'Almacenista')
);

-- Empleados conectado a plantas
INSERT INTO Empleados (NumEmpleado, Nombre, Turno, Activo, IdArea) VALUES 
(
'EMP-101', 
'Carlos Mendoza Silva', 
1, 
1, 
    (SELECT IdArea FROM Areas WHERE NombreArea = 'Mantenimiento Mecánico')
),
(
'EMP-102', 
'Ana Karen Ramírez', 
2, 
1, 
    (SELECT IdArea FROM Areas WHERE NombreArea = 'Ensamble')
),
(
'EMP-103', 
'Luis Gerardo Torres', 
1, 
1, 
    (SELECT IdArea FROM Areas WHERE NombreArea = 'Pintura')
),
(
'EMP-104', 
'Jorge Castillo Vega', 
3, 
0,
    (SELECT IdArea FROM Areas WHERE NombreArea = 'Mantenimiento Mecánico')
);

-- Articulso conectado con cat
INSERT INTO Articulos (IdCategoria, Codigo, Nombre, Ubicacion, Stock, StockMinimo) VALUES 
(
    (SELECT IdCategoria FROM Categorias WHERE NombreCategoria = 'Herramienta'),
'HRR-001',
'Juego de Llaves Allen Métricas',
'Estante A-1',
0,
2
),
(
    (SELECT IdCategoria FROM Categorias WHERE NombreCategoria = 'Herramienta'),
'HRR-002',
'Torquímetro Digital 1/2 pulgada',
'Gabinete B-1',
0,
1
),
(
    (SELECT IdCategoria FROM Categorias WHERE NombreCategoria = 'Herramienta'),
'HRR-003',
'Vernier Digital 6 Pulgadas',
'Cajón C-1',
0,
1
),
(
    (SELECT IdCategoria FROM Categorias WHERE NombreCategoria = 'Refacción'),
'REF-001',
'Sensor Óptico de Posición 24V',
'Gabinete D-3',
0,
3
),
(
    (SELECT IdCategoria FROM Categorias WHERE NombreCategoria = 'Consumible'),
'CON-001',
'Juego de Brocas para Acero HSS',
'Estante A-3',
0,
5
);
GO