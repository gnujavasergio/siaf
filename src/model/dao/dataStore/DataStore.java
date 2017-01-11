/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.dataStore;

import java.util.List;

/**
 *
 * @author Ochoa Martinez Sergio Antonio - gnu.java.sergio@gmail.com
 * @version 0.2
 */
public interface DataStore {

    public int add(Object obj);

    public int update(Object obj);

    public int delete(Object obj);

    public Object getObject(Object obj);

    public List getObjects(Object obj);

    public List select(Object obj);

    public List query(Object obj, int type, String condicion);
    
}