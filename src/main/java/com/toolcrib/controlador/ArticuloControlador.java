package com.toolcrib.controlador;

import com.toolcrib.modelo.Articulo;
import com.toolcrib.servicio.ArticuloServicio;
import java.sql.SQLException;
import java.util.List;

public class ArticuloControlador {

    private final ArticuloServicio articuloServicio = new ArticuloServicio();

    public List<Articulo> listar() throws SQLException {
        return articuloServicio.listar();
    }

    public void guardar(Articulo a) throws SQLException {
        articuloServicio.guardar(a);
    }

    public Articulo buscarPorId(int id) throws SQLException {
        return articuloServicio.buscarPorId(id);
    }

    public void actualizar(Articulo a) throws SQLException {
        articuloServicio.actualizar(a);
    }

    public void eliminar(int id) throws SQLException {
        articuloServicio.eliminar(id);
    }

    public void darDeBaja(int idArticulo) throws SQLException {
        articuloServicio.darDeBaja(idArticulo);
    }
}
