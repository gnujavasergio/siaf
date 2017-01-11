/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.hsqldb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.dataStore.DataStore;
import model.domain.Configuracion;
import org.jc.JCCalendar;

/**
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 */
public class ConfiguracionDAO implements DataStore {

    @Override
    public int add(Object obj) {
        Configuracion configuracion = (Configuracion) obj;
        String sql = "select max(idConfiguracion) AS ID from configuraciones";
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        int id = 0;
        try {
            while (res.next()) {
                id = res.getInt("ID");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        JCCalendar calendar = new JCCalendar(configuracion.getFechaUFV());
        String fechaUFV = calendar.getYear() + "-" + calendar.getMonthInt() + "-" + calendar.getDayOfMonth();
        sql = "INSERT INTO configuraciones (idConfiguracion,UFV,fechaUFV) "
                + "VALUES(" + (id + 1) + ",'" + configuracion.getUFV() + "','" + fechaUFV + "')";
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int update(Object obj) {
        Configuracion configuracion = (Configuracion) obj;
        JCCalendar calendar = new JCCalendar(configuracion.getFechaUFV());
        String fechaUFV = calendar.getYear() + "-" + calendar.getMonthInt() + "-" + calendar.getDayOfMonth();
        String sql = "UPDATE configuraciones SET"
                + " UFV = '" + configuracion.getUFV() + "',"
                + " fechaUFV = '" + fechaUFV + "'"
                + " WHERE idConfiguracion = " + configuracion.getIdConfiguracion();
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public Object getObject(Object obj) {
        Configuracion configuracion = (Configuracion) obj;
        String sql = "";

        if (configuracion.getIdConfiguracion() != 0) {
            sql = "SELECT * FROM configuraciones WHERE idConfiguracion = " + configuracion.getIdConfiguracion();
        } else {
            JCCalendar calendar = new JCCalendar(configuracion.getFechaUFV());
            sql = "SELECT * FROM configuraciones WHERE fechaUFV = '" + calendar.getDate() + "'";
        }
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        try {
            while (res.next()) {
                configuracion.setIdConfiguracion(res.getInt("idConfiguracion"));
                configuracion.setUFV(res.getString("UFV"));
                configuracion.setFechaUFV(res.getDate("fechaUFV"));
            }
        } catch (SQLException ex) {
            return null;
        }
        return configuracion;
    }

    @Override
    public List getObjects(Object obj) {
        Configuracion configuracion = (Configuracion) obj;
        String sql = "SELECT * FROM configuraciones ORDER BY fechaUFV";
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        List list = new ArrayList();
        try {
            if (res != null) {
                while (res.next()) {
                    configuracion = new Configuracion();
                    configuracion.setIdConfiguracion(res.getInt("idConfiguracion"));
                    configuracion.setUFV(res.getString("UFV"));
                    configuracion.setFechaUFV(res.getDate("fechaUFV"));
                    list.add(configuracion);
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
