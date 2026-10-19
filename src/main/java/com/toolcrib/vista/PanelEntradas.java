package com.toolcrib.vista;

import com.toolcrib.controlador.EntradaControlador;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelEntradas extends JPanel {

    private final EntradaControlador entradaControlador = new EntradaControlador();

    public PanelEntradas() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtIdProveedor = new JTextField(10);
        JTextField txtIdArticulo = new JTextField(10);
        JTextField txtCantidad = new JTextField(10);
        JTextField txtCostoUnitario = new JTextField(10);
        JButton btnRegistrar = new JButton("Registrar entrada");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("IdProveedor:"), gbc);
        gbc.gridx = 1;
        add(txtIdProveedor, gbc);

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
        gbc.gridx = 0;
        add(new JLabel("CostoUnitario:"), gbc);
        gbc.gridx = 1;
        add(txtCostoUnitario, gbc);

        gbc.gridy++;
        gbc.gridx = 1;
        add(btnRegistrar, gbc);

        btnRegistrar.addActionListener(e -> {
            try {
                int idProveedor = Integer.parseInt(txtIdProveedor.getText());
                int idArticulo = Integer.parseInt(txtIdArticulo.getText());
                int cantidad = Integer.parseInt(txtCantidad.getText());
                BigDecimal costoUnitario = new BigDecimal(txtCostoUnitario.getText());
                entradaControlador.registrarEntrada(idProveedor, idArticulo, cantidad, costoUnitario);
                JOptionPane.showMessageDialog(this, "Entrada registrada con exito");
                txtIdProveedor.setText("");
                txtIdArticulo.setText("");
                txtCantidad.setText("");
                txtCostoUnitario.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "IdProveedor, IdArticulo, Cantidad y CostoUnitario deben ser numeros", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al registrar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
