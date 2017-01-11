package model.dao.hsqldb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.dataStore.DataStore;
import model.domain.Oficina;
import model.domain.Responsable;
import org.jc.JCCalendar;

/**
 * ResponsableDAO
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 * @version 0.1
 */
public class ResponsableDAO
        implements DataStore {

    @Override
    public int add(Object obj) {
        Responsable responsable = (Responsable) obj;
        String sql = "select max(idResponsable) AS ID from responsables";
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        int id = 0;
        try {
            while (res.next()) {
                id = res.getInt("ID");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        sql = "select max(r.codigoResponsable) AS codigoResponsable from responsables r INNER JOIN oficinas o ON r.idOficina = o.idOficina WHERE r.idOficina = " + responsable.getOficina().getIdOficina();
        res = HsqldbDAO.consultaBD.consultar(sql);
        int codigoResponsable = 0;
        try {
            if (res != null) {
                while (res.next()) {
                    codigoResponsable = res.getInt("codigoResponsable");
                }
            }
        } catch (SQLException ex) {
        }
        JCCalendar calendar = new JCCalendar();
        String dateTime = calendar.getYear() + "-" + calendar.getMonthInt() + "-" + calendar.getDayOfMonth() + " " + calendar.getTime();
        sql = "INSERT INTO responsables (idResponsable,nombreResponsable,cargoResponsable,ciResponsable,fechaRegistroResponsable,idUsuario,idOficina,codigoResponsable)"
                + "VALUES (" + (id + 1) + ",'" + responsable.getNombre() + "','" + responsable.getCargo() + "','" + responsable.getCi() + "',"
                + "'" + dateTime + "'," + responsable.getUsuario().getIdUsuario() + "," + responsable.getOficina().getIdOficina() + "," + (codigoResponsable + 1) + ")";
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int update(Object obj) {
        Responsable responsable = (Responsable) obj;
        JCCalendar calendar = new JCCalendar();
        String dateTime = calendar.getYear() + "-" + calendar.getMonthInt() + "-" + calendar.getDayOfMonth() + " " + calendar.getTime();
        String sql = "UPDATE responsables SET "
                + " nombreResponsable='" + responsable.getNombre() + "',"
                + " cargoResponsable= '" + responsable.getCargo() + "',"
                + " ciResponsable = '" + responsable.getCi() + "',"
                + " fechaModificacionResponsable = '" + dateTime + "',"
                + " idUsuario = " + responsable.getUsuario().getIdUsuario() + ","
                + " idOficina = " + responsable.getOficina().getIdOficina()
                + " WHERE idResponsable = " + responsable.getIdResponsable();
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int delete(Object obj) {
        Responsable responsable = (Responsable) obj;
        String sql = "DELETE FROM responsables WHERE idResponsable = " + responsable.getIdResponsable();
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public Object getObject(Object obj) {
        Responsable responsable = (Responsable) obj;
        String sql = "SELECT * FROM responsables r INNER JOIN oficinas o ON o.idOficina = r.idOficina WHERE r.idResponsable = " + responsable.getIdResponsable();
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        try {
            while (res.next()) {
                //Responsable
                responsable.setIdResponsable(res.getInt("idResponsable"));
                responsable.setNombre(res.getString("nombreResponsable"));
                responsable.setCargo(res.getString("cargoResponsable"));
                responsable.setCi(res.getString("ciResponsable"));
                responsable.setFechaRegistro(res.getDate("fechaRegistroResponsable"));
                responsable.setFechaModificacion(res.getDate("fechaModificacionResponsable"));
                responsable.setCodigo(res.getInt("codigoResponsable"));
                //Oficina
                Oficina oficina = responsable.getOficina();
                oficina.setIdOficina(res.getInt("idOficina"));
                oficina.setNombre(res.getString("nombreOficina"));
                oficina.setObservacion(res.getString("observacionOficina"));
                oficina.setFechaRegistro(res.getDate("fechaRegistroOficina"));
                oficina.setFechaModificacion(res.getDate("fechaModificacionOficina"));
                responsable.setOficina(oficina);
            }
        } catch (SQLException ex) {
            return null;
        }
        return responsable;
    }

    @Override
    public List getObjects(Object obj) {
        Responsable responsable = (Responsable) obj;
        String sql = "";
        if (responsable.getOficina().getIdOficina() != 0) {
            sql = "SELECT * FROM responsables r INNER JOIN oficinas o ON r.idOficina = o.idOficina "
                    + " WHERE o.idOficina = " + responsable.getOficina().getIdOficina();
        } else if (responsable.getOficina().getUnidad().getIdUnidad() != 0) {
            sql = "SELECT * FROM responsables r INNER JOIN oficinas o ON r.idOficina = o.idOficina "
                    + " INNER JOIN unidades u ON u.idUnidad = o.idUnidad WHERE u.idUnidad = " + responsable.getOficina().getUnidad().getIdUnidad();
        }
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        List list = new ArrayList();
        try {
            if (res != null) {
                while (res.next()) {
                    responsable = new Responsable();
                    responsable.setIdResponsable(res.getInt("idResponsable"));
                    responsable.setNombre(res.getString("nombreResponsable"));
                    responsable.setCargo(res.getString("cargoResponsable"));
                    responsable.setCi(res.getString("ciResponsable"));
                    responsable.setCodigo(res.getInt("codigoResponsable"));
                    //Oficina
                    Oficina oficina = responsable.getOficina();
                    oficina.setIdOficina(res.getInt("idOficina"));
                    oficina.setNombre(res.getString("nombreOficina"));
                    oficina.setObservacion(res.getString("observacionOficina"));
                    responsable.setOficina(oficina);
                    list.add(responsable);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResponsableDAO.class.getName()).log(Level.SEVERE, null, ex);
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