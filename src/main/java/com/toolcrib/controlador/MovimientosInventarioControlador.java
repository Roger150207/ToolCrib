package com.toolcrib.controlador;

import com.toolcrib.modelo.MovimientosInventario;
import com.toolcrib.servicio.MovimientosInventarioServicio;
import java.sql.SQLException;
import java.util.List;

public class MovimientosInventarioControlador {

    private final MovimientosInventarioServicio movimientosServicio = new MovimientosInventarioServicio();

    public List<MovimientosInventario> listar() throws SQLException {
        return movimientosServicio.listar();
    }
}
