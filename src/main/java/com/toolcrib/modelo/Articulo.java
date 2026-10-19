/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toolcrib.modelo;

/**
 *
 * @author rogeer
 */
public class Articulo {
    // Nuevamente los ponemos en privado 
    private int idArticulo;
    private int idCategoria;
    private String codigo;
    private String nombre;
    private String ubicacion;
    private int stock;
    private int stockMinimo;

    // constructor vacio 
    public Articulo(){
    }

    // Pasamos a publico el get y set
    public int getIdArticulo(){
        return idArticulo;
    }

    public void setIdArticulo (int idArticulo){
        this.idArticulo = idArticulo;
    }

    public int getIdCategoria(){
        return idCategoria;
    }

    public void setIdCategoria (int idCategoria){
        this.idCategoria = idCategoria;
    }

    public String getCodigo(){
        return codigo;
    }

    public void setCodigo (String codigo){
        this.codigo = codigo;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre (String nombre){
        this.nombre = nombre;
    }

    public String getUbicacion (){
        return ubicacion;
    }

    public void setUbicacion (String ubicacion){
        this.ubicacion = ubicacion;
    }

    public int getStock (){
        return stock;
    }

    public void setStock (int stock){
        this.stock = stock;
    }

    public int getStockMinimo (){
        return stockMinimo;
    }

    public void setStockMinimo (int stockMinimo){
        this.stockMinimo = stockMinimo;
    }

    @Override 
    public String toString() {
        return 
        " Datos del Articulo : ID : " + idArticulo +
        " ID Categoria : " + idCategoria + 
        " Codigo : " + codigo +
        " Nombre : " + nombre + 
        " Ubicacion : " + ubicacion + 
        " Stock : " + stock + 
        " Stock Minimo : " + stockMinimo +
        ".";
    }
}
