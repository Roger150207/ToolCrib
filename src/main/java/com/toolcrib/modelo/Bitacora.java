package com.toolcrib.modelo;

import java.sql.Timestamp;

public class Bitacora {
    private int idBitacora;
    private int idUsuario;
    private String accion;
    private Timestamp fecha;

    public Bitacora() {
    }

    public Bitacora(int idBitacora, int idUsuario, String accion, Timestamp fecha) {
        this.idBitacora = idBitacora;
        this.idUsuario = idUsuario;
        this.accion = accion;
        this.fecha = fecha;
    }

    public int getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(int idBitacora) {
        this.idBitacora = idBitacora;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Bitacora{idBitacora=" + idBitacora + ", idUsuario=" + idUsuario + ", accion=" + accion + ", fecha=" + fecha + "}";
    }
}