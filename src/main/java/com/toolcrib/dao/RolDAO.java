package com.toolcrib.dao;

import com.toolcrib.config.ConexionBD;
import com.toolcrib.modelo.Rol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolDAO implements DAO<Rol> {

    // Traigo todos los roles de la tabla
    @Override
    public List<Rol> listarTodos() throws SQLException {
        List<Rol> roles = new ArrayList<>();
        String sql = "SELECT * FROM Roles";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Rol rol = new Rol();
                rol.setIdRol(rs.getInt("IdRol"));
                rol.setNombreRol(rs.getString("NombreRol"));
                roles.add(rol);
            }
        }
        return roles;
    }

    // Metodo para insertar un nuevo rol
    public void insertar(Rol r) throws SQLException {
        String sql = "INSERT INTO Roles (NombreRol) VALUES (?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, r.getNombreRol());

            ps.executeUpdate();
        }
    }

    // Metodo para buscar un rol por su ID
    public Rol buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Roles WHERE IdRol = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Rol rol = new Rol();
                    rol.setIdRol(rs.getInt("IdRol"));
                    rol.setNombreRol(rs.getString("NombreRol"));
                    return rol;
                }
            }
        }

        return null; // no encontro nada
    }

    // Metodo para actualizar un rol
    public void actualizar(Rol r) throws SQLException {
        String sql = "UPDATE Roles SET NombreRol = ? WHERE IdRol = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, r.getNombreRol());
            ps.setInt(2, r.getIdRol());

            ps.executeUpdate();
        }
    }

    // Metodo para borrar un rol segun su ID
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Roles WHERE IdRol = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }
}
