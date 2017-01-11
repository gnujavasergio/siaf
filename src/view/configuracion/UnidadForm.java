/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.configuracion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import model.domain.Unidad;
import org.jc.swing.CoolButton;
import org.jdesktop.swingx.JXHeader;
import utils.IconUI;
import view.ViewMain;
import view.common.CommonForm;

/**
 * UnidadForm
 *
 * @author Sergio Antonio Ochoa Martinez gnu.java.sergio@gmail.com
 * @version 0.1
 */
public class UnidadForm extends javax.swing.JPanel implements CommonForm {

    private Unidad unidad;
    private CtrlUnidadForm ctrlUnidadForm;

    /**
     * Unidad
     */
    public UnidadForm() {        
        ctrlUnidadForm = new CtrlUnidadForm();
        initComponents();
    }

    public CoolButton getbGuardar() {
        return bGuardar;
    }

    public CoolButton getbCancelar() {
        return bCancelar;
    }

    public CoolButton getbSelect() {
        return bSelect;
    }

    public JXHeader getHeaderUnidad() {
        return headerUnidad;
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
        lCiudad = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfCiudad = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescripcion = new javax.swing.JTextArea();
        headerUnidad = new org.jdesktop.swingx.JXHeader();
        jPanel2 = new javax.swing.JPanel();
        bGuardar = new org.jc.swing.CoolButton(0);
        bCancelar = new org.jc.swing.CoolButton(0);
        bSelect = new org.jc.swing.CoolButton(0);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lCiudad.setText("Ciudad:");

        jLabel3.setText("Descripción:");

        tfCiudad.addFocusListener(ctrlUnidadForm);

        taDescripcion.setColumns(20);
        taDescripcion.addFocusListener(ctrlUnidadForm);
        taDescripcion.setRows(5);
        taDescripcion.setLineWrap(true);
        taDescripcion.setWrapStyleWord(true);
        jScrollPane1.setViewportView(taDescripcion);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lCiudad)
                    .addComponent(jLabel3))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfCiudad, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lCiudad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 121, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                .addContainerGap())
        );

        headerUnidad.setDescription("");
        headerUnidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/autentificarse.png"))); // NOI18N
        headerUnidad.setIconPosition(org.jdesktop.swingx.JXHeader.IconPosition.LEFT);
        headerUnidad.setTitle("Unidad");
        headerUnidad.setTitleFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        headerUnidad.setToolTipText("");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        bGuardar.setText("Guardar");
        bGuardar.addActionListener(ctrlUnidadForm);
        bGuardar.setIcon(IconUI.getResizableIcon("aceptar", 32, 32));
        jPanel2.add(bGuardar);

        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(ctrlUnidadForm);
        bCancelar.setIcon(IconUI.getResizableIcon("cancelar", 32, 32));
        jPanel2.add(bCancelar);

        bSelect.setText("Seleccionar");
        bSelect.addActionListener(ctrlUnidadForm);
        bSelect.setIcon(IconUI.getResizableIcon("select", 32, 32));
        jPanel2.add(bSelect);
        bSelect.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerUnidad, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(headerUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jc.swing.CoolButton bCancelar;
    private org.jc.swing.CoolButton bGuardar;
    private org.jc.swing.CoolButton bSelect;
    private org.jdesktop.swingx.JXHeader headerUnidad;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lCiudad;
    private javax.swing.JTextArea taDescripcion;
    private javax.swing.JTextField tfCiudad;
    // End of variables declaration//GEN-END:variables

    @Override
    public Object getObject() {
        unidad.setCiudad(tfCiudad.getText());
        unidad.setDescripcion(taDescripcion.getText());
        unidad.setEntidad(ViewMain.viewMain.getEntidad());
        return unidad;
    }

    @Override
    public void setObject(Object obj) {
        unidad = (Unidad) obj;
        tfCiudad.setText(unidad.getCiudad());
        taDescripcion.setText(unidad.getDescripcion());
    }

    private class CtrlUnidadForm implements ActionListener, FocusListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object aux = e.getSource();
            if (aux.equals(bGuardar)) {
                unidad = (Unidad) getObject();
                if (UnidadForm.this.bGuardar.getText().equals("Guardar")) {
                    ViewMain.DAO.add(unidad);
                } else {
                    ViewMain.DAO.update(unidad);
                }
                UnidadView.unidadView.getUnidadTable().reload();
                ViewMain.viewMain.getCommonDialog().dispose();
            } else if (aux.equals(bCancelar)) {
                ViewMain.viewMain.getCommonDialog().dispose();
            } else if (aux.equals(bSelect)) {
                unidad = (Unidad) getObject();
                unidad.setEstado(true);
                ViewMain.DAO.add(unidad);                
                ViewMain.viewMain.setUnidad(unidad);
                ViewMain.viewMain.getCommonDialog().dispose();
                ViewMain.viewMain.getlUnidad().setText("<html><b>Unidad: </b>" + unidad.getIdUnidad() + "  " + unidad.getDescripcion() + "</b></html>");
                UnidadView.unidadView.getUnidadTable().reload();
                ViewMain.viewMain.getFrontend().hide(ViewMain.viewMain.getDockableUnidad());
                ViewMain.viewMain.getFrontend().show(ViewMain.viewMain.getDockableUnidad());
            }
        }

        @Override
        public void focusGained(FocusEvent e) {
            Object aux = e.getSource();
            if (aux.equals(tfCiudad)) {
                tfCiudad.selectAll();
            } else if (aux.equals(taDescripcion)) {
                taDescripcion.selectAll();
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
        }
    }
}