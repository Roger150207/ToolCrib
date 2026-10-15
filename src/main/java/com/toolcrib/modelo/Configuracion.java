/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toolcrib.modelo;

/**
 *
 * @author rogelio saldaña colorado 
 */
public class Configuracion {
    private int idConfiguracion;
    private String claveConfiguracion;
    private String valorConfiguracion;

    public Configuracion() {
    }

    public Configuracion(int idConfiguracion, String claveConfiguracion, String valorConfiguracion) {
        this.idConfiguracion = idConfiguracion;
        this.claveConfiguracion = claveConfiguracion;
        this.valorConfiguracion = valorConfiguracion;
    }

    public int getIdConfiguracion() {
        return idConfiguracion;
    }

    public void setIdConfiguracion(int idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }

    public String getClaveConfiguracion() {
        return claveConfiguracion;
    }

    public void setClaveConfiguracion(String claveConfiguracion) {
        this.claveConfiguracion = claveConfiguracion;
    }

    public String getValorConfiguracion() {
        return valorConfiguracion;
    }

    public void setValorConfiguracion(String valorConfiguracion) {
        this.valorConfiguracion = valorConfiguracion;
    }

    @Override
    public String toString() {
        return "Configuracion{idConfiguracion=" + idConfiguracion + ", claveConfiguracion=" + claveConfiguracion + ", valorConfiguracion=" + valorConfiguracion + "}";
    }
}