/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.controller;

import com.salesquest.model.Codigo;
import com.salesquest.model.Usuario;
import com.salesquest.servicio.Servicio_Codigo;
import com.salesquest.servicio.Servicio_Usuario;
import java.util.Properties;
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

/**
 *
 * @author Personal
 */
@ManagedBean(name = "recuperarContraController")
@SessionScoped
public class recuperarContraController {

    private Usuario usuario = new Usuario();
    private String correo;
    private String codigo;
    private String nuevaContrasenna;
    private String tempContrasenna; //variable temporal para comparar, la borro limpio despues de

    public recuperarContraController() {

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNuevaContrasenna() {
        return nuevaContrasenna;
    }

    public void setNuevaContrasenna(String nuevaContrasenna) {
        this.nuevaContrasenna = nuevaContrasenna;
    }

    public String getTempContrasenna() {
        return tempContrasenna;
    }

    public void setTempContrasenna(String tempContrasenna) {
        this.tempContrasenna = tempContrasenna;
    }

    public void recuperarContrasenna() {

        Servicio_Usuario s = new Servicio_Usuario();
        Servicio_Codigo c = new Servicio_Codigo();

        for (Object obj : s.mostrarDatos()) {

            if (((Usuario) obj).getCorreo().equalsIgnoreCase(correo)) {

                c.insertarDato(obj);
                usuario = ((Usuario) obj);

            }
        }

        for (Object cod : c.mostrarDatos()) {

            if (usuario.getIdUsuario() == ((Codigo) cod).getUsuario()) {

                Properties p = new Properties();

                p.put("mail.smtp.host", "smtp.gmail.com");
                p.put("mail.smtp.starttls.enable", "true");
                p.put("mail.smtp.port", "587");
                p.put("mail.smtp.user", "parapropruebas@gmail.com");
                p.put("mail.smtp.auth", "true");

                Session session = Session.getDefaultInstance(p, null);

                MimeMessage m = new MimeMessage(session);

                try {

                    m.setFrom(new InternetAddress("parapropruebas@gmail.com"));

                    m.addRecipient(Message.RecipientType.TO, new InternetAddress(usuario.getCorreo()));
                    m.setSubject("Recuperacion de contraseña.");

                    String mensaje = "El código de recuperación es: " + ((Codigo) cod).getCodigo() + " con el puede proceder a cambiar su contraseña.";
                    m.setText(mensaje);

                    Transport transport = session.getTransport("smtp");
                    transport.connect("parapropruebas@gmail.com", "123456pruebas");
                    transport.sendMessage(m, m.getAllRecipients());
                    transport.close();

                } catch (MessagingException me) {
                    me.printStackTrace();
                }

            }

        }

        correo = null;
    }

    public String confirmarCodigo() {

        Servicio_Usuario s = new Servicio_Usuario();
        Servicio_Codigo c = new Servicio_Codigo();
        String dir = "";

        for (Object obj : c.mostrarDatos()) {

            if (((Codigo) obj).getCodigo().equalsIgnoreCase(codigo)) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Correcto."));
                dir = "OlvidoContraCambiar.xhtml?faces-redirect=true";

            } else {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Código incorrecto."));

            }

        }
        return dir;
    }

    public void verificarNuevaContra() {//limpiar la variable de tempcontra al terminar (memoria)
        Servicio_Usuario s = new Servicio_Usuario();
        Usuario u = new Usuario();
        for (Object obj : s.mostrarDatos()) {//recorrer mostrarDatos         
            if (this.nuevaContrasenna == this.tempContrasenna && this.nuevaContrasenna != "" && this.tempContrasenna != "") {//algunas condiciones 

                if (((Usuario) obj).getContrasenna() != this.nuevaContrasenna) {//si la nueva contra es diferente a la vieja entonces que la actualize
                    u = ((Usuario) obj);
                    ((Usuario) obj).setContrasenna(tempContrasenna);//hace que la contrasena del usuario sea igual a tempcoontrasena
                    s.actualizarDato(u);//llama al update                    
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha actualizado su contraseña!"));
                    try {
                        HttpServletRequest request = (HttpServletRequest) FacesContext
                                .getCurrentInstance().getExternalContext().getRequest();
                        FacesContext
                                .getCurrentInstance()
                                .getExternalContext()
                                .redirect(
                                        request.getContextPath()
                                        + "/index.xhtml?faces-redirect=true");//hay que cambiar porque el index no va a ser el login.
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (((Usuario) obj).getContrasenna() == this.nuevaContrasenna) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Su contraseña no puede ser la misma que la antigua!"));
            } else if (this.nuevaContrasenna != this.tempContrasenna) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La contraseña ingresada no fue repetida correctamente!"));
            } else if (this.nuevaContrasenna == "") {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Por favor llenar los espacios!"));
            } else if (this.tempContrasenna == "") {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Por favor llenar los espacios!"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Algo salio mal! Por favor reintentar."));
            }
        }

    }
}
