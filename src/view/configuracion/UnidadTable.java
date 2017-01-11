/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.configuracion;

import java.util.List;
import javax.swing.table.TableColumnModel;
import model.domain.Unidad;
import org.jc.swing.JCAbstractXTable;
import org.jdesktop.swingx.JXTable;
import view.ViewMain;

/**
 * UnidadTable
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 * @version 0.1
 */
public class UnidadTable extends JCAbstractXTable {

    public UnidadTable() {
        super("unidad", new String[]{"idUnidad","codigo", "ciudad", "descripcion"});
    }

    @Override
    protected List getDefaultInitialData() {
        Unidad unidad = new Unidad();
        unidad.setEntidad(ViewMain.viewMain.getEntidad());
        return ViewMain.DAO.getObjects(unidad);
    }

    @Override
    protected void configureTable(JXTable table) {
        super.configureTable(table);
        TableColumnModel tcm = table.getColumnModel();
        tcm.getColumn(0).setMinWidth(60);
        tcm.getColumn(0).setPreferredWidth(60);
        tcm.getColumn(0).setMaxWidth(60);        
        table.getColumnExt(0).setVisible(false);
    }
}
