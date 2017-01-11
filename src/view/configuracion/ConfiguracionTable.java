/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.configuracion;

import java.util.List;
import model.domain.Configuracion;
import org.jc.swing.JCAbstractXTable;
import view.ViewMain;

/**
 * ConfiguracionTable
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 */
public class ConfiguracionTable extends JCAbstractXTable {

    public ConfiguracionTable() {
        super("configuracion", new String[]{"idConfiguracion","UFV","fechaUFV"});
    }

    @Override
    protected List getDefaultInitialData() {
        return ViewMain.DAO.getObjects(new Configuracion());
    }
}
