package com.toolcrib.controlador;

import com.toolcrib.modelo.Configuracion;
import com.toolcrib.servicio.ConfiguracionServicio;
import java.sql.SQLException;
import java.util.List;

public class ConfiguracionControlador {

    private final ConfiguracionServicio configuracionServicio = new ConfiguracionServicio();

    public List<Configuracion> listar() throws SQLException {
        return configuracionServicio.listar();
    }

    public void guardar(Configuracion c) throws SQLException {
        configuracionServicio.guardar(c);
    }

    public Configuracion buscarPorId(int id) throws SQLException {
        return configuracionServicio.buscarPorId(id);
    }

    public void actualizar(Configuracion c) throws SQLException {
        configuracionServicio.actualizar(c);
    }

    public void eliminar(int id) throws SQLException {
        configuracionServicio.eliminar(id);
    }
}
