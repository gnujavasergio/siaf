/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.seguridad;

import java.util.List;
import javax.swing.table.TableColumnModel;
import model.domain.Usuario;
import org.jc.swing.JCAbstractXTable;
import org.jdesktop.swingx.JXTable;
import view.ViewMain;

/**
 * UsuarioTable
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 * @version 0.2
 */
public class UsuarioTable extends JCAbstractXTable {

    public UsuarioTable() {
        super("usuario", new String[]{"idUsuario", "login", "nombres", "tipo"});
    }

    @Override
    protected List getDefaultInitialData() {
        return ViewMain.DAO.getObjects(new Usuario());
    }

    @Override
    protected void getDoubleClick() {
        UsuarioView.usuarioView.addUpdate();
    }

    @Override
    protected void configureTable(JXTable table) {
        super.configureTable(table);
        TableColumnModel tcm = table.getColumnModel();
        tcm.getColumn(0).setMinWidth(60);
        tcm.getColumn(0).setPreferredWidth(60);
        tcm.getColumn(0).setMaxWidth(60);
        tcm.getColumn(1).setMinWidth(150);
        tcm.getColumn(1).setPreferredWidth(200);
        tcm.getColumn(1).setMaxWidth(200);
        tcm.getColumn(3).setMinWidth(150);
        tcm.getColumn(3).setPreferredWidth(200);
        tcm.getColumn(3).setMaxWidth(250);
        table.getColumnExt(0).setVisible(false);
    }

    
}
