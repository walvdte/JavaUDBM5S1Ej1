/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.controlador;

import com.aerolinea.entidad.Rol;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author JAVA
 */
@Named(value = "rolControlador")
@ViewScoped
public class RolControlador implements Serializable {

    private Rol objRolSeleccionado;

    @PostConstruct
    public void alCargar() {
        inicializarVariables();
    }

    public void inicializarVariables() {
        objRolSeleccionado = new Rol();
    }

    public Rol getObjRolSeleccionado() {
        return objRolSeleccionado;
    }

    public void setObjRolSeleccionado(Rol objRolSeleccionado) {
        this.objRolSeleccionado = objRolSeleccionado;
    }
}
