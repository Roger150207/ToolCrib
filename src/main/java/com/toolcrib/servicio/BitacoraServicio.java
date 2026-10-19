package com.toolcrib.servicio;

import com.toolcrib.dao.BitacoraDAO;
import com.toolcrib.modelo.Bitacora;
import java.sql.SQLException;
import java.util.List;

public class BitacoraServicio {

    private final BitacoraDAO bitacoraDAO = new BitacoraDAO();

    // Regreso la lista completa de la bitacora
    public List<Bitacora> listar() throws SQLException {
        return bitacoraDAO.listarTodos();
    }
}
