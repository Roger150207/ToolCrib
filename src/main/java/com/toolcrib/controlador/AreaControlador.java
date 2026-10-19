package com.toolcrib.controlador;

import com.toolcrib.modelo.Area;
import com.toolcrib.servicio.AreaServicio;
import java.sql.SQLException;
import java.util.List;

public class AreaControlador {

    private final AreaServicio areaServicio = new AreaServicio();

    public List<Area> listar() throws SQLException {
        return areaServicio.listar();
    }

    public void guardar(Area a) throws SQLException {
        areaServicio.guardar(a);
    }

    public Area buscarPorId(int id) throws SQLException {
        return areaServicio.buscarPorId(id);
    }

    public void actualizar(Area a) throws SQLException {
        areaServicio.actualizar(a);
    }

    public void eliminar(int id) throws SQLException {
        areaServicio.eliminar(id);
    }
}
