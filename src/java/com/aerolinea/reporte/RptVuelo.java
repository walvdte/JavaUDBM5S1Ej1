/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerolinea.reporte;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author JAVA
 */
@Named(value = "rptVuelo")
@ViewScoped
public class RptVuelo implements Serializable {

    private Date fechaIni;
    private Date fechaFin;
    private Connection connection;

    JasperPrint jasperPrint;
    HttpServletResponse httpServletResponse;

    @PostConstruct
    public void alCargar() {
        inicializarVariables();
    }

    public void inicializarVariables() {
        ConexionPool cp = new ConexionPool();
        cp.conectar();
        connection = cp.getConexion();
    }

    public String generarReporte() throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("reportes/rptVuelo.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaIni", fechaIni);
        parametros.put("fechaFin", fechaFin);
        parametros.put(JRParameter.REPORT_LOCALE, new Locale("es", "ES"));
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, connection);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline;filename=rptVuelos.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
        return "";
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

}
