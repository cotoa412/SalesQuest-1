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
        Servicio_Categoria sc = new Servicio_Categoria();
        Categoria c = new Categoria();
        try{
            this.conectar();
            
            stmt = conn.createStatement();
            String sql = "SELECT * FROM promosFavoritas pf, promocion pr, usuario us WHERE pf.idUsuario = us.idUsuario AND pf.idPromocion = pr.idPromocion";
            
            rs = stmt.executeQuery(sql);
            
           while(rs.next()){
               
               int idPromocion = rs.getInt("idPromocion");
               String nombrePromocion = rs.getString("nombrePromocion");
               String linkPromocion = rs.getString("linkPromocion");
               int categoria = rs.getInt("categoria");
               
               for (Object o : sc.mostrarDatos()) {
                   if (categoria == ((Categoria)o).getIdCategoria()) {
                       c = ((Categoria)o);
                   }
               }
               
               listaRetorno.add(new Promocion(idPromocion,nombrePromocion,linkPromocion,c));    
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
    
    public List<Object> mostrarPromosFavoritas(int usuario) {
        ResultSet rs = null;
        Statement stmt = null;
        List<Object> listaRetorno = new ArrayList<Object>();
        Servicio_Categoria sc = new Servicio_Categoria();
        Categoria c = new Categoria();
        try{
            this.conectar();
            
            stmt = conn.createStatement();
            String sql = "SELECT * FROM promosFavoritas pf, promocion pr, usuario us WHERE us.idUsuario = '"+usuario+"' AND pf.idUsuario='"+usuario+"' AND pf.idPromocion = pr.idPromocion";
            
            rs = stmt.executeQuery(sql);
            
           while(rs.next()){
               
               int idPromocion = rs.getInt("idPromocion");
               String nombrePromocion = rs.getString("nombrePromocion");
               String linkPromocion = rs.getString("linkPromocion");
               int categoria = rs.getInt("categoria");
               
               for (Object o : sc.mostrarDatos()) {
                   if (categoria == ((Categoria)o).getIdCategoria()) {
                       c = ((Categoria)o);
                   }
               }
               
               listaRetorno.add(new Promocion(idPromocion,linkPromocion,nombrePromocion,c));    
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
    
    public void insertarPromoFavorita(int promo , int idUsuario){
        
        Statement stmt = null;
        
        try{
            this.conectar();
            stmt = conn.createStatement();
            
            String sql = "INSERT INTO promosFavoritas(idPromocion,idUsuario) VALUES("+promo+","+idUsuario+")";
        
            int insert = stmt.executeUpdate(sql);
            
            System.out.println("Se agrego correctamente." + promo + " " + idUsuario);
            
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
