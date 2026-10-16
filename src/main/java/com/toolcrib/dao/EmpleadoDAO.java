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
    
    // Metodo para insertar a un nuevo empelado
    public void insertar(Empleado e) throws SQLException{
        String sql = "INSERT INTO Empleados (NumEmpleado, Nombre, Turno, Activo, IdArea) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConexion();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getNumEmpleado());
            ps.setString(2, e.getNombre());
            ps.setInt(3, e.getTurno());
            ps.setBoolean(4, e.isActivo());
            ps.setInt(5, e.getIdArea());

            ps.executeUpdate(); 
        }
    }
    
    // Metodo para buscar a un empelado mediante ID 
    public Empleado buscarPorId(int id) throws SQLException{
        String sql = "SELECT * FROM Empleados WHERE IdEmpleado = ?";
        
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Empleado empleado = new Empleado();
                    empleado.setIdEmpleado(rs.getInt("IdEmpleado"));
                    empleado.setNumEmpleado(rs.getString("NumEmpleado"));
                    empleado.setNombre(rs.getString("Nombre"));
                    empleado.setTurno(rs.getInt("Turno"));
                    empleado.setActivo(rs.getBoolean("Activo"));
                    empleado.setIdArea(rs.getInt("IdArea"));
                    return empleado;
                }
            }
        }

        return null; // no encontro nada
    }
    
    // Metodo que uso para actualiza info de un empelado 
    public void actualizar(Empleado e) throws SQLException {
        String sql = "UPDATE Empleados SET NumEmpleado = ?, Nombre = ?, Turno = ?, Activo = ?, IdArea = ? WHERE IdEmpleado = ?";

            try (Connection con = ConexionBD.getConexion();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, e.getNumEmpleado());
                ps.setString(2, e.getNombre());
                ps.setInt(3, e.getTurno());
                ps.setBoolean(4, e.isActivo());
                ps.setInt(5, e.getIdArea());
                ps.setInt(6, e.getIdEmpleado());

                ps.executeUpdate();
            }
    }
    
    // Metodo para borrar a un empelado conform su ID 
    public void eliminar(int id) throws SQLException {
    String sql = "DELETE FROM Empleados WHERE IdEmpleado = ?";

    try (Connection con = ConexionBD.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);

        ps.executeUpdate();
    }
}
}