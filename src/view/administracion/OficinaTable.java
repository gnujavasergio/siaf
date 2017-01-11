/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.administracion;

import java.util.List;
import javax.swing.table.TableColumnModel;
import model.domain.Oficina;
import org.jc.swing.JCAbstractXTable;
import org.jdesktop.swingx.JXTable;
import view.ViewMain;

/**
 * OficinaTable
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 * @version 0.1
 */
public class OficinaTable extends JCAbstractXTable {

    public OficinaTable() {
        super("oficina", new String[]{"idOficina","codigo","nombre", "observacion"});
    }

    @Override
    protected List getDefaultInitialData() {
        Oficina oficina = new Oficina();
        oficina.setUnidad(ViewMain.viewMain.getUnidad());
        return ViewMain.DAO.getObjects(oficina);
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
