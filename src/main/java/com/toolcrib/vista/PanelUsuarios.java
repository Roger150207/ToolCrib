package com.toolcrib.vista;

import com.toolcrib.controlador.UsuarioControlador;
import com.toolcrib.modelo.Usuario;
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

public class PanelUsuarios extends JPanel {

    private final UsuarioControlador usuarioControlador = new UsuarioControlador();
    private final DefaultTableModel modeloTabla;
    private final JTable tabla;

    public PanelUsuarios() {
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel(new Object[]{"IdUsuario", "NombreUsuario", "IdRol"}, 0) {
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

    // No muestro ContrasenaHash en la tabla por seguridad, solo lo pido en el formulario
    private void cargarDatos() {
        try {
            modeloTabla.setRowCount(0);
            List<Usuario> usuarios = usuarioControlador.listar();
            for (Usuario u : usuarios) {
                modeloTabla.addRow(new Object[]{u.getIdUsuario(), u.getNombreUsuario(), u.getIdRol()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar usuarios: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregar() {
        JTextField txtNombreUsuario = new JTextField();
        JTextField txtContrasena = new JTextField();
        JTextField txtIdRol = new JTextField();
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(new JLabel("NombreUsuario:"));
        panel.add(txtNombreUsuario);
        panel.add(new JLabel("ContrasenaHash:"));
        panel.add(txtContrasena);
        panel.add(new JLabel("IdRol:"));
        panel.add(txtIdRol);

        int resultado = JOptionPane.showConfirmDialog(this, panel, "Nuevo usuario", JOptionPane.OK_CANCEL_OPTION);
        if (resultado == JOptionPane.OK_OPTION) {
            try {
                Usuario u = new Usuario();
                u.setNombreUsuario(txtNombreUsuario.getText());
                u.setContrasenaHash(txtContrasena.getText());
                u.setIdRol(Integer.parseInt(txtIdRol.getText()));
                usuarioControlador.guardar(u);
                cargarDatos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "IdRol debe ser numero", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editar() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un usuario de la tabla");
            return;
        }
        int id = (int) modeloTabla.getValueAt(fila, 0);

        try {
            Usuario actual = usuarioControlador.buscarPorId(id);
            if (actual == null) {
                JOptionPane.showMessageDialog(this, "No se encontro el usuario");
                return;
            }

            JTextField txtNombreUsuario = new JTextField(actual.getNombreUsuario());
            JTextField txtContrasena = new JTextField(actual.getContrasenaHash());
            JTextField txtIdRol = new JTextField(String.valueOf(actual.getIdRol()));
            JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
            panel.add(new JLabel("NombreUsuario:"));
            panel.add(txtNombreUsuario);
            panel.add(new JLabel("ContrasenaHash:"));
            panel.add(txtContrasena);
            panel.add(new JLabel("IdRol:"));
            panel.add(txtIdRol);

            int resultado = JOptionPane.showConfirmDialog(this, panel, "Editar usuario", JOptionPane.OK_CANCEL_OPTION);
            if (resultado == JOptionPane.OK_OPTION) {
                actual.setNombreUsuario(txtNombreUsuario.getText());
                actual.setContrasenaHash(txtContrasena.getText());
                actual.setIdRol(Integer.parseInt(txtIdRol.getText()));
                usuarioControlador.actualizar(actual);
                cargarDatos();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "IdRol debe ser numero", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al editar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminar() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un usuario de la tabla");
            return;
        }
        int id = (int) modeloTabla.getValueAt(fila, 0);
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Eliminar este usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                usuarioControlador.eliminar(id);
                cargarDatos();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
