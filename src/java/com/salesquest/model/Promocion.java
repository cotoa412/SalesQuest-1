/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.model;

import java.util.Objects;

/**
 *
 * @author Kainthel
 */
public class Promocion {
    
    private int idPromocion;
    private String linkPromo;
    private String nombrePromo;
    private Categoria categoria;
    
    public Promocion() {
    }

    public Promocion(String linkPromo, String nombrePromo, Categoria categoria) {
        this.linkPromo = linkPromo;
        this.nombrePromo = nombrePromo;
        this.categoria = categoria;
    }

    public Promocion(int idPromocion, String linkPromo, String nombrePromo) {
        this.idPromocion = idPromocion;
        this.linkPromo = linkPromo;
        this.nombrePromo = nombrePromo;
    }
    
    public Promocion(int idPromocion, String linkPromo, String nombrePromo, Categoria categoria) {
        this.idPromocion = idPromocion;
        this.linkPromo = linkPromo;
        this.nombrePromo = nombrePromo;
        this.categoria = categoria;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getLinkPromo() {
        return linkPromo;
    }

    public void setLinkPromo(String linkPromo) {
        this.linkPromo = linkPromo;
    }

    public String getNombrePromo() {
        return nombrePromo;
    }

    public void setNombrePromo(String nombrePromo) {
        this.nombrePromo = nombrePromo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.idPromocion;
        hash = 37 * hash + Objects.hashCode(this.linkPromo);
        hash = 37 * hash + Objects.hashCode(this.nombrePromo);
        hash = 37 * hash + Objects.hashCode(this.categoria);
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
        final Promocion other = (Promocion) obj;
        if (this.idPromocion != other.idPromocion) {
            return false;
        }
        if (!Objects.equals(this.linkPromo, other.linkPromo)) {
            return false;
        }
        if (!Objects.equals(this.nombrePromo, other.nombrePromo)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        return true;
    }

    

    public String toString(){
   
       return this.getIdPromocion() + " " + this.getNombrePromo() + " " +this.getLinkPromo();
   } 
    
    
}
