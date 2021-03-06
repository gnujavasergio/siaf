/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.administracion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import model.domain.Oficina;
import org.jc.swing.CoolButton;
import org.jc.swing.JCTextField;
import utils.IconUI;
import view.ViewMain;
import view.common.CommonForm;

/**
 * OficinaForm
 *
 * @author Sergio Antonio Ochoa Martinez gnu.java.sergio@gmail.com
 * @version 0.2
 */
public class OficinaForm extends javax.swing.JPanel implements CommonForm {

    private Oficina oficina;
    private CtrlOficinaForm ctrlOficinaForm;

    /**
     * Oficina
     */
    public OficinaForm(Oficina oficina) {
        this.oficina = oficina;
        ctrlOficinaForm = new CtrlOficinaForm();
        initComponents();
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

        jPanel2 = new javax.swing.JPanel();
        bGuardar = new org.jc.swing.CoolButton(0);
        bCancelar = new org.jc.swing.CoolButton(0);
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfNombre = new JCTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        taObservacion = new javax.swing.JTextArea();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        bGuardar.setText("Guardar");
        bGuardar.addActionListener(ctrlOficinaForm);
        bGuardar.setIcon(IconUI.getResizableIcon("aceptar", 32, 32));
        bGuardar.addKeyListener(ctrlOficinaForm);
        jPanel2.add(bGuardar);

        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(ctrlOficinaForm);
        bCancelar.setIcon(IconUI.getResizableIcon("cancelar", 32, 32));
        jPanel2.add(bCancelar);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Oficina"));

        jLabel1.setText("Nombre:");

        jLabel2.setText("Observacion:");

        tfNombre.addFocusListener(ctrlOficinaForm);
        tfNombre.addKeyListener(ctrlOficinaForm);

        taObservacion.setColumns(20);
        taObservacion.setRows(5);
        taObservacion.setLineWrap(true);
        taObservacion.setWrapStyleWord(true);
        jScrollPane1.setViewportView(taObservacion);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfNombre)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jc.swing.CoolButton bCancelar;
    private org.jc.swing.CoolButton bGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taObservacion;
    private javax.swing.JTextField tfNombre;
    // End of variables declaration//GEN-END:variables

    @Override
    public Object getObject() {
        oficina.setNombre(tfNombre.getText());
        oficina.setObservacion(taObservacion.getText());
        oficina.setUnidad(ViewMain.viewMain.getUnidad());
        oficina.setUsuario(ViewMain.viewMain.getUsuario());
        return oficina;
    }

    @Override
    public void setObject(Object obj) {
        oficina = (Oficina) obj;
        tfNombre.setText(oficina.getNombre());
        taObservacion.setText(oficina.getObservacion());
    }

    public void addSave() {
        oficina = (Oficina) getObject();
        if (OficinaForm.this.bGuardar.getText().equals("Guardar")) {
            ViewMain.DAO.add(oficina);
        } else {
            ViewMain.DAO.update(OficinaForm.this.oficina);
        }
        OficinaView.oficinaView.getOficinaTable().reload();
        ViewMain.viewMain.getCommonDialog().dispose();
    }

    private class CtrlOficinaForm extends KeyAdapter implements ActionListener, FocusListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object aux = e.getSource();
            if (aux.equals(bGuardar)) {
                addSave();
            } else if (aux.equals(OficinaForm.this.bCancelar)) {
                ViewMain.viewMain.getCommonDialog().dispose();
            }
        }

        @Override
        public void focusGained(FocusEvent e) {
            Object aux = e.getSource();
            if (aux.equals(tfNombre)) {
                tfNombre.selectAll();
            } else if (aux.equals(taObservacion)) {
                taObservacion.selectAll();
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == 10) {
                addSave();
            }
        }
    }
}
