/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.activos;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.domain.Baja;
import utils.IconUI;
import view.ViewMain;
import view.common.CommonDialog;

/**
 * BajaView
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 */
public class BajaView extends javax.swing.JPanel {

    private BajaView.CtrlBajaView ctrlBajaView;
    private BajaTable bajaTable;
    public static BajaView bajaView;
    private CommonDialog commonDialog;
    private Baja baja = new Baja();

    public BajaView() {
        bajaView = this;
        ctrlBajaView = new CtrlBajaView();
        initComponents();
        bajaTable = new BajaTable();
        this.add(bajaTable, BorderLayout.CENTER);
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
        bDetail = new org.jc.swing.CoolButton(1);
        bExit = new org.jc.swing.CoolButton(2);

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 5));

        bDetail.setText("Detalle");
        bDetail.setIcon(IconUI.getResizableIcon("view", 32,32));
        bDetail.addActionListener(ctrlBajaView);
        jPanel1.add(bDetail);

        bExit.setText("Salir");
        bExit.setIcon(IconUI.getResizableIcon("salir", 32,32));
        bExit.addActionListener(ctrlBajaView);
        jPanel1.add(bExit);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jc.swing.CoolButton bDetail;
    private org.jc.swing.CoolButton bExit;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    public CommonDialog getCommonDialog() {
        return commonDialog;
    }

    public BajaTable getBajaTable() {
        return bajaTable;
    }

    public void addDetail() {
        baja = new Baja();
        baja = (Baja) bajaTable.getSelectedObject(baja);
        if (baja != null) {
            baja = (Baja) ViewMain.DAO.getObject(baja);
            ActivoFijoView.activoFijoView.getBajaForm().getbView().setVisible(false);
            ActivoFijoView.activoFijoView.getBajaForm().getbGuardar().setVisible(false);
            ActivoFijoView.activoFijoView.getBajaForm().initConfiguracion(false);
            ActivoFijoView.activoFijoView.addBaja(baja);
        } else {
            JOptionPane.showMessageDialog(ViewMain.viewMain, "No selecciono ninguna Baja", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class CtrlBajaView implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object aux = e.getSource();
            if (aux.equals(bDetail)) {
                addDetail();
            } else if (aux.equals(bExit)) {
                ViewMain.viewMain.getFrontend().hide(ViewMain.viewMain.getDockableBaja());
            }
        }
    }
}
