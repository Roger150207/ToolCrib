package com.toolcrib.servicio;

import com.toolcrib.dao.PrestamoDAO;
import com.toolcrib.excepcion.StockInsuficienteException;
import java.sql.Date;
import java.sql.SQLException;

public class PrestamoServicio {

    private final PrestamoDAO prestamoDAO = new PrestamoDAO();

    // Registro un prestamo nuevo
    public void registrarPrestamo(int idEmpleado, Date fechaLimite, int idArticulo, int cantidad) throws StockInsuficienteException, SQLException {
        prestamoDAO.registrarPrestamo(idEmpleado, fechaLimite, idArticulo, cantidad);
    }

    // Registro la devolucion de un prestamo
    public void registrarDevolucion(int idPrestamo, int idArticulo) throws SQLException {
        prestamoDAO.registrarDevolucion(idPrestamo, idArticulo);
    }
}
