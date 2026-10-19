package com.toolcrib.vista;

import com.toolcrib.controlador.PrestamoControlador;
import com.toolcrib.excepcion.StockInsuficienteException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Date;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelPrestamos extends JPanel {

    private final PrestamoControlador prestamoControlador = new PrestamoControlador();

    public PanelPrestamos() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Registrar prestamo ---
        JTextField txtIdEmpleadoPrestamo = new JTextField(10);
        JTextField txtFechaLimite = new JTextField(10);
        JTextField txtIdArticuloPrestamo = new JTextField(10);
        JTextField txtCantidadPrestamo = new JTextField(10);
        JButton btnRegistrarPrestamo = new JButton("Registrar prestamo");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Registrar prestamo"), gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("IdEmpleado:"), gbc);
        gbc.gridx = 1;
        add(txtIdEmpleadoPrestamo, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("FechaLimite (yyyy-MM-dd):"), gbc);
        gbc.gridx = 1;
        add(txtFechaLimite, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("IdArticulo:"), gbc);
        gbc.gridx = 1;
        add(txtIdArticuloPrestamo, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Cantidad:"), gbc);
        gbc.gridx = 1;
        add(txtCantidadPrestamo, gbc);

        gbc.gridy++;
        gbc.gridx = 1;
        add(btnRegistrarPrestamo, gbc);

        btnRegistrarPrestamo.addActionListener(e -> {
            try {
                int idEmpleado = Integer.parseInt(txtIdEmpleadoPrestamo.getText());
                Date fechaLimite = Date.valueOf(txtFechaLimite.getText());
                int idArticulo = Integer.parseInt(txtIdArticuloPrestamo.getText());
                int cantidad = Integer.parseInt(txtCantidadPrestamo.getText());
                prestamoControlador.registrarPrestamo(idEmpleado, fechaLimite, idArticulo, cantidad);
                JOptionPane.showMessageDialog(this, "Prestamo registrado con exito");
                txtIdEmpleadoPrestamo.setText("");
                txtFechaLimite.setText("");
                txtIdArticuloPrestamo.setText("");
                txtCantidadPrestamo.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "IdEmpleado, IdArticulo y Cantidad deben ser numeros", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "FechaLimite debe tener formato yyyy-MM-dd", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (StockInsuficienteException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Stock insuficiente", JOptionPane.WARNING_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al registrar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // --- Registrar devolucion ---
        JTextField txtIdPrestamoDevolucion = new JTextField(10);
        JTextField txtIdArticuloDevolucion = new JTextField(10);
        JButton btnRegistrarDevolucion = new JButton("Registrar devolucion");

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Registrar devolucion"), gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("IdPrestamo:"), gbc);
        gbc.gridx = 1;
        add(txtIdPrestamoDevolucion, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("IdArticulo:"), gbc);
        gbc.gridx = 1;
        add(txtIdArticuloDevolucion, gbc);

        gbc.gridy++;
        gbc.gridx = 1;
        add(btnRegistrarDevolucion, gbc);

        btnRegistrarDevolucion.addActionListener(e -> {
            try {
                int idPrestamo = Integer.parseInt(txtIdPrestamoDevolucion.getText());
                int idArticulo = Integer.parseInt(txtIdArticuloDevolucion.getText());
                prestamoControlador.registrarDevolucion(idPrestamo, idArticulo);
                JOptionPane.showMessageDialog(this, "Devolucion registrada con exito");
                txtIdPrestamoDevolucion.setText("");
                txtIdArticuloDevolucion.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "IdPrestamo e IdArticulo deben ser numeros", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al registrar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
