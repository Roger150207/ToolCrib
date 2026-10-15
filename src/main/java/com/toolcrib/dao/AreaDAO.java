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
import com.toolcrib.modelo.Area;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaDAO implements DAO<Area>{

    public List<Area> listarTodos() throws SQLException {
        List<Area> lista = new ArrayList<>();

        // Abro la conexion
        try (Connection miConexion = ConexionBD.getConexion();
             PreparedStatement consulta = miConexion.prepareStatement("SELECT * FROM Areas");
             ResultSet resultSet = consulta.executeQuery()) {

            // Recorro las filaa optra vez
            while (resultSet.next()) {
                Area area = new Area();
                area.setIdArea(resultSet.getInt("IdArea"));
                area.setNombreArea(resultSet.getString("NombreArea"));
                lista.add(area); // Ingreso el nuevo
            }
        }

        return lista;
    }
}