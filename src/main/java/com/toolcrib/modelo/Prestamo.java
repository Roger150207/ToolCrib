/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toolcrib.modelo;

import java.sql.Timestamp;
import java.sql.Date;
/**
 *
 * @author Saldaña Colorado Rogelio
 */
public class Prestamo {
    // Defino las variables que habitan en la tabla SQL de prestmaos
    private int idPrestamo;
    private int idEmpleado;
    private Timestamp fechaPrestamo;
    private Date fechaLimite;
    private String estado;
    
    // Los cambiamos a get y set cada uno 
    public int getIdPrestamo() {
        return idPrestamo;
    }
    
    public void setIdPrestamo(int idPrestamo)
    {
        this.idPrestamo = idPrestamo;
    }
    
    public int getIdEmpleado() {
        return idEmpleado;
    }
    
    public void setIdEmpleado (int idEmpleado)
    {
        this.idEmpleado = idEmpleado;
    }
    
    public Timestamp getFechaPrestamo(){
        return fechaPrestamo;
    }
    
    public void setFechaPrestamo(Timestamp fechaPrestamo){
        this.fechaPrestamo = fechaPrestamo;
    }
    
    public Date getFechaLimite(){
        return fechaLimite;
    }
    
    public void setFechaLimite(Date fechaLimite){
        this.fechaLimite = fechaLimite;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado)
    {
        this.estado = estado;
    }
    
    // Ahora para finalizar lo convierto en string 
    @Override
    public String toString(){
        return 
                " Datos del prestamo : ID : " + idPrestamo +
                " ID del Empleado : " + idEmpleado + 
                " Fecha de Prestamo : " + fechaPrestamo + 
                " Fecha Limite : " + fechaLimite + 
                " Estado : " + estado +
                ".";
    }
}