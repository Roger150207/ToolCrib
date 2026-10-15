package com.toolcrib;

import com.toolcrib.dao.AreaDAO;
import com.toolcrib.dao.ArticuloDAO;
import com.toolcrib.dao.CategoriaDAO;
import com.toolcrib.dao.EmpleadoDAO;
import com.toolcrib.modelo.Area;
import com.toolcrib.modelo.Articulo;
import com.toolcrib.modelo.Categoria;
import com.toolcrib.modelo.Empleado;
import java.util.List;

public class Toolcrib {

    public static void main(String[] args) {
        try {
            // Pruebo empleados
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            List<Empleado> empleados = empleadoDAO.listarTodos();
            System.out.println("--- Empleados ---");
            for (Empleado e : empleados) {
                System.out.println(e);
            }

            // Pruebo articulos
            ArticuloDAO articuloDAO = new ArticuloDAO();
            List<Articulo> articulos = articuloDAO.listarTodos();
            System.out.println("--- Articulos ---");
            for (Articulo a : articulos) {
                System.out.println(a);
            }

            // Pruebo categorias
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            List<Categoria> categorias = categoriaDAO.listarTodos();
            System.out.println("--- Categorias ---");
            for (Categoria c : categorias) {
                System.out.println(c);
            }

            // Pruebo areas
            AreaDAO areaDAO = new AreaDAO();
            List<Area> areas = areaDAO.listarTodos();
            System.out.println("--- Areas ---");
            for (Area ar : areas) {
                System.out.println(ar);
            }

        } catch (Exception e) {
            System.out.println("Error:");
            e.printStackTrace();
        }
    }
}