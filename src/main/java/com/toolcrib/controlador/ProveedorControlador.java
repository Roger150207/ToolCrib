package com.toolcrib.controlador;

import com.toolcrib.modelo.Proveedor;
import com.toolcrib.servicio.ProveedorServicio;
import java.sql.SQLException;
import java.util.List;

public class ProveedorControlador {

    private final ProveedorServicio proveedorServicio = new ProveedorServicio();

    public List<Proveedor> listar() throws SQLException {
        return proveedorServicio.listar();
    }

    public void guardar(Proveedor p) throws SQLException {
        proveedorServicio.guardar(p);
    }

    public Proveedor buscarPorId(int id) throws SQLException {
        return proveedorServicio.buscarPorId(id);
    }

    public void actualizar(Proveedor p) throws SQLException {
        proveedorServicio.actualizar(p);
    }

    public void eliminar(int id) throws SQLException {
        proveedorServicio.eliminar(id);
    }
}
