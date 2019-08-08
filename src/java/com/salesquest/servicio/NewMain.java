/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.servicio;

import com.salesquest.model.Categoria;
import com.salesquest.model.Usuario;

/**
 *
 * @author Personal
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Servicio_Categoria s = new Servicio_Categoria();
        
        Categoria c = new Categoria(1,"Video Juegos");
        
        s.insertarDato(c);
        
        Categoria c2 = new Categoria(2,"Deportes");
        
        s.insertarDato(c2);
    }
    
}
