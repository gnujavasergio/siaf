/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.seguridad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import utils.FiltroBackup;
import utils.IconUI;
import view.ViewMain;

/**
 * RestaurarForm
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 */
public class RestaurarForm extends javax.swing.JPanel {

    private CtrlRestaurarForm ctrlRestaurarForm;

    public RestaurarForm() {
        ctrlRestaurarForm = new CtrlRestaurarForm();
        initComponents();
    }

    public JTextField getTfDireccion() {
        return tfDireccion;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fcRestaurar = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        tfDireccion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        bExaminar = new org.jc.swing.CoolButton(0);
        jPanel2 = new javax.swing.JPanel();
        bRestaurar = new org.jc.swing.CoolButton(0);
        bCancelar = new org.jc.swing.CoolButton(0);
        headerEntidad = new org.jdesktop.swingx.JXHeader();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tfDireccion.setEditable(false);

        jLabel4.setText("Nombre:");

        bExaminar.setText("Examinar");
        bExaminar.addActionListener(ctrlRestaurarForm);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bExaminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bExaminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        bRestaurar.setText("Restaurar");
        bRestaurar.addActionListener(ctrlRestaurarForm);
        bRestaurar.setIcon(IconUI.getResizableIcon("aceptar", 32, 32));
        jPanel2.add(bRestaurar);

        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(ctrlRestaurarForm);
        bCancelar.setIcon(IconUI.getResizableIcon("cancelar", 32, 32));
        jPanel2.add(bCancelar);

        headerEntidad.setDescription("Se restaurara la Base de Datos.");
        headerEntidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/entidad.png"))); // NOI18N
        headerEntidad.setIconPosition(org.jdesktop.swingx.JXHeader.IconPosition.LEFT);
        headerEntidad.setTitle("Restaurar");
        headerEntidad.setTitleFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headerEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jc.swing.CoolButton bCancelar;
    private org.jc.swing.CoolButton bExaminar;
    private org.jc.swing.CoolButton bRestaurar;
    private javax.swing.JFileChooser fcRestaurar;
    private org.jdesktop.swingx.JXHeader headerEntidad;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField tfDireccion;
    // End of variables declaration//GEN-END:variables

    private void addExaminar() {
        fcRestaurar.addChoosableFileFilter(new FiltroBackup(new String[]{".saf"}, "Archivos Backup SAF (*.saf)"));
        int abrir = fcRestaurar.showOpenDialog(ViewMain.viewMain);
        if (abrir == JFileChooser.APPROVE_OPTION) {
            if (fcRestaurar.getSelectedFile() != null) {
                tfDireccion.setText(fcRestaurar.getSelectedFile().getAbsolutePath());
            }
        }
    }

    public void addRestaurar() {
        if (tfDireccion.getText().compareTo("") != 0) {
            restaurarBackup(tfDireccion.getText());
            ViewMain.viewMain.getCommonDialog().dispose();
        } else {
            JOptionPane.showMessageDialog(ViewMain.viewMain, "No se tiene una direccion, no se puede realizar el Backup", "Mensaje", JOptionPane.WARNING_MESSAGE);
            addExaminar();
        }
    }

    private void restaurarBackup(String direccion) {
        try {
            ZipInputStream zis = new ZipInputStream(new FileInputStream(direccion));
            ZipEntry entrada;
            while (null != (entrada = zis.getNextEntry())) {
                System.out.println(entrada.getName());

                FileOutputStream fos = new FileOutputStream(entrada.getName());
                int leido;
                byte[] buffer = new byte[1024];
                while (0 < (leido = zis.read(buffer))) {
                    fos.write(buffer, 0, leido);
                }
                fos.close();
                zis.closeEntry();
            }
            JOptionPane.showMessageDialog(ViewMain.viewMain, "Se restauro la Base de Datos correctamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(ViewMain.viewMain, "No se pudo restaurar la Base de Datos.", "Mensaje", JOptionPane.WARNING_MESSAGE);
        }
    }

    private class CtrlRestaurarForm implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object aux = e.getSource();
            if (aux.equals(bExaminar)) {
                addExaminar();
            } else if (aux.equals(bRestaurar)) {
                addRestaurar();
            } else if (aux.equals(bCancelar)) {
                ViewMain.viewMain.getCommonDialog().dispose();
            }
        }
    }
}
