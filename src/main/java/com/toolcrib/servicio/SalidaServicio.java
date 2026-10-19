package com.toolcrib.servicio;

import com.toolcrib.dao.SalidaDAO;
import com.toolcrib.excepcion.StockInsuficienteException;
import java.sql.SQLException;

public class SalidaServicio {

    private final SalidaDAO salidaDAO = new SalidaDAO();

    // Registro una salida de almacen
    public void registrarSalida(int idEmpleado, int idArea, String motivo, int idArticulo, int cantidad) throws StockInsuficienteException, SQLException {
        salidaDAO.registrarSalida(idEmpleado, idArea, motivo, idArticulo, cantidad);
    }
}
