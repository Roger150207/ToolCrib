package com.toolcrib.vista;

import com.toolcrib.controlador.AreaControlador;
import com.toolcrib.modelo.Area;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelAreas extends JPanel {

    private final AreaControlador areaControlador = new AreaControlador();
    private final DefaultTableModel modeloTabla;
    private final JTable tabla;

    public PanelAreas() {
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel(new Object[]{"IdArea", "NombreArea"}, 0) {
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
            List<Area> areas = areaControlador.listar();
            for (Area a : areas) {
                modeloTabla.addRow(new Object[]{a.getIdArea(), a.getNombreArea()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar areas: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregar() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del area:");
        if (nombre != null && !nombre.isBlank()) {
            try {
                Area a = new Area();
                a.setNombreArea(nombre);
                areaControlador.guardar(a);
                cargarDatos();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editar() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un area de la tabla");
            return;
        }
        int id = (int) modeloTabla.getValueAt(fila, 0);
        String nombreActual = (String) modeloTabla.getValueAt(fila, 1);
        String nuevoNombre = JOptionPane.showInputDialog(this, "Nombre del area:", nombreActual);
        if (nuevoNombre != null && !nuevoNombre.isBlank()) {
            try {
                Area a = new Area();
                a.setIdArea(id);
                a.setNombreArea(nuevoNombre);
                areaControlador.actualizar(a);
                cargarDatos();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al editar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminar() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un area de la tabla");
            return;
        }
        int id = (int) modeloTabla.getValueAt(fila, 0);
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Eliminar esta area?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                areaControlador.eliminar(id);
                cargarDatos();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
