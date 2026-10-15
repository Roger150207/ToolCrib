/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toolcrib.dao;

import com.toolcrib.config.ConexionBD;
import com.toolcrib.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author rogeer
 */
public class UsuarioDAO implements DAO<Usuario>{

    public List<Usuario> listarTodos() throws SQLException {
        String sql = "SELECT * FROM Usuarios";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Armo cada usuario con lo que trae la fila
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("IdUsuario"));
                u.setNombreUsuario(rs.getString("NombreUsuario"));
                u.setContrasenaHash(rs.getString("ContraseñaHash"));
                u.setIdRol(rs.getInt("IdRol"));
                usuarios.add(u);
            }
        }

        return usuarios;
    }
}