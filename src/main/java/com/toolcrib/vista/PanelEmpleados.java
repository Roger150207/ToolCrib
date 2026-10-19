package com.toolcrib.vista;

import com.toolcrib.controlador.EmpleadoControlador;
import com.toolcrib.modelo.Empleado;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PanelEmpleados extends JPanel {

    private final EmpleadoControlador empleadoControlador = new EmpleadoControlador();
    private final DefaultTableModel modeloTabla;
    private final JTable tabla;

    public PanelEmpleados() {
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel(new Object[]{"IdEmpleado", "NumEmpleado", "Nombre", "Turno", "Activo", "IdArea"}, 0) {
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

    // Traigo todos los empleados y lleno la tabla
    private void cargarDatos() {
        try {
            modeloTabla.setRowCount(0);
            List<Empleado> empleados = empleadoControlador.listar();
            for (Empleado emp : empleados) {
                modeloTabla.addRow(new Object[]{
                    emp.getIdEmpleado(), emp.getNumEmpleado(), emp.getNombre(),
                    emp.getTurno(), emp.isActivo(), emp.getIdArea()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar empleados: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Abro el formulario para agregar un nuevo empleado
    private void agregar() {
        JTextField txtNumEmpleado = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtTurno = new JTextField();
        JCheckBox chkActivo = new JCheckBox("Activo", true);
        JTextField txtIdArea = new JTextField();

        JPanel panel = construirFormulario(txtNumEmpleado, txtNombre, txtTurno, chkActivo, txtIdArea);

        int resultado = JOptionPane.showConfirmDialog(this, panel, "Nuevo empleado", JOptionPane.OK_CANCEL_OPTION);
        if (resultado == JOptionPane.OK_OPTION) {
            try {
                Empleado emp = new Empleado();
                emp.setNumEmpleado(txtNumEmpleado.getText());
                emp.setNombre(txtNombre.getText());
                emp.setTurno(Integer.parseInt(txtTurno.getText()));
                emp.setActivo(chkActivo.isSelected());
                emp.setIdArea(Integer.parseInt(txtIdArea.getText()));
                empleadoControlador.guardar(emp);
                cargarDatos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Turno e IdArea deben ser numeros", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Abro el formulario para editar el empleado seleccionado
    private void editar() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un empleado de la tabla");
            return;
        }

        int idEmpleado = (int) modeloTabla.getValueAt(fila, 0);

        try {
            Empleado emp = empleadoControlador.buscarPorId(idEmpleado);
            if (emp == null) {
                JOptionPane.showMessageDialog(this, "No se encontro el empleado");
                return;
            }

            JTextField txtNumEmpleado = new JTextField(emp.getNumEmpleado());
            JTextField txtNombre = new JTextField(emp.getNombre());
            JTextField txtTurno = new JTextField(String.valueOf(emp.getTurno()));
            JCheckBox chkActivo = new JCheckBox("Activo", emp.isActivo());
            JTextField txtIdArea = new JTextField(String.valueOf(emp.getIdArea()));

            JPanel panel = construirFormulario(txtNumEmpleado, txtNombre, txtTurno, chkActivo, txtIdArea);

            int resultado = JOptionPane.showConfirmDialog(this, panel, "Editar empleado", JOptionPane.OK_CANCEL_OPTION);
            if (resultado == JOptionPane.OK_OPTION) {
                emp.setNumEmpleado(txtNumEmpleado.getText());
                emp.setNombre(txtNombre.getText());
                emp.setTurno(Integer.parseInt(txtTurno.getText()));
                emp.setActivo(chkActivo.isSelected());
                emp.setIdArea(Integer.parseInt(txtIdArea.getText()));
                empleadoControlador.actualizar(emp);
                cargarDatos();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Turno e IdArea deben ser numeros", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al editar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Elimino el empleado seleccionado
    private void eliminar() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un empleado de la tabla");
            return;
        }

        int idEmpleado = (int) modeloTabla.getValueAt(fila, 0);
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Eliminar este empleado?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                empleadoControlador.eliminar(idEmpleado);
                cargarDatos();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private JPanel construirFormulario(JTextField txtNumEmpleado, JTextField txtNombre, JTextField txtTurno, JCheckBox chkActivo, JTextField txtIdArea) {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.add(new javax.swing.JLabel("NumEmpleado:"));
        panel.add(txtNumEmpleado);
        panel.add(new javax.swing.JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new javax.swing.JLabel("Turno:"));
        panel.add(txtTurno);
        panel.add(new javax.swing.JLabel("IdArea:"));
        panel.add(txtIdArea);
        panel.add(new javax.swing.JLabel(""));
        panel.add(chkActivo);
        return panel;
    }
}
