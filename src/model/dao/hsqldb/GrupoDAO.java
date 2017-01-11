package model.dao.hsqldb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.dataStore.DataStore;
import model.domain.Grupo;
import org.jc.JCCalendar;

/**
 * GrupoDAO
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 * @version 0.1
 */
public class GrupoDAO implements DataStore {

    @Override
    public int add(Object obj) {
        Grupo grupo = (Grupo) obj;
        String sql = "select max(idGrupo) AS ID from grupos";
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        int id = 0;
        try {
            while (res.next()) {
                id = res.getInt("ID");
            }
        } catch (SQLException ex) {
        }
        JCCalendar calendar = new JCCalendar();
        String date = calendar.getYear() + "-" + calendar.getMonthInt() + "-" + calendar.getDayOfMonth() + " " + calendar.getTime();
        sql = "INSERT INTO grupos (idGrupo,nombreGrupo,observacionGrupo,vidaUtilGrupo,depreciarGrupo,actualizarGrupo,fechaRegistroGrupo,idUsuario)"
                + " VALUES (" + (id + 1) + ",'" + grupo.getNombre() + "','" + grupo.getObservacion() + "',"
                + grupo.getVidaUtil() + "," + grupo.isDepreciar() + "," + grupo.isActualizar() + ","
                + "'" + date + "'," + grupo.getUsuario().getIdUsuario() + ")";
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int update(Object obj) {
        Grupo grupo = (Grupo) obj;
        JCCalendar calendar = new JCCalendar();
        String date = calendar.getYear() + "-" + calendar.getMonthInt() + "-" + calendar.getDayOfMonth() + " " + calendar.getTime();
        String sql = " UPDATE grupos SET  "
                + " nombreGrupo = '" + grupo.getNombre() + "',"
                + " observacionGrupo = '" + grupo.getObservacion() + "',"
                + " fechaModificacionGrupo = '" + date + "',"
                + " vidaUtilGrupo = " + grupo.getVidaUtil() + ","
                + " depreciarGrupo = " + grupo.isDepreciar() + ","
                + " actualizarGrupo = " + grupo.isActualizar() + ","
                + " idUsuario = " + grupo.getUsuario().getIdUsuario()
                + " WHERE idGrupo = " + grupo.getIdGrupo();
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int delete(Object obj) {
        Grupo grupo = (Grupo) obj;
        String sql = "DELETE FROM grupos WHERE idGrupo = " + grupo.getIdGrupo();
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public Object getObject(Object obj) {
        Grupo grupo = (Grupo) obj;
        String sql = "SELECT * FROM grupos WHERE idGrupo = " + grupo.getIdGrupo() + "";
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        try {
            while (res.next()) {
                //Grupo
                grupo.setIdGrupo(res.getInt("idGrupo"));
                grupo.setNombre(res.getString("nombreGrupo"));
                grupo.setObservacion(res.getString("observacionGrupo"));
                grupo.setVidaUtil(res.getInt("vidaUtilGrupo"));
                grupo.setDepreciar(res.getBoolean("depreciarGrupo"));
                grupo.setActualizar(res.getBoolean("actualizarGrupo"));
                grupo.setFechaRegistro(res.getDate("fechaRegistroGrupo"));
                grupo.setFechaModificacion(res.getDate("fechaModificacionGrupo"));
            }
        } catch (SQLException ex) {
            return null;
        }
        return grupo;
    }

    @Override
    public List getObjects(Object obj) {
        String sql = "SELECT * FROM grupos ORDER BY idGrupo";
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        List<Grupo> list = new ArrayList<>();
        try {
            if (res != null) {
                while (res.next()) {
                    //Grupo
                    Grupo grupo = new Grupo();
                    grupo.setIdGrupo(res.getInt("idGrupo"));
                    grupo.setNombre(res.getString("nombreGrupo"));
                    grupo.setObservacion(res.getString("observacionGrupo"));
                    grupo.setVidaUtil(res.getInt("vidaUtilGrupo"));
                    grupo.setDepreciar(res.getBoolean("depreciarGrupo"));
                    grupo.setActualizar(res.getBoolean("actualizarGrupo"));
                    grupo.setFechaRegistro(res.getDate("fechaRegistroGrupo"));
                    grupo.setFechaModificacion(res.getDate("fechaModificacionGrupo"));

                    list.add(grupo);
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