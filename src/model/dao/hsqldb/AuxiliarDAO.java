package model.dao.hsqldb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.dataStore.DataStore;
import model.domain.Auxiliar;
import model.domain.Grupo;
import model.domain.Unidad;
import org.jc.JCCalendar;

/**
 * AuxiliarDAO
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 * @version 0.1
 */
public class AuxiliarDAO
        implements DataStore {

    @Override
    public int add(Object obj) {
        Auxiliar auxiliar = (Auxiliar) obj;
        String sql = "select max(idAuxiliar) AS ID from auxiliares";
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        int id = 0;
        try {
            while (res.next()) {
                id = res.getInt("ID");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        sql = "select max(a.codigoAuxiliar) AS codigoAuxiliar from auxiliares a INNER JOIN unidades u ON a.idUnidad = u.idUnidad "
                + " INNER JOIN grupos g ON a.idGrupo = g.idGrupo WHERE a.idUnidad = " + auxiliar.getUnidad().getIdUnidad() + " AND a.idGrupo = " + auxiliar.getGrupo().getIdGrupo();
        res = HsqldbDAO.consultaBD.consultar(sql);
        int codigoAuxiliar = 0;
        try {
            if (res != null) {
                while (res.next()) {
                    codigoAuxiliar = res.getInt("codigoAuxiliar");
                }
            }
        } catch (SQLException ex) {
        }
        JCCalendar calendar = new JCCalendar();
        String date = calendar.getYear() + "-" + calendar.getMonthInt() + "-" + calendar.getDayOfMonth() + " " + calendar.getTime();
        sql = "INSERT INTO auxiliares (idAuxiliar,nombreAuxiliar,fechaRegistroAuxiliar,idUsuario,idGrupo,idUnidad,codigoAuxiliar)"
                + "VALUES (" + (id + 1) + ",'" + auxiliar.getNombre() + "',"
                + "'" + date + "'," + auxiliar.getUsuario().getIdUsuario() + "," + auxiliar.getGrupo().getIdGrupo() + ","
                + auxiliar.getUnidad().getIdUnidad() + "," + (codigoAuxiliar + 1) + ")";
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int update(Object obj) {
        Auxiliar auxiliar = (Auxiliar) obj;
        JCCalendar calendar = new JCCalendar();
        String date = calendar.getYear() + "-" + calendar.getMonthInt() + "-" + calendar.getDayOfMonth() + " " + calendar.getTime();
        String sql = "UPDATE auxiliares SET "
                + " nombreAuxiliar='" + auxiliar.getNombre() + "',"
                + " fechaModificacionAuxiliar = '" + date + "',"
                + " idUsuario = " + auxiliar.getUsuario().getIdUsuario() + ","
                + " idGrupo = " + auxiliar.getGrupo().getIdGrupo() + ","
                + " idUnidad = " + auxiliar.getUnidad().getIdUnidad()
                + " WHERE idAuxiliar = " + auxiliar.getIdAuxiliar();
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int delete(Object obj) {
        Auxiliar auxiliar = (Auxiliar) obj;
        String sql = "DELETE FROM auxiliares WHERE idAuxiliar = " + auxiliar.getIdAuxiliar();
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public Object getObject(Object obj) {
        Auxiliar auxiliar = (Auxiliar) obj;
        String sql = "SELECT * FROM auxiliares a INNER JOIN grupos g ON a.idGrupo = g.idGrupo "
                + " INNER JOIN unidades u ON a.idUnidad = u.idUnidad WHERE a.idAuxiliar = " + auxiliar.getIdAuxiliar();
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        try {
            while (res.next()) {
                auxiliar.setIdAuxiliar(res.getInt("idAuxiliar"));
                auxiliar.setNombre(res.getString("nombreAuxiliar"));
                auxiliar.setFechaRegistro(res.getDate("fechaRegistroAuxiliar"));
                auxiliar.setFechaModificacion(res.getDate("fechaModificacionAuxiliar"));
                auxiliar.setCodigo(res.getInt("codigoAuxiliar"));
                //Grupo
                Grupo grupo = auxiliar.getGrupo();
                grupo.setIdGrupo(res.getInt("idGrupo"));
                grupo.setNombre(res.getString("nombreGrupo"));
                grupo.setObservacion(res.getString("observacionGrupo"));
                grupo.setVidaUtil(res.getInt("vidaUtilGrupo"));
                grupo.setDepreciar(res.getBoolean("depreciarGrupo"));
                grupo.setActualizar(res.getBoolean("actualizarGrupo"));
                grupo.setFechaRegistro(res.getDate("fechaRegistroGrupo"));
                grupo.setFechaModificacion(res.getDate("fechaModificacionGrupo"));
                auxiliar.setGrupo(grupo);
                //Unidad
                Unidad unidad = auxiliar.getUnidad();
                unidad.setIdUnidad(res.getInt("idUnidad"));
                unidad.setDescripcion(res.getString("descripcionUnidad"));
                unidad.setCiudad(res.getString("ciudadUnidad"));
                unidad.setEstado(res.getBoolean("estadoUnidad"));
                auxiliar.setUnidad(unidad);
            }
        } catch (SQLException ex) {
            return null;
        }
        return auxiliar;
    }

    @Override
    public List getObjects(Object obj) {
        Auxiliar auxiliar = (Auxiliar) obj;
        String sql = "";
        if (auxiliar.getGrupo().getIdGrupo() != 0) {
            sql = "SELECT * FROM auxiliares a INNER JOIN grupos g ON a.idGrupo = g.idGrupo"
                    + " INNER JOIN unidades u ON a.idUnidad = u.idUnidad WHERE g.idGrupo = " + auxiliar.getGrupo().getIdGrupo() + " AND a.idUnidad = " + auxiliar.getUnidad().getIdUnidad();
        } else if (auxiliar.getUnidad().getIdUnidad() != 0) {
            sql = "SELECT * FROM auxiliares a INNER JOIN grupos g ON a.idGrupo = g.idGrupo"
                    + " INNER JOIN unidades u ON a.idUnidad = u.idUnidad WHERE u.idUnidad = " + auxiliar.getUnidad().getIdUnidad();
        }
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        List list = new ArrayList();
        try {
            if (res != null) {
                while (res.next()) {
                    //Auxiliar
                    auxiliar = new Auxiliar();
                    auxiliar.setIdAuxiliar(res.getInt("idAuxiliar"));
                    auxiliar.setNombre(res.getString("nombreAuxiliar"));
                    auxiliar.setFechaRegistro(res.getDate("fechaRegistroAuxiliar"));
                    auxiliar.setFechaModificacion(res.getDate("fechaModificacionAuxiliar"));
                    auxiliar.setCodigo(res.getInt("codigoAuxiliar"));
                    //Grupo
                    Grupo grupo = auxiliar.getGrupo();
                    grupo.setIdGrupo(res.getInt("idGrupo"));
                    grupo.setNombre(res.getString("nombreGrupo"));
                    grupo.setObservacion(res.getString("observacionGrupo"));
                    grupo.setVidaUtil(res.getInt("vidaUtilGrupo"));
                    grupo.setDepreciar(res.getBoolean("depreciarGrupo"));
                    grupo.setActualizar(res.getBoolean("actualizarGrupo"));
                    grupo.setFechaRegistro(res.getDate("fechaRegistroGrupo"));
                    grupo.setFechaModificacion(res.getDate("fechaModificacionGrupo"));
                    //Unidad
                    Unidad unidad = auxiliar.getUnidad();
                    unidad.setIdUnidad(res.getInt("idUnidad"));
                    unidad.setDescripcion(res.getString("descripcionUnidad"));
                    unidad.setCiudad(res.getString("ciudadUnidad"));
                    unidad.setEstado(res.getBoolean("estadoUnidad"));
                    auxiliar.setUnidad(unidad);
                    list.add(auxiliar);
                }
            }
        } catch (SQLException ex) {
            return null;
        }
        return list;
    }

    @Override
    public List select(Object obj) {
        Auxiliar auxiliar = (Auxiliar) obj;
        String sql = "SELECT * FROM auxiliares a INNER JOIN unidades u ON a.idUnidad = u.idUnidad WHERE a.nombreAuxiliar = '" + auxiliar.getNombre() + "' AND u.idUnidad = " + auxiliar.getUnidad().getIdUnidad();
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        List list = new ArrayList();
        try {
            if (res != null) {
                while (res.next()) {
                    //Auxiliar
                    auxiliar = new Auxiliar();
                    auxiliar.setIdAuxiliar(res.getInt("idAuxiliar"));
                    auxiliar.setNombre(res.getString("nombreAuxiliar"));
                    auxiliar.setFechaRegistro(res.getDate("fechaRegistroAuxiliar"));
                    auxiliar.setFechaModificacion(res.getDate("fechaModificacionAuxiliar"));
                    //Unidad
                    Unidad unidad = auxiliar.getUnidad();
                    unidad.setIdUnidad(res.getInt("idUnidad"));
                    unidad.setDescripcion(res.getString("descripcionUnidad"));
                    unidad.setCiudad(res.getString("ciudadUnidad"));
                    unidad.setEstado(res.getBoolean("estadoUnidad"));
                    auxiliar.setUnidad(unidad);
                    list.add(auxiliar);
                }
            }
        } catch (SQLException ex) {
            return null;
        }
        return list;
    }

    @Override
    public List query(Object obj, int type, String condicion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}