/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toolcrib.dao;

import com.toolcrib.config.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.math.BigDecimal;

/**
 *
 * @author rogeer
 */
public class EntradaDAO {
    public void registrarSalida(int idProveedor, int idArticulo , int cantidad, BigDecimal costoUnitario) throws SQLException {
        String sql = "{call SP_RegistrarEntrada(?, ?, ?, ?)}";

        try (Connection con = ConexionBD.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, idProveedor);
            cs.setInt(2, idArticulo);
            cs.setInt(3, cantidad);
            cs.setBigDecimal(4, costoUnitario);

            cs.execute();
        }
    }
    
}