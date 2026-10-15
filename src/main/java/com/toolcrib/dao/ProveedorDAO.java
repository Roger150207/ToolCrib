/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toolcrib.dao;

import com.toolcrib.config.ConexionBD;
import com.toolcrib.modelo.Proveedor;
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
public class ProveedorDAO implements DAO<Proveedor>{

    public List<Proveedor> listarTodos() throws SQLException {
        String sql = "SELECT * FROM Proveedores";
        List<Proveedor> proveedores = new ArrayList<>();

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Voy armando cada proveedor con lo que trae la fila
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setIdProveedor(rs.getInt("IdProveedor"));
                p.setNombreProveedor(rs.getString("NombreProveedor"));
                p.setContacto(rs.getString("Contacto"));
                proveedores.add(p);
            }
        }

        return proveedores;
    }
}