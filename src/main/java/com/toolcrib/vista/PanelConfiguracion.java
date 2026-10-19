package com.toolcrib.vista;

import com.toolcrib.controlador.ConfiguracionControlador;
import com.toolcrib.modelo.Configuracion;
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

public class PanelConfiguracion extends JPanel {

    private final ConfiguracionControlador configuracionControlador = new ConfiguracionControlador();
    private final DefaultTableModel modeloTabla;
    private final JTable tabla;

    public PanelConfiguracion() {
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel(new Object[]{"IdConfiguracion", "ClaveConfiguracion", "ValorConfiguracion"}, 0) {
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
            List<Configuracion> configuraciones = configuracionControlador.listar();
            for (Configuracion c : configuraciones) {
                modeloTabla.addRow(new Object[]{c.getIdConfiguracion(), c.getClaveConfiguracion(), c.getValorConfiguracion()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar configuracion: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregar() {
        JTextField txtClave = new JTextField();
        JTextField txtValor = new JTextField();
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel("ClaveConfiguracion:"));
        panel.add(txtClave);
        panel.add(new JLabel("ValorConfiguracion:"));
        panel.add(txtValor);

        int resultado = JOptionPane.showConfirmDialog(this, panel, "Nueva configuracion", JOptionPane.OK_CANCEL_OPTION);
        if (resultado == JOptionPane.OK_OPTION) {
            try {
                Configuracion c = new Configuracion();
                c.setClaveConfiguracion(txtClave.getText());
                c.setValorConfiguracion(txtValor.getText());
                configuracionControlador.guardar(c);
                cargarDatos();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editar() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una configuracion de la tabla");
            return;
        }
        int id = (int) modeloTabla.getValueAt(fila, 0);
        JTextField txtClave = new JTextField((String) modeloTabla.getValueAt(fila, 1));
        JTextField txtValor = new JTextField((String) modeloTabla.getValueAt(fila, 2));
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel("ClaveConfiguracion:"));
        panel.add(txtClave);
        panel.add(new JLabel("ValorConfiguracion:"));
        panel.add(txtValor);

        int resultado = JOptionPane.showConfirmDialog(this, panel, "Editar configuracion", JOptionPane.OK_CANCEL_OPTION);
        if (resultado == JOptionPane.OK_OPTION) {
            try {
                Configuracion c = new Configuracion();
                c.setIdConfiguracion(id);
                c.setClaveConfiguracion(txtClave.getText());
                c.setValorConfiguracion(txtValor.getText());
                configuracionControlador.actualizar(c);
                cargarDatos();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al editar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminar() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una configuracion de la tabla");
            return;
        }
        int id = (int) modeloTabla.getValueAt(fila, 0);
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Eliminar esta configuracion?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                configuracionControlador.eliminar(id);
                cargarDatos();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
