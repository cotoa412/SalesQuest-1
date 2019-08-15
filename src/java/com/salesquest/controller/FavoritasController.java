/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.controller;

import com.salesquest.model.Promocion;
import com.salesquest.servicio.ServicioFavorito;
import com.salesquest.servicio.Servicio_Promocion;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author Personal
 */
@ManagedBean(name = "favoritasController")
@SessionScoped
public class FavoritasController {
    
    
    private List<Promocion> favoritas = new ArrayList<>();
    private Promocion promoSeleccionada = new Promocion();
    private List<Promocion> promociones = new ArrayList<>();
        
    @ManagedProperty("#{loginController}")
    private LoginController u;
    
    public FavoritasController(){
        this.cargarPromos();
           
    }

    public List<Promocion> getFavoritas() {
        return favoritas;
    }

    public void setFavoritas(List<Promocion> favoritas) {
        this.favoritas = favoritas;
    }

    public LoginController getU() {
        return u;
    }

    public void setU(LoginController u) {
        this.u = u;
    }

    public Promocion getPromoSeleccionada(){
        return this.promoSeleccionada;
    }
    
    public void setPromoSeleccionada(Promocion promoSeleccionada){
        this.promoSeleccionada = promoSeleccionada;
    }

    public List<Promocion> getPromociones() {
        return promociones;
    }

    public void setPromociones(List<Promocion> promociones) {
        this.promociones = promociones;
    }

    public void cargarPromos(){
        
        Servicio_Promocion sp = new Servicio_Promocion();
    
        for (Object o : sp.mostrarDatos()) {
            this.promociones.add(((Promocion)o));
        }
    
    }
    
    public void redireccionarFavoritas(){
        
        this.cargarFavoritas();
        
        if (this.favoritas.isEmpty()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No tiene promociones favoritas guardadas.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }else{
          
            try {
           
            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            request.getContextPath()
                            + "/faces/PromosFavoritas.xhtml?faces-redirect=true");
            } catch (Exception e) {
                e.printStackTrace();
            }
        
        
        }
        
    }
    
    
    public void cargarFavoritas(){
        
        ServicioFavorito sf = new ServicioFavorito();
        
        List<Object> lista = new ArrayList<>();
        
        lista = sf.mostrarPromosFavoritas(this.u.getUsuario().getIdUsuario());
            
        for (Object o : lista) {
            this.favoritas.add(((Promocion)o));
        }
        
    }
    
   
     public void agregarAFavoritas(){
         
         ServicioFavorito sf = new ServicioFavorito();
         
         if (this.promoSeleccionada != null) {
             
             sf.insertarPromoFavorita(this.promoSeleccionada.getIdPromocion(), this.u.getUsuario().getIdUsuario());
             FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se agregó su promoción correctamente.");
             FacesContext.getCurrentInstance().addMessage(null, msg);
             
         }else{
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al guardar la promo.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
         }
         
     }
    
    
}