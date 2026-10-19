package com.toolcrib.dao;

import com.toolcrib.config.ConexionBD;
import com.toolcrib.modelo.MovimientosInventario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovimientosInventarioDAO implements DAO<MovimientosInventario> {

    public List<MovimientosInventario> listarTodos() throws SQLException {
        List<MovimientosInventario> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM MovimientosInventario");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MovimientosInventario mov = new MovimientosInventario();
                mov.setIdMovimiento(rs.getInt("IdMovimiento"));
                mov.setIdArticulo(rs.getInt("IdArticulo"));
                mov.setTipoMovimiento(rs.getString("TipoMovimiento"));
                mov.setCantidad(rs.getInt("Cantidad"));
                mov.setFecha(rs.getTimestamp("Fecha"));
                lista.add(mov);
            }
        }

        return lista;
    }
}