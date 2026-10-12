USE ToolCribDB;
GO

--- Saldaña Colorado Rogelio ---
--- 07 de Octubre 2026 ---

-- Relaciona préstamos con artículos y empleados mediante las
-- llaves foráneas para consultar el historial completo de prestamo
CREATE OR ALTER VIEW HistorialPrestamo AS
SELECT
    e.Nombre AS NombreEmpleado,
    a.Nombre AS NombreArticulo,
    dp.Cantidad,
    p.FechaPrestamo,
    p.FechaLimite,
    p.Estado
FROM Articulos a
JOIN DetallePrestamo dp ON a.IdArticulo = dp.IdArticulo
JOIN Prestamos p ON dp.IdPrestamo = p.IdPrestamo
JOIN Empleados e ON p.IdEmpleado = e.IdEmpleado;
GO

-- Agregamos la lista de prestamos activos para poder verificar de buena manera 
-- que articulos no han devuelto los empleados y excedieron la fecha limite 
CREATE OR ALTER VIEW VistaPrestamosActivos AS
SELECT 
    p.IdPrestamo,
    e.Nombre AS NombreEmpleado,
    a.Nombre AS NombreArticulo,
    dp.Cantidad,
    p.FechaPrestamo,
    p.FechaLimite,
    p.Estado,
    CASE 
        WHEN p.FechaLimite < CAST(GETDATE() AS DATE) THEN 'Vencido'
        ELSE 'En plazo'
    END AS EstadoVencimiento
FROM Prestamos p
JOIN Empleados e 
    ON p.IdEmpleado = e.IdEmpleado
JOIN DetallePrestamo dp 
    ON p.IdPrestamo = dp.IdPrestamo
JOIN Articulos a 
    ON dp.IdArticulo = a.IdArticulo
WHERE p.Estado = 'Abierto';
GO

-- Creamos otra para aquellos articulos los cuales necesitan un reabastecimiento o 
-- que quedan muy pocos de ellos 
CREATE OR ALTER VIEW VistaStockBajo AS
SELECT 
    IdArticulo,
    Nombre,
    Stock,
    StockMinimo,
    (StockMinimo - Stock) AS UnidadesFaltantes
FROM Articulos
WHERE Stock <= StockMinimo;
GO