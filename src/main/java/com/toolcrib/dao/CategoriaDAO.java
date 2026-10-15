/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toolcrib.dao;

/**
 *
 * @author rogeer
 */
import com.toolcrib.config.ConexionBD;
import com.toolcrib.modelo.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO implements DAO<Categoria>{

    public List<Categoria> listarTodos() throws SQLException {
        List<Categoria> lista = new ArrayList<>();

        // Abro conexion, preparo el select y lo ejecuto, todo se cierra solo al salir
        try (Connection miConexion = ConexionBD.getConexion();
             PreparedStatement consulta = miConexion.prepareStatement("SELECT * FROM Categorias");
             ResultSet resultSet = consulta.executeQuery()) {

            // Recorro las filas
            while (resultSet.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(resultSet.getInt("IdCategoria"));
                categoria.setNombreCategoria(resultSet.getString("NombreCategoria"));
                lista.add(categoria); 
            }
        }

        return lista;
    }
}