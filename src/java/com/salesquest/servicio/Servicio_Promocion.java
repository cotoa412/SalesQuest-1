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
 * @author Kainthel
 */
public class Servicio_Promocion extends Servicio implements IDAO{
    
    @Override
    public List<Object> mostrarDatos() {
        
        ResultSet rs = null;
        Statement stmt = null;
        List<Object> listaPromos = new ArrayList<Object>();
        
        try{
            this.conectar();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM promociones";
            rs = stmt.executeQuery(sql);
           while(rs.next()){
               int idPromocion = rs.getInt("idPromocion");
               String nombrePromo = rs.getString("nombrePromo");
               listaPromos.add(new TipoUsuario(idPromocion,nombrePromo));
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
        
        return listaPromos;
                 
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
