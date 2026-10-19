package com.toolcrib.dao;

import com.toolcrib.config.ConexionBD;
import com.toolcrib.modelo.Area;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaDAO implements DAO<Area> {

    // Traigo todas las areas de la tabla
    @Override
    public List<Area> listarTodos() throws SQLException {
        List<Area> areas = new ArrayList<>();
        String sql = "SELECT * FROM Areas";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Area area = new Area();
                area.setIdArea(rs.getInt("IdArea"));
                area.setNombreArea(rs.getString("NombreArea"));
                areas.add(area);
            }
        }
        return areas;
    }

    // Metodo para insertar una nueva area
    public void insertar(Area a) throws SQLException {
        String sql = "INSERT INTO Areas (NombreArea) VALUES (?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, a.getNombreArea());

            ps.executeUpdate();
        }
    }

    // Metodo para buscar una area por su ID
    public Area buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Areas WHERE IdArea = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Area area = new Area();
                    area.setIdArea(rs.getInt("IdArea"));
                    area.setNombreArea(rs.getString("NombreArea"));
                    return area;
                }
            }
        }

        return null; // no encontro nada
    }

    // Metodo para actualizar una area
    public void actualizar(Area a) throws SQLException {
        String sql = "UPDATE Areas SET NombreArea = ? WHERE IdArea = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, a.getNombreArea());
            ps.setInt(2, a.getIdArea());

            ps.executeUpdate();
        }
    }

    // Metodo para borrar una area segun su ID
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Areas WHERE IdArea = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }
}
