package com.toolcrib.controlador;

import com.toolcrib.excepcion.StockInsuficienteException;
import com.toolcrib.servicio.PrestamoServicio;
import java.sql.Date;
import java.sql.SQLException;

public class PrestamoControlador {

    private final PrestamoServicio prestamoServicio = new PrestamoServicio();

    public void registrarPrestamo(int idEmpleado, Date fechaLimite, int idArticulo, int cantidad) throws StockInsuficienteException, SQLException {
        prestamoServicio.registrarPrestamo(idEmpleado, fechaLimite, idArticulo, cantidad);
    }

    public void registrarDevolucion(int idPrestamo, int idArticulo) throws SQLException {
        prestamoServicio.registrarDevolucion(idPrestamo, idArticulo);
    }
}
