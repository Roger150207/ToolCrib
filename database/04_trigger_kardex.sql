USE ToolCribDB;
GO

-- Echo por Rogelio Saldaña Colorado --
-- Triggers para la automatizacion e ir actualizando el inventario 

-- Registro de prestamos 
CREATE OR ALTER TRIGGER TR_DetallePrestamo_Insert
ON DetallePrestamo
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE a
    SET a.Stock = a.Stock - i.Cantidad
    FROM Articulos a
    INNER JOIN INSERTED i ON a.IdArticulo = i.IdArticulo;

    INSERT INTO MovimientosInventario (IdArticulo, TipoMovimiento, Cantidad, Fecha)
    SELECT 
        i.IdArticulo,
        'PRESTAMO',
        i.Cantidad,
        SYSDATETIME()
    FROM INSERTED i;
END;
GO

-- Devoluciones de las herrmaientas 
CREATE OR ALTER TRIGGER TR_DetallePrestamo_Update
ON DetallePrestamo
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE a
    SET a.Stock = a.Stock + i.Cantidad
    FROM Articulos a
    INNER JOIN INSERTED i 
        ON a.IdArticulo = i.IdArticulo
    INNER JOIN DELETED d 
        ON i.IdPrestamo = d.IdPrestamo 
       AND i.IdArticulo = d.IdArticulo
    WHERE i.EstadoDevolucion = 'Devuelto'
      AND (d.EstadoDevolucion IS NULL OR d.EstadoDevolucion <> 'Devuelto');

    INSERT INTO MovimientosInventario (IdArticulo, TipoMovimiento, Cantidad, Fecha)
    SELECT 
        i.IdArticulo,
        'DEVOLUCION',
        i.Cantidad,
        SYSDATETIME()
    FROM INSERTED i
    INNER JOIN DELETED d 
        ON i.IdPrestamo = d.IdPrestamo 
       AND i.IdArticulo = d.IdArticulo
    WHERE i.EstadoDevolucion = 'Devuelto'
      AND (d.EstadoDevolucion IS NULL OR d.EstadoDevolucion <> 'Devuelto');
END;
GO

-- Salidas de refacciones definitivas 
CREATE OR ALTER TRIGGER TR_DetalleSalida_Insert
ON DetalleSalida
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE a
    SET a.Stock = a.Stock - i.Cantidad
    FROM Articulos a
    INNER JOIN INSERTED i ON a.IdArticulo = i.IdArticulo;

    INSERT INTO MovimientosInventario (IdArticulo, TipoMovimiento, Cantidad, Fecha)
    SELECT 
        i.IdArticulo,
        'SALIDA',
        i.Cantidad,
        SYSDATETIME()
    FROM INSERTED i;
END;
GO

-- Nuevas herramientas traida por provedores 
CREATE OR ALTER TRIGGER TR_DetalleEntrada_Insert
ON DetalleEntrada
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE a
    SET a.Stock = a.Stock + i.Cantidad
    FROM Articulos a
    INNER JOIN INSERTED i ON a.IdArticulo = i.IdArticulo;

    INSERT INTO MovimientosInventario (IdArticulo, TipoMovimiento, Cantidad, Fecha)
    SELECT 
        i.IdArticulo,
        'ENTRADA',
        i.Cantidad,
        SYSDATETIME()
    FROM INSERTED i;
END;
GO