USE ToolCribDB;
GO
-- Saldaña Colorado 

-- Registramos los prestamos
CREATE OR ALTER PROCEDURE SP_RegistrarPrestamo
    @IdEmpleado INT,
    @FechaLimite DATE,
    @IdArticulo INT,
    @Cantidad INT
AS
BEGIN
SET NOCOUNT ON;

-- Validamos priemro que exista lo que se pide
IF @Cantidad <= 0
BEGIN
RAISERROR('La cantidad a prestar debe ser mayor a cero.', 16, 1);
RETURN;
END;

-- Validamos que no haya pasado de la fecha de entrega
IF @FechaLimite < CAST(SYSDATETIME() AS DATE)
BEGIN
RAISERROR('La fecha límite no puede ser una fecha pasada.', 16, 1);
RETURN;
END;

-- Aqui valido que el empelado exista
IF NOT EXISTS (SELECT 1 FROM Empleados WHERE IdEmpleado = @IdEmpleado AND Activo = 1)
BEGIN
RAISERROR('El empleado no existe o no se encuentra activo.', 16, 1);
RETURN;
END;

-- Validar que el artículo exista 
IF NOT EXISTS (SELECT 1 FROM Articulos WHERE IdArticulo = @IdArticulo AND Activo = 1)
BEGIN
RAISERROR('El artículo no existe o está dado de baja.', 16, 1);
RETURN;
END;

-- Se checa la existencia del articulo 
IF NOT EXISTS (SELECT 1 FROM Articulos WHERE IdArticulo = @IdArticulo AND Stock >= @Cantidad)
BEGIN
RAISERROR('Stock insuficiente para cubrir la cantidad solicitada.', 16, 1);
RETURN;
END;

BEGIN TRY
BEGIN TRANSACTION;

INSERT INTO Prestamos (IdEmpleado, FechaPrestamo, FechaLimite, Estado)
VALUES (@IdEmpleado, SYSDATETIME(), @FechaLimite, 'Abierto');

DECLARE @NuevoIdPrestamo INT = SCOPE_IDENTITY();

INSERT INTO DetallePrestamo (IdPrestamo, IdArticulo, Cantidad, EstadoDevolucion)
VALUES (@NuevoIdPrestamo, @IdArticulo, @Cantidad, 'Pendiente');

COMMIT TRANSACTION;
END TRY
BEGIN CATCH
IF @@TRANCOUNT > 0
ROLLBACK TRANSACTION;

DECLARE @ErrorMessage NVARCHAR(4000) = ERROR_MESSAGE();
DECLARE @ErrorSeverity INT = ERROR_SEVERITY();
DECLARE @ErrorState INT = ERROR_STATE();
RAISERROR(@ErrorMessage, @ErrorSeverity, @ErrorState);
END CATCH;
END;
GO

-- Devoluciones 
CREATE OR ALTER PROCEDURE SP_RegistrarDevolucion
    @IdPrestamo INT,
    @IdArticulo INT
AS
BEGIN
    SET NOCOUNT ON;

    -- Validar existencia 
    IF NOT EXISTS (
        SELECT 1 
        FROM DetallePrestamo 
        WHERE IdPrestamo = @IdPrestamo AND IdArticulo = @IdArticulo
    )
    BEGIN
        RAISERROR('El registro del préstamo o artículo especificado no existe.', 16, 1);
        RETURN;
    END;

    -- Checamos si ya estaba devuelto antes
    IF EXISTS (
        SELECT 1 
        FROM DetallePrestamo 
        WHERE IdPrestamo = @IdPrestamo 
        AND IdArticulo = @IdArticulo 
        AND EstadoDevolucion = 'Devuelto'
    )
    BEGIN
        RAISERROR('Este artículo ya ha sido marcado como devuelto previamente.', 16, 1);
        RETURN;
    END;

    BEGIN TRY
        BEGIN TRANSACTION;

        -- Actualizamos
        UPDATE DetallePrestamo
        SET EstadoDevolucion = 'Devuelto'
        WHERE IdPrestamo = @IdPrestamo AND IdArticulo = @IdArticulo;

        -- Si ya no hay mas pendientes lo cierro automaticamnete 
        IF NOT EXISTS (
            SELECT 1 
            FROM DetallePrestamo 
            WHERE IdPrestamo = @IdPrestamo 
            AND (EstadoDevolucion IS NULL OR EstadoDevolucion <> 'Devuelto')
        )
        BEGIN
            UPDATE Prestamos
            SET Estado = 'Cerrado'
            WHERE IdPrestamo = @IdPrestamo;
        END;

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;

        DECLARE @ErrMsgDev NVARCHAR(4000) = ERROR_MESSAGE();
        DECLARE @ErrSevDev INT = ERROR_SEVERITY();
        DECLARE @ErrStDev  INT = ERROR_STATE();
        RAISERROR(@ErrMsgDev, @ErrSevDev, @ErrStDev);
    END CATCH;
END;
GO

-- Registro las salidas 
CREATE OR ALTER PROCEDURE SP_RegistrarSalida
    @IdEmpleado INT,
    @IdArea INT,
    @Motivo NVARCHAR(255),
    @IdArticulo INT,
    @Cantidad INT
