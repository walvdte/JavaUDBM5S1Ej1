/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.controlador;

import com.aerolinea.dao.AvionFacade;
import com.aerolinea.entidad.Avion;
import com.aerolinea.wscliente.WsAvion_Service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author JAVA
 */
@Named(value = "avionControlador")
@ViewScoped
public class AvionControlador implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/EjemploS1/WsAvion.wsdl")
    private WsAvion_Service service;

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

        lstAvion = new ArrayList<>();
        //lstAvion = daoAvion.buscarPorDescripcion(descripcionABuscar);
        buscarPorDescripcion_ws(descripcionABuscar).forEach((a) -> {
            lstAvion.add(new Avion(a.getIdavion(), a.getCapacidad(), a.getDescripcion()));
        });
        agregarEditar = "Agregar";
    }

    public String guardar() {
        //daoAvion.edit(avionSeleccionado);
        com.aerolinea.wscliente.Avion a = new com.aerolinea.wscliente.Avion();
        a.setCapacidad(avionSeleccionado.getCapacidad());
        a.setDescripcion(avionSeleccionado.getDescripcion());
        a.setIdavion(avionSeleccionado.getIdavion());
        edit(a);
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
        //daoAvion.remove(avionSeleccionado);
        com.aerolinea.wscliente.Avion a = new com.aerolinea.wscliente.Avion();
        a.setCapacidad(avionSeleccionado.getCapacidad());
        a.setDescripcion(avionSeleccionado.getDescripcion());
        a.setIdavion(avionSeleccionado.getIdavion());
        remove(a);
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

    private void edit(com.aerolinea.wscliente.Avion entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        com.aerolinea.wscliente.WsAvion port = service.getWsAvionPort();
        port.edit(entity);
    }

    private void remove(com.aerolinea.wscliente.Avion entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        com.aerolinea.wscliente.WsAvion port = service.getWsAvionPort();
        port.remove(entity);
    }

    private java.util.List<com.aerolinea.wscliente.Avion> buscarPorDescripcion_ws(java.lang.String descripcion) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        com.aerolinea.wscliente.WsAvion port = service.getWsAvionPort();
        return port.buscarPorDescripcion(descripcion);
    }

}
