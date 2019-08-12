/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.servicio;

import com.salesquest.controller.LoginController;
import com.salesquest.model.PromoFavorita;
import com.salesquest.model.Promocion;
import static com.salesquest.servicio.Servicio.conn;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Laboratorio
 */
public class Servicio_Favorito extends Servicio implements IDAO {

    @ManagedProperty("#{loginController}")
    private LoginController usuario;

    @Override
    public List<Object> mostrarDatos() {
        ResultSet rs = null;
        Statement stmt = null;
        List<Object> listaPromosFavoritas = new ArrayList<Object>();
        try {
            this.conectar();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM promosFavoritas";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int idPromocion = rs.getInt("idPromocion");
                int idUsuario = rs.getInt("idUsuario");

                listaPromosFavoritas.add(new PromoFavorita(idPromocion, idUsuario));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                this.desconectar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return listaPromosFavoritas;
    }

    @Override
    public void insertarDato(Object obj) {
        Statement stmt = null;
        try {
            this.conectar();//Me conecto a la base de datos.

            stmt = conn.createStatement();
            String sql = "INSERT INTO promosFavoritas(idPromocion, idUsuario) VALUE('" + ((Promocion) obj).getIdPromocion() + "','" + usuario.getUsuario().getIdUsuario() + "')";//recorrer usuarios
            int i = stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        try {
            stmt.close();

            this.desconectar();//Me desconecto.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarDato(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarDato(Object obj) {
        //DELETE FROM `table_name` [WHERE condition];
        Statement stmt = null;
        try {
            this.conectar();//Me conecto a la base de datos.

            stmt = conn.createStatement();
            String sql = "DELETE FROM promosFavoritas WHERE idUsuario = ('" + usuario.getUsuario().getIdUsuario() + "')";
            int i = stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        try {
            stmt.close();

            this.desconectar();//Me desconecto.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


