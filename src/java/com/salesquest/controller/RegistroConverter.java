/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.controller;

import com.salesquest.model.EstadoCivil;
import com.salesquest.servicio.Servicio_EstadoCivil;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Personal
 */
@FacesConverter(value = "registroConverter")
public class RegistroConverter implements Converter{
    
     private static final long serialVersionUID = 1L;

    private Servicio_EstadoCivil servicioEstadoCivil;

    public RegistroConverter() {
        servicioEstadoCivil = new Servicio_EstadoCivil();
    }
    
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component,
            String submittedValue) {
        if (submittedValue != null && submittedValue.trim().length() > 0) {
            try {
                return servicioEstadoCivil.seleccionarEstadoCivil(submittedValue);
            } catch (Exception exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "No es un tipo estado civil válido."));
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
            return String.valueOf(((EstadoCivil) value).getIdEstadoCivil());
        }
    }
    
}
