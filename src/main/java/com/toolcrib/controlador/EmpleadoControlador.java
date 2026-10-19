package com.toolcrib.controlador;

import com.toolcrib.modelo.Empleado;
import com.toolcrib.servicio.EmpleadoServicio;
import java.sql.SQLException;
import java.util.List;

public class EmpleadoControlador {

    private final EmpleadoServicio empleadoServicio = new EmpleadoServicio();

    public List<Empleado> listar() throws SQLException {
        return empleadoServicio.listar();
    }

    public void guardar(Empleado e) throws SQLException {
        empleadoServicio.guardar(e);
    }

    public Empleado buscarPorId(int id) throws SQLException {
        return empleadoServicio.buscarPorId(id);
    }

    public void actualizar(Empleado e) throws SQLException {
        empleadoServicio.actualizar(e);
    }

    public void eliminar(int id) throws SQLException {
        empleadoServicio.eliminar(id);
    }
}
