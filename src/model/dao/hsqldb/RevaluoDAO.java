package model.dao.hsqldb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.dataStore.DataStore;
import model.domain.ActivoFijo;
import model.domain.Revaluo;
import org.jc.JCCalendar;

/**
 * RevaluoDAO
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 * @version 0.1
 */
public class RevaluoDAO implements DataStore {

    @Override
    public int add(Object obj) {
        Revaluo revaluo = (Revaluo) obj;
        String sql = "select max(idRevaluo) AS ID from revaluos";
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        int id = 0;
        try {
            while (res.next()) {
                id = res.getInt("ID");
            }
        } catch (SQLException ex) {
        }
        JCCalendar calendarIncorporacion = new JCCalendar();
        String incorporacion = calendarIncorporacion.getYear() + "-" + calendarIncorporacion.getMonthInt() + "-" + calendarIncorporacion.getDayOfMonth();
        JCCalendar calendarFechaRevaluoTecnico = new JCCalendar();
        String fechaRevaluoTecnico = calendarFechaRevaluoTecnico.getYear() + "-" + calendarFechaRevaluoTecnico.getMonthInt() + "-" + calendarFechaRevaluoTecnico.getDayOfMonth();
        sql = "INSERT INTO revaluos (idRevaluo,idActivoFijo,incorporacionRevaluo,fechaRevaluoTecnicoRevaluo,costoInicialRevaluo,costoInicialNuevoRevaluo,vidaUtilRevaluo,"
                + "vidaUtilNuevoRevaluo,indiceUFVRevaluo,indiceUFVNuevoRevaluo,disposicionRespaldoRevaluo,motivoRevaluo,depreciacionGestionRevaluo,depreciacionAcumuladaRevaluo,valorNetoRevaluo,gestionRevaluo,"
                + "idUsuario)"
                + " VALUES (" + (id + 1) + ",'" + revaluo.getActivoFijo().getIdActivoFijo() + "','" + incorporacion + "','" + fechaRevaluoTecnico + "',"
                + revaluo.getCostoInicial() + "," + revaluo.getCostoInicialNuevo() + "," + revaluo.getVidaUtil() + "," + revaluo.getVidaUtilNuevo() + ","
                + "'" + revaluo.getIndiceUFV() + "','" + revaluo.getIndiceUFVNuevo() + "','" + revaluo.getDisposicionRespaldo() + "','" + revaluo.getMotivo() + "',"
                + revaluo.getDepreciacionGestion() + "," + revaluo.getDepreciacionAcumulada() + "," + revaluo.getValorNeto() + "," + calendarFechaRevaluoTecnico.getYear() + ","
                + revaluo.getUsuario().getIdUsuario() + ")";
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public Object getObject(Object obj) {
        Revaluo revaluo = (Revaluo) obj;
        String sql = "SELECT * FROM revaluos r "
                + " INNER JOIN activosFijos a ON r.idActivoFijo = a.idActivoFijo"
                + " WHERE r.idRevaluo = " + revaluo.getIdRevaluo();
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        try {
            while (res.next()) {
                revaluo.setIdRevaluo(res.getInt("idRevaluo"));
                revaluo.setIncorporacion(res.getDate("incorporacionRevaluo"));
                revaluo.setFechaRevaluoTecnico(res.getDate("fechaRevaluoTecnicoRevaluo"));
                revaluo.setCostoInicial(res.getDouble("costoInicialRevaluo"));
                revaluo.setCostoInicialNuevo(res.getDouble("costoInicialNuevoRevaluo"));
                revaluo.setVidaUtil(res.getInt("vidaUtilRevaluo"));
                revaluo.setVidaUtilNuevo(res.getInt("vidaUtilNuevoRevaluo"));
                revaluo.setIndiceUFV(res.getString("indiceUFVRevaluo"));
                revaluo.setIndiceUFVNuevo(res.getString("indiceUFVNuevoRevaluo"));
                revaluo.setDisposicionRespaldo(res.getString("disposicionRespaldoRevaluo"));
                revaluo.setMotivo(res.getString("motivoRevaluo"));
                revaluo.setDepreciacionGestion(res.getDouble("depreciacionGestionRevaluo"));
                revaluo.setDepreciacionAcumulada(res.getDouble("depreciacionAcumuladaRevaluo"));
                revaluo.setValorNeto(res.getDouble("valorNetoRevaluo"));
                revaluo.setGestion(res.getInt("gestionRevaluo"));
                //ActivoFijo 
                ActivoFijo activoFijo = revaluo.getActivoFijo();
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
                revaluo.setActivoFijo(activoFijo);
            }
        } catch (SQLException ex) {
            return null;
        }
        return revaluo;
    }

    @Override
    public List getObjects(Object obj) {
        Revaluo revaluo = (Revaluo) obj;
        String sql = "SELECT * FROM revaluos r "
                + " INNER JOIN activosFijos a ON r.idActivoFijo = a.idActivoFijo "
                + " WHERE r.idActivoFijo = " + revaluo.getActivoFijo().getIdActivoFijo();
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        List list = new ArrayList();
        try {
            if (res != null) {
                while (res.next()) {
                    //Revaluo
                    revaluo = new Revaluo();
                    revaluo.setIdRevaluo(res.getInt("idRevaluo"));
                    revaluo.setIncorporacion(res.getDate("incorporacionRevaluo"));
                    revaluo.setFechaRevaluoTecnico(res.getDate("fechaRevaluoTecnicoRevaluo"));
                    revaluo.setCostoInicial(res.getDouble("costoInicialRevaluo"));
                    revaluo.setCostoInicialNuevo(res.getDouble("costoInicialNuevoRevaluo"));
                    revaluo.setVidaUtil(res.getInt("vidaUtilRevaluo"));
                    revaluo.setVidaUtilNuevo(res.getInt("vidaUtilNuevoRevaluo"));
                    revaluo.setIndiceUFV(res.getString("indiceUFVRevaluo"));
                    revaluo.setIndiceUFVNuevo(res.getString("indiceUFVNuevoRevaluo"));
                    revaluo.setDisposicionRespaldo(res.getString("disposicionRespaldoRevaluo"));
                    revaluo.setMotivo(res.getString("motivoRevaluo"));
                    revaluo.setDepreciacionGestion(res.getDouble("depreciacionGestionRevaluo"));
                    revaluo.setDepreciacionAcumulada(res.getDouble("depreciacionAcumuladaRevaluo"));
                    revaluo.setValorNeto(res.getDouble("valorNetoRevaluo"));
                    revaluo.setGestion(res.getInt("gestionRevaluo"));
                    //ActivoFijo 
                    ActivoFijo activoFijo = revaluo.getActivoFijo();
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
                    revaluo.setActivoFijo(activoFijo);
                    list.add(revaluo);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RevaluoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return list;
    }

    @Override
    public List select(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List query(Object obj, int type, String condicion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}