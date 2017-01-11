/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import jdbc.Conexion;
import static model.dao.CommonDAO.NONE;
import model.dao.dataStore.AbstractDataSore;
import model.dao.dataStore.DataStore;
import model.dao.hsqldb.HsqldbDAO;
import model.dao.postgres.PostgresDAO;

/**
 * CommonDAO
 *
 * @author Ochoa Martinez Sergio Antonio - gnu.java.sergio@gmail.com
 * @version 0.2
 */
public class CommonDAO extends AbstractDataSore {

    public static int NONE = 0;
    public static int POSTGRES = 1;
    public static int MYSQL = 2;
    public static int HIBERNATE = 3;
    public static int MYIBATIS = 4;
    public static int HSQLDB = 5;
    private DataStore postgres;
    private DataStore mysql;
    private DataStore hsqldb;
    private int type = NONE;

    public CommonDAO(Conexion conexion, int type) {
        this.type = type;
        if (CommonDAO.HSQLDB == type) {
            hsqldb = new HsqldbDAO(conexion);
        }
        else if(CommonDAO.POSTGRES ==  type){
            postgres = new PostgresDAO(conexion);
        } 

    }

    @Override
    public DataStore getDataStore() {
        if (CommonDAO.POSTGRES == type) {
            return postgres;
        } else if (CommonDAO.MYSQL == type) {
            return mysql;
        } else if (CommonDAO.HSQLDB == type) {
            return hsqldb;
        }
        return null;
    }
}
