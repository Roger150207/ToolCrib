package com.toolcrib.dao;

import com.toolcrib.config.ConexionBD;
import com.toolcrib.modelo.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO implements DAO<Categoria> {

    // Traigo todas las categorias de la tabla
    @Override
    public List<Categoria> listarTodos() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM Categorias";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("IdCategoria"));
                categoria.setNombreCategoria(rs.getString("NombreCategoria"));
                categorias.add(categoria);
            }
        }
        return categorias;
    }

    // Metodo para insertar una nueva categoria
    public void insertar(Categoria c) throws SQLException {
        String sql = "INSERT INTO Categorias (NombreCategoria) VALUES (?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombreCategoria());

            ps.executeUpdate();
        }
    }

    // Metodo para buscar una categoria por su ID
    public Categoria buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Categorias WHERE IdCategoria = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Categoria categoria = new Categoria();
                    categoria.setIdCategoria(rs.getInt("IdCategoria"));
                    categoria.setNombreCategoria(rs.getString("NombreCategoria"));
                    return categoria;
                }
            }
        }

        return null; // no encontro nada
    }

    // Metodo para actualizar una categoria
    public void actualizar(Categoria c) throws SQLException {
        String sql = "UPDATE Categorias SET NombreCategoria = ? WHERE IdCategoria = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombreCategoria());
            ps.setInt(2, c.getIdCategoria());

            ps.executeUpdate();
        }
    }

    // Metodo para borrar una categoria segun su ID
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Categorias WHERE IdCategoria = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }
}
