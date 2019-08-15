/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.servicio;

import com.salesquest.model.Promocion;
import java.util.List;

/**
 *
 * @author Personal
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        ServicioFavorito  sf = new ServicioFavorito();
         
        List<Object> lista = sf.mostrarPromosFavoritas(3);
        
        for (Object o : lista) {
            System.out.println(((Promocion)o));
        }
        
    }
    
}
