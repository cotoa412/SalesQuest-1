/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.controller;

import com.salesquest.model.Promocion;
import com.salesquest.servicio.Servicio_Promocion;
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
@FacesConverter(value = "promoConverter")
public class PromoConverter implements Converter{
    
    private static final long serialVersionUID = 1L;

    private Servicio_Promocion servicioPromo;

    public PromoConverter() {
        servicioPromo = new Servicio_Promocion();
    }

    
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component,
            String submittedValue) {
        if (submittedValue != null && submittedValue.trim().length() > 0) {
            try {
                return servicioPromo.seleccionarPromo(submittedValue);
            } catch (Exception exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "No es una promocion válida."));
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
            return String.valueOf(((Promocion) value).getIdPromocion());
        }
    }
    
}
