/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.servicio;
import com.salesquest.model.TipoUsuario;
import com.salesquest.model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Vigo02
 */
public class Servicio_Usuario extends Servicio implements IDAO{

    @Override
    public List<Object> mostrarDatos() {
          
        ResultSet rs = null;
        Statement stmt = null;
        Servicio_TipoUsuario s = new Servicio_TipoUsuario();
        List<Object> listaRetorno = new ArrayList<Object>();
        TipoUsuario tpu = null;
        
        try{
            this.conectar();
            
            stmt = conn.createStatement();
            String sql = "SELECT * FROM usuario";
            
            rs = stmt.executeQuery(sql);
            
           while(rs.next()){
               
               int id = rs.getInt("idUsuario");
               String nom = rs.getString("nombre");
               String apellidos = rs.getString("apellidos");
               String correo = rs.getString("correo");
               String nombreUsuario = rs.getString("nombreUsuario");
               String contrasenna = rs.getString("contrasenna");
               int tipoUsuario = rs.getInt("tipoUsuario");
               
               for (Object tp : s.mostrarDatos()) {
                   if (((TipoUsuario)tp).getIdTipoUsuario() == tipoUsuario) {
                       tpu = ((TipoUsuario)tp);
                   }
               }
               
               listaRetorno.add(new Usuario(id,nom,apellidos,correo,nombreUsuario,contrasenna,tpu));
            
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
           this.conectar();//Me conecto a la base de datos.
           
           
           stmt = conn.createStatement();
           String sql = "INSERT INTO usuario(nombre, apellidos, correo, nombreUsuario, contrasenna, tipoUsuario) VALUE('"+((Usuario)obj).getNombre()+"','"+((Usuario)obj).getApellidos()+"','"+((Usuario)obj).getCorreo()+"','"+((Usuario)obj).getNombreUsuario()+"','"+((Usuario)obj).getContrasenna()+"',"+((Usuario)obj).getTipoUsuario().getIdTipoUsuario()+")";                                                                                                            
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
       Statement stmt = null;
       try{
           this.conectar();//Me conecto a la base de datos.
           
           
           stmt = conn.createStatement();
           String sql = "UPDATE 'usuario' SET (contrasenna) = ('"+((Usuario)obj).getContrasenna()+"') WHERE idUsuario = ('"+((Usuario)obj).getIdUsuario()+"')";                                                                                                            
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
    public void eliminarDato(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
