/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.servicio;

import com.salesquest.model.*;
import com.salesquest.servicio.IDAO;
import static com.salesquest.servicio.Servicio.conn;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Laboratorio
 */
public class ServicioFavorito extends Servicio implements IDAO{

    @Override
    public List<Object> mostrarDatos() {
         ResultSet rs = null;
        Statement stmt = null;
        List<Object> listaRetorno = new ArrayList<Object>();
        
        try{
            this.conectar();
            
            stmt = conn.createStatement();
            String sql = "SELECT * FROM promosFavoritas";
            
            rs = stmt.executeQuery(sql);
            
           while(rs.next()){
               
               int idCategoria = rs.getInt("idPromocion");
               String nombreCategoria = rs.getString("idUsuario");
                 
               listaRetorno.add(new Categoria(idCategoria,nombreCategoria));    
           }
             
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                
                rs.close();
                stmt.close();
                this.desconectar();
                
            }catch(Exception e){
                
                e.printStackTrace();
            
            }
        }
        
        return listaRetorno;
    }

    @Override
    public void insertarDato(Object obj) {
      
    }
    
    public void insertarPromoFavorita(Promocion promocion , int idUsuario){
        Statement stmt = null;
        
        try{
            this.conectar();
            stmt = conn.createStatement();
            
            String sql = "Insert Into promosFavoritas(idPromocion,idUsuario) Values('"+ promocion.getIdPromocion() +"','"+idUsuario+"')";
        
            int insert = stmt.executeUpdate(sql);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                stmt.close();
                this.desconectar();
                
            }catch(Exception e){
                
                e.printStackTrace();
            
            }
        }    
    
    }

    @Override
    public void actualizarDato(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarDato(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
