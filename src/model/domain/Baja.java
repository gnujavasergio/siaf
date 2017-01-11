/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import java.util.Date;

/**
 * Baja
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 */
public class Baja {

    private int idBaja;
    private Date fechaBaja = new Date();
    private String disposicionRespaldo;
    private String motivoBaja;
    private String motivo;
    private Unidad unidad = new Unidad();
    private ActivoFijo activoFijo = new ActivoFijo();
    private Usuario usuario = new Usuario();

    public int getIdBaja() {
        return idBaja;
    }

    public void setIdBaja(int idBaja) {
        this.idBaja = idBaja;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getDisposicionRespaldo() {
        return disposicionRespaldo;
    }

    public void setDisposicionRespaldo(String disposicionRespaldo) {
        this.disposicionRespaldo = disposicionRespaldo;
    }

    public String getMotivoBaja() {
        return motivoBaja;
    }

    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public ActivoFijo getActivoFijo() {
        return activoFijo;
    }

    public void setActivoFijo(ActivoFijo activoFijo) {
        this.activoFijo = activoFijo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }   

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }
    
    
}
