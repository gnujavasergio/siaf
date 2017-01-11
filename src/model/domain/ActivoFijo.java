/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import java.util.Calendar;
import java.util.Date;
import org.jc.JCCalendar;
import org.jc.util.CMath;
import org.joda.time.DateTime;
import org.joda.time.Days;
import view.ViewMain;

/**
 * Activo Fijo
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 * @version 0.1
 */
public class ActivoFijo {

    public static final int ESTADO_NONE = 0;
    public static final int ESTADO_BUENO = 1;
    public static final int ESTADO_REGULAR = 2;
    public static final int ESTADO_MALO = 3;
    private String idActivoFijo;
    private String codigoADI;
    private Date incorporacion = new Date();
    private String indiceUFV;
    private String descripcion;
    private String apiEstado = "ELABORADO"; // 1 Elaborado 2 Aprovado
    private int estado;
    private String estadoArray[] = {"Bueno", "Regular", "Malo"};
    private String observaciones = "";
    private String rube = "";
    private String financiador = "";
    private int numeroConvenio;
    private double costoInicial;
    private double depreciacionAcumuladaInicial;
    private double factorActual;
    private double valor;
    private double porcentajeDepreciacion;
    private double depreciacionGestion;
    private double depreciacionAcumulada;
    private double valorNeto;
    private int dias;
    private int vidaUtil;
    private boolean revaluo; // si el revaluo es verdadero ya no se utiliza Grupo solo la vida Util
    private boolean cerrarGestion;
    private boolean baja;
    private boolean transfer;
    private int gestion;
    private Date fechaModificacion;
    private Grupo grupo = new Grupo();
    private Auxiliar auxiliar = new Auxiliar();
    private Oficina oficina = new Oficina();
    private Responsable responsable = new Responsable();
    private Usuario usuario = new Usuario();
    //Report
    private String nombreGrupo;
    private int cantidad;
    private double actualizacionGestion;
    private double valorInicial;
    private double valorNetoInicial;
    private double actualizacionDepreciacionAcumulada;
    
    public ActivoFijo() {
    }

    public String getIdActivoFijo() {
        return idActivoFijo;
    }

    public int getGestion() {
        return gestion;
    }

    public void setGestion(int gestion) {
        this.gestion = gestion;
    }

    public boolean isCerrarGestion() {
        return cerrarGestion;
    }

    public void setCerrarGestion(boolean cerrarGestion) {
        this.cerrarGestion = cerrarGestion;
    }

    public void setIdActivoFijo(String idActivoFijo) {
        this.idActivoFijo = idActivoFijo;
    }

    public String getCodigoADI() {
        return codigoADI;
    }

    public void setCodigoADI(String codigoADI) {
        this.codigoADI = codigoADI;
    }

    public Date getIncorporacion() {
        return incorporacion;
    }

    public void setIncorporacion(Date incorporacion) {
        this.incorporacion = incorporacion;
    }

    public String getIndiceUFV() {
        return indiceUFV;
    }

