package model.dao.hsqldb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.dataStore.DataStore;
import model.domain.ActivoFijo;
import model.domain.Baja;
import org.jc.JCCalendar;

/**
 * BajaDAO
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 * @version 0.1
 */
public class BajaDAO implements DataStore {

    @Override
    public int add(Object obj) {
        Baja baja = (Baja) obj;
        String sql = "select max(idBaja) AS ID from bajas";
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        int id = 0;
        try {
            while (res.next()) {
                id = res.getInt("ID");
            }
        } catch (SQLException ex) {
        }
        JCCalendar calendarFechaBaja = new JCCalendar();
        String fechaBaja = calendarFechaBaja.getYear() + "-" + calendarFechaBaja.getMonthInt() + "-" + calendarFechaBaja.getDayOfMonth();
        sql = "INSERT INTO bajas (idBaja,idActivoFijo,fechaBaja,disposicionRespaldo,motivoBaja,motivo,idUnidad,idUsuario)"
                + " VALUES (" + (id + 1) + ",'" + baja.getActivoFijo().getIdActivoFijo() + "','" + fechaBaja + "','" + baja.getDisposicionRespaldo() + "',"
                + "'" + baja.getMotivoBaja() + "','" + baja.getMotivo() + "'," + baja.getUnidad().getIdUnidad() + "," + baja.getUsuario().getIdUsuario() + ")";
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
        Baja baja = (Baja) obj;
        String sql = "SELECT * FROM bajas b "
                + " INNER JOIN activosFijos a ON b.idActivoFijo = a.idActivoFijo"
                + " WHERE b.idBaja = " + baja.getIdBaja();
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        try {
            while (res.next()) {
                baja.setIdBaja(res.getInt("idBaja"));
                baja.setFechaBaja(res.getDate("fechaBaja"));
                baja.setDisposicionRespaldo(res.getString("disposicionRespaldo"));
                baja.setMotivoBaja(res.getString("motivoBaja"));
                baja.setMotivo(res.getString("motivo"));
                //ActivoFijo 
                ActivoFijo activoFijo = baja.getActivoFijo();
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
                baja.setActivoFijo(activoFijo);
            }
        } catch (SQLException ex) {
            return null;
        }
        return baja;
    }

    @Override
    public List getObjects(Object obj) {
        Baja baja = (Baja) obj;
        String sql = "SELECT * FROM bajas b "
                + " INNER JOIN activosFijos a ON b.idActivoFijo = a.idActivoFijo"
                + " WHERE b.idUnidad = " + baja.getUnidad().getIdUnidad();
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        List list = new ArrayList();
        try {
            if (res != null) {
                while (res.next()) {
                    baja = new Baja();
                    baja.setIdBaja(res.getInt("idBaja"));
                    baja.setFechaBaja(res.getDate("fechaBaja"));
                    baja.setDisposicionRespaldo(res.getString("disposicionRespaldo"));
                    baja.setMotivoBaja(res.getString("motivoBaja"));
                    baja.setMotivo(res.getString("motivo"));
                    //ActivoFijo 
                    ActivoFijo activoFijo = baja.getActivoFijo();
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
                    baja.setActivoFijo(activoFijo);
                    list.add(baja);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BajaDAO.class.getName()).log(Level.SEVERE, null, ex);
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