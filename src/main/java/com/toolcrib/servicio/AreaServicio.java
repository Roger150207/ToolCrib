package com.toolcrib.servicio;

import com.toolcrib.dao.AreaDAO;
import com.toolcrib.modelo.Area;
import java.sql.SQLException;
import java.util.List;

public class AreaServicio {

    private final AreaDAO areaDAO = new AreaDAO();

    public List<Area> listar() throws SQLException {
        return areaDAO.listarTodos();
    }

    public void guardar(Area a) throws SQLException {
        areaDAO.insertar(a);
    }

    public Area buscarPorId(int id) throws SQLException {
        return areaDAO.buscarPorId(id);
    }

    public void actualizar(Area a) throws SQLException {
        areaDAO.actualizar(a);
    }

    public void eliminar(int id) throws SQLException {
        areaDAO.eliminar(id);
    }
}
