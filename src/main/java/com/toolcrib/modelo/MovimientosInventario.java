package com.toolcrib.modelo;

import java.sql.Timestamp;
/**
 *
 * @author rogeer
 */
public class MovimientosInventario {
    private int idMovimiento;
    private int idArticulo;
    private String tipoMovimiento;
    private int cantidad;
    private Timestamp fecha;
    
    // Ahora paso los metodos con get y set a publico 
    public int getIdMovimiento(){
        return idMovimiento;
    }
    
    public void setIdMovimiento(int idMovimiento){
        this.idMovimiento = idMovimiento;
    }
    
    // 2
    public int getIdArticulo(){
        return idArticulo;
    }
    
    public void setIdArticulo(int idArticulo){
        this.idArticulo = idArticulo;
    }
    
    // 3
    public String getTipoMovimiento(){
        return tipoMovimiento;
    }
    
    public void setTipoMovimiento(String tipoMovimiento){
        this.tipoMovimiento = tipoMovimiento;
    }
    
    // 4
    public int getCantidad(){
        return cantidad;
    }
    
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    
    // 5
    public Timestamp getFecha(){
        return fecha;
    }
    
    public void setFecha(Timestamp fecha){
        this.fecha = fecha;
    }

    @Override
    public String toString(){
        return "MovimientosInventario{idMovimiento=" + idMovimiento +
                ", idArticulo=" + idArticulo +
                ", tipoMovimiento=" + tipoMovimiento +
                ", cantidad=" + cantidad +
                ", fecha=" + fecha + "}";
    }
}