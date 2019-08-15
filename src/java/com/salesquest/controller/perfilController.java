/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.controller;
import com.salesquest.model.Categoria;
import com.salesquest.servicio.Servicio_Usuario;
import com.salesquest.model.EstadoCivil;
import com.salesquest.servicio.Servicio_Categoria;
import com.salesquest.servicio.Servicio_EstadoCivil;
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
 * @author Laboratorio
 */
@ManagedBean(name = "perfilController")
@SessionScoped
public class perfilController {

    private List<EstadoCivil> listaEstadoCivil = new ArrayList<EstadoCivil>();
    private List<Categoria> listaCategorias = new ArrayList<Categoria>();
    private boolean editar = true;
    private boolean editar2 = false;
    
    
    @ManagedProperty("#{loginController}")
    private LoginController perfil;

    public LoginController getPerfil() {
        return perfil;
    }

    public void setPerfil(LoginController perfil) {
        this.perfil = perfil;
    }

    public List<EstadoCivil> getListaEstadoCivil() {
        return listaEstadoCivil;
    }

    public void setListaEstadoCivil(List<EstadoCivil> listaEstadoCivil) {
        this.listaEstadoCivil = listaEstadoCivil;
    }

    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    public boolean isEditar2() {
        return editar2;
    }

    public void setEditar2(boolean editar2) {
        this.editar2 = editar2;
    }
    
    
    
      
    public void redireccionarPerfil(){
    
        this.cargarListaEstadoCivil();
        
        this.cargarListaCategoria();
        
        if (this.perfil.getUsuario().getDireccion() == null || this.perfil.getUsuario().getTelefono() == null || this.perfil.getUsuario().getFechaNacimiento() == null || this.perfil.getUsuario().getEstadoCivil() == null || this.perfil.getUsuario().getCategoriaFavorita() == null) {
            
            this.editar2 = false;
            
        }else if(this.perfil.getUsuario().getDireccion() != null || this.perfil.getUsuario().getTelefono() != null || this.perfil.getUsuario().getFechaNacimiento() != null || this.perfil.getUsuario().getEstadoCivil() != null || this.perfil.getUsuario().getCategoriaFavorita() != null){
            
            this.editar2 = true;
            
        }
            
        
        
        this.redireccionar();
        
    }
    
    public boolean paraEditar(){
        editar = false;
        editar2 = false;
        return editar;
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
                            + "/faces/perfil.xhtml?faces-redirect=true");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void cargarListaCategoria(){
        
        Servicio_Categoria sc = new Servicio_Categoria();
        
        for (Object obj : sc.mostrarDatos()) {
            this.listaCategorias.add(((Categoria)obj));
        }
        
    }
    
    public void cargarListaEstadoCivil(){
        
        Servicio_EstadoCivil se = new Servicio_EstadoCivil();
      
        for (Object obj : se.mostrarDatos()) {
            this.listaEstadoCivil.add((EstadoCivil)obj);
        }
    }
    
    public void completarPerfil(){
    
        Servicio_Usuario su = new Servicio_Usuario();
        
        su.actualizarDatos(this.perfil.getUsuario());
        
        if (this.perfil.getUsuario().getDireccion() != "") {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro Completado.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            this.editar = true;
            this.editar2 = true;
                    
        }
        
        
        
    }
    
}


