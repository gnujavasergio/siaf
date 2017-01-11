/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.activos;

import java.util.List;
import javax.swing.table.TableColumnModel;
import model.domain.Baja;
import org.jc.swing.JCAbstractXTable;
import org.jdesktop.swingx.JXTable;
import view.ViewMain;

/**
 * BajaTable
 *
 * @author Sergio antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 */
public class BajaTable extends JCAbstractXTable {

    public BajaTable() {
        super("baja", new String[]{"idBaja", "fechaBaja", "motivoBaja", "activoFijo"});
    }

    @Override
    protected List getDefaultInitialData() {
        Baja baja = new Baja();
        baja.setUnidad(ViewMain.viewMain.getUnidad());
        return ViewMain.DAO.getObjects(baja);
    }

    @Override
    protected void configureTable(JXTable table) {
        super.configureTable(table);
        TableColumnModel tcm = table.getColumnModel();
        tcm.getColumn(0).setMinWidth(80);
        tcm.getColumn(0).setPreferredWidth(80);
        tcm.getColumn(0).setMaxWidth(80);
        table.getColumnExt(0).setVisible(false);
    }
}
