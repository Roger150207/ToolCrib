package com.toolcrib.dao;

import com.toolcrib.config.ConexionBD;
import com.toolcrib.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements DAO<Usuario> {

    // Traigo todos los usuarios de la tabla
    @Override
    public List<Usuario> listarTodos() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuarios";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("IdUsuario"));
                usuario.setNombreUsuario(rs.getString("NombreUsuario"));
                usuario.setContrasenaHash(rs.getString("ContraseñaHash"));
                usuario.setIdRol(rs.getInt("IdRol"));
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    // Metodo para insertar un nuevo usuario
    public void insertar(Usuario u) throws SQLException {
        String sql = "INSERT INTO Usuarios (NombreUsuario, ContraseñaHash, IdRol) VALUES (?, ?, ?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u.getContrasenaHash());
            ps.setInt(3, u.getIdRol());

            ps.executeUpdate();
        }
    }

    // Metodo para buscar un usuario por su ID
    public Usuario buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Usuarios WHERE IdUsuario = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("IdUsuario"));
                    usuario.setNombreUsuario(rs.getString("NombreUsuario"));
                    usuario.setContrasenaHash(rs.getString("ContraseñaHash"));
                    usuario.setIdRol(rs.getInt("IdRol"));
                    return usuario;
                }
            }
        }

        return null; // no encontro nada
    }

    // Metodo para actualizar un usuario
    public void actualizar(Usuario u) throws SQLException {
        String sql = "UPDATE Usuarios SET NombreUsuario = ?, ContraseñaHash = ?, IdRol = ? WHERE IdUsuario = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u.getContrasenaHash());
            ps.setInt(3, u.getIdRol());
            ps.setInt(4, u.getIdUsuario());

            ps.executeUpdate();
        }
    }

    // Metodo para borrar un usuario segun su ID
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Usuarios WHERE IdUsuario = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }
}
