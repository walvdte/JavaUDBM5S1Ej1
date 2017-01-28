/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.controlador;

import com.aerolinea.dao.AvionFacade;
import com.aerolinea.entidad.Avion;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author JAVA
 */
@Named(value = "avionControlador")
@ViewScoped
public class AvionControlador implements Serializable {

    @EJB
    private AvionFacade daoAvion;

    private List<Avion> lstAvion;

    private Avion avionSeleccionado;

    @PostConstruct
    public void alCargar() {
        avionSeleccionado = new Avion();
        lstAvion = daoAvion.findAll();
    }

    public String guardar() {
        daoAvion.edit(avionSeleccionado);
        lstAvion = daoAvion.findAll();
        avionSeleccionado = new Avion();
        return null;
    }

    public Avion getAvionSeleccionado() {
        return avionSeleccionado;
    }

    public void setAvionSeleccionado(Avion avionSeleccionado) {
        this.avionSeleccionado = avionSeleccionado;
    }

    public List<Avion> getLstAvion() {
        return lstAvion;
    }

    public void setLstAvion(List<Avion> lstAvion) {
        this.lstAvion = lstAvion;
    }

}
