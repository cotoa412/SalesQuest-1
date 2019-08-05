/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.servicio;
import com.salesquest.model.Categoria;
import com.salesquest.model.EstadoCivil;
import com.salesquest.model.TipoUsuario;
import com.salesquest.model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        Servicio_EstadoCivil se = new Servicio_EstadoCivil();
        Servicio_Categoria sc = new Servicio_Categoria();
        Categoria ca = null;
        EstadoCivil ec = null;
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
               String direccion = rs.getString("direccion");
               String telefono = rs.getString("telefono");
               int estadoCivil = rs.getInt("estadoCivil");
               Date fechaNacimiento = rs.getDate("fechaNacimiento");
               int categoriaFavorita = rs.getInt("categoriaFavorita");
               
               
               for (Object tp : s.mostrarDatos()) {
                   if (((TipoUsuario)tp).getIdTipoUsuario() == tipoUsuario) {
                       tpu = ((TipoUsuario)tp);
                   }
               }
               
               for (Object obj : se.mostrarDatos()) {
                   if (((EstadoCivil)obj).getIdEstadoCivil() == estadoCivil) {
                       ec = ((EstadoCivil)obj);
                   }
               }
               
               for (Object obj : sc.mostrarDatos()) {
                   if (((Categoria)obj).getIdCategoria() == categoriaFavorita) {
                       ca = ((Categoria)obj);
                   }
               }
               
               listaRetorno.add(new Usuario(id,nom,apellidos,correo,nombreUsuario,contrasenna,tpu,direccion,telefono,ec,fechaNacimiento,ca));
            
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

    public void actualizarDatos(Object obj){
        Statement stmt = null;
        DateFormat formato = new SimpleDateFormat("YY-MM-dd");
       try{
           this.conectar();//Me conecto a la base de datos.
           
           
           stmt = conn.createStatement();
           String sql = "UPDATE usuario SET direccion='"+((Usuario)obj).getDireccion()+"',telefono='"+((Usuario)obj).getTelefono()+"',estadoCivil='"+((Usuario)obj).getEstadoCivil().getIdEstadoCivil()+"',fechaNacimiento='"+formato.format(((Usuario)obj).getFechaNacimiento())+"',categoriaFavorita='"+((Usuario)obj).getCategoriaFavorita().getIdCategoria()+"' WHERE idUsuario='"+((Usuario)obj).getIdUsuario()+"'";                                                                                                            
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

}
