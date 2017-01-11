/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import java.util.Date;

/**
 * Configuracion
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 */
public class Configuracion {

    private int idConfiguracion;
    private String UFV;
    private Date fechaUFV = new Date();

    public Configuracion() {
    }

    public int getIdConfiguracion() {
        return idConfiguracion;
    }

    public void setIdConfiguracion(int idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }

    public String getUFV() {
        return UFV;
    }

    public void setUFV(String UFV) {
        this.UFV = UFV;
    }

    public Date getFechaUFV() {
        return fechaUFV;
    }

    public void setFechaUFV(Date fechaUFV) {
        this.fechaUFV = fechaUFV;
    }
}
