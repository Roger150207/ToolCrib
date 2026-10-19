package com.toolcrib.servicio;

import com.toolcrib.dao.UsuarioDAO;
import com.toolcrib.modelo.Usuario;
import java.sql.SQLException;
import java.util.List;

public class UsuarioServicio {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public List<Usuario> listar() throws SQLException {
        return usuarioDAO.listarTodos();
    }

    public void guardar(Usuario u) throws SQLException {
        usuarioDAO.insertar(u);
    }

    public Usuario buscarPorId(int id) throws SQLException {
        return usuarioDAO.buscarPorId(id);
    }

    public void actualizar(Usuario u) throws SQLException {
        usuarioDAO.actualizar(u);
    }

    public void eliminar(int id) throws SQLException {
        usuarioDAO.eliminar(id);
    }
}
