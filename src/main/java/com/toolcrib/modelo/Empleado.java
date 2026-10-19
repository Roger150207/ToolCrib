package com.toolcrib.modelo;

/**
 *
 * @author Rogelio Saldaña Colorado
 */
public class Empleado {
    // Aqui lo pongo como private para protegerlo de las otras clases
    private int idEmpleado;
    private String numEmpleado;
    private String nombre;
    private int turno;
    private boolean activo;
    private int idArea;
    
    // Creo el constructor y lod ejo vacio
    public Empleado (){
    }
    
    // Guardo con getter y con setter cambiamos cada metodo privado 
    
    // Id del Empleado 
    public int getIdEmpleado(){
        return idEmpleado;
    }
    
    public void setIdEmpleado (int idEmpleado){
        this.idEmpleado = idEmpleado;
    }
    
    //Numero de Empleado
    public String getNumEmpleado (){
        return numEmpleado;
    }
    
    public void setNumEmpleado (String numEmpleado){
        this.numEmpleado = numEmpleado;
    }
    
    // Nombre
    public String getNombre (){
        return nombre;
    }
    
    public void setNombre (String nombre){
        this.nombre = nombre;
    }
    
    // Turno
    public int getTurno (){
        return turno;
    }
    
    public void setTurno (int turno){
        this.turno = turno;
    }
    
    // Activo o no
    public boolean isActivo(){
        return activo;
    }
    
    public void setActivo (boolean activo){
        this.activo = activo;
    }
    
    // Area 
    public int getIdArea(){
        return idArea;
    }
    
    public void setIdArea (int idArea){
        this.idArea = idArea;
    }
    
    // Lo convierto a TooString para que depsues la ocnsola no me imprima valores aleatorios 
    @Override 
    public String toString () {
        return " Datos del Empleado : Id : " + idEmpleado +
                " Numero : " + numEmpleado + 
                " Nombre : " + nombre +
                " Turno : " + turno + 
                " ¿Esta Activo? " + activo + 
                " Area : " + idArea;
    }
}
