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
import model.domain.Unidad;

/**
 * UnidadDAO
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 * @version 0.1
 */
public class UnidadDAO implements DataStore {

    @Override
    public int add(Object obj) {
        Unidad unidad = (Unidad) obj;
        String sql = "select max(idUnidad) AS ID from unidades";
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        int idUnidad = 0;
        try {
            while (res.next()) {
                idUnidad = res.getInt("ID");
            }
        } catch (SQLException ex) {
        }
        sql = "select max(u.codigoUnidad) AS codigoUnidad from unidades u INNER JOIN entidades e ON u.idEntidad = e.idEntidad WHERE u.idEntidad = " + unidad.getEntidad().getIdEntidad();
        res = HsqldbDAO.consultaBD.consultar(sql);
        int codigoUnidad = 0;
        try {
            if (res != null) {
            while (res.next()) {
                codigoUnidad = res.getInt("codigoUnidad");
            }
            }
        } catch (SQLException ex) {
        }
        sql = "INSERT INTO unidades (idUnidad,descripcionUnidad,ciudadUnidad,idEntidad,estadoUnidad,codigoUnidad) "
                + " VALUES (" + (idUnidad + 1) + ",'" + unidad.getDescripcion() + "','" + unidad.getCiudad() + "'"
                + "," + unidad.getEntidad().getIdEntidad() + "," + unidad.isEstado() + "," + (codigoUnidad + 1) + ")";
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int update(Object obj) {
        Unidad unidad = (Unidad) obj;
        String sql = " UPDATE unidades SET  "
                + " descripcionUnidad = '" + unidad.getDescripcion() + "',"
                + " ciudadUnidad = '" + unidad.getCiudad() + "',"
                + " estadoUnidad = " + unidad.isEstado() + ","
                + " idEntidad = " + unidad.getEntidad().getIdEntidad()
                + " WHERE idUnidad = " + unidad.getIdUnidad();
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int delete(Object obj) {
        Unidad unidad = (Unidad) obj;
        String sql = "DELETE FROM unidades WHERE idUnidad = " + unidad.getIdUnidad();
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public Object getObject(Object obj) {
        Unidad unidad = (Unidad) obj;
        String sql;
        if (unidad.getIdUnidad() != 0) {
            sql = "SELECT * FROM unidades u INNER JOIN entidades e ON e.idEntidad = u.idEntidad "
                    + " WHERE idUnidad = " + unidad.getIdUnidad() + "";
        } else {
            sql = "SELECT * FROM unidades u INNER JOIN entidades e ON e.idEntidad = u.idEntidad "
                    + " WHERE u.estadoUnidad = 1";
        }
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        try {
            while (res.next()) {
                unidad.setIdUnidad(res.getInt("idUnidad"));
                unidad.setDescripcion(res.getString("descripcionUnidad"));
                unidad.setCiudad(res.getString("ciudadUnidad"));
                unidad.setEstado(res.getBoolean("estadoUnidad"));
                unidad.setCodigo(res.getInt("codigoUnidad"));
                //entidad
                Entidad entidad = unidad.getEntidad();
                entidad.setIdEntidad(res.getInt("idEntidad"));
                entidad.setNombre(res.getString("nombreEntidad"));
                entidad.setSigla(res.getString("siglaEntidad"));
                entidad.setInstitucion(res.getString("institucionEntidad"));
                entidad.setEstado(res.getBoolean("estadoEntidad"));
                unidad.setEntidad(entidad);
            }
        } catch (SQLException ex) {
            return null;
        }
        return unidad;
    }

    @Override
    public List getObjects(Object obj) {
        Unidad unidad = (Unidad) obj;
        String sql = "SELECT * FROM unidades u INNER JOIN entidades e ON e.idEntidad = u.idEntidad "
                + "WHERE u.idEntidad = " + unidad.getEntidad().getIdEntidad() + " ORDER BY u.idUnidad";
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        List<Unidad> list = new ArrayList<>();
        try {
            if (res != null) {
                while (res.next()) {
                    unidad = new Unidad();
                    unidad.setIdUnidad(res.getInt("idUnidad"));
                    unidad.setDescripcion(res.getString("descripcionUnidad"));
                    unidad.setCiudad(res.getString("ciudadUnidad"));
                    unidad.setEstado(res.getBoolean("estadoUnidad"));
                    unidad.setCodigo(res.getInt("codigoUnidad"));
                    //entidad
                    Entidad entidad = unidad.getEntidad();
                    entidad.setIdEntidad(res.getInt("idEntidad"));
                    entidad.setNombre(res.getString("nombreEntidad"));
                    entidad.setSigla(res.getString("siglaEntidad"));
                    entidad.setInstitucion(res.getString("institucionEntidad"));
                    entidad.setEstado(res.getBoolean("estadoEntidad"));
                    unidad.setEntidad(entidad);
                    list.add(unidad);
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
