/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.servicio;

import java.util.List;

/**
 *
 * @author Vigo02
 */
public interface IDAO {
    public List<Object> mostrarDatos();
    public void insertarDato(Object obj);
    public void actualizarDato(Object obj);
    public void eliminarDato(Object obj);
}
