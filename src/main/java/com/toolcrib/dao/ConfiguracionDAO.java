package com.toolcrib.dao;

import com.toolcrib.config.ConexionBD;
import com.toolcrib.modelo.Configuracion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConfiguracionDAO implements DAO<Configuracion> {

    // Traigo toda la configuracion de la tabla
    @Override
    public List<Configuracion> listarTodos() throws SQLException {
        List<Configuracion> configuraciones = new ArrayList<>();
        String sql = "SELECT * FROM Configuracion";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Configuracion configuracion = new Configuracion();
                configuracion.setIdConfiguracion(rs.getInt("IdConfiguracion"));
                configuracion.setClaveConfiguracion(rs.getString("ClaveConfiguracion"));
                configuracion.setValorConfiguracion(rs.getString("ValorConfiguracion"));
                configuraciones.add(configuracion);
            }
        }
        return configuraciones;
    }

    // Metodo para insertar una nueva configuracion
    public void insertar(Configuracion c) throws SQLException {
        String sql = "INSERT INTO Configuracion (ClaveConfiguracion, ValorConfiguracion) VALUES (?, ?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getClaveConfiguracion());
            ps.setString(2, c.getValorConfiguracion());

            ps.executeUpdate();
        }
    }

    // Metodo para buscar una configuracion por su ID
    public Configuracion buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Configuracion WHERE IdConfiguracion = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Configuracion configuracion = new Configuracion();
                    configuracion.setIdConfiguracion(rs.getInt("IdConfiguracion"));
                    configuracion.setClaveConfiguracion(rs.getString("ClaveConfiguracion"));
                    configuracion.setValorConfiguracion(rs.getString("ValorConfiguracion"));
                    return configuracion;
                }
            }
        }

        return null; // no encontro nada
    }

    // Metodo para actualizar una configuracion
    public void actualizar(Configuracion c) throws SQLException {
        String sql = "UPDATE Configuracion SET ClaveConfiguracion = ?, ValorConfiguracion = ? WHERE IdConfiguracion = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getClaveConfiguracion());
            ps.setString(2, c.getValorConfiguracion());
            ps.setInt(3, c.getIdConfiguracion());

            ps.executeUpdate();
        }
    }

    // Metodo para borrar una configuracion segun su ID
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Configuracion WHERE IdConfiguracion = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }
}
