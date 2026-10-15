/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toolcrib.modelo;

/**
 *
 * @author rogeer
 */
public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String contrasenaHash;
    private int idRol;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombreUsuario, String contrasenaHash, int idRol) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasenaHash = contrasenaHash;
        this.idRol = idRol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenaHash() {
        return contrasenaHash;
    }

    public void setContrasenaHash(String contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    @Override
    public String toString() {
        return "Usuario{idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + ", idRol=" + idRol + "}";
    }
}