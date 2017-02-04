/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.dao;

import com.aerolinea.entidad.Avion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JAVA
 */
@Stateless
public class AvionFacade extends AbstractFacade<Avion> {

    @PersistenceContext(unitName = "EjemploS1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AvionFacade() {
        super(Avion.class);
    }

    public List<Avion> buscarPorDescripcion(String descripcion) {
        return em.createQuery("SELECT a FROM Avion a WHERE a.descripcion LIKE :desc")
                .setParameter("desc", "%" + descripcion + "%").getResultList();
    }
}
