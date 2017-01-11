/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.hsqldb;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.Conexion;
import jdbc.ConsultaBD;
import model.dao.dataStore.DataStore;

/**
 * HsqldbDAO
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 * @version 1.0
 */
public class HsqldbDAO implements DataStore {

    public static ConsultaBD consultaBD;

    public HsqldbDAO(Conexion conexion) {
        consultaBD = new ConsultaBD(conexion);
    }

    public HsqldbDAO() {
    }

    @Override
    public int add(Object obj) {
        String nameObject = (String) obj.getClass().getName().substring(13);
        try {
            Object o = Class.forName("model.dao.hsqldb." + nameObject + "DAO").newInstance();
            return (Integer) o.getClass().getMethod("add", new Class[]{Object.class}).invoke(o, obj);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(HsqldbDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public int update(Object obj) {
        String nameObject = (String) obj.getClass().getName().substring(13);
        try {
            Object o = Class.forName("model.dao.hsqldb." + nameObject + "DAO").newInstance();
            return (Integer) o.getClass().getMethod("update", new Class[]{Object.class}).invoke(o, obj);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(HsqldbDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public int delete(Object obj) {
        String nameObject = (String) obj.getClass().getName().substring(13);
        try {
            Object o = Class.forName("model.dao.hsqldb." + nameObject + "DAO").newInstance();
            return (Integer) o.getClass().getMethod("delete", new Class[]{Object.class}).invoke(o, obj);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(HsqldbDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public Object getObject(Object obj) {
        String nameObject = (String) obj.getClass().getName().substring(13);
        Object o = null;
        try {
            o = Class.forName("model.dao.hsqldb." + nameObject + "DAO").newInstance();
            obj = o.getClass().getMethod("getObject", new Class[]{Object.class}).invoke(o, obj);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(HsqldbDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return obj;
    }

    @Override
    public List getObjects(Object obj) {
        String nameObject = (String) obj.getClass().getName().substring(13);
        List list = new ArrayList();
        try {
            Object o = Class.forName("model.dao.hsqldb." + nameObject + "DAO").newInstance();
            list = (List) o.getClass().getMethod("getObjects", new Class[]{Object.class}).invoke(o, obj);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(HsqldbDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List select(Object obj) {
        String nameObject = (String) obj.getClass().getName().substring(13);
        List list = new ArrayList();
        try {
            Object o = Class.forName("model.dao.hsqldb." + nameObject + "DAO").newInstance();
            list = (List) o.getClass().getMethod("select", new Class[]{Object.class}).invoke(o, obj);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(HsqldbDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }//fin del metodo select

    @Override
    public List query(Object obj, int type, String condicion) {
        String nameObject = (String) obj.getClass().getName().substring(13);
        List list = new ArrayList();
        try {
            Object o = Class.forName("model.dao.hsqldb." + nameObject + "DAO").newInstance();
            list = (List) o.getClass().getMethod("query", new Class[]{Object.class,int.class,String.class}).invoke(o, obj,type,condicion);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(HsqldbDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
