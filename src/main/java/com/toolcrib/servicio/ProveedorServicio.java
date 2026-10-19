package com.toolcrib.servicio;

import com.toolcrib.dao.ProveedorDAO;
import com.toolcrib.modelo.Proveedor;
import java.sql.SQLException;
import java.util.List;

public class ProveedorServicio {

    private final ProveedorDAO proveedorDAO = new ProveedorDAO();

    public List<Proveedor> listar() throws SQLException {
        return proveedorDAO.listarTodos();
    }

    public void guardar(Proveedor p) throws SQLException {
        proveedorDAO.insertar(p);
    }

    public Proveedor buscarPorId(int id) throws SQLException {
        return proveedorDAO.buscarPorId(id);
    }

    public void actualizar(Proveedor p) throws SQLException {
        proveedorDAO.actualizar(p);
    }

    public void eliminar(int id) throws SQLException {
        proveedorDAO.eliminar(id);
    }
}
