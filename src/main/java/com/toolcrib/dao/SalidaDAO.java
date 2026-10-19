/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toolcrib.dao;

import com.toolcrib.config.ConexionBD;
import com.toolcrib.excepcion.StockInsuficienteException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;


/**
 *
 * @author rogeer
 */
public class SalidaDAO {
    public void registrarSalida(int idEmpleado, int idArea , String motivo, int idArticulo, int cantidad) throws StockInsuficienteException, SQLException {
        String sql = "{call SP_RegistrarSalida(?, ?, ?, ?, ?)}";

        try (Connection con = ConexionBD.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {

            // Le paso los parametros en el mismo orden que el procedimiento
            cs.setInt(1, idEmpleado);
            cs.setInt(2, idArea);
            cs.setString(3, motivo);
            cs.setInt(4, idArticulo);
            cs.setInt(5, cantidad);

            cs.execute();
        }
        
        catch (SQLException e) {
        if (e.getMessage().contains("Stock insuficiente")) {
            throw new StockInsuficienteException(e.getMessage());
        }
        throw e; // si es otro error, lo dejo pasar normal
        }
    }
    
}
