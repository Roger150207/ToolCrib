USE ToolCribDB;
GO

-- Echo por Rogelio Saldaña Colorado
-- Prueba para toolcrib

-- Entrada de proivedor para llaves 
DECLARE @IdProv INT;
DECLARE @IdArt  INT;

SELECT @IdProv = IdProveedor FROM Proveedores WHERE NombreProveedor = 'Herramientas Industriales del Potosí';
SELECT @IdArt  = IdArticulo  FROM Articulos   WHERE Codigo = 'HRR-001';

EXEC SP_RegistrarEntrada
    @IdProveedor   = @IdProv,
    @IdArticulo    = @IdArt,
    @Cantidad      = 10,
    @CostoUnitario = 150.00;
GO

-- Rntrada de rpovedor de brocas 
DECLARE @IdProv INT;
DECLARE @IdArt  INT;

SELECT @IdProv = IdProveedor FROM Proveedores WHERE NombreProveedor = 'Ferretera Central de México';
SELECT @IdArt  = IdArticulo  FROM Articulos   WHERE Codigo = 'CON-001';

EXEC SP_RegistrarEntrada
    @IdProveedor   = @IdProv,
    @IdArticulo    = @IdArt,
    @Cantidad      = 25,
    @CostoUnitario = 45.50;
GO

-- Validar X prestamo
DECLARE @IdEmp INT;
DECLARE @IdArt INT;

SELECT @IdEmp = IdEmpleado FROM Empleados WHERE NumEmpleado = 'EMP-101';
SELECT @IdArt = IdArticulo FROM Articulos WHERE Codigo = 'HRR-001';

EXEC SP_RegistrarPrestamo
    @IdEmpleado  = @IdEmp,
    @FechaLimite = '2026-10-20',
    @IdArticulo  = @IdArt,
    @Cantidad    = 2;
GO

-- Prestamo valido 
DECLARE @IdEmp INT;
DECLARE @IdArt INT;

SELECT @IdEmp = IdEmpleado FROM Empleados WHERE NumEmpleado = 'EMP-102';
SELECT @IdArt = IdArticulo FROM Articulos WHERE Codigo = 'HRR-001';

EXEC SP_RegistrarPrestamo
    @IdEmpleado  = @IdEmp,
    @FechaLimite = '2026-10-25',
    @IdArticulo  = @IdArt,
    @Cantidad    = 1;
GO

-- Prueba negativa 
DECLARE @IdEmp INT;
DECLARE @IdArt INT;

SELECT @IdEmp = IdEmpleado FROM Empleados WHERE NumEmpleado = 'EMP-104';
SELECT @IdArt = IdArticulo FROM Articulos WHERE Codigo = 'HRR-001';

-- Debe arrojar: "El empleado no existe o no se encuentra activo."
EXEC SP_RegistrarPrestamo
    @IdEmpleado  = @IdEmp,
    @FechaLimite = '2026-10-30',
    @IdArticulo  = @IdArt,
    @Cantidad    = 1;
GO

-- Devolucion de prestamo 
DECLARE @IdPres INT;
DECLARE @IdArt  INT;

SELECT @IdArt = IdArticulo FROM Articulos WHERE Codigo = 'HRR-001';

--Buscar X prestamos que esta abierto 
SELECT TOP 1 @IdPres = p.IdPrestamo
FROM Prestamos p
INNER JOIN Empleados e ON p.IdEmpleado = e.IdEmpleado
INNER JOIN DetallePrestamo dp ON p.IdPrestamo = dp.IdPrestamo
WHERE e.NumEmpleado = 'EMP-101' 
AND dp.IdArticulo = @IdArt 
AND (dp.EstadoDevolucion IS NULL OR dp.EstadoDevolucion = 'Pendiente')
ORDER BY p.IdPrestamo DESC;

EXEC SP_RegistrarDevolucion
    @IdPrestamo = @IdPres,
    @IdArticulo = @IdArt;
GO

-- Consumo de X brocas 
DECLARE @IdEmp  INT;
DECLARE @IdArea INT;
DECLARE @IdArt  INT;

SELECT @IdEmp  = IdEmpleado FROM Empleados WHERE NumEmpleado = 'EMP-101';
SELECT @IdArea = IdArea     FROM Areas     WHERE NombreArea = 'Mantenimiento Mecánico';
SELECT @IdArt  = IdArticulo FROM Articulos WHERE Codigo = 'CON-001';

EXEC SP_RegistrarSalida
    @IdEmpleado = @IdEmp,
    @IdArea     = @IdArea,
    @Motivo     = 'Sustitución de brocas desgastadas en torno',
    @IdArticulo = @IdArt,
    @Cantidad   = 5;
GO