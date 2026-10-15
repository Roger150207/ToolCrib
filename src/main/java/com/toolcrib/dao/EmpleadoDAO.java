package com.toolcrib.dao;

import com.toolcrib.config.ConexionBD;
import com.toolcrib.modelo.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Saldaña Colorado Rogelio
 */
public class EmpleadoDAO implements DAO<Empleado>{

    // Traigo todos los empleados de la tabla
    public List<Empleado> listarTodos() throws SQLException {
        List<Empleado> lista = new ArrayList<>();

        // Abro la conexion
        try (Connection miConexion = ConexionBD.getConexion();
             PreparedStatement consulta = miConexion.prepareStatement("SELECT * FROM Empleados");
             ResultSet resultSet = consulta.executeQuery()) {

            // Recorro fila por fila 
            while (resultSet.next()) {
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(resultSet.getInt("IdEmpleado"));
                empleado.setNumEmpleado(resultSet.getString("NumEmpleado"));
                empleado.setNombre(resultSet.getString("Nombre"));
                empleado.setTurno(resultSet.getInt("Turno"));
                empleado.setActivo(resultSet.getBoolean("Activo"));
                empleado.setIdArea(resultSet.getInt("IdArea"));
                // Se agrega 
                lista.add(empleado); 
            }
        }

        return lista;
    }
}