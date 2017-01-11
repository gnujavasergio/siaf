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
import model.domain.Entidad;

/**
 * EntidadDAO
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 * @version 0.1
 */
public class EntidadDAO implements DataStore {

    @Override
    public int add(Object obj) {
        Entidad entidad = (Entidad) obj;
        String sql = "select max(idEntidad) AS ID from entidades";
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        int id = 0;
        try {
            while (res.next()) {
                id = res.getInt("ID");
            }
        } catch (SQLException ex) {
        }
        sql = "INSERT INTO entidades (idEntidad,nombreEntidad,siglaEntidad,institucionEntidad,estadoEntidad) "
                + " VALUES ('" + (id + 1) + "','" + entidad.getNombre() + "','" + entidad.getSigla() + "',"
                + "'" + entidad.getInstitucion() + "'," + entidad.isEstado() + ")";
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int update(Object obj) {
        Entidad entidad = (Entidad) obj;
        String sql = " UPDATE entidades SET  "
                + " nombreEntidad = '" + entidad.getNombre() + "',"
                + " siglaEntidad = '" + entidad.getSigla() + "',"
                + " institucionEntidad = '" + entidad.getInstitucion() + "',"
                + " estadoEntidad = " + entidad.isEstado()
                + " WHERE idEntidad = " + entidad.getIdEntidad();
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int delete(Object obj) {
        Entidad entidad = (Entidad) obj;
        String sql = "DELETE FROM entidades WHERE idEntidad = " + entidad.getIdEntidad();
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public Object getObject(Object obj) {
        Entidad entidad = (Entidad) obj;
        String sql;
        if (entidad.getIdEntidad() != 0) {
            sql = "SELECT * FROM entidades WHERE idEntidad = " + entidad.getIdEntidad();
        } else {
            sql = "SELECT * FROM entidades WHERE estadoEntidad = 1";
        }
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        try {
            while (res.next()) {
                entidad.setIdEntidad(res.getInt("idEntidad"));
                entidad.setNombre(res.getString("nombreEntidad"));
                entidad.setSigla(res.getString("siglaEntidad"));
                entidad.setInstitucion(res.getString("institucionEntidad"));
                entidad.setEstado(res.getBoolean("estadoEntidad"));
            }
        } catch (SQLException ex) {
            return null;
        }
        return entidad;
    }

    @Override
    public List getObjects(Object obj) {
        String sql = "SELECT * FROM entidades ORDER BY idEntidad";
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        List<Entidad> list = new ArrayList<>();
        try {
            if (res != null) {
                while (res.next()) {
                    Entidad entidad = new Entidad();
                    entidad.setIdEntidad(res.getInt("idEntidad"));
                    entidad.setNombre(res.getString("nombreEntidad"));
                    entidad.setSigla(res.getString("siglaEntidad"));
                    entidad.setInstitucion(res.getString("institucionEntidad"));
                    entidad.setEstado(res.getBoolean("estadoEntidad"));
                    list.add(entidad);
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
