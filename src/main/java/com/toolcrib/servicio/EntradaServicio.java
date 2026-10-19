package com.toolcrib.servicio;

import com.toolcrib.dao.EntradaDAO;
import java.math.BigDecimal;
import java.sql.SQLException;

public class EntradaServicio {

    private final EntradaDAO entradaDAO = new EntradaDAO();

    // Registro una entrada de almacen
    public void registrarEntrada(int idProveedor, int idArticulo, int cantidad, BigDecimal costoUnitario) throws SQLException {
        entradaDAO.registrarEntrada(idProveedor, idArticulo, cantidad, costoUnitario);
    }
}
