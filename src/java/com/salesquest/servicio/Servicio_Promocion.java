/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.servicio;

import com.salesquest.model.Categoria;
import com.salesquest.model.Promocion;
import com.salesquest.model.Usuario;
import static com.salesquest.servicio.Servicio.conn;
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
        Categoria categoria = new Categoria();
        Servicio_Categoria sc = new Servicio_Categoria();
        try{
            this.conectar();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM promocion";
            rs = stmt.executeQuery(sql);
           while(rs.next()){
               int idPromocion = rs.getInt("idPromocion");
               String nombrePromo = rs.getString("nombrePromocion");
               String linkPromo = rs.getString("linkPromocion");
               int categ = rs.getInt("categoria");
               
               for (Object obj : sc.mostrarDatos()) {
                   if (((Categoria)obj).getIdCategoria() == categ) {
                       categoria = ((Categoria)obj);
                   }
               }
               
               
               listaPromos.add(new Promocion(idPromocion,linkPromo,nombrePromo,categoria));
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
        
     Statement stmt = null;
       try{
           this.conectar();//Me conecto a la base de datos.
                
           stmt = conn.createStatement();
           String sql = "INSERT INTO promocion(nombrePromocion,linkPromocion,categoria) VALUE('"+((Promocion)obj).getNombrePromo()+"','"+((Promocion)obj).getLinkPromo()+"','"+((Promocion)obj).getCategoria().getIdCategoria()+"')";                                                                                                            
           int i = stmt.executeUpdate(sql);
           
       }catch(Exception e){
         e.printStackTrace();
       }
       finally{
           
       }
       try{
           stmt.close();
           
           this.desconectar();//Me desconecto.
       }catch (Exception e){
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
    public void anadirFavorito(){
        
    }
}
