/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.model;

/**
 *
 * @author Laboratorio
 */
public class Usuario {
    
    int idUsuario;
    private String nombre;
    private String apellidos;
    private String correo;
    private String nombreUsuario;
    private String contrasenna;
    private String tipoUsuario;
    
    
    public Usuario() {
        
    }
    
    public Usuario(int idUsuario,String nombre,String apellidos,String correo,String nombreUsuario,String contrasenna,String tipoUsuario) {
           
         this.idUsuario = idUsuario;
         this.nombre = nombre;
         this.apellidos = apellidos;
         this.correo = correo;
         this.nombreUsuario = nombreUsuario;
         this.contrasenna = contrasenna;
         this.tipoUsuario = tipoUsuario;
         
     }
    
     public Usuario(String nombre,String apellidos,String correo,String nombreUsuario,String contrasenna,String tipoUsuario) {
           
         this.nombre = nombre;
         this.apellidos = apellidos;
         this.correo = correo;
         this.nombreUsuario = nombreUsuario;
         this.contrasenna = contrasenna;
         this.tipoUsuario = tipoUsuario;
         
     }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    
    public String toString(){
    
    
            return this.getNombre() + " " + this.getCorreo();
    }
}
