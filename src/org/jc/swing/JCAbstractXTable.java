/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jc.swing;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resources;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.ColorHighlighter;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory;

/**
 *
 * @author DEVIAN-02
 */
public abstract class JCAbstractXTable extends JScrollPane {

    private String id;
    /**
     * el titulo que tendra cada columana de la tabla
     */
    private String[] title;
    /**
     * atributo con el cual leeremos el archivo properties
     */
    private Properties properties;
    private Object value;
    private int selected = -1;

    public JCAbstractXTable() {
        initComponents();
    }

    public JCAbstractXTable(String id, String[] list) {
        this.id = id;
        this.title = list;
        initComponents();
    }

    /**
     *
     * @return un Vector
     */
    private Vector nameColumn() {
        Vector column = new Vector();
        properties = new Properties();
        try {
            properties.load(Resources.class.getResourceAsStream("/resources/ui/messages.properties"));
            for (Object o : title) {
                column.addElement(properties.getProperty(id + "." + o.toString() + ".label"));
            }
            return column;
        } catch (IOException ex) {
            Logger.getLogger(JCAbstractTable.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }//fin nameColumn

    /**
     * Convertir minusculas en mayusculas (sola las iniciales) y encontrar los
     * atributo
     *
     * @param toString
     * @return
     */
    private String convertir(String a) {
        StringBuilder atributo = new StringBuilder(a);
        atributo.insert(0, Character.toUpperCase(atributo.charAt(0)));
        return atributo.deleteCharAt(1).toString();
    }

    /**
     * Crea el contenido a visualizar
     *
     * @return DefaultTableModel
     */
    private DefaultTableModel createModel() {
        List list = getDefaultInitialData();//obtenemos toda la lista a mostrar
        Vector data = new Vector();//vector tabla
        if (list != null) {
            for (Object o : list) {
                Vector columnas = new Vector();//columnas para llenar la tabla
                //lenar el vector columna
                Object object = null;
                for (Object x : title) {
                    try {
                        object = o.getClass().getMethod("get" + convertir(x.toString()), new Class[]{}).invoke(o);
                        columnas.add(object);
                    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        Logger.getLogger(JCAbstractXTable.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
                data.add(columnas);
            }
        }
        return new DefaultTableModel(data, nameColumn());
    }

    /**
     * @return un Class segun el tipo que se da
     */
    private Class tipo() {
        Class type = value.getClass();
        if (type == java.lang.Integer.class) {
            return int.class;
        } else if (type == java.lang.Long.class) {
            return long.class;
        } else if (type == java.util.Date.class) {
            return java.util.Date.class;
        } else if (type == java.lang.Boolean.class) {
            return boolean.class;
        } else if (type == String.class) {
            return String.class;
        } else {
            return String.class;
        }
    }

    /**
     *
     * @param obj
     * @return
     */
    public Object getSelectedObject(Object obj) {
//        if(selected ==-1)
        selected = table.getSelectedRow();
        //int columnCount = table.getColumnCount(true) - 1;
        if (selected > -1) {
            try {
                value = table.getModel().getValueAt(selected, 0);
                obj.getClass().getMethod("setId" + convertir(id), new Class[]{tipo()}).invoke(obj, value);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                Logger.getLogger(JCAbstractTable.class.getName()).log(Level.SEVERE, null, e);
                System.err.println("Error");
            }
            return obj;
        } else {
            return null;
        }
    }

    public void reload() {
        table.setModel(createModel());
        configureTable(table);
        table.updateUI();
    }

    protected void getDoubleClick() {
    }

    final class DoubleClickListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            // If the user right clicks on a row other than the selection,
            // then move the selection to the current row
            if (e.getButton() == MouseEvent.BUTTON3) {
                int rowUnderMouse = getTable().rowAtPoint(e.getPoint());
//                if (rowUnderMouse != -1 && !getTable().isRowSelected(rowUnderMouse)) {
//                    selected = rowUnderMouse;
//                }
                selected = rowUnderMouse;
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // If the user double clicked on a row, then call onDoubleClick
            if (e.getClickCount() == 2) {
                getDoubleClick();
            }
        }
    }

    public JXTable getTable() {
        return table;
    }

    protected abstract List getDefaultInitialData();

    protected void configureTable(JXTable table) {
        table.setColumnControlVisible(true);
        table.addHighlighter(HighlighterFactory.createSimpleStriping());
        table.addHighlighter(new ColorHighlighter(HighlightPredicate.ROLLOVER_ROW, Color.BLACK, Color.WHITE));
        // Add the context menu listener
        table.addMouseListener(new DoubleClickListener());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        table = new org.jdesktop.swingx.JXTable();

        table.setModel(createModel());
        table.setEditable(false);
        configureTable(table);
        setViewportView(table);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXTable table;
    // End of variables declaration//GEN-END:variables
}