    public void setIndiceUFV(String indiceUFV) {
        this.indiceUFV = indiceUFV;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getApiEstado() {
        return apiEstado;
    }

    public void setApiEstado(String apiEstado) {
        this.apiEstado = apiEstado;
    }

    public String getEstado() {
        return estadoArray[estado];
    }

    public int getEstadoIndex() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getRube() {
        return rube;
    }

    public void setRube(String rube) {
        this.rube = rube;
    }

    public String getFinanciador() {
        return financiador;
    }

    public void setFinanciador(String financiador) {
        this.financiador = financiador;
    }

    public int getNumeroConvenio() {
        return numeroConvenio;
    }

    public void setNumeroConvenio(int numeroConvenio) {
        this.numeroConvenio = numeroConvenio;
    }

    public double getCostoInicial() {
        return costoInicial;
    }

    public void setCostoInicial(double costoInicial) {
        this.costoInicial = costoInicial;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Auxiliar getAuxiliar() {
        return auxiliar;
    }

    public void setAuxiliar(Auxiliar auxiliar) {
        this.auxiliar = auxiliar;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    public double getDepreciacionAcumuladaInicial() {
        return depreciacionAcumuladaInicial;
    }

    public void setDepreciacionAcumuladaInicial(double depreciacionAcumuladaInicial) {
        this.depreciacionAcumuladaInicial = depreciacionAcumuladaInicial;
    }

    public double getFactorActual() {
        return factorActual;
    }

    public void setFactorActual(double factorActual) {
        this.factorActual = factorActual;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getPorcentajeDepreciacion() {
        return porcentajeDepreciacion;
    }

    public void setPorcentajeDepreciacion(double porcentajeDepreciacion) {
        this.porcentajeDepreciacion = porcentajeDepreciacion;
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

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(int vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public boolean isRevaluo() {
        return revaluo;
    }

    public void setRevaluo(boolean revaluo) {
        this.revaluo = revaluo;
    }

    public boolean isBaja() {
        return baja;
    }

    public void setBaja(boolean baja) {
        this.baja = baja;
    }

    public boolean isTransfer() {
        return transfer;
    }

    public void setTransfer(boolean transfer) {
        this.transfer = transfer;
    }

    public String[] getEstadoArray() {
        return estadoArray;
    }

    public void setEstadoArray(String[] estadoArray) {
        this.estadoArray = estadoArray;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getActualizacionGestion() {
        return actualizacionGestion;
    }

    public void setActualizacionGestion(double actualizacionGestion) {
        this.actualizacionGestion = actualizacionGestion;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public double getValorNetoInicial() {
        return valorNetoInicial;
    }

    public void setValorNetoInicial(double valorNetoInicial) {
        this.valorNetoInicial = valorNetoInicial;
    }

    public double getActualizacionDepreciacionAcumulada() {
        return actualizacionDepreciacionAcumulada;
    }

    public void setActualizacionDepreciacionAcumulada(double actualizacionDepreciacionAcumulada) {
        this.actualizacionDepreciacionAcumulada = actualizacionDepreciacionAcumulada;
    }

    public void calculo(Date today) {
        JCCalendar calendarIncorporacion = new JCCalendar(incorporacion);
        JCCalendar calendarToday;
        if (today != null) {
            calendarToday = new JCCalendar(today);
        } else {
            calendarToday = new JCCalendar();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendarIncorporacion.getYear() + vidaUtil, calendarIncorporacion.getMonthInt() - 1, calendarIncorporacion.getDayOfMonth());
        JCCalendar calendarEnd = new JCCalendar(calendar.getTime());
        double ufv;
        int diasActivoFijo = Days.daysBetween(new DateTime(calendarIncorporacion.getDate()), new DateTime(calendarToday.getDate())).getDays() + 1;
        int diasTotal = Days.daysBetween(new DateTime(calendarIncorporacion.getDate()), new DateTime(calendarEnd.getDate())).getDays();
        double depreciacionDia = 0;
        double depreciacionYear = 0;
        if (diasActivoFijo >= diasTotal && vidaUtil != 0) {
            Calendar calendarYear = Calendar.getInstance();
            calendarYear.set(calendarToday.getYear(), 0, 1);
            JCCalendar calendarStart = new JCCalendar(calendarYear.getTime());//cuando empieza el año
            int diasYear = Days.daysBetween(new DateTime(calendarStart.getDate()), new DateTime(calendarEnd.getDate())).getDays();
            Configuracion configuracion = new Configuracion();
            configuracion.setFechaUFV(calendar.getTime());
            configuracion = (Configuracion) ViewMain.DAO.getObject(configuracion);
            double ufvActual = Double.parseDouble("0.0");
            if (idActivoFijo != null && indiceUFV != null) {
                ufv = Double.parseDouble(indiceUFV);
            } else {
                ufv = ufvActual;
            }
            factorActual = (CMath.redondear(ufvActual / ufv, 6));
            valor = CMath.redondear(ufvActual * costoInicial / ufv, 2);
            valorNeto = 1.0;
            depreciacionAcumulada = valor - valorNeto;
            if (vidaUtil != 0) {
                depreciacionYear = (double) diasYear / (double) (vidaUtil * 365);
            }
            if (calendarEnd.getYear() == calendarToday.getYear()) {
                depreciacionGestion = CMath.redondear(depreciacionYear * valor, 2);
            } else {
                depreciacionGestion = 0;
            }
            dias = diasTotal;
        } else {
            calendar.set(calendarToday.getYear(), 0, 1);
            JCCalendar calendarStart = new JCCalendar(calendar.getTime());//cuando empieza el año
            int diasYear = Days.daysBetween(new DateTime(calendarStart.getDate()), new DateTime(calendarToday.getDate())).getDays() + 1;
            double ufvActual;
            if (today != null) {
                Configuracion configuracion = new Configuracion();
                configuracion.setFechaUFV(today);
                configuracion = (Configuracion) ViewMain.DAO.getObject(configuracion);
                ufvActual = Double.parseDouble(configuracion.getUFV());
            } else {
                ufvActual = Double.parseDouble(ViewMain.viewMain.getConfiguracion().getUFV());
            }
            if (idActivoFijo != null && indiceUFV != null) {
                ufv = Double.parseDouble(indiceUFV);
            } else {
                ufv = ufvActual;
            }
            factorActual = CMath.redondear(ufvActual / ufv, 6);
            if (grupo.isActualizar()) {
                valor = CMath.redondear(ufvActual * costoInicial / ufv, 2);
            } else {
                valor = costoInicial;
            }
            dias = diasActivoFijo;
            if (vidaUtil != 0) {
                depreciacionDia = (double) dias / (double) (vidaUtil * 365);
                depreciacionYear = (double) diasYear / (double) (vidaUtil * 365);
            }
            depreciacionAcumulada = CMath.redondear(depreciacionDia * valor, 2);
            if (diasYear < dias) {
                depreciacionGestion = CMath.redondear(depreciacionYear * valor, 2);
            } else {
                depreciacionGestion = depreciacionAcumulada;
            }
            valorNeto = CMath.redondear(valor - depreciacionAcumulada, 2);
        }
        if (vidaUtil != 0) {
            porcentajeDepreciacion = CMath.redondear((double) 100 / vidaUtil,2);
        }
    }

    @Override
    public String toString() {
        return this.descripcion;
    }

}
