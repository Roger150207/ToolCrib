package com.toolcrib.vista;

import com.toolcrib.controlador.BitacoraControlador;
import com.toolcrib.modelo.Bitacora;
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

public class PanelBitacora extends JPanel {

    private final BitacoraControlador bitacoraControlador = new BitacoraControlador();
    private final DefaultTableModel modeloTabla;
    private final JTable tabla;

    public PanelBitacora() {
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel(new Object[]{"IdBitacora", "IdUsuario", "Accion", "Fecha"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla = new JTable(modeloTabla);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnRefrescar = new JButton("Refrescar");
        panelBotones.add(btnRefrescar);
        add(panelBotones, BorderLayout.SOUTH);

        btnRefrescar.addActionListener(e -> cargarDatos());

        cargarDatos();
    }

    // Traigo toda la bitacora, es solo lectura
    private void cargarDatos() {
        try {
            modeloTabla.setRowCount(0);
            List<Bitacora> registros = bitacoraControlador.listar();
            for (Bitacora b : registros) {
                modeloTabla.addRow(new Object[]{b.getIdBitacora(), b.getIdUsuario(), b.getAccion(), b.getFecha()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar bitacora: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
