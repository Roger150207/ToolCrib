USE ToolCribDB;
GO

-- Echo por Rogelio Saldaña Colorado --
-- 06/10/2026 --
-- Diseñamos la tabla de indices tomando en cuenta unicamente aquellos valores los cuales 
-- sabemos que se van a consultar de manera constante, los demas unicamente los mantenemos 
-- para casos mas especificos como lo puede ser auditorias, checar historial etc ... 

-- 1. Usuarios: consulta por rol
CREATE INDEX IX_Usuarios_IdRol 
ON Usuarios (IdRol);

-- 2. Empleados: consulta por área
CREATE INDEX IX_Empleados_IdArea
ON Empleados (IdArea);

-- 3. Articulos: consulta por categoría
CREATE INDEX IX_Articulos_IdCategoria 
ON Articulos (IdCategoria);

-- 4. Articulos: búsqueda exacta frecuente en mostrador
CREATE INDEX IX_Articulos_Codigo 
ON Articulos (Codigo);

-- 5. Prestamos: préstamos por empleado
CREATE INDEX IX_Prestamos_IdEmpleado 
ON Prestamos (IdEmpleado);

-- 6. Prestamos: préstamos abiertos vs cerrados
CREATE INDEX IX_Prestamos_Estado 
ON Prestamos (Estado);

-- 7. Prestamos: consultas por fecha
CREATE INDEX IX_Prestamos_FechaPrestamo 
ON Prestamos (FechaPrestamo);

-- 8. Prestamos: préstamos vencidos/próximos a vencer
CREATE INDEX IX_Prestamos_FechaLimite 
ON Prestamos (FechaLimite);

-- 9. DetallePrestamo: no cubierto por el prefijo izquierdo de la PK compuesta
CREATE INDEX IX_DetallePrestamo_IdArticulo 
ON DetallePrestamo (IdArticulo);

-- 10. Salidas: salidas por empleado
CREATE INDEX IX_Salidas_IdEmpleado 
ON Salidas (IdEmpleado);

-- 11. Salidas: salidas por área
CREATE INDEX IX_Salidas_IdArea 
ON Salidas (IdArea);

-- 12. Salidas: consultas por fecha
CREATE INDEX IX_Salidas_Fecha 
ON Salidas (Fecha);

-- 13. DetalleSalida: no cubierto por el prefijo izquierdo
CREATE INDEX IX_DetalleSalida_IdArticulo 
ON DetalleSalida (IdArticulo);

-- 14. Entradas: entradas por proveedor
CREATE INDEX IX_Entradas_IdProveedor 
ON Entradas (IdProveedor);

-- 15. Entradas: consultas por fecha
CREATE INDEX IX_Entradas_Fecha 
ON Entradas (Fecha);

-- 16. DetalleEntrada: no cubierto por el prefijo izquierdo
CREATE INDEX IX_DetalleEntrada_IdArticulo 
ON DetalleEntrada (IdArticulo);

-- 17. MovimientosInventario: Kardex por herramienta (MVP)
CREATE INDEX IX_MovimientosInventario_IdArticulo 
ON MovimientosInventario (IdArticulo);