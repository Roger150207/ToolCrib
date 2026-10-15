/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toolcrib.dao;

/**
 *
 * @author Saldaña Colorado Rogelio
 */
import com.toolcrib.config.ConexionBD;
import com.toolcrib.modelo.Rol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolDAO implements DAO<Rol>{

    public List<Rol> listarTodos() throws SQLException {
        String sql = "SELECT * FROM Roles";
        List<Rol> roles = new ArrayList<>();

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Recorro cada fila y armo el objeto Rol
            while (rs.next()) {
                Rol r = new Rol();
                r.setIdRol(rs.getInt("IdRol"));
                r.setNombreRol(rs.getString("NombreRol"));
                roles.add(r);
            }
        }

        return roles;
    }
}