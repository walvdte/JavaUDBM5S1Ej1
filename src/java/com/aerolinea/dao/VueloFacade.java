/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.dao;

import com.aerolinea.entidad.Vuelo;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JAVA
 */
@Stateless
public class VueloFacade extends AbstractFacade<Vuelo> {

    @PersistenceContext(unitName = "EjemploS1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VueloFacade() {
        super(Vuelo.class);
    }

    public List<Vuelo> consultarVuelos(Date fechaIni, Date fechaFin) {
        return em.createQuery("SELECT v FROM Vuelo v WHERE v.fecha BETWEEN :fechaIni AND :fechaFin")
                .setParameter("fechaIni", fechaIni).setParameter("fechaFin", fechaFin).getResultList();

    }
}
