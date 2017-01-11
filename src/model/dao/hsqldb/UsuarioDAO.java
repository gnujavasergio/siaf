package model.dao.hsqldb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.dataStore.DataStore;
import model.domain.Usuario;

class UsuarioDAO
        implements DataStore {

    @Override
    public int add(Object obj) {
        Usuario usuario = (Usuario) obj;
        String sql = "select max(idUsuario) AS ID from usuarios";
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        int id = 0;
        try {
            while (res.next()) {
                id = res.getInt("ID");
            }
        } catch (SQLException ex) {
        }
        sql = "INSERT INTO usuarios (idUsuario,loginUsuario,passwordUsuario,nombresUsuario,descripcionUsuario,tipoUsuario)"
                + " VALUES (" + (id + 1) + ",'" + usuario.getLogin() + "','" + usuario.getPassword() + "',"
                + "'" + usuario.getNombres() + "','" + usuario.getDescripcion() + "','" + usuario.getTipo() + "')";
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int update(Object obj) {
        Usuario usuario = (Usuario) obj;
        String sql = " UPDATE usuarios SET  "
                + " loginUsuario = '" + usuario.getLogin() + "',"
                + " passwordUsuario = '" + usuario.getPassword() + "',"
                + " nombresUsuario = '" + usuario.getNombres() + "',"
                + " descripcionUsuario = '" + usuario.getDescripcion() + "',"
                + " tipoUsuario= '" + usuario.getTipo() + "'"
                + " WHERE idUsuario = " + usuario.getIdUsuario();
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int delete(Object obj) {
        Usuario usuario = (Usuario) obj;
        String sql = "DELETE FROM usuarios WHERE idUsuario = " + usuario.getIdUsuario();
        if (HsqldbDAO.consultaBD.ejecutar(sql)) {
            return 1;
        }
        return 0;
    }

    @Override
    public Object getObject(Object obj) {
        Usuario usuario = (Usuario) obj;
        String sql;
        if (usuario.getIdUsuario() == 0) {
            sql = "SELECT * FROM usuarios WHERE loginUsuario = '" + usuario.getLogin() + "' AND passwordUsuario = '" + usuario.getPassword() + "'";
        } else {
            sql = "SELECT * FROM usuarios WHERE idUsuario = " + usuario.getIdUsuario();
        }
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        try {
            while (res.next()) {
                usuario.setIdUsuario(res.getInt("idUsuario"));
                usuario.setLogin(res.getString("loginUsuario"));
                usuario.setPassword(res.getString("passwordUsuario"));
                usuario.setNombres(res.getString("nombresUsuario"));
                usuario.setDescripcion(res.getString("descripcionUsuario"));
                usuario.setTipo(res.getString("tipoUsuario"));
            }
        } catch (SQLException ex) {
            usuario = null;
        }
        return usuario;
    }

    @Override
    public List getObjects(Object obj) {
        String sql = "SELECT * FROM usuarios ORDER BY idUsuario";
        ResultSet res = HsqldbDAO.consultaBD.consultar(sql);
        List<Usuario> list = new ArrayList<>();
        try {
            if (res != null) {
                while (res.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(res.getInt("idUsuario"));
                    usuario.setLogin(res.getString("loginUsuario"));
                    usuario.setPassword(res.getString("passwordUsuario"));
                    usuario.setNombres(res.getString("nombresUsuario"));
                    usuario.setDescripcion(res.getString("descripcionUsuario"));
                    usuario.setTipo(res.getString("tipoUsuario"));
                    list.add(usuario);
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