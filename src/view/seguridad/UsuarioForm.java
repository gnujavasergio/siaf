/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.seguridad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import model.domain.Usuario;
import org.jc.swing.CoolButton;
import org.jdesktop.swingx.JXHeader;
import utils.IconUI;
import view.ViewMain;
import view.common.CommonForm;

/**
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio
 */
public class UsuarioForm extends javax.swing.JPanel implements CommonForm {

    private Usuario usuario;
    private CtrlUsuarioForm ctrlUsuarioForm;

    /**
     * Usuario
     */
    public UsuarioForm(Usuario usuario) {
        this.usuario = usuario;
        ctrlUsuarioForm = new CtrlUsuarioForm();
        initComponents();
        setObject(this.usuario);
    }

    public CoolButton getbGuardar() {
        return bGuardar;
    }

    public CoolButton getbCancelar() {
        return bCancelar;
    }

    public JXHeader getHeaderUsuario() {
        return headerUsuario;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerUsuario = new org.jdesktop.swingx.JXHeader();
        panelCurves1 = new org.jc.swing.panel.PanelCurves();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfLogin = new javax.swing.JTextField();
        pfPassword = new javax.swing.JPasswordField();
        cbTipo = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        tfNombres = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescripcion = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        bGuardar = new org.jc.swing.CoolButton(0);
        bCancelar = new org.jc.swing.CoolButton(0);

        headerUsuario.setDescription("");
        headerUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/autentificarse.png"))); // NOI18N
        headerUsuario.setIconPosition(org.jdesktop.swingx.JXHeader.IconPosition.LEFT);
        headerUsuario.setTitle("Datos Usuario");
        headerUsuario.setTitleFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N

        panelCurves1.setStart(new java.awt.Color(102, 102, 102));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Login:");

        jLabel2.setText("Contaseña:");

        tfLogin.addFocusListener(ctrlUsuarioForm);

        pfPassword.addFocusListener(ctrlUsuarioForm);

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrador", "Operador" }));

        jLabel4.setText("Tipo:");

        tfNombres.addFocusListener(ctrlUsuarioForm);

        jLabel5.setText("Nombre Completo:");

        taDescripcion.setColumns(20);
        taDescripcion.setRows(5);
        taDescripcion.addFocusListener(ctrlUsuarioForm);
        jScrollPane1.setViewportView(taDescripcion);

        jLabel6.setText("Descripcion:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbTipo, 0, 137, Short.MAX_VALUE)
                        .addGap(170, 170, 170))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tfLogin)
                            .addComponent(pfPassword, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfNombres, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
                        .addGap(73, 73, 73))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        bGuardar.setText("Guardar");
        bGuardar.addActionListener(ctrlUsuarioForm);
        bGuardar.setIcon(IconUI.getResizableIcon("aceptar", 32, 32));
        jPanel2.add(bGuardar);

        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(ctrlUsuarioForm);
        bCancelar.setIcon(IconUI.getResizableIcon("cancelar", 32, 32));
        jPanel2.add(bCancelar);

        javax.swing.GroupLayout panelCurves1Layout = new javax.swing.GroupLayout(panelCurves1);
        panelCurves1.setLayout(panelCurves1Layout);
        panelCurves1Layout.setHorizontalGroup(
            panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCurves1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelCurves1Layout.setVerticalGroup(
            panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCurves1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 489, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(headerUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(panelCurves1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 471, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 11, Short.MAX_VALUE)
                    .addComponent(headerUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(panelCurves1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 12, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jc.swing.CoolButton bCancelar;
    private org.jc.swing.CoolButton bGuardar;
    private javax.swing.JComboBox cbTipo;
    private org.jdesktop.swingx.JXHeader headerUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jc.swing.panel.PanelCurves panelCurves1;
    private javax.swing.JPasswordField pfPassword;
    private javax.swing.JTextArea taDescripcion;
    private javax.swing.JTextField tfLogin;
    private javax.swing.JTextField tfNombres;
    // End of variables declaration//GEN-END:variables
    @Override
    public Object getObject() {
        usuario.setLogin(tfLogin.getText());
        usuario.setPassword(pfPassword.getText());
        usuario.setNombres(tfNombres.getText());
        usuario.setDescripcion(taDescripcion.getText());
        usuario.setTipo(cbTipo.getSelectedItem().toString());
        return usuario;
    }

    @Override
    public void setObject(Object obj) {
        usuario = (Usuario) obj;
        tfLogin.setText(usuario.getLogin());
        tfNombres.setText(usuario.getNombres());
        taDescripcion.setText(usuario.getDescripcion());
        cbTipo.setSelectedItem(usuario.getTipo());
    }

    private class CtrlUsuarioForm implements ActionListener, FocusListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object aux = e.getSource();
            if (aux.equals(bGuardar)) {
                usuario = (Usuario) getObject();
                if (bGuardar.getText().equals("Guardar")) {
                    ViewMain.DAO.add(usuario);
                } else {
                    ViewMain.DAO.update(UsuarioForm.this.usuario);
                }
                UsuarioView.usuarioView.getUsuarioTable().reload();
                ViewMain.viewMain.getCommonDialog().dispose();
            } else if (aux.equals(bCancelar)) {
                ViewMain.viewMain.getCommonDialog().dispose();
            }
        }

        @Override
        public void focusGained(FocusEvent e) {
            Object aux = e.getSource();
            if(aux.equals(tfLogin)){
                tfLogin.selectAll();
            } else if(aux.equals(pfPassword)){
                pfPassword.selectAll();
            } else if(aux.equals(tfNombres)){
                tfNombres.selectAll();
            } else if(aux.equals(taDescripcion)){
                taDescripcion.selectAll();
            }
            
        }

        @Override
        public void focusLost(FocusEvent e) {
            
        }
    }
}
