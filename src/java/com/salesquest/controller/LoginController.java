/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.controller;

import com.salesquest.model.TipoUsuario;
import com.salesquest.model.Usuario;
import com.salesquest.servicio.Servicio_TipoUsuario;
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
        Servicio_TipoUsuario st = new Servicio_TipoUsuario();
        
        
        for (Object obj : su.mostrarDatos()) {
            if (((Usuario)obj).getNombreUsuario().equalsIgnoreCase(ingreso) && ((Usuario)obj).getContrasenna().equalsIgnoreCase(contra)) {
                
                usuario = ((Usuario)obj);

            }else{
                
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o contraseña incorrecta.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                
            }
        }
        
//        for (Object o : st.mostrarDatos()) {
//            if (usuario.getTipoUsuario().getIdTipoUsuario() == ((TipoUsuario)o).getIdTipoUsuario()) {
//                tU = ((TipoUsuario)o).getNombreTipoUsuario();
//            }
//        }
        
        if (usuario.getTipoUsuario().getNombreTipoUsuario().equalsIgnoreCase("oferente")) {
            
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
            
        }else if(usuario.getTipoUsuario().getNombreTipoUsuario().equalsIgnoreCase("cliente")){
    
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
            
        }else{
        
        }
        
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
                            + "/faces/landingPage.xhtml?faces-redirect=true");
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
    
    

}
