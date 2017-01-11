/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.configuracion;

import java.util.List;
import javax.swing.table.TableColumnModel;
import model.domain.Entidad;
import org.jc.swing.JCAbstractXTable;
import org.jdesktop.swingx.JXTable;
import view.ViewMain;

/**
 * EntidadTable
 *
 * @author Sergio Antonio Ochoa Martinez gnu.java.sergio@gmail.com
 * @version 0.2
 */
public class EntidadTable extends JCAbstractXTable {

    public EntidadTable() {
        super("entidad", new String[]{"idEntidad", "nombre", "sigla","institucion"});
    }

    
    @Override
    protected List getDefaultInitialData() {
        return ViewMain.DAO.getObjects(new Entidad());
    }
}
