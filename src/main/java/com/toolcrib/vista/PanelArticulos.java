package com.toolcrib.vista;

import com.toolcrib.controlador.ArticuloControlador;
import com.toolcrib.modelo.Articulo;
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

public class PanelArticulos extends JPanel {

    private final ArticuloControlador articuloControlador = new ArticuloControlador();
    private final DefaultTableModel modeloTabla;
    private final JTable tabla;

    public PanelArticulos() {
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel(new Object[]{"IdArticulo", "IdCategoria", "Codigo", "Nombre", "Ubicacion", "Stock", "StockMinimo"}, 0) {
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
        JButton btnDarDeBaja = new JButton("Dar de baja");
        JButton btnRefrescar = new JButton("Refrescar");
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnDarDeBaja);
        panelBotones.add(btnRefrescar);
        add(panelBotones, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> agregar());
        btnEditar.addActionListener(e -> editar());
        btnEliminar.addActionListener(e -> eliminar());
        btnDarDeBaja.addActionListener(e -> darDeBaja());
        btnRefrescar.addActionListener(e -> cargarDatos());

        cargarDatos();
    }

    // Traigo todos los articulos y lleno la tabla
    private void cargarDatos() {
        try {
            modeloTabla.setRowCount(0);
            List<Articulo> articulos = articuloControlador.listar();
            for (Articulo art : articulos) {
                modeloTabla.addRow(new Object[]{
                    art.getIdArticulo(), art.getIdCategoria(), art.getCodigo(), art.getNombre(),
                    art.getUbicacion(), art.getStock(), art.getStockMinimo()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar articulos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Abro el formulario para agregar un nuevo articulo
    private void agregar() {
        JTextField txtIdCategoria = new JTextField();
        JTextField txtCodigo = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtUbicacion = new JTextField();
        JTextField txtStock = new JTextField();
        JTextField txtStockMinimo = new JTextField();

        JPanel panel = construirFormulario(txtIdCategoria, txtCodigo, txtNombre, txtUbicacion, txtStock, txtStockMinimo);

        int resultado = JOptionPane.showConfirmDialog(this, panel, "Nuevo articulo", JOptionPane.OK_CANCEL_OPTION);
        if (resultado == JOptionPane.OK_OPTION) {
            try {
                Articulo art = new Articulo();
                art.setIdCategoria(Integer.parseInt(txtIdCategoria.getText()));
                art.setCodigo(txtCodigo.getText());
                art.setNombre(txtNombre.getText());
                art.setUbicacion(txtUbicacion.getText());
                art.setStock(Integer.parseInt(txtStock.getText()));
                art.setStockMinimo(Integer.parseInt(txtStockMinimo.getText()));
                articuloControlador.guardar(art);
                cargarDatos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "IdCategoria, Stock y StockMinimo deben ser numeros", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Abro el formulario para editar el articulo seleccionado
    private void editar() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un articulo de la tabla");
            return;
        }

        int idArticulo = (int) modeloTabla.getValueAt(fila, 0);

        try {
            Articulo art = articuloControlador.buscarPorId(idArticulo);
            if (art == null) {
                JOptionPane.showMessageDialog(this, "No se encontro el articulo");
                return;
            }

            JTextField txtIdCategoria = new JTextField(String.valueOf(art.getIdCategoria()));
            JTextField txtCodigo = new JTextField(art.getCodigo());
            JTextField txtNombre = new JTextField(art.getNombre());
            JTextField txtUbicacion = new JTextField(art.getUbicacion());
            JTextField txtStock = new JTextField(String.valueOf(art.getStock()));
            JTextField txtStockMinimo = new JTextField(String.valueOf(art.getStockMinimo()));

            JPanel panel = construirFormulario(txtIdCategoria, txtCodigo, txtNombre, txtUbicacion, txtStock, txtStockMinimo);

            int resultado = JOptionPane.showConfirmDialog(this, panel, "Editar articulo", JOptionPane.OK_CANCEL_OPTION);
            if (resultado == JOptionPane.OK_OPTION) {
                art.setIdCategoria(Integer.parseInt(txtIdCategoria.getText()));
                art.setCodigo(txtCodigo.getText());
                art.setNombre(txtNombre.getText());
                art.setUbicacion(txtUbicacion.getText());
                art.setStock(Integer.parseInt(txtStock.getText()));
                art.setStockMinimo(Integer.parseInt(txtStockMinimo.getText()));
                articuloControlador.actualizar(art);
                cargarDatos();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "IdCategoria, Stock y StockMinimo deben ser numeros", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al editar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Elimino el articulo seleccionado
    private void eliminar() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un articulo de la tabla");
            return;
        }

        int idArticulo = (int) modeloTabla.getValueAt(fila, 0);
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Eliminar este articulo?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                articuloControlador.eliminar(idArticulo);
                cargarDatos();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Doy de baja el articulo seleccionado usando el procedimiento almacenado
    private void darDeBaja() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un articulo de la tabla");
            return;
        }

        int idArticulo = (int) modeloTabla.getValueAt(fila, 0);
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Dar de baja este articulo?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                articuloControlador.darDeBaja(idArticulo);
                cargarDatos();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al dar de baja: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private JPanel construirFormulario(JTextField txtIdCategoria, JTextField txtCodigo, JTextField txtNombre, JTextField txtUbicacion, JTextField txtStock, JTextField txtStockMinimo) {
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.add(new JLabel("IdCategoria:"));
        panel.add(txtIdCategoria);
        panel.add(new JLabel("Codigo:"));
        panel.add(txtCodigo);
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Ubicacion:"));
        panel.add(txtUbicacion);
        panel.add(new JLabel("Stock:"));
        panel.add(txtStock);
        panel.add(new JLabel("StockMinimo:"));
        panel.add(txtStockMinimo);
        return panel;
    }
}
