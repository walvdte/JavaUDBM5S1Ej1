package com.aerolinea.webservice;

import com.aerolinea.dao.VueloFacade;
import com.aerolinea.entidad.Vuelo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author JAVA
 */
@Path("reportes")
public class RsVuelos {

    VueloFacade vueloFacade = lookupVueloFacadeBean();

    @GET
    @Path("vuelos/{fechaIni}/{fechaFin}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vuelo> consultarVuelos(
            @PathParam("fechaIni") String fechaIni,
            @PathParam("fechaFin") String fechaFin) {

        Date fechaIniD;
        Date fechaFinD;
        
        try {
            fechaIniD = new SimpleDateFormat("dd-MM-yyyy").parse(fechaIni);
            fechaFinD = new SimpleDateFormat("dd-MM-yyyy").parse(fechaFin);

        } catch (Exception e) {
            return null;
        }
        return vueloFacade.consultarVuelos(fechaIniD, fechaFinD);
    }

    private VueloFacade lookupVueloFacadeBean() {
        try {
            Context c = new InitialContext();
            return (VueloFacade) c.lookup("java:global/EjemploS1/VueloFacade!com.aerolinea.dao.VueloFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
