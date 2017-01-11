/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import java.util.Date;

/**
 * Revaluo
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 */
public class Revaluo {

    private int idRevaluo;
    private Date incorporacion = new Date();
    private Date fechaRevaluoTecnico = new Date();
    private double costoInicial;
    private double costoInicialNuevo;
    private int vidaUtil;
    private int vidaUtilNuevo;
    private String indiceUFV;
    private String indiceUFVNuevo;
    private String disposicionRespaldo;
    private String motivo;
    private double depreciacionGestion;
    private double depreciacionAcumulada;
    private double valorNeto;
    private int gestion;    
    private ActivoFijo activoFijo = new ActivoFijo();
    private Usuario usuario = new Usuario();

    public int getIdRevaluo() {
        return idRevaluo;
    }

    public void setIdRevaluo(int idRevaluo) {
        this.idRevaluo = idRevaluo;
    }
    
    public String getIndiceUFV() {
        return indiceUFV;
    }

    public void setIndiceUFV(String indiceUFV) {
        this.indiceUFV = indiceUFV;
    }

    public String getIndiceUFVNuevo() {
        return indiceUFVNuevo;
    }

    public void setIndiceUFVNuevo(String indiceUFVNuevo) {
        this.indiceUFVNuevo = indiceUFVNuevo;
    }

    public Date getIncorporacion() {
        return incorporacion;
    }

    public void setIncorporacion(Date incorporacion) {
        this.incorporacion = incorporacion;
    }

    public Date getFechaRevaluoTecnico() {
        return fechaRevaluoTecnico;
    }

    public void setFechaRevaluoTecnico(Date fechaRevaluoTecnico) {
        this.fechaRevaluoTecnico = fechaRevaluoTecnico;
    }

    public double getCostoInicial() {
        return costoInicial;
    }

    public void setCostoInicial(double costoInicial) {
        this.costoInicial = costoInicial;
    }

    public double getCostoInicialNuevo() {
        return costoInicialNuevo;
    }

    public void setCostoInicialNuevo(double costoInicialNuevo) {
        this.costoInicialNuevo = costoInicialNuevo;
    }

    public int getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(int vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public int getVidaUtilNuevo() {
        return vidaUtilNuevo;
    }

    public void setVidaUtilNuevo(int vidaUtilNuevo) {
        this.vidaUtilNuevo = vidaUtilNuevo;
    }

    public String getDisposicionRespaldo() {
        return disposicionRespaldo;
    }

    public void setDisposicionRespaldo(String disposicionRespaldo) {
        this.disposicionRespaldo = disposicionRespaldo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public double getDepreciacionGestion() {
        return depreciacionGestion;
    }

    public void setDepreciacionGestion(double depreciacionGestion) {
        this.depreciacionGestion = depreciacionGestion;
    }

    public double getDepreciacionAcumulada() {
        return depreciacionAcumulada;
    }

    public void setDepreciacionAcumulada(double depreciacionAcumulada) {
        this.depreciacionAcumulada = depreciacionAcumulada;
    }

    public double getValorNeto() {
        return valorNeto;
    }

    public void setValorNeto(double valorNeto) {
        this.valorNeto = valorNeto;
    }

    public int getGestion() {
        return gestion;
    }

    public void setGestion(int gestion) {
        this.gestion = gestion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ActivoFijo getActivoFijo() {
        return activoFijo;
    }

    public void setActivoFijo(ActivoFijo activoFijo) {
        this.activoFijo = activoFijo;
    }        
}
