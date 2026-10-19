package com.toolcrib.servicio;

import com.toolcrib.dao.MovimientosInventarioDAO;
import com.toolcrib.modelo.MovimientosInventario;
import java.sql.SQLException;
import java.util.List;

public class MovimientosInventarioServicio {

    private final MovimientosInventarioDAO movimientosDAO = new MovimientosInventarioDAO();

    // Regreso la lista completa de movimientos de inventario
    public List<MovimientosInventario> listar() throws SQLException {
        return movimientosDAO.listarTodos();
    }
}
