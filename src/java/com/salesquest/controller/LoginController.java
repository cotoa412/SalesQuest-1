/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Kainthel
 */

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController {

    private String usuario;
    private String clave;
    private String mensaje;

    public LoginController() {
        this.mensaje = "Bienvenidos a SalesQuest";
    }

    public LoginController(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public void ingresar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (this.getUsuario().equalsIgnoreCase("admin") && (this.getClave().equalsIgnoreCase("testtest"))) { //Usuario registrado

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", this.getUsuario()));

        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No es admin"));
        }
        
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
