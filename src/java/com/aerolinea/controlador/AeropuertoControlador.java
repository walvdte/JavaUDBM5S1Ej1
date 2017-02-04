/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.controlador;

import com.aerolinea.dao.AeropuertoFacade;
import com.aerolinea.dao.PaisFacade;
import com.aerolinea.entidad.Aeropuerto;
import com.aerolinea.entidad.Pais;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author JAVA
 */
@Named(value = "aeropuertoControlador")
@ViewScoped
public class AeropuertoControlador implements Serializable {

    @EJB
    private PaisFacade daoPais;
    @EJB
    private AeropuertoFacade daoAeropuerto;

    private Aeropuerto objAeropuertoSeleccionado;

    private String agregarEditar;

    private List<Pais> lstPais;
    private List<Aeropuerto> lstAeropuerto;

    @PostConstruct
    public void alCargar() {
        inicializarVariables();
    }

    public void inicializarVariables() {
        objAeropuertoSeleccionado = new Aeropuerto();
        objAeropuertoSeleccionado.setIdpais(new Pais());
        lstPais = daoPais.findAll();
        lstAeropuerto = daoAeropuerto.findAll();
        agregarEditar = "Agregar";
    }

    public String inicializarAgregar() {
        objAeropuertoSeleccionado = new Aeropuerto();
        return null;
    }

    public String inicializarEditar() {
        agregarEditar = "Editar";
        return null;
    }

    public String guardar() {
        daoAeropuerto.edit(objAeropuertoSeleccionado);
        inicializarVariables();

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos guardados correctamente", null));

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('wEditar').hide();");

        return null;
    }

    public String eliminar() {
        daoAeropuerto.remove(objAeropuertoSeleccionado);
        inicializarVariables();
        return null;
    }

    public Aeropuerto getObjAeropuertoSeleccionado() {
        return objAeropuertoSeleccionado;
    }

    public void setObjAeropuertoSeleccionado(Aeropuerto objAeropuertoSeleccionado) {
        this.objAeropuertoSeleccionado = objAeropuertoSeleccionado;
    }

    public List<Pais> getLstPais() {
        return lstPais;
    }

    public void setLstPais(List<Pais> lstPais) {
        this.lstPais = lstPais;
    }

    public String getAgregarEditar() {
        return agregarEditar;
    }

    public void setAgregarEditar(String agregarEditar) {
        this.agregarEditar = agregarEditar;
    }

    public List<Aeropuerto> getLstAeropuerto() {
        return lstAeropuerto;
    }

    public void setLstAeropuerto(List<Aeropuerto> lstAeropuerto) {
        this.lstAeropuerto = lstAeropuerto;
    }

}
