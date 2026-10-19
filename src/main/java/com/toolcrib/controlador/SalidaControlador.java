package com.toolcrib.controlador;

import com.toolcrib.excepcion.StockInsuficienteException;
import com.toolcrib.servicio.SalidaServicio;
import java.sql.SQLException;

public class SalidaControlador {

    private final SalidaServicio salidaServicio = new SalidaServicio();

    public void registrarSalida(int idEmpleado, int idArea, String motivo, int idArticulo, int cantidad) throws StockInsuficienteException, SQLException {
        salidaServicio.registrarSalida(idEmpleado, idArea, motivo, idArticulo, cantidad);
    }
}
