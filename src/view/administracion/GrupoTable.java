/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.administracion;

import java.util.List;
import javax.swing.table.TableColumnModel;
import model.domain.Grupo;
import org.jc.swing.JCAbstractXTable;
import org.jdesktop.swingx.JXTable;
import view.ViewMain;

/**
 * GrupoTable
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 * @version 0.2
 */
public class GrupoTable extends JCAbstractXTable {

    public GrupoTable() {
        super("grupo", new String[]{"idGrupo", "nombre", "vidaUtil", "observacion"});
    }

    @Override
    protected List getDefaultInitialData() {
        return ViewMain.DAO.getObjects(new Grupo());
    }

    @Override
    protected void configureTable(JXTable table) {
        super.configureTable(table);
        TableColumnModel tcm = table.getColumnModel();
        tcm.getColumn(0).setMinWidth(60);
        tcm.getColumn(0).setPreferredWidth(60);
        tcm.getColumn(0).setMaxWidth(60);
        tcm.getColumn(1).setMinWidth(200);
        tcm.getColumn(1).setPreferredWidth(300);
        tcm.getColumn(1).setMaxWidth(350);
        tcm.getColumn(2).setMinWidth(100);
        tcm.getColumn(2).setPreferredWidth(150);
        tcm.getColumn(2).setMaxWidth(200);
    }
}
