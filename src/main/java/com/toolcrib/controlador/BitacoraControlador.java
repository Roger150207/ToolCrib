package com.toolcrib.controlador;

import com.toolcrib.modelo.Bitacora;
import com.toolcrib.servicio.BitacoraServicio;
import java.sql.SQLException;
import java.util.List;

public class BitacoraControlador {

    private final BitacoraServicio bitacoraServicio = new BitacoraServicio();

    public List<Bitacora> listar() throws SQLException {
        return bitacoraServicio.listar();
    }
}
