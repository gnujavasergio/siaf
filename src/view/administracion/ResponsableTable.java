/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.administracion;

import java.util.List;
import javax.swing.table.TableColumnModel;
import model.domain.Oficina;
import model.domain.Responsable;
import org.jc.swing.JCAbstractXTable;
import org.jdesktop.swingx.JXTable;
import view.ViewMain;

/**
 * ResponsableTable
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 * @version 0.2
 */
public class ResponsableTable extends JCAbstractXTable {

    public ResponsableTable() {
        super("responsable", new String[]{"idResponsable", "codigo", "nombre", "cargo", "Oficina"});
    }

    @Override
    protected List getDefaultInitialData() {
        Oficina oficina = new Oficina();
        oficina.setUnidad(ViewMain.viewMain.getUnidad());
        Responsable responsable = new Responsable();
        responsable.setOficina(oficina);
        return ViewMain.DAO.getObjects(responsable);
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
