package com.toolcrib.dao;

import com.toolcrib.config.ConexionBD;
import com.toolcrib.modelo.Articulo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticuloDAO implements DAO<Articulo> {

    // Traigo todos los articulos de la tabla
    @Override
    public List<Articulo> listarTodos() throws SQLException {
        List<Articulo> articulos = new ArrayList<>();
        String sql = "SELECT * FROM Articulos";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Articulo articulo = new Articulo();
                articulo.setIdArticulo(rs.getInt("IdArticulo"));
                articulo.setIdCategoria(rs.getInt("IdCategoria"));
                articulo.setCodigo(rs.getString("Codigo"));
                articulo.setNombre(rs.getString("Nombre"));
                articulo.setUbicacion(rs.getString("Ubicacion"));
                articulo.setStock(rs.getInt("Stock"));
                articulo.setStockMinimo(rs.getInt("StockMinimo"));
                articulos.add(articulo);
            }
        }
        return articulos;
    }

    // Metodo para insertar un nuevo articulo
    public void insertar(Articulo a) throws SQLException {
        String sql = "INSERT INTO Articulos (IdCategoria, Codigo, Nombre, Ubicacion, Stock, StockMinimo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, a.getIdCategoria());
            ps.setString(2, a.getCodigo());
            ps.setString(3, a.getNombre());
            ps.setString(4, a.getUbicacion());
            ps.setInt(5, a.getStock());
            ps.setInt(6, a.getStockMinimo());

            ps.executeUpdate();
        }
    }

    // Metodo para buscar un articulo por su ID
    public Articulo buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Articulos WHERE IdArticulo = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Articulo articulo = new Articulo();
                    articulo.setIdArticulo(rs.getInt("IdArticulo"));
                    articulo.setIdCategoria(rs.getInt("IdCategoria"));
                    articulo.setCodigo(rs.getString("Codigo"));
                    articulo.setNombre(rs.getString("Nombre"));
                    articulo.setUbicacion(rs.getString("Ubicacion"));
                    articulo.setStock(rs.getInt("Stock"));
                    articulo.setStockMinimo(rs.getInt("StockMinimo"));
                    return articulo;
                }
            }
        }

        return null; // no encontro nada
    }

    // Metodo para actualizar un articulo
    public void actualizar(Articulo a) throws SQLException {
        String sql = "UPDATE Articulos SET IdCategoria = ?, Codigo = ?, Nombre = ?, Ubicacion = ?, Stock = ?, StockMinimo = ? WHERE IdArticulo = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, a.getIdCategoria());
            ps.setString(2, a.getCodigo());
            ps.setString(3, a.getNombre());
            ps.setString(4, a.getUbicacion());
            ps.setInt(5, a.getStock());
            ps.setInt(6, a.getStockMinimo());
            ps.setInt(7, a.getIdArticulo());

            ps.executeUpdate();
        }
    }

    // Metodo para borrar un articulo segun su ID
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Articulos WHERE IdArticulo = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }

    // Metodo para dar de baja un articulo (llama al procedimiento SP_DarDeBajaArticulo)
    public void darDeBajaArticulo(int idArticulo) throws SQLException {
        String sql = "{call SP_DarDeBajaArticulo(?)}";

        try (Connection con = ConexionBD.getConexion();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, idArticulo);

            cs.execute();
        }
    }
}
