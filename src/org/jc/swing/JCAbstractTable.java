
package org.jc.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resources;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXCollapsiblePane;

/**
 * @file JCAstractTable.java
 * @version 0.1
 * @author gnuX - Juan Carlos Canaza Ayarachi - gnu.andrajo@gmail.com
 * @author chechi  - Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 * @date   11/07/2009
 * @url    http://www.programacionhelp.com
 * @description clase abstracta para implemetar una tabla
 */
public abstract  class JCAbstractTable extends javax.swing.JScrollPane {

    private String id;
    /**
     * el titulo que tendra cada columana de la tabla
     */
    private String [] title;
    /**
     * atributo con el cual leeremos el archivo properties
     */
    private Properties properties;    
    
    private Object value;

    public JCAbstractTable() {
        initComponents();
    }

    public JCAbstractTable(String id, String[] list) {
        this.id = id;
        this.title = list;
        initComponents();
    }
    /**
     *
     * @return un Vector
     */
    private Vector nameColumn(){
        Vector column = new Vector();
        properties = new Properties();
        try {
            properties.load(Resources.class.getResourceAsStream("/resources/ui/messages.properties"));
            for(Object o :title)
            {
                column.addElement(properties.getProperty(id+"."+o.toString()+".label"));
            }
            return column;
        } catch (IOException ex) {
            Logger.getLogger(JCAbstractTable.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }//fin nameColumn
 
   /**
     * Convertir minusculas en mayusculas (sola las iniciales) y encontrar los atributo
     * @param toString
     * @return
     */
    private String convertir(String a) {
        StringBuffer atributo = new StringBuffer(a);
        atributo.insert(0, Character.toUpperCase(atributo.charAt(0)));
        return atributo.deleteCharAt(1).toString();
    }

    /**
     * Crea el contenido a visualizar
     * @return DefaultTableModel
     */
    private DefaultTableModel createModel(){        
        List list = getDefaultInitialData();//obtenemos toda la lista a mostrar
        Vector data = new Vector();//vector tabla
        if(list != null){
            for(Object o : list)
            {
                Vector columnas = new Vector();//columnas para llenar la tabla
                //lenar el vector columna
                for(Object x: title)
                {
                    try {
                        columnas.add(o.getClass().getMethod( "get" + convertir(x.toString()),new Class[] {}).invoke(o));
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
                data.add(columnas);
            }
        }        
        return new DefaultTableModel(data,nameColumn());
    }
   /**
     * @return un Class segun el tipo que se da
     */    
   private Class tipo(){
        Class type = value.getClass();
        if(type == java.lang.Integer.class){
            return int.class;
        }
        else if(type == java.lang.Long.class){
            return long.class;
        }
        else if(type == java.util.Date.class){
            return java.util.Date.class;
        }
        else if(type == String.class){
           return  String.class;
        }
        else
            return  String.class;  
   }
   
   public Object getSelectedObject(Object obj){
        int selected = table.getSelectedRow();
        int columnCount = table.getColumnCount()-1;
        if(selected > -1){
            try {
                value = table.getValueAt(selected, columnCount);
                obj.getClass().getMethod("setId" + convertir(id),new Class[]{tipo()}).invoke(obj,value);
            } catch (Exception e) {
                Logger.getLogger(JCAbstractTable.class.getName()).log(Level.SEVERE, null, e);
                System.err.println("Error");
            }
            return obj;
        }
        else{            
            return null;
        }
    }

    public void reload(){
        table.setModel(createModel());
        configureTable(table);
        table.updateUI();
    }

    public void setDoubleClick(){        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                System.out.println("doubleClik");
                if(e.getClickCount()== 2 ){
                    System.out.println("doubleClik");
                }
            }
        });
    }

    protected abstract List getDefaultInitialData();
    protected void configureTable(JTable table){
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        table = new javax.swing.JTable();

        setName("Form"); // NOI18N

        table.setModel(createModel());
        configureTable(table);
        table.setName("table"); // NOI18N
        setViewportView(table);
    }// </editor-fold>//GEN-END:initComponents

    public JTable getTable() {
        return table;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
    protected JXCollapsiblePane collapsiblePaneFind;
    protected JButton closeSearchPanel;
}
