package com.toolcrib.dao;

import com.toolcrib.config.ConexionBD;
import com.toolcrib.modelo.Bitacora;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BitacoraDAO implements DAO<Bitacora> {

    public List<Bitacora> listarTodos() throws SQLException {
        List<Bitacora> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM Bitacora");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Bitacora b = new Bitacora();
                b.setIdBitacora(rs.getInt("IdBitacora"));
                b.setIdUsuario(rs.getInt("IdUsuario"));
                b.setAccion(rs.getString("Accion"));
                b.setFecha(rs.getTimestamp("Fecha"));
                lista.add(b);
            }
        }

        return lista;
    }
}