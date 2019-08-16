/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesquest.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "faceController")
@SessionScoped
public class FaceController {
   private boolean ensena=false; 

    public FaceController() {
    }

    public boolean getEnsena() {
        return ensena;
    }

    public void setEnsena(boolean ensena) {
        this.ensena = ensena;
    }
   
   public void ensenar(){
       ensena=true;
   }
   
}