AS
BEGIN
    SET NOCOUNT ON;

    IF @Cantidad <= 0
    BEGIN
        RAISERROR('La cantidad de la salida debe ser mayor a cero.', 16, 1);
        RETURN;
    END;

    IF NOT EXISTS (SELECT 1 FROM Empleados WHERE IdEmpleado = @IdEmpleado AND Activo = 1)
    BEGIN
        RAISERROR('El empleado no existe o no se encuentra activo.', 16, 1);
        RETURN;
    END;

    IF NOT EXISTS (SELECT 1 FROM Areas WHERE IdArea = @IdArea)
    BEGIN
        RAISERROR('El área especificada no existe.', 16, 1);
        RETURN;
    END;

    IF NOT EXISTS (SELECT 1 FROM Articulos WHERE IdArticulo = @IdArticulo AND Activo = 1)
    BEGIN
        RAISERROR('El artículo no existe o está dado de baja.', 16, 1);
        RETURN;
    END;

    IF NOT EXISTS (SELECT 1 FROM Articulos WHERE IdArticulo = @IdArticulo AND Stock >= @Cantidad)
    BEGIN
        RAISERROR('Stock insuficiente para registrar la salida.', 16, 1);
        RETURN;
    END;

    BEGIN TRY
        BEGIN TRANSACTION;

        INSERT INTO Salidas (IdEmpleado, IdArea, Fecha, Motivo)
        VALUES (@IdEmpleado, @IdArea, SYSDATETIME(), @Motivo);

        DECLARE @NuevoIdSalida INT = SCOPE_IDENTITY();

        INSERT INTO DetalleSalida (IdSalida, IdArticulo, Cantidad)
        VALUES (@NuevoIdSalida, @IdArticulo, @Cantidad);

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;

        DECLARE @ErrMsgSal NVARCHAR(4000) = ERROR_MESSAGE();
        DECLARE @ErrSevSal INT = ERROR_SEVERITY();
        DECLARE @ErrStSal  INT = ERROR_STATE();
        RAISERROR(@ErrMsgSal, @ErrSevSal, @ErrStSal);
    END CATCH;
END;
GO

-- Regsitro de las entradas 
CREATE OR ALTER PROCEDURE SP_RegistrarEntrada
    @IdProveedor INT,
    @IdArticulo INT,
    @Cantidad INT,
    @CostoUnitario DECIMAL(18,2)
AS
BEGIN
    SET NOCOUNT ON;

    IF @Cantidad <= 0
    BEGIN
        RAISERROR('La cantidad de entrada debe ser mayor a cero.', 16, 1);
        RETURN;
    END;

    IF @CostoUnitario < 0
    BEGIN
        RAISERROR('El costo unitario no puede ser negativo.', 16, 1);
        RETURN;
    END;

    IF NOT EXISTS (SELECT 1 FROM Proveedores WHERE IdProveedor = @IdProveedor)
    BEGIN
        RAISERROR('El proveedor especificado no existe.', 16, 1);
        RETURN;
    END;

    IF NOT EXISTS (SELECT 1 FROM Articulos WHERE IdArticulo = @IdArticulo AND Activo = 1)
    BEGIN
        RAISERROR('El artículo no existe o está dado de baja.', 16, 1);
        RETURN;
    END;

    BEGIN TRY
        BEGIN TRANSACTION;

        INSERT INTO Entradas (IdProveedor, Fecha)
        VALUES (@IdProveedor, SYSDATETIME());

        DECLARE @NuevoIdEntrada INT = SCOPE_IDENTITY();

        INSERT INTO DetalleEntrada (IdEntrada, IdArticulo, Cantidad, CostoUnitario)
        VALUES (@NuevoIdEntrada, @IdArticulo, @Cantidad, @CostoUnitario);

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;

        DECLARE @ErrMsgEnt NVARCHAR(4000) = ERROR_MESSAGE();
        DECLARE @ErrSevEnt INT = ERROR_SEVERITY();
        DECLARE @ErrStEnt  INT = ERROR_STATE();
        RAISERROR(@ErrMsgEnt, @ErrSevEnt, @ErrStEnt);
    END CATCH;
END;
GO

-- Para dar de baja un articulo 
CREATE OR ALTER PROCEDURE SP_DarDeBajaArticulo
    @IdArticulo INT
AS
BEGIN
    SET NOCOUNT ON;

    IF NOT EXISTS (SELECT 1 FROM Articulos WHERE IdArticulo = @IdArticulo)
    BEGIN
        RAISERROR('El artículo especificado no existe.', 16, 1);
        RETURN;
    END;

    IF EXISTS (SELECT 1 FROM Articulos WHERE IdArticulo = @IdArticulo AND Activo = 0)
    BEGIN
        RAISERROR('El artículo ya se encuentra inactivo / dado de baja.', 16, 1);
        RETURN;
    END;

    IF EXISTS (
        SELECT 1 
        FROM DetallePrestamo dp
        INNER JOIN Prestamos p ON dp.IdPrestamo = p.IdPrestamo
        WHERE dp.IdArticulo = @IdArticulo 
        AND p.Estado = 'Abierto'
        AND (dp.EstadoDevolucion IS NULL OR dp.EstadoDevolucion <> 'Devuelto')
    )
    BEGIN
        RAISERROR('No se puede dar de baja el artículo porque tiene préstamos pendientes de devolución.', 16, 1);
        RETURN;
    END;

    UPDATE Articulos
    SET Activo = 0
    WHERE IdArticulo = @IdArticulo;
END;
GO