package com.toolcrib.servicio;

import com.toolcrib.dao.ArticuloDAO;
import com.toolcrib.modelo.Articulo;
import java.sql.SQLException;
import java.util.List;

public class ArticuloServicio {

    private final ArticuloDAO articuloDAO = new ArticuloDAO();

    // Regreso la lista completa de articulos
    public List<Articulo> listar() throws SQLException {
        return articuloDAO.listarTodos();
    }

    // Guardo un articulo nuevo
    public void guardar(Articulo a) throws SQLException {
        articuloDAO.insertar(a);
    }

    // Busco un articulo por su ID
    public Articulo buscarPorId(int id) throws SQLException {
        return articuloDAO.buscarPorId(id);
    }

    // Actualizo los datos de un articulo
    public void actualizar(Articulo a) throws SQLException {
        articuloDAO.actualizar(a);
    }

    // Elimino un articulo por su ID
    public void eliminar(int id) throws SQLException {
        articuloDAO.eliminar(id);
    }

    // Doy de baja un articulo usando el procedimiento almacenado
    public void darDeBaja(int idArticulo) throws SQLException {
        articuloDAO.darDeBajaArticulo(idArticulo);
    }
}
