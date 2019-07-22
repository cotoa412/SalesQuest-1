/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.servicio;

import com.salesquest.model.TipoUsuario;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Laboratorio
 */
public class Servicio_TipoUsuario extends Servicio implements IDAO{

    @Override
    public List<Object> mostrarDatos() {
        
        ResultSet rs = null;
        Statement stmt = null;
        List<Object> listaRetorno = new ArrayList<Object>();
        
        try{
            this.conectar();
            
            stmt = conn.createStatement();
            String sql = "SELECT * FROM tipousuario";
            
            rs = stmt.executeQuery(sql);
            
           while(rs.next()){
               
               int idTipoUsuario = rs.getInt("idtipoUsuario");
               String nombreTipoUsuario = rs.getString("nombreTipoUsuario");
               
               
               listaRetorno.add(new TipoUsuario(idTipoUsuario,nombreTipoUsuario));
            
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
    
    public TipoUsuario seleccionarTipoUsuario(String tipoUsuario){
    
        ResultSet rs = null;
        Statement stmt = null;
        TipoUsuario tipoUsuarioDato = null;
        try{
            this.conectar();
            
            stmt = conn.createStatement();
            String sql = "SELECT * FROM tipousuario WHERE idtipoUsuario='"+tipoUsuario+"'";
            
            rs = stmt.executeQuery(sql);
            
           if(rs.next()){
              tipoUsuarioDato = new TipoUsuario(rs.getInt("idtipoUsuario"),rs.getString("nombreTipoUsuario"));
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
        return tipoUsuarioDato;
    }


    @Override
    public void insertarDato(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
