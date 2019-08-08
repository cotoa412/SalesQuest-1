/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.controller;

import com.salesquest.model.Categoria;
import com.salesquest.model.Promocion;
import com.salesquest.servicio.Servicio_Categoria;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Personal
 */
@ManagedBean(name = "promoController")
@SessionScoped
public class PromoController {
    
    private Promocion promocion;
    private List<Categoria> listaCategorias = new ArrayList<Categoria>();
    
    public PromoController(){
        this.cargarListaCategorias();
    }
    
    public PromoController(Promocion promo){
        this.promocion = promo;
    }
    
    public Promocion getPromocion() {
        return promocion;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }

    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }
    
    
    
    public void cargarListaCategorias(){
        
        Servicio_Categoria s = new Servicio_Categoria();

        for (Object o : s.mostrarDatos()) {
            listaCategorias.add((Categoria)o);
        }
        
    }
    
    
}
