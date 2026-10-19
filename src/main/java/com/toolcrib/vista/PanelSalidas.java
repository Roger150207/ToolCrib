package com.toolcrib.vista;

import com.toolcrib.controlador.SalidaControlador;
import com.toolcrib.excepcion.StockInsuficienteException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelSalidas extends JPanel {

    private final SalidaControlador salidaControlador = new SalidaControlador();

    public PanelSalidas() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtIdEmpleado = new JTextField(10);
        JTextField txtIdArea = new JTextField(10);
        JTextField txtMotivo = new JTextField(15);
        JTextField txtIdArticulo = new JTextField(10);
        JTextField txtCantidad = new JTextField(10);
        JButton btnRegistrar = new JButton("Registrar salida");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("IdEmpleado:"), gbc);
        gbc.gridx = 1;
        add(txtIdEmpleado, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("IdArea:"), gbc);
        gbc.gridx = 1;
        add(txtIdArea, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Motivo:"), gbc);
        gbc.gridx = 1;
        add(txtMotivo, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("IdArticulo:"), gbc);
        gbc.gridx = 1;
        add(txtIdArticulo, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Cantidad:"), gbc);
        gbc.gridx = 1;
        add(txtCantidad, gbc);

        gbc.gridy++;
        gbc.gridx = 1;
        add(btnRegistrar, gbc);

        btnRegistrar.addActionListener(e -> {
            try {
                int idEmpleado = Integer.parseInt(txtIdEmpleado.getText());
                int idArea = Integer.parseInt(txtIdArea.getText());
                String motivo = txtMotivo.getText();
                int idArticulo = Integer.parseInt(txtIdArticulo.getText());
                int cantidad = Integer.parseInt(txtCantidad.getText());
                salidaControlador.registrarSalida(idEmpleado, idArea, motivo, idArticulo, cantidad);
                JOptionPane.showMessageDialog(this, "Salida registrada con exito");
                txtIdEmpleado.setText("");
                txtIdArea.setText("");
                txtMotivo.setText("");
                txtIdArticulo.setText("");
                txtCantidad.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "IdEmpleado, IdArea, IdArticulo y Cantidad deben ser numeros", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (StockInsuficienteException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Stock insuficiente", JOptionPane.WARNING_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al registrar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
