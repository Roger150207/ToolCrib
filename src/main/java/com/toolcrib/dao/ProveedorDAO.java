package com.toolcrib.dao;

import com.toolcrib.config.ConexionBD;
import com.toolcrib.modelo.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO implements DAO<Proveedor> {

    // Traigo todos los proveedores de la tabla
    @Override
    public List<Proveedor> listarTodos() throws SQLException {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM Proveedores";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt("IdProveedor"));
                proveedor.setNombreProveedor(rs.getString("NombreProveedor"));
                proveedor.setContacto(rs.getString("Contacto"));
                proveedores.add(proveedor);
            }
        }
        return proveedores;
    }

    // Metodo para insertar un nuevo proveedor
    public void insertar(Proveedor p) throws SQLException {
        String sql = "INSERT INTO Proveedores (NombreProveedor, Contacto) VALUES (?, ?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombreProveedor());
            ps.setString(2, p.getContacto());

            ps.executeUpdate();
        }
    }

    // Metodo para buscar un proveedor por su ID
    public Proveedor buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Proveedores WHERE IdProveedor = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Proveedor proveedor = new Proveedor();
                    proveedor.setIdProveedor(rs.getInt("IdProveedor"));
                    proveedor.setNombreProveedor(rs.getString("NombreProveedor"));
                    proveedor.setContacto(rs.getString("Contacto"));
                    return proveedor;
                }
            }
        }

        return null; // no encontro nada
    }

    // Metodo para actualizar un proveedor
    public void actualizar(Proveedor p) throws SQLException {
        String sql = "UPDATE Proveedores SET NombreProveedor = ?, Contacto = ? WHERE IdProveedor = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombreProveedor());
            ps.setString(2, p.getContacto());
            ps.setInt(3, p.getIdProveedor());

            ps.executeUpdate();
        }
    }

    // Metodo para borrar un proveedor segun su ID
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Proveedores WHERE IdProveedor = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }
}
