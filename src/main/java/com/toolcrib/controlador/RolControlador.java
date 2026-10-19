package com.toolcrib.controlador;

import com.toolcrib.modelo.Rol;
import com.toolcrib.servicio.RolServicio;
import java.sql.SQLException;
import java.util.List;

public class RolControlador {

    private final RolServicio rolServicio = new RolServicio();

    public List<Rol> listar() throws SQLException {
        return rolServicio.listar();
    }

    public void guardar(Rol r) throws SQLException {
        rolServicio.guardar(r);
    }

    public Rol buscarPorId(int id) throws SQLException {
        return rolServicio.buscarPorId(id);
    }

    public void actualizar(Rol r) throws SQLException {
        rolServicio.actualizar(r);
    }

    public void eliminar(int id) throws SQLException {
        rolServicio.eliminar(id);
    }
}
