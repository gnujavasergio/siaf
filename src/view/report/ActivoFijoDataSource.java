/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.report;

import java.util.ArrayList;
import java.util.List;
import model.domain.ActivoFijo;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * ActivoFijoDataSource
 *
 * @author Sergio
 */
public class ActivoFijoDataSource implements JRDataSource {

    private List<ActivoFijo> listActivosFijos = new ArrayList<ActivoFijo>();
    private int indiceActual = -1;

    @Override
    public boolean next() throws JRException {
        return ++indiceActual < listActivosFijos.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
       Object value = null;
        if(jrf.getName().equals("nombreGrupo")){
            value = listActivosFijos.get(indiceActual).getNombreGrupo();
        } else if(jrf.getName().equals("cantidad")){
            value = new Integer(listActivosFijos.get(indiceActual).getCantidad());
        } else if(jrf.getName().equals("vidaUtil")){
            value = new Integer(listActivosFijos.get(indiceActual).getVidaUtil());
        } else if(jrf.getName().equals("vidaUtil")){
            value = new Integer(listActivosFijos.get(indiceActual).getVidaUtil());
        }
        return value;
    }
}
