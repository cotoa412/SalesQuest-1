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
public class PromoFavorita {
    private int idUsuario;
    private int idPromocion;

    public PromoFavorita(int idUsuario, int idPromocion) {
        this.idUsuario = idUsuario;
        this.idPromocion = idPromocion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }
    
    
}
