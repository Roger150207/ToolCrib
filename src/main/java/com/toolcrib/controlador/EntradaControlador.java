package com.toolcrib.controlador;

import com.toolcrib.servicio.EntradaServicio;
import java.math.BigDecimal;
import java.sql.SQLException;

public class EntradaControlador {

    private final EntradaServicio entradaServicio = new EntradaServicio();

    public void registrarEntrada(int idProveedor, int idArticulo, int cantidad, BigDecimal costoUnitario) throws SQLException {
        entradaServicio.registrarEntrada(idProveedor, idArticulo, cantidad, costoUnitario);
    }
}
