package com.toolcrib.vista;

import com.toolcrib.controlador.ProveedorControlador;
import com.toolcrib.modelo.Proveedor;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PanelProveedores extends JPanel {

    private final ProveedorControlador proveedorControlador = new ProveedorControlador();
    private final DefaultTableModel modeloTabla;
    private final JTable tabla;

    public PanelProveedores() {
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel(new Object[]{"IdProveedor", "NombreProveedor", "Contacto"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla = new JTable(modeloTabla);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnRefrescar = new JButton("Refrescar");
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnRefrescar);
        add(panelBotones, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> agregar());
        btnEditar.addActionListener(e -> editar());
        btnEliminar.addActionListener(e -> eliminar());
        btnRefrescar.addActionListener(e -> cargarDatos());

        cargarDatos();
    }

    private void cargarDatos() {
        try {
            modeloTabla.setRowCount(0);
            List<Proveedor> proveedores = proveedorControlador.listar();
            for (Proveedor p : proveedores) {
                modeloTabla.addRow(new Object[]{p.getIdProveedor(), p.getNombreProveedor(), p.getContacto()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar proveedores: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregar() {
        JTextField txtNombre = new JTextField();
        JTextField txtContacto = new JTextField();
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel("NombreProveedor:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Contacto:"));
        panel.add(txtContacto);

        int resultado = JOptionPane.showConfirmDialog(this, panel, "Nuevo proveedor", JOptionPane.OK_CANCEL_OPTION);
        if (resultado == JOptionPane.OK_OPTION) {
            try {
                Proveedor p = new Proveedor();
                p.setNombreProveedor(txtNombre.getText());
                p.setContacto(txtContacto.getText());
                proveedorControlador.guardar(p);
                cargarDatos();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editar() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un proveedor de la tabla");
            return;
        }
        int id = (int) modeloTabla.getValueAt(fila, 0);
        JTextField txtNombre = new JTextField((String) modeloTabla.getValueAt(fila, 1));
        JTextField txtContacto = new JTextField((String) modeloTabla.getValueAt(fila, 2));
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel("NombreProveedor:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Contacto:"));
        panel.add(txtContacto);

        int resultado = JOptionPane.showConfirmDialog(this, panel, "Editar proveedor", JOptionPane.OK_CANCEL_OPTION);
        if (resultado == JOptionPane.OK_OPTION) {
            try {
                Proveedor p = new Proveedor();
                p.setIdProveedor(id);
                p.setNombreProveedor(txtNombre.getText());
                p.setContacto(txtContacto.getText());
                proveedorControlador.actualizar(p);
                cargarDatos();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al editar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminar() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un proveedor de la tabla");
            return;
        }
        int id = (int) modeloTabla.getValueAt(fila, 0);
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Eliminar este proveedor?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                proveedorControlador.eliminar(id);
                cargarDatos();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
