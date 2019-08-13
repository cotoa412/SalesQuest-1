/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.controller;

import com.salesquest.model.Categoria;
import com.salesquest.model.Promocion;
import com.salesquest.servicio.Servicio_Categoria;
import com.salesquest.servicio.Servicio_Promocion;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Kainthel
 */
@ManagedBean(name = "promoController")
@SessionScoped
public class PromoController {
        
    private Promocion promocion = new Promocion();
    private DualListModel<Promocion> favoritas;
    private List<Categoria> listaCategorias = new ArrayList<Categoria>();
    private List<Promocion> listaPromociones = new ArrayList<Promocion>();
    private List<Promocion> listaPromocionesDeportes = new ArrayList<Promocion>();
    private List<Promocion> listaPromocionesVideoJuegos = new ArrayList<Promocion>();
    
    public PromoController(){
        this.cargarListaCategorias();
        this.cargarPromociones();
        this.cargarListarXCategoria();
        this.iniciar();
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
    
    public List<Promocion> getListaPromociones() {
        return listaPromociones;
    }

    public void setListaPromociones(List<Promocion> listaPromociones) {
        this.listaPromociones = listaPromociones;
    }

    public List<Promocion> getListaPromocionesDeportes() {
        return listaPromocionesDeportes;
    }

    public void setListaPromocionesDeportes(List<Promocion> listaPromocionesDeportes) {
        this.listaPromocionesDeportes = listaPromocionesDeportes;
    }

    public List<Promocion> getListaPromocionesVideoJuegos() {
        return listaPromocionesVideoJuegos;
    }

    public void setListaPromocionesVideoJuegos(List<Promocion> listaPromocionesVideoJuegos) {
        this.listaPromocionesVideoJuegos = listaPromocionesVideoJuegos;
    }
      
    public void cargarListaCategorias(){
        
        Servicio_Categoria s = new Servicio_Categoria();
        
        
        for (Object o : s.mostrarDatos()) {
            listaCategorias.add((Categoria)o);
        }
    }

     public void cargarListarXCategoria(){
        
         Servicio_Promocion sp = new Servicio_Promocion();
         
         for (Object o : sp.mostrarDatos()) {
            
             if (((Promocion)o).getCategoria().getNombreCategoria().equalsIgnoreCase("Video Juegos")) {
                 this.listaPromocionesVideoJuegos.add(((Promocion)o));
             }else if(((Promocion)o).getCategoria().getNombreCategoria().equalsIgnoreCase("Deportes")){
                 this.listaPromocionesDeportes.add(((Promocion)o));
             }
             
        }
         
     }
    
    public void cargarPromociones(){
        
        Servicio_Promocion sp = new Servicio_Promocion();
        
        for (Object o : sp.mostrarDatos()) {
            this.listaPromociones.add((Promocion)o);
        }
         
    }
    
    public void redireccionar(){
    
    try {
           
            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            request.getContextPath()
                            + "/faces/agregarPromo.xhtml?faces-redirect=true");
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    
    
    }
    
    public void agregarPromo(){
          
        Servicio_Promocion sp = new Servicio_Promocion();
        
        if (this.promocion != null) {
            sp.insertarDato(this.promocion);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se agregó su promoción correctamente.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }else{
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe llenar todos los campos.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        
        this.promocion.setNombrePromo("");
        this.promocion.setLinkPromo("");
        
        this.cargarListaCategorias();
        this.cargarPromociones();
        this.cargarListarXCategoria();
        this.iniciar();
             
    }
    
    public void iniciar(){
    
        Servicio_Promocion sp = new Servicio_Promocion();
        
        List<Promocion> promosSource = new ArrayList<Promocion>();
        
        for (Object o : sp.mostrarDatos()) {
            promosSource.add((Promocion)o);
        }
        
        List<Promocion> promosTarget = new ArrayList<Promocion>();
         
        this.favoritas = new DualListModel<Promocion>(promosSource, promosTarget);
        
    
    }

    public DualListModel<Promocion> getFavoritas() {
        return favoritas;
    }

    public void setFavoritas(DualListModel<Promocion> favoritas) {
        this.favoritas = favoritas;
    }
    
    
      
}

