/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.servicio;

import com.salesquest.model.Categoria;
import static com.salesquest.servicio.Servicio.conn;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Personal
 */
public class Servicio_Categoria extends Servicio implements IDAO {

    @Override
    public List<Object> mostrarDatos() {
        ResultSet rs = null;
        Statement stmt = null;
        List<Object> listaRetorno = new ArrayList<Object>();
        
        try{
            this.conectar();
            
            stmt = conn.createStatement();
            String sql = "SELECT * FROM categoria";
            
            rs = stmt.executeQuery(sql);
            
           while(rs.next()){
               
               int idCategoria = rs.getInt("idCategoria");
               String nombreCategoria = rs.getString("nombreCategoria");
               
               
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
        
        Statement stmt = null;
        
        try{
            this.conectar();
            stmt = conn.createStatement();
            
            String sql = "Insert Into Categoria(nombreCategoria) Values('"+ ((Categoria)obj).getNombreCategoria() +"')";
        
            int insert = stmt.executeUpdate(sql);
            
        }catch(Exception e){
            e.printStackTrace();
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
    
   public Categoria seleccionarCategoria(String nombreCategoria){
       ResultSet rs = null;
       Statement stmt = null;
       Categoria categoria = null;
       
       try{
           this.conectar();
           
           stmt = conn.createStatement();
           
           String sql = "SELECT * FROM  categoria WHERE idCategoria='" + nombreCategoria + "'";
           
           rs = stmt.executeQuery(sql);
           
           if (rs.next()) {
               categoria = new Categoria(rs.getInt("idCategoria"),rs.getString("nombreCategoria"));
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
          
       return categoria;
   }
    
}
