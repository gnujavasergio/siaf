/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.configuracion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import model.domain.Configuracion;
import org.jc.swing.CoolButton;
import org.jdesktop.swingx.JXHeader;
import utils.IconUI;
import view.ViewMain;
import view.common.CommonForm;

/**
 * ConfiguracionForm
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 */
public class ConfiguracionForm extends javax.swing.JPanel implements CommonForm {

    private Configuracion configuracion;
    private CtrlConfiguracionForm ctrlConfiguracionForm;

    /**
     * ConfiguracionForm
     */
    public ConfiguracionForm() {
        ctrlConfiguracionForm = new CtrlConfiguracionForm();
        initComponents();
    }

    public JXHeader getHeaderConfiguracion() {
        return headerConfiguracion;
    }

    public CoolButton getbGuardar() {
        return bGuardar;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jPanel2 = new javax.swing.JPanel();
        bGuardar = new org.jc.swing.CoolButton(0);
        bCancelar = new org.jc.swing.CoolButton(0);
        jPanel1 = new javax.swing.JPanel();
        tfUFV = new javax.swing.JTextField(7);
        jLabel4 = new javax.swing.JLabel();
        dccFechaUFV = new datechooser.beans.DateChooserCombo();
        jLabel1 = new javax.swing.JLabel();
        headerConfiguracion = new org.jdesktop.swingx.JXHeader();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        bGuardar.setText("Guardar");
        bGuardar.addActionListener(ctrlConfiguracionForm);
        bGuardar.setIcon(IconUI.getResizableIcon("aceptar", 32, 32));
        jPanel2.add(bGuardar);

        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(ctrlConfiguracionForm);
        bCancelar.setIcon(IconUI.getResizableIcon("cancelar", 32, 32));
        jPanel2.add(bCancelar);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("UFV:");

        dccFechaUFV.setFormat(0);

        jLabel1.setText("Fecha:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dccFechaUFV, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                    .addComponent(tfUFV))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfUFV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dccFechaUFV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        headerConfiguracion.setDescription("");
        headerConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/entidad.png"))); // NOI18N
        headerConfiguracion.setIconPosition(org.jdesktop.swingx.JXHeader.IconPosition.LEFT);
        headerConfiguracion.setTitle("UFV");
        headerConfiguracion.setTitleFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerConfiguracion, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(headerConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jc.swing.CoolButton bCancelar;
    private org.jc.swing.CoolButton bGuardar;
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserCombo dccFechaUFV;
    private org.jdesktop.swingx.JXHeader headerConfiguracion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField tfUFV;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setObject(Object obj) {
        configuracion = (Configuracion) obj;
        tfUFV.setText(configuracion.getUFV());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(configuracion.getFechaUFV());
        dccFechaUFV.setSelectedDate(calendar);
    }

    @Override
    public Object getObject() {
        configuracion.setUFV(tfUFV.getText());
        configuracion.setFechaUFV(dccFechaUFV.getSelectedDate().getTime());
        return configuracion;
    }

    private class CtrlConfiguracionForm implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object aux = e.getSource();
            if (aux.equals(bGuardar)) {
                configuracion = (Configuracion) getObject();
                if (bGuardar.getText().equals("Guardar")) {
                    ViewMain.DAO.add(configuracion);
                } else {
                    ViewMain.DAO.update(configuracion);
                }
                ConfiguracionView.configuracionView.getConfiguracionTable().reload();
                ViewMain.viewMain.getCommonDialog().dispose();
            } else if (aux.equals(bCancelar)) {
                ViewMain.viewMain.getCommonDialog().dispose();
            }
            ViewMain.viewMain.getCommonDialog().dispose();
        }
    }
}
