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

public class PrestamoDAO {

    public void registrarPrestamo(int idEmpleado, Date fechaLimite, int idArticulo, int cantidad) throws StockInsuficienteException, SQLException {
        String sql = "{call SP_RegistrarPrestamo(?, ?, ?, ?)}";

        try (Connection con = ConexionBD.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {

            // Le paso los parametros en el mismo orden que el procedimiento
            cs.setInt(1, idEmpleado);
            cs.setDate(2, fechaLimite);
            cs.setInt(3, idArticulo);
            cs.setInt(4, cantidad);

            // No es un SELECT, solo lo ejecuto
            cs.execute();
        }
        
        // Para los errores cunaod ya no haya stock
        catch (SQLException e) {
        if (e.getMessage().contains("Stock insuficiente")) {
            throw new StockInsuficienteException(e.getMessage());
        }
        throw e; // si es otro error, lo dejo pasar normal
        }
    }
    
    public void registrarDevolucion(int idPrestamo, int idArticulo) throws SQLException {
    String sql = "{call SP_RegistrarDevolucion(?, ?)}";

    try (Connection con = ConexionBD.getConexion();
         CallableStatement cs = con.prepareCall(sql)) {

        // Le paso el prestamo y el articulo que se esta devolviendo
        cs.setInt(1, idPrestamo);
        cs.setInt(2, idArticulo);

        cs.execute();
    }
}
}