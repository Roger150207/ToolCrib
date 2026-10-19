package com.toolcrib.servicio;

import com.toolcrib.dao.RolDAO;
import com.toolcrib.modelo.Rol;
import java.sql.SQLException;
import java.util.List;

public class RolServicio {

    private final RolDAO rolDAO = new RolDAO();

    public List<Rol> listar() throws SQLException {
        return rolDAO.listarTodos();
    }

    public void guardar(Rol r) throws SQLException {
        rolDAO.insertar(r);
    }

    public Rol buscarPorId(int id) throws SQLException {
        return rolDAO.buscarPorId(id);
    }

    public void actualizar(Rol r) throws SQLException {
        rolDAO.actualizar(r);
    }

    public void eliminar(int id) throws SQLException {
        rolDAO.eliminar(id);
    }
}
