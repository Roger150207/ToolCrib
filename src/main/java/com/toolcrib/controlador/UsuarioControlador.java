package com.toolcrib.controlador;

import com.toolcrib.modelo.Usuario;
import com.toolcrib.servicio.UsuarioServicio;
import java.sql.SQLException;
import java.util.List;

public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio = new UsuarioServicio();

    public List<Usuario> listar() throws SQLException {
        return usuarioServicio.listar();
    }

    public void guardar(Usuario u) throws SQLException {
        usuarioServicio.guardar(u);
    }

    public Usuario buscarPorId(int id) throws SQLException {
        return usuarioServicio.buscarPorId(id);
    }

    public void actualizar(Usuario u) throws SQLException {
        usuarioServicio.actualizar(u);
    }

    public void eliminar(int id) throws SQLException {
        usuarioServicio.eliminar(id);
    }
}
