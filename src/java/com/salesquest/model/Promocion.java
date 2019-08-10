/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.model;

import com.salesquest.model.Categoria;

/**
 *
 * @author Kainthel
 */
public class Promocion {
    
    private int idPromocion;
    private String linkPromo;
    private String nombrePromo;
    private Categoria categoria;
    private int idUsuario;
    private boolean favorito = false;
    
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

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
    
}
