package com.toolcrib.servicio;

import com.toolcrib.dao.EmpleadoDAO;
import com.toolcrib.modelo.Empleado;
import java.sql.SQLException;
import java.util.List;

public class EmpleadoServicio {

    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    // Regreso la lista completa de empleados
    public List<Empleado> listar() throws SQLException {
        return empleadoDAO.listarTodos();
    }

    // Guardo un empleado nuevo
    public void guardar(Empleado e) throws SQLException {
        empleadoDAO.insertar(e);
    }

    // Busco un empleado por su ID
    public Empleado buscarPorId(int id) throws SQLException {
        return empleadoDAO.buscarPorId(id);
    }

    // Actualizo los datos de un empleado
    public void actualizar(Empleado e) throws SQLException {
        empleadoDAO.actualizar(e);
    }

    // Elimino un empleado por su ID
    public void eliminar(int id) throws SQLException {
        empleadoDAO.eliminar(id);
    }
}
