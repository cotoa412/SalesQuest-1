/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.model;

import java.util.Objects;

/**
 *
 * @author Personal
 */
public class EstadoCivil {
    
    private int idEstadoCivil;
    private String nombreEstadoCivil;
    
    public EstadoCivil(){
    
    }
    
    public EstadoCivil(int idEstadoCivil,String nombreEstadoCivil){
    
        this.idEstadoCivil = idEstadoCivil;
        this.nombreEstadoCivil = nombreEstadoCivil;
        
    }

    public int getIdEstadoCivil() {
        return idEstadoCivil;
    }

    public void setIdEstadoCivil(int idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    public String getNombreEstadoCivil() {
        return nombreEstadoCivil;
    }

    public void setNombreEstadoCivil(String nombreEstadoCivil) {
        this.nombreEstadoCivil = nombreEstadoCivil;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idEstadoCivil;
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
        final EstadoCivil other = (EstadoCivil) obj;
        if (this.idEstadoCivil != other.idEstadoCivil) {
            return false;
        }
        if (!Objects.equals(this.nombreEstadoCivil, other.nombreEstadoCivil)) {
            return false;
        }
        return true;
    }
    
    
    
}
