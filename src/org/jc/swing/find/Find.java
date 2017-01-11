/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jc.swing.find;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resources;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.FocusManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.jc.swing.JCAbstractTable;
import org.jdesktop.swingx.JXCollapsiblePane;

/**
 *
 * @author Ochoa Martinez Sergio Antonio - gnu.java.sergio@gmail.com
 */
public class Find {

    private EventList objectEventList = new BasicEventList();

    private JXCollapsiblePane findCollapsiblePane;
    private JButton closeSearchPanel;
    private JPanel contenedor;
    private JPanel contenedorFind;
    private PanelFind panelFind;
    private CtrlFind ctrlFind;
    
    private String id;
    /**
     * Lista de vectores que se mostraran en el ComboBox
     */
    private String[] item;

    private DefaultComboBoxModel comboBoxModel;
    /**
     * atributo con el cual leeremos el archivo properties
     */
    private Properties properties;

    public Find(String id, String[] item) {
        this.id = id;
        this.item = item;
        ctrlFind = new CtrlFind();    
    }


    private void initComboBox(){
        Vector<String> vector = new Vector<String>();
        properties = new Properties();
        try {
            properties.load(Resources.class.getResourceAsStream("/resources/ui/messages.properties"));
            for(Object o : item)
            {
                vector.addElement(properties.getProperty(id + "." + o.toString() + ".label"));
            }
        } catch (IOException ex) {
            Logger.getLogger(JCAbstractTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        comboBoxModel = new DefaultComboBoxModel(vector);
        panelFind.getCbOption().setModel(comboBoxModel);
    }

    public JPanel initFind(){
        
        contenedor = new JPanel();
        contenedor.setLayout(new GridBagLayout());

        closeSearchPanel = new JButton();
        closeSearchPanel.setIcon(new ImageIcon(getClass().getResource("/org/resources/close_01.png")));
        closeSearchPanel.setBorder(null);
        closeSearchPanel.setBorderPainted(false);
        closeSearchPanel.setContentAreaFilled(false);
        closeSearchPanel.setFocusPainted(false);
        closeSearchPanel.setPressedIcon(new ImageIcon(getClass().getResource("/org/resources/close_03.png")));
        closeSearchPanel.setRolloverIcon(new ImageIcon(getClass().getResource("/org/resources/close_02.png")));
        closeSearchPanel.addActionListener(ctrlFind);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        
        gridBagConstraints.insets = new Insets(12, 5, 12, 5);
        contenedor.add(closeSearchPanel, gridBagConstraints);

        panelFind = new PanelFind();        
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        contenedor.add(panelFind, gridBagConstraints);
        /**
         *
         */
        initComboBox();
        /**
         * Este componnete hace el efecto de animacion
         */
        findCollapsiblePane = new JXCollapsiblePane();
        findCollapsiblePane.getContentPane().setLayout(new BorderLayout());
        findCollapsiblePane.setCollapsed(true);
        findCollapsiblePane.getContentPane().add(contenedor, BorderLayout.CENTER);
        /**
         *
         */
        contenedorFind = new JPanel();
        contenedorFind.setLayout(new BorderLayout());
        contenedorFind.setBorder(BorderFactory.createEmptyBorder(3, 3, 0, 3));
        contenedorFind.add(findCollapsiblePane, BorderLayout.SOUTH);
        return contenedorFind;
    }

    public void actionFind(){
            boolean collapsed = findCollapsiblePane.isCollapsed();
            findCollapsiblePane.setCollapsed(!collapsed);
            if (collapsed) {
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            SwingUtilities.invokeAndWait(new Runnable() {
                                public void run() {
                                    panelFind.getTfBuscar().requestFocusInWindow();
                                }
                            });
                            SwingUtilities.invokeAndWait(new Runnable() {
                                public void run() {
                                    panelFind.getTfBuscar().transferFocus();
                                }
                            });
                            SwingUtilities.invokeAndWait(new Runnable() {
                                public void run() {
                                    FocusManager.getCurrentManager().focusNextComponent();
                                }
                            });
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        } catch (InvocationTargetException ex) {
                            ex.printStackTrace();
                        }
                    }
                }).start();
            }        
    }//fin metodo actionFind

    private class CtrlFind implements  ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object aux = e.getSource();
            if(aux.equals(closeSearchPanel)){
                findCollapsiblePane.setCollapsed(true);
            }
        }
    }//fin de la clase CtrlFind

    public PanelFind getPanelFind() {
        return panelFind;
    }

    public String[] getItem() {
        return item;
    }

    public void setItem(String[] item) {
        this.item = item;
    }

}//fin class find
