/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.activos;

import java.util.List;
import model.domain.ActivoFijo;
import model.domain.Configuracion;
import model.domain.Oficina;
import org.jc.swing.JCAbstractXTable;
import view.ViewMain;

/**
 * ActivoFijoTable
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 * @version 0.2
 */
public class ActivoFijoTable extends JCAbstractXTable {

    public ActivoFijoTable() {
        super("activoFijo", new String[]{"idActivoFijo", "descripcion", "indiceUFV", "Grupo", "Auxiliar", "Oficina", "Responsable", "costoInicial", "valor", "depreciacionGestion", "depreciacionAcumulada", "valorNeto", "vidaUtil", "dias", "incorporacion"});
    }

    @Override
    protected List getDefaultInitialData() {
        Oficina oficina = new Oficina();
        oficina.setUnidad(ViewMain.viewMain.getUnidad());
        ActivoFijo activoFijo = new ActivoFijo();
        activoFijo.setOficina(oficina);
        List list = ViewMain.DAO.getObjects(activoFijo);
        for (int i = 0; i < list.size(); i++) {
            activoFijo = (ActivoFijo) list.get(i);
            Configuracion configuracion = new Configuracion();
            configuracion.setFechaUFV(activoFijo.getIncorporacion());
            configuracion = (Configuracion) ViewMain.DAO.getObject(configuracion);
            activoFijo.setIndiceUFV(configuracion.getUFV());
            activoFijo.setUsuario(ViewMain.viewMain.getUsuario());
            activoFijo.calculo(null);
            ViewMain.DAO.update(activoFijo);
        }
        return list;
    }   
}
