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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author Kainthel
 */
@ManagedBean(name = "promoController")
@SessionScoped
public class PromoController {
        
    private Promocion promocion = new Promocion();
    private Promocion promoBuscar = new Promocion();
    private List<Categoria> listaCategorias = new ArrayList<Categoria>();
    private List<Promocion> listaPromociones = new ArrayList<Promocion>();
    private List<Promocion> listaPromocionesDeportes = new ArrayList<Promocion>();
    private List<Promocion> listaPromocionesVideoJuegos = new ArrayList<Promocion>();
    
    @ManagedProperty("#{loginController}")
    private LoginController usuario;
    
    public PromoController(){
        this.cargarListaCategorias();
        this.cargarPromociones();
        this.cargarListarXCategoria();
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

    public Promocion getPromoBuscar() {
        return promoBuscar;
    }

    public void setPromoBuscar(Promocion promoBuscar) {
        this.promoBuscar = promoBuscar;
    }
    
    public void buscarPromo(){
    
        
            for (Object obj : this.listaPromociones) {
                if (((Promocion)obj).getNombrePromo().equalsIgnoreCase(this.promoBuscar.getNombrePromo())) {
                    
                    this.promoBuscar = ((Promocion)obj);
                    
                }
            }
        
            if (promoBuscar != null) {
            
                try {    
                    HttpServletRequest request = (HttpServletRequest) FacesContext
                            .getCurrentInstance().getExternalContext().getRequest();
                    FacesContext
                            .getCurrentInstance()
                            .getExternalContext()
                            .redirect(
                                    request.getContextPath()
                                    + "/faces/newxhtml.xhtml?faces-redirect=true");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
        }else{
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se encontro la promocion.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        
        
        
        
    
    }
    
    public void redireccionarPromoBuscada(){
    
    
    
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

    public LoginController getUsuario() {
        return usuario;
    }

    public void setUsuario(LoginController usuario) {
        this.usuario = usuario;
    }
     
     
    
    public void cargarPromociones(){
        
        Servicio_Promocion sp = new Servicio_Promocion();
        
        for (Object o : sp.mostrarDatos()) {
            this.listaPromociones.add((Promocion)o);
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
        
        this.promocion.setLinkPromo("");
        this.promocion.setNombrePromo("");
       
        this.cargarListarXCategoria();
        this.cargarListaCategorias();
        this.cargarPromociones();
        
    }
    
          
}

