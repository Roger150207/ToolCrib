/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toolcrib.modelo;

/**
 *
 * @author saldaña colorado rogelio
 */
public class Proveedor {
    private int idProveedor;
    private String nombreProveedor;
    private String contacto;

    public Proveedor() {
    }

    public Proveedor(int idProveedor, String nombreProveedor, String contacto) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.contacto = contacto;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    @Override
    public String toString() {
        return "Proveedor{idProveedor=" + idProveedor + ", nombreProveedor=" + nombreProveedor + ", contacto=" + contacto + "}";
    }
}
