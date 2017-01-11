package model.dao.hsqldb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.dataStore.DataStore;
import model.domain.Oficina;
import model.domain.Unidad;
import org.jc.JCCalendar;

/**
 * OficinaDAO
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 * @version 0.1
 */
public class OficinaDAO implements DataStore {

    @Override
    public int add(Object obj) {
        Oficina oficina = (Oficina) obj;
        String sql = "select max(idOficina) AS ID from oficinas";
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        int idOficina = 0;
        try {
            while (res.next()) {
                idOficina = res.getInt("ID");
            }
        } catch (SQLException ex) {
        }
        sql = "select max(o.codigoOficina) AS codigoOficina from oficinas o INNER JOIN unidades u ON o.idUnidad= u.idUnidad WHERE o.idUnidad = " + oficina.getUnidad().getIdUnidad();
        res = HsqldbDAO.consultaBD.consultar(sql);
        int codigoOficina = 0;
        try {
            if (res != null) {
                while (res.next()) {
                    codigoOficina = res.getInt("codigoOficina");
                }
            }
        } catch (SQLException ex) {
        }
        JCCalendar calendar = new JCCalendar();
        String date = calendar.getYear() + "-" + calendar.getMonthInt() + "-" + calendar.getDayOfMonth() + " " + calendar.getTime();
        sql = "INSERT INTO oficinas (idOficina,nombreOficina,observacionOficina,fechaRegistroOficina,idUsuario,idUnidad,codigoOficina)"
                + " VALUES (" + (idOficina + 1) + ",'" + oficina.getNombre() + "','" + oficina.getObservacion() + "',"
                + "'" + date + "'," + oficina.getUsuario().getIdUsuario() + "," + oficina.getUnidad().getIdUnidad() + "," + (codigoOficina + 1) + ")";
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int update(Object obj) {
        Oficina oficina = (Oficina) obj;
        JCCalendar calendar = new JCCalendar();
        String date = calendar.getYear() + "-" + calendar.getMonthInt() + "-" + calendar.getDayOfMonth() + " " + calendar.getTime();
        String sql = " UPDATE oficinas SET  "
                + " nombreOficina = '" + oficina.getNombre() + "',"
                + " observacionOficina = '" + oficina.getObservacion() + "',"
                + " fechaModificacionOficina = '" + date + "',"
                + " idUsuario = " + oficina.getUsuario().getIdUsuario() + ","
                + " idUnidad = " + oficina.getUnidad().getIdUnidad()
                + " WHERE idOficina = " + oficina.getIdOficina();
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int delete(Object obj) {
        Oficina oficina = (Oficina) obj;
        String sql = "DELETE FROM oficinas WHERE idOficina = " + oficina.getIdOficina();
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public Object getObject(Object obj) {
        Oficina oficina = (Oficina) obj;
        String sql = "SELECT * FROM oficinas o INNER JOIN unidades u ON o.idUnidad = u.idUnidad WHERE o.idOficina = " + oficina.getIdOficina() + "";
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        try {
            while (res.next()) {
                //Oficina
                oficina.setIdOficina(res.getInt("idOficina"));
                oficina.setNombre(res.getString("nombreOficina"));
                oficina.setObservacion(res.getString("observacionOficina"));
                oficina.setFechaRegistro(res.getDate("fechaRegistroOficina"));
                oficina.setFechaModificacion(res.getDate("fechaModificacionOficina"));
                oficina.setCodigo(res.getInt("codigoOficina"));
                //Unidad
                Unidad unidad = oficina.getUnidad();
                unidad.setIdUnidad(res.getInt("idUnidad"));
                unidad.setDescripcion(res.getString("descripcionUnidad"));
                unidad.setCiudad(res.getString("ciudadUnidad"));
                unidad.setEstado(res.getBoolean("estadoUnidad"));
                oficina.setUnidad(unidad);
            }
        } catch (SQLException ex) {
            return null;
        }
        return oficina;
    }

    @Override
    public List getObjects(Object obj) {
        Oficina oficina = (Oficina) obj;
        String sql = "SELECT * FROM oficinas o INNER JOIN unidades u ON o.idUnidad = u.idUnidad"
                + " WHERE o.idUnidad = " + oficina.getUnidad().getIdUnidad() + " ORDER BY o.idOficina";
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        List<Oficina> list = new ArrayList<>();
        try {
            if (res != null) {
                while (res.next()) {
                    //Oficina
                    oficina = new Oficina();
                    oficina.setIdOficina(res.getInt("idOficina"));
                    oficina.setNombre(res.getString("nombreOficina"));
                    oficina.setObservacion(res.getString("observacionOficina"));
                    oficina.setFechaRegistro(res.getDate("fechaRegistroOficina"));
                    oficina.setFechaModificacion(res.getDate("fechaModificacionOficina"));
                    oficina.setCodigo(res.getInt("codigoOficina"));
                    //Unidad
                    Unidad unidad = oficina.getUnidad();
                    unidad.setIdUnidad(res.getInt("idUnidad"));
                    unidad.setDescripcion(res.getString("descripcionUnidad"));
                    unidad.setCiudad(res.getString("ciudadUnidad"));
                    unidad.setEstado(res.getBoolean("estadoUnidad"));
                    oficina.setUnidad(unidad);
                    list.add(oficina);
                }
            }
        } catch (SQLException ex) {
            return null;
        }
        return list;
    }

    @Override
    public List select(Object obj) {
        return null;
    }

    @Override
    public List query(Object obj, int type, String condicion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}