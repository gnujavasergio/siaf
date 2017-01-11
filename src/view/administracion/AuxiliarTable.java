/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.administracion;

import java.util.List;
import javax.swing.table.TableColumnModel;
import model.domain.Auxiliar;
import org.jc.swing.JCAbstractXTable;
import org.jdesktop.swingx.JXTable;
import view.ViewMain;

/**
 * AuxiliarTable
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 * @version 0.2
 */
public class AuxiliarTable extends JCAbstractXTable {

    public AuxiliarTable() {
        super("auxiliar", new String[]{"idAuxiliar", "codigo", "nombre", "Grupo"});
    }

    @Override
    protected List getDefaultInitialData() {
        Auxiliar auxiliar = new Auxiliar();
        auxiliar.setUnidad(ViewMain.viewMain.getUnidad());
        return ViewMain.DAO.getObjects(auxiliar);
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
