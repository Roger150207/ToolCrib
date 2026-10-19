package com.toolcrib.servicio;

import com.toolcrib.dao.ConfiguracionDAO;
import com.toolcrib.modelo.Configuracion;
import java.sql.SQLException;
import java.util.List;

public class ConfiguracionServicio {

    private final ConfiguracionDAO configuracionDAO = new ConfiguracionDAO();

    public List<Configuracion> listar() throws SQLException {
        return configuracionDAO.listarTodos();
    }

    public void guardar(Configuracion c) throws SQLException {
        configuracionDAO.insertar(c);
    }

    public Configuracion buscarPorId(int id) throws SQLException {
        return configuracionDAO.buscarPorId(id);
    }

    public void actualizar(Configuracion c) throws SQLException {
        configuracionDAO.actualizar(c);
    }

    public void eliminar(int id) throws SQLException {
        configuracionDAO.eliminar(id);
    }
}
