package com.toolcrib.controlador;

import com.toolcrib.modelo.Categoria;
import com.toolcrib.servicio.CategoriaServicio;
import java.sql.SQLException;
import java.util.List;

public class CategoriaControlador {

    private final CategoriaServicio categoriaServicio = new CategoriaServicio();

    public List<Categoria> listar() throws SQLException {
        return categoriaServicio.listar();
    }

    public void guardar(Categoria c) throws SQLException {
        categoriaServicio.guardar(c);
    }

    public Categoria buscarPorId(int id) throws SQLException {
        return categoriaServicio.buscarPorId(id);
    }

    public void actualizar(Categoria c) throws SQLException {
        categoriaServicio.actualizar(c);
    }

    public void eliminar(int id) throws SQLException {
        categoriaServicio.eliminar(id);
    }
}
