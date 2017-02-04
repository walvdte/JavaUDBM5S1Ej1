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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

    private String agregarEditar;
    private String descripcionABuscar;

    @PostConstruct
    public void alCargar() {
        descripcionABuscar = "";
        inicializarVariables();
    }

    public String buscarPorDescripcion() {
        lstAvion = daoAvion.buscarPorDescripcion(descripcionABuscar);
        return null;
    }

    public void inicializarVariables() {
        avionSeleccionado = new Avion();
        lstAvion = daoAvion.buscarPorDescripcion(descripcionABuscar);
        agregarEditar = "Agregar";
    }

    public String guardar() {
        daoAvion.edit(avionSeleccionado);
        inicializarVariables();

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos guardados correctamente", ""));

        return null;
    }

    public String cancelar() {
        inicializarVariables();
        return null;
    }

    public String eliminar() {
        daoAvion.remove(avionSeleccionado);
        inicializarVariables();
        return null;
    }

    public String inicializarEditar() {
        agregarEditar = "Editar";
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

    public String getAgregarEditar() {
        return agregarEditar;
    }

    public void setAgregarEditar(String agregarEditar) {
        this.agregarEditar = agregarEditar;
    }

    public String getDescripcionABuscar() {
        return descripcionABuscar;
    }

    public void setDescripcionABuscar(String descripcionABuscar) {
        this.descripcionABuscar = descripcionABuscar;
    }

}
