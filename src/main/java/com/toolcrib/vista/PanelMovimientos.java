package com.toolcrib.vista;

import com.toolcrib.controlador.MovimientosInventarioControlador;
import com.toolcrib.modelo.MovimientosInventario;
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

public class PanelMovimientos extends JPanel {

    private final MovimientosInventarioControlador movimientosControlador = new MovimientosInventarioControlador();
    private final DefaultTableModel modeloTabla;
    private final JTable tabla;

    public PanelMovimientos() {
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel(new Object[]{"IdMovimiento", "IdArticulo", "TipoMovimiento", "Cantidad", "Fecha"}, 0) {
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

    // Traigo todos los movimientos de inventario, es solo lectura
    private void cargarDatos() {
        try {
            modeloTabla.setRowCount(0);
            List<MovimientosInventario> movimientos = movimientosControlador.listar();
            for (MovimientosInventario m : movimientos) {
                modeloTabla.addRow(new Object[]{
                    m.getIdMovimiento(), m.getIdArticulo(), m.getTipoMovimiento(), m.getCantidad(), m.getFecha()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar movimientos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
