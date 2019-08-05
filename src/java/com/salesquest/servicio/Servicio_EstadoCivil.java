/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.servicio;

import com.salesquest.model.EstadoCivil;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Personal
 */
public class Servicio_EstadoCivil extends Servicio implements IDAO{

    @Override
    public List<Object> mostrarDatos() {
        
        ResultSet rs = null;
        Statement stmt = null;
        List<Object> listaRetorno = new ArrayList<Object>();
        
        try{
            this.conectar();
            
            stmt = conn.createStatement();
            String sql = "SELECT * FROM estadocivil";
            
            rs = stmt.executeQuery(sql);
            
           while(rs.next()){
               
               int idEstadoCivil = rs.getInt("idEstadoCivil");
               String estadoCivil = rs.getString("estadoCivil");
               
               
               listaRetorno.add(new EstadoCivil(idEstadoCivil,estadoCivil));
            
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
    
    public EstadoCivil seleccionarEstadoCivil(String nombreEstadoCivil){
    
        ResultSet rs = null;
        Statement stmt = null;
        EstadoCivil estadoCivil = null;
        try{
            this.conectar();
            
            stmt = conn.createStatement();
            String sql = "SELECT * FROM estadocivil WHERE idEstadoCivil='"+nombreEstadoCivil+"'";
            
            rs = stmt.executeQuery(sql);
            
           if(rs.next()){
              estadoCivil = new EstadoCivil(rs.getInt("idEstadoCivil"),rs.getString("estadoCivil"));
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
        return estadoCivil;
            
    }
    
}
