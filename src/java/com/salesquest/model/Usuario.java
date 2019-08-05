/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.model;

import java.util.Date;

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
    private TipoUsuario tipoUsuario;
    private String direccion;
    private Date fechaNacimiento;
    private String telefono;
    private EstadoCivil estadoCivil;
    private Categoria categoriaFavorita;
    
    
    
    public Usuario() {
        
    }
    
    public Usuario(int idUsuario,String nombre,String apellidos,String correo,String nombreUsuario,String contrasenna,TipoUsuario tipoUsuario) {
           
         this.idUsuario = idUsuario;
         this.nombre = nombre;
         this.apellidos = apellidos;
         this.correo = correo;
         this.nombreUsuario = nombreUsuario;
         this.contrasenna = contrasenna;
         this.tipoUsuario = tipoUsuario;
         
     }
    
    public Usuario(int idUsuario,String nombre,String apellidos,String correo,String nombreUsuario,String contrasenna,TipoUsuario tipoUsuario,String direccion,String telefono,EstadoCivil estadoCivil,Date fechaNacimiento,Categoria categoriaFavorita) {
           
         this.idUsuario = idUsuario;
         this.nombre = nombre;
         this.apellidos = apellidos;
         this.correo = correo;
         this.nombreUsuario = nombreUsuario;
         this.contrasenna = contrasenna;
         this.tipoUsuario = tipoUsuario;
         this.direccion = direccion;
         this.telefono = telefono;
         this.estadoCivil = estadoCivil;
         this.fechaNacimiento = fechaNacimiento;
         this.categoriaFavorita = categoriaFavorita;
         
     }
    
     public Usuario(String nombre,String apellidos,String correo,String nombreUsuario,String contrasenna,TipoUsuario tipoUsuario) {
           
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

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Categoria getCategoriaFavorita() {
        return categoriaFavorita;
    }

    public void setCategoriaFavorita(Categoria categoriaFavorita) {
        this.categoriaFavorita = categoriaFavorita;
    }
    
   
    
    
    public String toString(){
    
    
            return this.getNombre() + " " + this.getCorreo();
    }
}
