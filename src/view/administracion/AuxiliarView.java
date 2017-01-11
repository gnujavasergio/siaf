/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.administracion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.domain.Auxiliar;
import model.domain.Grupo;
import utils.IconUI;
import view.ViewMain;
import view.common.CommonDialog;
import view.common.CommonView;

/**
 * AuxiliarView
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 * @version 0.1
 */
public class AuxiliarView extends javax.swing.JPanel implements CommonView {

    private CtrlAuxiliarView ctrlAuxiliarView;
    private AuxiliarForm auxiliarForm;
    private AuxiliarTable auxiliarTable;
    public static AuxiliarView auxiliarView;    

    public AuxiliarView() {
        auxiliarView = this;
        ctrlAuxiliarView = new CtrlAuxiliarView();
        initComponents();
        auxiliarForm = new AuxiliarForm();
        auxiliarTable = new AuxiliarTable();
        this.add(auxiliarTable, BorderLayout.CENTER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bNew = new org.jc.swing.CoolButton(1);
        bUpdate = new org.jc.swing.CoolButton(2);
        bDelete = new org.jc.swing.CoolButton(2);

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 5));

        bNew.setText("Nuevo");
        bNew.setIcon(IconUI.getResizableIcon("add", 32,32));
        bNew.addActionListener(ctrlAuxiliarView);
        jPanel1.add(bNew);

        bUpdate.setText("Editar");
        bUpdate.setIcon(IconUI.getResizableIcon("update", 32, 32));
        jPanel1.add(bUpdate);
        bUpdate.addActionListener(ctrlAuxiliarView);

        bDelete.setText("Borrar");
        bDelete.setIcon(IconUI.getResizableIcon("delete", 32, 32));
        jPanel1.add(bDelete);
        bDelete.addActionListener(ctrlAuxiliarView);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jc.swing.CoolButton bDelete;
    private org.jc.swing.CoolButton bNew;
    private org.jc.swing.CoolButton bUpdate;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    public AuxiliarTable getAuxiliarTable() {
        return auxiliarTable;
    }

    @Override
    public void addNew() {        
        ViewMain.viewMain.setCommonDialog(new CommonDialog(ViewMain.viewMain, true));
        ViewMain.viewMain.getCommonDialog().setTitle("Nueva Auxiliar");
        auxiliarForm.initComboBoxGrupo(ViewMain.DAO.getObjects(new Grupo()));
        auxiliarForm.setObject(new Auxiliar());
        auxiliarForm.getbGuardar().setText("Guardar");
        ViewMain.viewMain.getCommonDialog().add(auxiliarForm);
        ViewMain.viewMain.getCommonDialog().visibleDialog("Nuevo Auxiliar");        
    }

    @Override
    public void addUpdate() {
        ViewMain.viewMain.getGlassPane().setVisible(true);
        ViewMain.viewMain.setCommonDialog(new CommonDialog(ViewMain.viewMain, true));
        Auxiliar auxiliar = new Auxiliar();
        auxiliar = (Auxiliar) auxiliarTable.getSelectedObject(auxiliar);
        if (auxiliar != null) {
            auxiliar = (Auxiliar) ViewMain.DAO.getObject(auxiliar);
            auxiliarForm.initComboBoxGrupo(ViewMain.DAO.getObjects(new Grupo()));
            auxiliarForm.setObject(auxiliar);
            auxiliarForm.getbGuardar().setText("Actualizar");            
            ViewMain.viewMain.getCommonDialog().add(auxiliarForm);
            ViewMain.viewMain.getCommonDialog().visibleDialog("Actualizando " + auxiliar.getNombre());
            ViewMain.viewMain.getGlassPane().setVisible(false);
        } else {
            JOptionPane.showMessageDialog(ViewMain.viewMain, "No selecciono ninguna Auxiliar", "Mensaje", 0);
            ViewMain.viewMain.getGlassPane().setVisible(false);
        }
    }

    @Override
    public void addDelete() {
        Auxiliar auxiliar = new Auxiliar();
        auxiliar = (Auxiliar) auxiliarTable.getSelectedObject(auxiliar);
        if (auxiliar != null) {
            int option = JOptionPane.showConfirmDialog(ViewMain.viewMain, "Esta seguro que desea borrar la Auxiliar", "Borrar", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                ViewMain.DAO.delete(auxiliar);
                auxiliarTable.reload();
            }
        } else {
            JOptionPane.showMessageDialog(ViewMain.viewMain, "No selecciono ninguna auxiliar", "Borrar", 0);
        }
    }

    private class CtrlAuxiliarView implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object aux = e.getSource();
            if (aux.equals(bNew)) {
                addNew();
            } else if (aux.equals(bUpdate)) {
                addUpdate();
            } else if (aux.equals(bDelete)) {
                addDelete();
            }
        }
    }
}