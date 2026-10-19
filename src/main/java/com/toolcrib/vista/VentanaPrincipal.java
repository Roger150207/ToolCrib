package com.toolcrib.vista;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("ToolCrib");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        JTabbedPane pestañas = new JTabbedPane();
        pestañas.addTab("Empleados", new PanelEmpleados());
        pestañas.addTab("Articulos", new PanelArticulos());
        pestañas.addTab("Categorias", new PanelCategorias());
        pestañas.addTab("Areas", new PanelAreas());
        pestañas.addTab("Roles", new PanelRoles());
        pestañas.addTab("Proveedores", new PanelProveedores());
        pestañas.addTab("Configuracion", new PanelConfiguracion());
        pestañas.addTab("Usuarios", new PanelUsuarios());
        pestañas.addTab("Prestamos", new PanelPrestamos());
        pestañas.addTab("Salidas", new PanelSalidas());
        pestañas.addTab("Entradas", new PanelEntradas());
        pestañas.addTab("Movimientos", new PanelMovimientos());
        pestañas.addTab("Bitacora", new PanelBitacora());

        add(pestañas);
    }

    // Punto de entrada para correr la interfaz grafica
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}
