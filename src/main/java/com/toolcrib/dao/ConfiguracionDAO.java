/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toolcrib.dao;

import com.toolcrib.config.ConexionBD;
import com.toolcrib.modelo.Configuracion;
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
public class ConfiguracionDAO implements DAO<Configuracion>{

    public List<Configuracion> listarTodos() throws SQLException {
        String sql = "SELECT * FROM Configuracion";
        List<Configuracion> configs = new ArrayList<>();

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Saco cada clave/valor de configuracion guardado
            while (rs.next()) {
                Configuracion c = new Configuracion();
                c.setIdConfiguracion(rs.getInt("IdConfiguracion"));
                c.setClaveConfiguracion(rs.getString("ClaveConfiguracion"));
                c.setValorConfiguracion(rs.getString("ValorConfiguracion"));
                configs.add(c);
            }
        }

        return configs;
    }
}