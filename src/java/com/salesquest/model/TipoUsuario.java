/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.model;

import java.util.Objects;

/**
 *
 * @author Laboratorio
 */
public class TipoUsuario {
    
    private int idTipoUsuario;
    private String nombreTipoUsuario;

    public TipoUsuario() {
        
    }
    
    public TipoUsuario(int idTipoUsuario, String nombreTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
        this.nombreTipoUsuario = nombreTipoUsuario;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getNombreTipoUsuario() {
        return nombreTipoUsuario;
    }

    public void setNombreTipoUsuario(String nombreTipoUsuario) {
        this.nombreTipoUsuario = nombreTipoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.idTipoUsuario;
        hash = 29 * hash + Objects.hashCode(this.nombreTipoUsuario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoUsuario other = (TipoUsuario) obj;
        if (this.idTipoUsuario != other.idTipoUsuario) {
            return false;
        }
        if (!Objects.equals(this.nombreTipoUsuario, other.nombreTipoUsuario)) {
            return false;
        }
        return true;
    }
    
    
}
