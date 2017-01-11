package model.dao.hsqldb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.dataStore.DataStore;
import model.domain.ActivoFijo;
import model.domain.Auxiliar;
import model.domain.Grupo;
import model.domain.Oficina;
import model.domain.Responsable;
import org.jc.JCCalendar;

/**
 * ActivoFijoDAO
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 * @version 0.1
 */
public class ActivoFijoDAO implements DataStore {

    @Override
    public int add(Object obj) {
        ActivoFijo activoFijo = (ActivoFijo) obj;
        JCCalendar calendar = new JCCalendar(activoFijo.getIncorporacion());
        String incorporacion = calendar.getYear() + "-" + calendar.getMonthInt() + "-" + calendar.getDayOfMonth();
        String sql = "INSERT INTO activosFijos (idActivoFijo,codigoADI,incorporacion,indiceUFV,descripcion,apiEstado,estado,observaciones,"
                + "rube,financiador,numeroConvenio,costoInicial,depreciacionAcumuladaInicial,factorActual,valor,porcentajeDepreciacion,"
                + "depreciacionGestion,depreciacionAcumulada,valorNeto,dias,vidaUtil,gestion,idAuxiliar,idResponsable,idUsuario)"
                + " VALUES ('" + activoFijo.getIdActivoFijo() + "','" + activoFijo.getCodigoADI() + "','" + incorporacion + "','" + activoFijo.getIndiceUFV() + "',"
                + "'" + activoFijo.getDescripcion() + "','" + activoFijo.getApiEstado() + "'," + activoFijo.getEstadoIndex() + ",'" + activoFijo.getObservaciones() + "',"
                + "'" + activoFijo.getRube() + "','" + activoFijo.getFinanciador() + "'," + activoFijo.getNumeroConvenio() + "," + activoFijo.getCostoInicial() + ","
                + activoFijo.getDepreciacionAcumuladaInicial() + "," + activoFijo.getFactorActual() + "," + activoFijo.getValor() + "," + activoFijo.getPorcentajeDepreciacion() + ","
                + activoFijo.getDepreciacionGestion() + "," + activoFijo.getDepreciacionAcumulada() + "," + activoFijo.getValorNeto() + "," + activoFijo.getDias() + ","
                + +activoFijo.getVidaUtil() + "," + calendar.getYear() + "," + activoFijo.getAuxiliar().getIdAuxiliar() + ","
                + activoFijo.getResponsable().getIdResponsable() + "," + activoFijo.getUsuario().getIdUsuario() + ")";
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int update(Object obj) {
        ActivoFijo activoFijo = (ActivoFijo) obj;
        JCCalendar calendar = new JCCalendar(activoFijo.getIncorporacion());
        String incorporacion = calendar.getYear() + "-" + calendar.getMonthInt() + "-" + calendar.getDayOfMonth();
        JCCalendar calendarModificacion = new JCCalendar();
        String fechaModificacion = calendarModificacion.getYear() + "-" + calendarModificacion.getMonthInt() + "-" + calendarModificacion.getDayOfMonth() + " " + calendarModificacion.getTime();
        String sql = "UPDATE activosFijos SET "
                + " codigoADI = '" + activoFijo.getCodigoADI() + "',"
                + " indiceUFV = '" + activoFijo.getIndiceUFV() + "',"
                + " incorporacion = '" + incorporacion + "',"
                + " descripcion = '" + activoFijo.getDescripcion() + "',"
                + " apiEstado =  '" + activoFijo.getApiEstado() + "',"
                + " estado = " + activoFijo.getEstadoIndex() + ","
                + " observaciones = '" + activoFijo.getObservaciones() + "',"
                + " rube = '" + activoFijo.getRube() + "',"
                + " financiador = '" + activoFijo.getFinanciador() + "',"
                + " numeroConvenio = " + activoFijo.getNumeroConvenio() + ","
                + " costoInicial = " + activoFijo.getCostoInicial() + ","
                + " depreciacionAcumuladaInicial = " + activoFijo.getDepreciacionAcumuladaInicial() + ","
                + " factorActual = " + activoFijo.getFactorActual() + ","
                + " valor = " + activoFijo.getValor() + ","
                + " porcentajeDepreciacion = " + activoFijo.getPorcentajeDepreciacion() + ","
                + " depreciacionGestion = " + activoFijo.getDepreciacionGestion() + ","
                + " depreciacionAcumulada = " + activoFijo.getDepreciacionAcumulada() + ","
                + " valorNeto = " + activoFijo.getValorNeto() + ","
                + " dias = " + activoFijo.getDias() + ","
                + " vidaUtil = " + activoFijo.getVidaUtil() + ","
                + " gestion = " + activoFijo.getGestion() + ","
                + " revaluo = " + activoFijo.isRevaluo() + ","
                + " cerrarGestion = " + activoFijo.isCerrarGestion() + ","
                + " baja = " + activoFijo.isBaja() + ","
                + " transfer = " + activoFijo.isTransfer() + ","
                + " fechaModificacionActivoFijo = '" + fechaModificacion + "',"
                + " idAuxiliar = " + activoFijo.getAuxiliar().getIdAuxiliar() + ","
                + " idResponsable = " + activoFijo.getResponsable().getIdResponsable() + ","
                + " idUsuario = " + activoFijo.getUsuario().getIdUsuario()
                + " WHERE idActivoFijo = '" + activoFijo.getIdActivoFijo() + "'";
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int delete(Object obj) {
        ActivoFijo activoFijo = (ActivoFijo) obj;
        String sql = "DELETE FROM activosFijos WHERE idActivoFijo = '" + activoFijo.getIdActivoFijo() + "'";
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public Object getObject(Object obj) {
        ActivoFijo activoFijo = (ActivoFijo) obj;
        String sql = "SELECT * FROM activosFijos af INNER JOIN auxiliares a ON af.idAuxiliar = a.idAuxiliar "
                + " INNER JOIN grupos g ON g.idGrupo = a.idGrupo"
                + " INNER JOIN responsables r ON af.idResponsable = r.idResponsable"
                + " INNER JOIN oficinas o ON o.idOficina = r.idOficina WHERE af.idActivoFijo = '" + activoFijo.getIdActivoFijo() + "'";
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        try {
            while (res.next()) {
                activoFijo.setIdActivoFijo(res.getString("idActivoFijo"));
                activoFijo.setCodigoADI(res.getString("codigoADI"));
                activoFijo.setIncorporacion(res.getDate("incorporacion"));
                activoFijo.setIndiceUFV(res.getString("indiceUFV"));
                activoFijo.setDescripcion(res.getString("descripcion"));
                activoFijo.setApiEstado(res.getString("apiEstado"));
                activoFijo.setEstado(res.getInt("estado"));
                activoFijo.setObservaciones(res.getString("observaciones"));
                activoFijo.setRube(res.getString("rube"));
                activoFijo.setFinanciador(res.getString("financiador"));
                activoFijo.setNumeroConvenio(res.getInt("numeroConvenio"));
                activoFijo.setCostoInicial(res.getDouble("costoInicial"));
                activoFijo.setDepreciacionAcumuladaInicial(res.getDouble("depreciacionAcumuladaInicial"));
                activoFijo.setFactorActual(res.getDouble("factorActual"));
                activoFijo.setValor(res.getDouble("valor"));
                activoFijo.setPorcentajeDepreciacion(res.getDouble("porcentajeDepreciacion"));
                activoFijo.setDepreciacionGestion(res.getDouble("depreciacionGestion"));
                activoFijo.setDepreciacionAcumulada(res.getDouble("depreciacionAcumulada"));
                activoFijo.setValorNeto(res.getDouble("valorNeto"));
                activoFijo.setDias(res.getInt("dias"));
                activoFijo.setVidaUtil(res.getInt("vidaUtil"));
                activoFijo.setGestion(res.getInt("gestion"));
                activoFijo.setRevaluo(res.getBoolean("revaluo"));
                activoFijo.setCerrarGestion(res.getBoolean("cerrarGestion"));
                activoFijo.setBaja(res.getBoolean("baja"));
                activoFijo.setTransfer(res.getBoolean("transfer"));
                activoFijo.setFechaModificacion(res.getDate("fechaModificacionActivoFijo"));
                //Auxiliar
                Auxiliar auxiliar = activoFijo.getAuxiliar();
                auxiliar.setIdAuxiliar(res.getInt("idAuxiliar"));
                auxiliar.setNombre(res.getString("nombreAuxiliar"));
                auxiliar.setFechaRegistro(res.getDate("fechaRegistroAuxiliar"));
                auxiliar.setFechaModificacion(res.getDate("fechaModificacionAuxiliar"));
                activoFijo.setAuxiliar(auxiliar);
                //Grupo
                Grupo grupo = activoFijo.getAuxiliar().getGrupo();
                grupo.setIdGrupo(res.getInt("idGrupo"));
                grupo.setNombre(res.getString("nombreGrupo"));
                grupo.setObservacion(res.getString("observacionGrupo"));
                grupo.setVidaUtil(res.getInt("vidaUtilGrupo"));
                grupo.setDepreciar(res.getBoolean("depreciarGrupo"));
                grupo.setActualizar(res.getBoolean("actualizarGrupo"));
                grupo.setFechaRegistro(res.getDate("fechaRegistroGrupo"));
                grupo.setFechaModificacion(res.getDate("fechaModificacionGrupo"));
                activoFijo.getAuxiliar().setGrupo(grupo);
                activoFijo.setGrupo(grupo);
                //Responsable
                Responsable responsable = activoFijo.getResponsable();
                responsable.setIdResponsable(res.getInt("idResponsable"));
                responsable.setNombre(res.getString("nombreResponsable"));
                responsable.setCargo(res.getString("cargoResponsable"));
                responsable.setCi(res.getString("ciResponsable"));
                responsable.setFechaRegistro(res.getDate("fechaRegistroResponsable"));
                responsable.setFechaModificacion(res.getDate("fechaModificacionResponsable"));
                activoFijo.setResponsable(responsable);
                //Oficina
                Oficina oficina = activoFijo.getResponsable().getOficina();
                oficina.setIdOficina(res.getInt("idOficina"));
                oficina.setNombre(res.getString("nombreOficina"));
                oficina.setObservacion(res.getString("observacionOficina"));
                oficina.setFechaRegistro(res.getDate("fechaRegistroOficina"));
                oficina.setFechaModificacion(res.getDate("fechaModificacionOficina"));
                activoFijo.getResponsable().setOficina(oficina);
                activoFijo.setOficina(oficina);
            }
        } catch (SQLException ex) {
            return null;
        }
        return activoFijo;
    }

    @Override
    public List getObjects(Object obj) {
        ActivoFijo activoFijo = (ActivoFijo) obj;
        String sql = "SELECT * FROM activosFijos af INNER JOIN auxiliares a ON af.idAuxiliar = a.idAuxiliar "
                + " INNER JOIN grupos g ON g.idGrupo = a.idGrupo"
                + " INNER JOIN responsables r ON af.idResponsable = r.idResponsable"
                + " INNER JOIN oficinas o ON o.idOficina = r.idOficina"
                + " WHERE o.idUnidad = " + activoFijo.getOficina().getUnidad().getIdUnidad() + " ORDER BY af.idActivoFijo";
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        List list = new ArrayList();
        try {
            if (res != null) {
                while (res.next()) {
                    //ActivoFijo
                    activoFijo = new ActivoFijo();
                    activoFijo.setIdActivoFijo(res.getString("idActivoFijo"));
                    activoFijo.setCodigoADI(res.getString("codigoADI"));
                    activoFijo.setIncorporacion(res.getDate("incorporacion"));
                    activoFijo.setIndiceUFV(res.getString("indiceUFV"));
                    activoFijo.setDescripcion(res.getString("descripcion"));
                    activoFijo.setApiEstado(res.getString("apiEstado"));
                    activoFijo.setEstado(res.getInt("estado"));
                    activoFijo.setObservaciones(res.getString("observaciones"));
                    activoFijo.setRube(res.getString("rube"));
                    activoFijo.setFinanciador(res.getString("financiador"));
                    activoFijo.setNumeroConvenio(res.getInt("numeroConvenio"));
                    activoFijo.setCostoInicial(res.getDouble("costoInicial"));
                    activoFijo.setDepreciacionAcumuladaInicial(res.getDouble("depreciacionAcumuladaInicial"));
                    activoFijo.setFactorActual(res.getDouble("factorActual"));
                    activoFijo.setValor(res.getDouble("valor"));
                    activoFijo.setPorcentajeDepreciacion(res.getDouble("porcentajeDepreciacion"));
                    activoFijo.setDepreciacionGestion(res.getDouble("depreciacionGestion"));
                    activoFijo.setDepreciacionAcumulada(res.getDouble("depreciacionAcumulada"));
                    activoFijo.setValorNeto(res.getDouble("valorNeto"));
                    activoFijo.setDias(res.getInt("dias"));
                    activoFijo.setVidaUtil(res.getInt("vidaUtil"));
                    activoFijo.setGestion(res.getInt("gestion"));
                    activoFijo.setRevaluo(res.getBoolean("revaluo"));
                    activoFijo.setCerrarGestion(res.getBoolean("cerrarGestion"));
                    activoFijo.setBaja(res.getBoolean("baja"));
                    activoFijo.setTransfer(res.getBoolean("transfer"));
                    activoFijo.setFechaModificacion(res.getDate("fechaModificacionActivoFijo"));
                    //Auxiliar
                    Auxiliar auxiliar = activoFijo.getAuxiliar();
                    auxiliar.setIdAuxiliar(res.getInt("idAuxiliar"));
                    auxiliar.setNombre(res.getString("nombreAuxiliar"));
                    auxiliar.setFechaRegistro(res.getDate("fechaRegistroAuxiliar"));
                    auxiliar.setFechaModificacion(res.getDate("fechaModificacionAuxiliar"));
                    activoFijo.setAuxiliar(auxiliar);
                    //Grupo
                    Grupo grupo = activoFijo.getAuxiliar().getGrupo();
                    grupo.setIdGrupo(res.getInt("idGrupo"));
                    grupo.setNombre(res.getString("nombreGrupo"));
                    grupo.setObservacion(res.getString("observacionGrupo"));
                    grupo.setVidaUtil(res.getInt("vidaUtilGrupo"));
                    grupo.setDepreciar(res.getBoolean("depreciarGrupo"));
                    grupo.setActualizar(res.getBoolean("actualizarGrupo"));
                    grupo.setFechaRegistro(res.getDate("fechaRegistroGrupo"));
                    grupo.setFechaModificacion(res.getDate("fechaModificacionGrupo"));
                    activoFijo.getAuxiliar().setGrupo(grupo);
                    activoFijo.setGrupo(grupo);
                    //Responsable
                    Responsable responsable = activoFijo.getResponsable();
                    responsable.setIdResponsable(res.getInt("idResponsable"));
                    responsable.setNombre(res.getString("nombreResponsable"));
                    responsable.setCargo(res.getString("cargoResponsable"));
                    responsable.setCi(res.getString("ciResponsable"));
                    responsable.setFechaRegistro(res.getDate("fechaRegistroResponsable"));
                    responsable.setFechaModificacion(res.getDate("fechaModificacionResponsable"));
                    activoFijo.setResponsable(responsable);
                    //Oficina
                    Oficina oficina = activoFijo.getResponsable().getOficina();
                    oficina.setIdOficina(res.getInt("idOficina"));
                    oficina.setNombre(res.getString("nombreOficina"));
                    oficina.setObservacion(res.getString("observacionOficina"));
                    oficina.setFechaRegistro(res.getDate("fechaRegistroOficina"));
                    oficina.setFechaModificacion(res.getDate("fechaModificacionOficina"));
                    activoFijo.getResponsable().setOficina(oficina);
                    activoFijo.setOficina(oficina);
                    list.add(activoFijo);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActivoFijoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return list;
    }

    @Override
    public List select(Object obj) {
        ActivoFijo activoFijo = (ActivoFijo) obj;
        String sql = "";
        if (activoFijo.getGrupo().getIdGrupo() != 0) {
            sql = "SELECT * FROM activosFijos af INNER JOIN auxiliares a ON af.idAuxiliar = a.idAuxiliar "
                    + " INNER JOIN grupos g ON g.idGrupo = a.idGrupo"
                    + " INNER JOIN responsables r ON af.idResponsable = r.idResponsable"
                    + " INNER JOIN oficinas o ON o.idOficina = r.idOficina"
                    + " WHERE g.idGrupo = " + activoFijo.getGrupo().getIdGrupo()
                    + " ORDER BY af.idActivoFijo";
        } else {
            sql = "SELECT * FROM activosFijos af INNER JOIN auxiliares a ON af.idAuxiliar = a.idAuxiliar "
                    + " INNER JOIN grupos g ON g.idGrupo = a.idGrupo"
                    + " INNER JOIN responsables r ON af.idResponsable = r.idResponsable"
                    + " INNER JOIN oficinas o ON o.idOficina = r.idOficina"
                    + " ORDER BY af.idActivoFijo";
        }
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        List list = new ArrayList();
        try {
            if (res != null) {
                while (res.next()) {
                    //ActivoFijo
                    activoFijo = new ActivoFijo();
                    activoFijo.setIdActivoFijo(res.getString("idActivoFijo"));
                    activoFijo.setCodigoADI(res.getString("codigoADI"));
                    activoFijo.setIncorporacion(res.getDate("incorporacion"));
                    activoFijo.setIndiceUFV(res.getString("indiceUFV"));
                    activoFijo.setDescripcion(res.getString("descripcion"));
                    activoFijo.setApiEstado(res.getString("apiEstado"));
                    activoFijo.setEstado(res.getInt("estado"));
                    activoFijo.setObservaciones(res.getString("observaciones"));
                    activoFijo.setRube(res.getString("rube"));
                    activoFijo.setFinanciador(res.getString("financiador"));
                    activoFijo.setNumeroConvenio(res.getInt("numeroConvenio"));
                    activoFijo.setCostoInicial(res.getDouble("costoInicial"));
                    activoFijo.setDepreciacionAcumuladaInicial(res.getDouble("depreciacionAcumuladaInicial"));
                    activoFijo.setFactorActual(res.getDouble("factorActual"));
                    activoFijo.setValor(res.getDouble("valor"));
                    activoFijo.setPorcentajeDepreciacion(res.getDouble("porcentajeDepreciacion"));
                    activoFijo.setDepreciacionGestion(res.getDouble("depreciacionGestion"));
                    activoFijo.setDepreciacionAcumulada(res.getDouble("depreciacionAcumulada"));
                    activoFijo.setValorNeto(res.getDouble("valorNeto"));
                    activoFijo.setDias(res.getInt("dias"));
                    activoFijo.setVidaUtil(res.getInt("vidaUtil"));
                    activoFijo.setGestion(res.getInt("gestion"));
                    activoFijo.setRevaluo(res.getBoolean("revaluo"));
                    activoFijo.setCerrarGestion(res.getBoolean("cerrarGestion"));
                    activoFijo.setBaja(res.getBoolean("baja"));
                    activoFijo.setTransfer(res.getBoolean("transfer"));
                    activoFijo.setFechaModificacion(res.getDate("fechaModificacionActivoFijo"));
                    //Auxiliar
                    Auxiliar auxiliar = activoFijo.getAuxiliar();
                    auxiliar.setIdAuxiliar(res.getInt("idAuxiliar"));
                    auxiliar.setNombre(res.getString("nombreAuxiliar"));
                    auxiliar.setFechaRegistro(res.getDate("fechaRegistroAuxiliar"));
                    auxiliar.setFechaModificacion(res.getDate("fechaModificacionAuxiliar"));
                    activoFijo.setAuxiliar(auxiliar);
                    //Grupo
                    Grupo grupo = activoFijo.getAuxiliar().getGrupo();
                    grupo.setIdGrupo(res.getInt("idGrupo"));
                    grupo.setNombre(res.getString("nombreGrupo"));
                    grupo.setObservacion(res.getString("observacionGrupo"));
                    grupo.setVidaUtil(res.getInt("vidaUtilGrupo"));
                    grupo.setDepreciar(res.getBoolean("depreciarGrupo"));
                    grupo.setActualizar(res.getBoolean("actualizarGrupo"));
                    grupo.setFechaRegistro(res.getDate("fechaRegistroGrupo"));
                    grupo.setFechaModificacion(res.getDate("fechaModificacionGrupo"));
                    activoFijo.getAuxiliar().setGrupo(grupo);
                    activoFijo.setGrupo(grupo);
                    //Responsable
                    Responsable responsable = activoFijo.getResponsable();
                    responsable.setIdResponsable(res.getInt("idResponsable"));
                    responsable.setNombre(res.getString("nombreResponsable"));
                    responsable.setCargo(res.getString("cargoResponsable"));
                    responsable.setCi(res.getString("ciResponsable"));
                    responsable.setFechaRegistro(res.getDate("fechaRegistroResponsable"));
                    responsable.setFechaModificacion(res.getDate("fechaModificacionResponsable"));
                    activoFijo.setResponsable(responsable);
                    //Oficina
                    Oficina oficina = activoFijo.getResponsable().getOficina();
                    oficina.setIdOficina(res.getInt("idOficina"));
                    oficina.setNombre(res.getString("nombreOficina"));
                    oficina.setObservacion(res.getString("observacionOficina"));
                    oficina.setFechaRegistro(res.getDate("fechaRegistroOficina"));
                    oficina.setFechaModificacion(res.getDate("fechaModificacionOficina"));
                    activoFijo.getResponsable().setOficina(oficina);
                    activoFijo.setOficina(oficina);
                    list.add(activoFijo);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActivoFijoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return list;
    }

    @Override
    public List query(Object obj, int type, String condicion) {
        ActivoFijo activoFijo = (ActivoFijo) obj;
        String sql = "";
        List list = new ArrayList();
        ResultSet res;
        if (type != 10) {
            if (type == 0) {
                sql = "SELECT *"
                        + " FROM"
                        + " grupos grupos INNER JOIN auxiliares auxiliares ON grupos.idgrupo = auxiliares.idgrupo"
                        + " INNER JOIN activosfijos activosfijos ON auxiliares.idauxiliar = activosfijos.idauxiliar"
                        + " INNER JOIN responsables responsables ON activosfijos.idresponsable = responsables.idresponsable"
                        + " INNER JOIN oficinas oficinas ON responsables.idoficina = oficinas.idoficina"
                        + " WHERE true " + condicion
                        + " ORDER BY activosfijos.idActivoFijo ASC";
            } else if (type == 1) {
                sql = "SELECT * "
                        + " FROM"
                        + " grupos grupos INNER JOIN auxiliares auxiliares ON grupos.idgrupo = auxiliares.idgrupo"
                        + " INNER JOIN activosfijos activosfijos ON auxiliares.idauxiliar = activosfijos.idauxiliar"
                        + " INNER JOIN responsables responsables ON activosfijos.idresponsable = responsables.idresponsable"
                        + " INNER JOIN oficinas oficinas ON responsables.idoficina = oficinas.idoficina"
                        + " WHERE true " + condicion
                        + " ORDER BY grupos.idGrupo";
            } else if (type == 3) {
                sql = "SELECT * "
                        + " FROM"
                        + " grupos grupos INNER JOIN auxiliares auxiliares ON grupos.idgrupo = auxiliares.idgrupo"
                        + " INNER JOIN activosfijos activosfijos ON auxiliares.idauxiliar = activosfijos.idauxiliar"
                        + " INNER JOIN responsables responsables ON activosfijos.idresponsable = responsables.idresponsable"
                        + " INNER JOIN oficinas oficinas ON responsables.idoficina = oficinas.idoficina"
                        + " WHERE true " + condicion
                        + " ORDER BY oficinas.idOficina";
            } else if (type == 7) {
                sql = "SELECT *"
                        + " FROM"
                        + " grupos grupos INNER JOIN auxiliares auxiliares ON grupos.idgrupo = auxiliares.idgrupo"
                        + " INNER JOIN activosfijos activosfijos ON auxiliares.idauxiliar = activosfijos.idauxiliar"
                        + " INNER JOIN responsables responsables ON activosfijos.idresponsable = responsables.idresponsable"
                        + " INNER JOIN oficinas oficinas ON responsables.idoficina = oficinas.idoficina"
                        + " WHERE"
                        + " activosFijos.transfer = true AND true " + condicion
                        + " ORDER BY activosfijos.idActivoFijo ASC";
            } else if (type == 8) {
                sql = "SELECT *"
                        + " FROM"
                        + " grupos grupos INNER JOIN auxiliares auxiliares ON grupos.idgrupo = auxiliares.idgrupo"
                        + " INNER JOIN activosfijos activosfijos ON auxiliares.idauxiliar = activosfijos.idauxiliar"
                        + " INNER JOIN responsables responsables ON activosfijos.idresponsable = responsables.idresponsable"
                        + " INNER JOIN oficinas oficinas ON responsables.idoficina = oficinas.idoficina"
                        + " WHERE"
                        + " activosFijos.revaluo = true AND true " + condicion
                        + " ORDER BY activosfijos.idActivoFijo ASC";
            } else if (type == 9) {
                sql = "SELECT *"
                        + " FROM"
                        + " grupos grupos INNER JOIN auxiliares auxiliares ON grupos.idgrupo = auxiliares.idgrupo"
                        + " INNER JOIN activosfijos activosfijos ON auxiliares.idauxiliar = activosfijos.idauxiliar"
                        + " INNER JOIN responsables responsables ON activosfijos.idresponsable = responsables.idresponsable"
                        + " INNER JOIN oficinas oficinas ON responsables.idoficina = oficinas.idoficina"
                        + " WHERE"
                        + " activosFijos.baja = true AND true " + condicion
                        + " ORDER BY activosfijos.idActivoFijo ASC";
            }
            res = HsqldbDAO.consultaBD.consultar(sql);
            try {
                if (res != null) {
                    while (res.next()) {
                        //ActivoFijo
                        activoFijo = new ActivoFijo();
                        activoFijo.setIdActivoFijo(res.getString("idActivoFijo"));
                        activoFijo.setCodigoADI(res.getString("codigoADI"));
                        activoFijo.setIncorporacion(res.getDate("incorporacion"));
                        activoFijo.setIndiceUFV(res.getString("indiceUFV"));
                        activoFijo.setDescripcion(res.getString("descripcion"));
                        activoFijo.setApiEstado(res.getString("apiEstado"));
                        activoFijo.setEstado(res.getInt("estado"));
                        activoFijo.setObservaciones(res.getString("observaciones"));
                        activoFijo.setRube(res.getString("rube"));
                        activoFijo.setFinanciador(res.getString("financiador"));
                        activoFijo.setNumeroConvenio(res.getInt("numeroConvenio"));
                        activoFijo.setCostoInicial(res.getDouble("costoInicial"));
                        activoFijo.setDepreciacionAcumuladaInicial(res.getDouble("depreciacionAcumuladaInicial"));
                        activoFijo.setFactorActual(res.getDouble("factorActual"));
                        activoFijo.setValor(res.getDouble("valor"));
                        activoFijo.setPorcentajeDepreciacion(res.getDouble("porcentajeDepreciacion"));
                        activoFijo.setDepreciacionGestion(res.getDouble("depreciacionGestion"));
                        activoFijo.setDepreciacionAcumulada(res.getDouble("depreciacionAcumulada"));
                        activoFijo.setValorNeto(res.getDouble("valorNeto"));
                        activoFijo.setDias(res.getInt("dias"));
                        activoFijo.setVidaUtil(res.getInt("vidaUtil"));
                        activoFijo.setGestion(res.getInt("gestion"));
                        activoFijo.setRevaluo(res.getBoolean("revaluo"));
                        activoFijo.setCerrarGestion(res.getBoolean("cerrarGestion"));
                        activoFijo.setBaja(res.getBoolean("baja"));
                        activoFijo.setTransfer(res.getBoolean("transfer"));
                        activoFijo.setFechaModificacion(res.getDate("fechaModificacionActivoFijo"));
                        //Auxiliar
                        Auxiliar auxiliar = activoFijo.getAuxiliar();
                        auxiliar.setIdAuxiliar(res.getInt("idAuxiliar"));
                        auxiliar.setNombre(res.getString("nombreAuxiliar"));
                        auxiliar.setFechaRegistro(res.getDate("fechaRegistroAuxiliar"));
                        auxiliar.setFechaModificacion(res.getDate("fechaModificacionAuxiliar"));
                        activoFijo.setAuxiliar(auxiliar);
                        //Grupo
                        Grupo grupo = activoFijo.getAuxiliar().getGrupo();
                        grupo.setIdGrupo(res.getInt("idGrupo"));
                        grupo.setNombre(res.getString("nombreGrupo"));
                        grupo.setObservacion(res.getString("observacionGrupo"));
                        grupo.setVidaUtil(res.getInt("vidaUtilGrupo"));
                        grupo.setDepreciar(res.getBoolean("depreciarGrupo"));
                        grupo.setActualizar(res.getBoolean("actualizarGrupo"));
                        grupo.setFechaRegistro(res.getDate("fechaRegistroGrupo"));
                        grupo.setFechaModificacion(res.getDate("fechaModificacionGrupo"));
                        activoFijo.getAuxiliar().setGrupo(grupo);
                        activoFijo.setGrupo(grupo);
                        //Responsable
                        Responsable responsable = activoFijo.getResponsable();
                        responsable.setIdResponsable(res.getInt("idResponsable"));
                        responsable.setNombre(res.getString("nombreResponsable"));
                        responsable.setCargo(res.getString("cargoResponsable"));
                        responsable.setCi(res.getString("ciResponsable"));
                        responsable.setFechaRegistro(res.getDate("fechaRegistroResponsable"));
                        responsable.setFechaModificacion(res.getDate("fechaModificacionResponsable"));
                        activoFijo.setResponsable(responsable);
                        //Oficina
                        Oficina oficina = activoFijo.getResponsable().getOficina();
                        oficina.setIdOficina(res.getInt("idOficina"));
                        oficina.setNombre(res.getString("nombreOficina"));
                        oficina.setObservacion(res.getString("observacionOficina"));
                        oficina.setFechaRegistro(res.getDate("fechaRegistroOficina"));
                        oficina.setFechaModificacion(res.getDate("fechaModificacionOficina"));
                        activoFijo.getResponsable().setOficina(oficina);
                        activoFijo.setOficina(oficina);
                        list.add(activoFijo);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ActivoFijoDAO.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } else {
            sql = "SELECT"
                    + " COUNT(activosfijos.idActivoFijo) AS cantidad,"
                    + " SUM(activosfijos.costoInicial) AS costoInicial,"
                    + " SUM(activosfijos.valor) AS valor,"
                    + " SUM(activosfijos.valorNeto) AS valorNeto,"
                    + " SUM(activosFijos.depreciacionGestion) AS depreciacionGestion,"
                    + " SUM(activosFijos.depreciacionAcumulada) AS depreciacionAcumulada,"
                    + " grupos.idGrupo,"
                    + " grupos.nombreGrupo,"
                    + " grupos.vidaUtilGrupo"
                    + " FROM"
                    + " grupos grupos INNER JOIN auxiliares auxiliares ON grupos.idgrupo = auxiliares.idgrupo"
                    + " INNER JOIN activosfijos activosfijos ON auxiliares.idauxiliar = activosfijos.idauxiliar"
                    + " INNER JOIN responsables responsables ON activosfijos.idresponsable = responsables.idresponsable"
                    + " INNER JOIN oficinas oficinas ON responsables.idoficina = oficinas.idoficina"
                    + " WHERE true " + condicion
                    + " GROUP BY grupos.idGrupo,nombreGrupo,vidaUtilGrupo"
                    + " ORDER BY grupos.idGrupo";
            res = HsqldbDAO.consultaBD.consultar(sql);
            try {
                if (res != null) {
                    while (res.next()) {
                        //ActivoFijo
                        activoFijo = new ActivoFijo();
                        activoFijo.setCostoInicial(res.getDouble("costoInicial"));
                        activoFijo.setValor(res.getDouble("valor"));
                        activoFijo.setDepreciacionGestion(res.getDouble("depreciacionGestion"));
                        activoFijo.setDepreciacionAcumulada(res.getDouble("depreciacionAcumulada"));
                        activoFijo.setValorNeto(res.getDouble("valorNeto"));
                        activoFijo.setCantidad(res.getInt("cantidad"));
                        //Grupo
                        Grupo grupo = activoFijo.getAuxiliar().getGrupo();
                        grupo.setIdGrupo(res.getInt("idGrupo"));
                        grupo.setNombre(res.getString("nombreGrupo"));
                        grupo.setVidaUtil(res.getInt("vidaUtilGrupo"));                      
                        activoFijo.getAuxiliar().setGrupo(grupo);
                        activoFijo.setGrupo(grupo);
                        list.add(activoFijo);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ActivoFijoDAO.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return list;
    }
}