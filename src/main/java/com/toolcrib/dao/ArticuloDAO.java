/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toolcrib.dao;

/**
 *
 * @author Saldaña oloradop Rogelio
 */
import com.toolcrib.config.ConexionBD;
import com.toolcrib.modelo.Articulo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticuloDAO implements DAO<Articulo>{

    // Traigo todos los articulos 
    public List<Articulo> listarTodos() throws SQLException {
        List<Articulo> lista = new ArrayList<>();

        // Abro conexion
        try (Connection miConexion = ConexionBD.getConexion();
             PreparedStatement consulta = miConexion.prepareStatement("SELECT * FROM Articulos");
             ResultSet resultSet = consulta.executeQuery()) {

            // Recorro fila por fila lo que trajo la consulta
            while (resultSet.next()) {
                Articulo articulo = new Articulo();
                articulo.setIdArticulo(resultSet.getInt("IdArticulo"));
                articulo.setIdCategoria(resultSet.getInt("IdCategoria"));
                articulo.setCodigo(resultSet.getString("Codigo"));
                articulo.setNombre(resultSet.getString("Nombre"));
                articulo.setUbicacion(resultSet.getString("Ubicacion"));
                articulo.setStock(resultSet.getInt("Stock"));
                articulo.setStockMinimo(resultSet.getInt("StockMinimo"));
                lista.add(articulo); // lo agrego a la lista que voy a regresar
            }
        }

        return lista;
    }
    
       public void darDeBajaArticulo(int idArticulo) throws SQLException {
        String sql = "{call SP_DarDeBajaArticulo(?)}";

        try (Connection con = ConexionBD.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {

            // Le paso los parametros en el mismo orden que el procedimiento
            cs.setInt(1, idArticulo);

            cs.execute();
        }
    }
}