
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.controller;

import com.salesquest.model.Usuario;
import com.salesquest.servicio.Servicio_Codigo;
import com.salesquest.servicio.Servicio_Usuario;
import com.salesquest.model.Codigo;
import com.salesquest.model.EstadoCivil;
import com.salesquest.model.TipoUsuario;
import com.salesquest.servicio.Servicio_EstadoCivil;
import com.salesquest.servicio.Servicio_TipoUsuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

/**
 *
 * @author Laboratorio
 */
@ManagedBean(name = "registroController")
@SessionScoped
public class RegistroController {
    
    private Usuario usuario = new Usuario();
   
    private List<TipoUsuario> listaTipoUsuarios = new ArrayList<TipoUsuario>();
    
    
   
    public void linkRegistrar() {
        this.cargarLista();
        this.redirect();
        
    }
    
   public void redirect() {

        try {
           
            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            request.getContextPath()
                            + "/faces/registro.xhtml?faces-redirect=true");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void cargarLista() {
     Servicio_TipoUsuario s = new Servicio_TipoUsuario();
        for (Object obj : s.mostrarDatos()) {
            
            this.listaTipoUsuarios.add((TipoUsuario)obj);
            
        }
     
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public List<TipoUsuario> getTipoUsuarios(){
        return this.listaTipoUsuarios;
    }
    
    public void setTipoUsuarios(TipoUsuario usuario){
        this.listaTipoUsuarios.add(usuario);
    }
    
    public String registrar(){
     
        String redirect = "";
        
        if (usuario.getNombre()=="" && usuario.getApellidos()=="" && usuario.getCorreo()=="" && usuario.getNombreUsuario()=="") {
            
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se deben llenar todos los campos.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        
        }else if(this.comprobarNombreUsuario(usuario.getNombreUsuario())    ==  true){
        
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El nombre de usuario ya está en uso .");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
        }else if(this.comprobarCorreo_Existe(usuario.getCorreo()) == true){
        
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El correo electrónico ya está en uso.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
        }else{
            
            
            if (this.comprobarCorreoValido(usuario.getCorreo()) == true) {
        
            Servicio_Usuario su = new Servicio_Usuario();
            su.insertarDato(usuario);
        
            usuario.setNombre("");
            usuario.setApellidos("");
            usuario.setCorreo("");
            usuario.setNombreUsuario("");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", "Correct");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
         redirect =  "login.xhtml?faces-redirect=true";
            
        }else{
            
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo realizar el registro."));
            
        }
            
        }
        
        
        
        return redirect;
    }
     
    public String volverInicio(){
        usuario.setNombre("");
        usuario.setApellidos("");
        usuario.setCorreo("");
        usuario.setNombreUsuario("");
    return "index.xhtml?faces-redirect=true";
    
    }
    
    public boolean comprobarCorreoValido(String correo){
        
        boolean comprobar = false;
        
        // Patrón para validar el email
        Pattern pattern = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@"+"(([a-z]+)\\.([a-z]+))+");
        
        // El email a validar
 
        Matcher mather = pattern.matcher(correo);
 
        if (mather.find() == true) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", "El email ingresado es válido.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            comprobar = true;
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El email ingresado es inválido.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return comprobar;
    }
    
    public boolean comprobarNombreUsuario(String nombreUsuario){
        
        boolean comprob = false;
        
        for (Object obj : new Servicio_Usuario().mostrarDatos()) {
            
            if (((Usuario)obj).getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
            
                comprob = true;
                
                
            }
               
        }
        return comprob;
    }
    
    public boolean comprobarCorreo_Existe(String correo){
    
        boolean comprob = false;
        
        for (Object obj : new Servicio_Usuario().mostrarDatos()) {
            
            if (((Usuario)obj).getCorreo().equalsIgnoreCase(correo)) {
            
               comprob = true;
                
            }
               
        }
        
        return comprob;
    }
     
}
