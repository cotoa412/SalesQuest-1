/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.controller;

import com.salesquest.model.Usuario;
import com.salesquest.servicio.Servicio_Usuario;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Kainthel
 */

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController {
  
    //private String tU;
    private String ingreso;
    private String contra;
    private String mensaje; 
    private Usuario usuario = new Usuario();

    public LoginController() {
        this.mensaje = "Bienvenidos a SalesQuest";
    }


    public void ingresoAlSistema(){
        
        Servicio_Usuario su = new Servicio_Usuario();
       
        for (Object obj : su.mostrarDatos()) {
            if (((Usuario)obj).getNombreUsuario().equalsIgnoreCase(ingreso) && ((Usuario)obj).getContrasenna().equalsIgnoreCase(contra)) { 
                usuario = ((Usuario)obj);
                this.redireccionALandingPage(usuario);
            }else{
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o contraseña incorrecta.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }  
    }
    
    public void redireccionarAlPerfil(){
            
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
    
    public void perfilPrimeraVe(Usuario u){
        
        
        if (u.getDireccion() == null || u.getTelefono()== null || u.getFechaNacimiento() == null || u.getEstadoCivil() == null || u.getCategoriaFavorita() == null) {
            
            this.redireccionarAlPerfil();
            
        }else{
            
            
            
        }
        
    
    
    }
    
    
    public void redireccionALandingPage(Usuario u){
        
        if (u.getTipoUsuario().getNombreTipoUsuario().equalsIgnoreCase("oferente")) {   
                    PromoController pr = new PromoController();
                    pr.cargarListaCategorias();
            
                    try {
           
            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            request.getContextPath()
                            + "/faces/landingPageOferente.xhtml?faces-redirect=true");
            } catch (Exception e) {
                e.printStackTrace();
            }
           
            
            }else if(u.getTipoUsuario().getNombreTipoUsuario().equalsIgnoreCase("consumidor")){
    
                try {
           
                    HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
                    FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            request.getContextPath()
                            + "/faces/landingPageLogged.xhtml?faces-redirect=true");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                          
          }
        
        this.perfilPrimeraVe(u);
    
    }
    
    public void logout() {

        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .invalidateSession();

            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            request.getContextPath()
                            + "/faces/login.xhtml?faces-redirect=true");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public String getIngreso() {
        return ingreso;
    }

    public void setIngreso(String ingreso) {
        this.ingreso = ingreso;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
                            + "/faces/login.xhtml?faces-redirect=true");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
}
