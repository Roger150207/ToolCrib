package com.toolcrib.servicio;

import com.toolcrib.dao.CategoriaDAO;
import com.toolcrib.modelo.Categoria;
import java.sql.SQLException;
import java.util.List;

public class CategoriaServicio {

    private final CategoriaDAO categoriaDAO = new CategoriaDAO();

    public List<Categoria> listar() throws SQLException {
        return categoriaDAO.listarTodos();
    }

    public void guardar(Categoria c) throws SQLException {
        categoriaDAO.insertar(c);
    }

    public Categoria buscarPorId(int id) throws SQLException {
        return categoriaDAO.buscarPorId(id);
    }

    public void actualizar(Categoria c) throws SQLException {
        categoriaDAO.actualizar(c);
    }

    public void eliminar(int id) throws SQLException {
        categoriaDAO.eliminar(id);
    }
}
