/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package view.activos;

import java.util.List;
import org.jc.swing.JCAbstractXTable;
import view.ViewMain;

/**
 * RevaluoTable
 *
 * @author Sergio antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 */
public class RevaluoTable extends JCAbstractXTable{

    public RevaluoTable() {
        super("revaluo", new String[]{"idRevaluo","incorporacion","fechaRevaluoTecnico","costoInicial","costoInicialNuevo","vidaUtil","vidaUtilNuevo","indiceUFV","indiceUFVNuevo","activoFijo"});
    }

    @Override
    protected List getDefaultInitialData() {        
        return ViewMain.DAO.getObjects(RevaluoView.revaluoView.getRevaluo());
    }

 }
