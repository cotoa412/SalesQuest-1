/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.controller;

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
 * @author Alejandro
 */
@ManagedBean(name = "contactoController")
@SessionScoped
public class contactoController {

    private String opinion;
    private String correo;
    private String nombre;
    private String queja;

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getQueja() {
        return queja;
    }

    public void setQueja(String queja) {
        this.queja = queja;
    }
    
    public void enviarOpinion() {
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

            m.addRecipient(Message.RecipientType.TO, new InternetAddress("parapropruebas@gmail.com"));
            m.setSubject("Opinion de SaleQuest:");

            m.setText(opinion);

            Transport transport = session.getTransport("smtp");
            transport.connect("parapropruebas@gmail.com", "123456pruebas");
            transport.sendMessage(m, m.getAllRecipients());
            transport.close();
            this.mensajeExito();
            this.borrarOpinion();

        } catch (MessagingException me) {
            me.printStackTrace();
        }
                    
    }
    public void mensajeExito(){
        FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage("Listo!", "Se ha enviado su mensaje!"));
    }
    public void enviarQueja() {
        Properties p = new Properties();

        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.port", "587");
        p.put("mail.smtp.user", "parapropruebas@gmail.com");
        p.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(p, null);

        MimeMessage m = new MimeMessage(session);

        try {

            m.setFrom(new InternetAddress(correo));

            m.addRecipient(Message.RecipientType.TO, new InternetAddress("parapropruebas@gmail.com"));
            m.setSubject("Queja de SaleQuest por parte de "+nombre+":");

            m.setText(queja);

            Transport transport = session.getTransport("smtp");
            transport.connect("parapropruebas@gmail.com", "123456pruebas");
            transport.sendMessage(m, m.getAllRecipients());
            transport.close();
            this.mensajeExito();
            this.borrarQueja();

        } catch (MessagingException me) {
            me.printStackTrace();
        }
                    
    }
    public void borrarQueja(){
        setCorreo("");
        setQueja("");
        setNombre("");
    }
    public void borrarOpinion(){
        setOpinion("");
    }
    public void redireccionar(){
    
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
                            + "/faces/Contacto.xhtml?faces-redirect=true");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}
