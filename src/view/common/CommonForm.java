/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package view.common;

/**
 *
 * @author Ochoa Martinez Sergio Antonio - gnu.java.sergio@gmail.com
 */
public interface CommonForm {
    /**
     * Para obtener los datos de la formulario
     * @return un objeto
     */
    public Object getObject();
    /**
     * para modificar los campos del formulario
     * @param obj
     * @return un objeto
     */
    public void setObject(Object obj);
}
