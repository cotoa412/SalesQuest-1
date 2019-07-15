/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.servicio;

import com.salesquest.model.Codigo;
import com.salesquest.model.Usuario;
import static com.salesquest.servicio.Servicio.conn;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Personal
 */
public class Servicio_Codigo extends Servicio implements IDAO {

    @Override
    public List<Object> mostrarDatos() {
        
        ResultSet rs = null;
        Statement stmt = null;
        List<Object> listaRetorno = new ArrayList<Object>();
        
        try{
            this.conectar();
            
            stmt = conn.createStatement();
            String sql = "SELECT * FROM recuperacion";
            
            rs = stmt.executeQuery(sql);
            
           while(rs.next()){
               
               int usuario = rs.getInt("usuario_r");
               String codigo = rs.getString("codigo");
               
               
               listaRetorno.add(new Codigo(usuario,codigo));
            
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
        List<Integer> random = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        
        for (int i = 0; i < 6; i++) {
        int numero = (int) (Math.random() * 10) + 1;
            random.add(numero);
        }
        
        for (Integer i : random) {
            sb.append(i);
        } 
        
        String codigo = sb.toString();
                
        try{
            this.conectar();
            stmt = conn.createStatement();
            
            String sql = "INSERT INTO recuperacion(usuario_r,codigo) VALUE("+((Usuario)obj).getIdUsuario()+",'"+codigo+"')";
            
            int insert = stmt.executeUpdate(sql);
            
        }
        catch(Exception e)
        {
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
