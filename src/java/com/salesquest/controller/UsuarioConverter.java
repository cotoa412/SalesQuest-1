/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.controller;

/**
 *
 * @author Personal
 */

import com.salesquest.model.TipoUsuario;
import com.salesquest.model.Usuario;
import com.salesquest.servicio.Servicio_TipoUsuario;
import com.salesquest.servicio.Servicio_Usuario;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "usuarioConverter")
public class UsuarioConverter implements Converter {
 
    private static final long serialVersionUID = 1L;

    private Servicio_TipoUsuario servicioTipoUsuario;

    public UsuarioConverter() {
        servicioTipoUsuario = new Servicio_TipoUsuario();
    }

    
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component,
            String submittedValue) {
        if (submittedValue != null && submittedValue.trim().length() > 0) {
            try {
                return servicioTipoUsuario.seleccionarTipoUsuario(submittedValue);
            } catch (Exception exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "No es un tipo usuario válido."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component,
            Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((TipoUsuario) value).getIdTipoUsuario());
        }
    }
    
}