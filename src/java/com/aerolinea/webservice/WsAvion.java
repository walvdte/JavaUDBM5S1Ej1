
package com.aerolinea.webservice;

import com.aerolinea.dao.AvionFacade;
import com.aerolinea.entidad.Avion;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author JAVA
 */
@WebService(serviceName = "WsAvion")
public class WsAvion {

    @EJB
    private AvionFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "create")
    @Oneway
    public void create(@WebParam(name = "entity") Avion entity) {
        ejbRef.create(entity);
    }

    @WebMethod(operationName = "edit")
    @Oneway
    public void edit(@WebParam(name = "entity") Avion entity) {
        ejbRef.edit(entity);
    }

    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(@WebParam(name = "entity") Avion entity) {
        ejbRef.remove(entity);
    }

    @WebMethod(operationName = "find")
    public Avion find(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAll")
    public List<Avion> findAll() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "findRange")
    public List<Avion> findRange(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    @WebMethod(operationName = "count")
    public int count() {
        return ejbRef.count();
    }

    @WebMethod(operationName = "buscarPorDescripcion")
    public List<Avion> buscarPorDescripcion(@WebParam(name = "descripcion") String descripcion) {
        return ejbRef.buscarPorDescripcion(descripcion);
    }
    
}
