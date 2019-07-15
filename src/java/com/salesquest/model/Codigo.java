/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.model;

/**
 *
 * @author Personal
 */
public class Codigo {
    
    private int usuario;
    private String codigo;
    
    public Codigo(){}
    
    public Codigo(int usuario,String codigo){
        this.usuario = usuario;
        this.codigo = codigo;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    
}
