/*
 * ShowComponents.java
 *
 * Created on 15 ������� 2006 �., 12:20
 */

package datechooser.demo.steps;

import static datechooser.demo.locale.DemoBundle.getLocaleString;
import datechooser.beans.*;
import datechooser.demo.Demo;
import java.awt.*;
import java.util.Locale;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
/**
 *
 * @author  Vadik
 */
public class Show1Components extends DemoPanel {
    
    
    public Show1Components() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        calendarDialog = new datechooser.beans.DateChooserDialog();
        showDate = new javax.swing.JLabel();
        demonstratePane = new javax.swing.JPanel();
        ComponentsPane = new javax.swing.JPanel();
        ComboMultyPane = new javax.swing.JPanel();
        calendarComboMulty = new datechooser.beans.DateChooserCombo();
        calendarComboMulty.setLocale(Locale.getDefault());
        ComboSinglePane = new javax.swing.JPanel();
        calendarComboSingle = new datechooser.beans.DateChooserCombo();
        calendarComboSingle.setLocale(Locale.getDefault());
        DialogPane = new javax.swing.JPanel();
        bShowDialog = new javax.swing.JButton();
        dateChooserPanel = new datechooser.beans.DateChooserPanel();

        calendarDialog.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                onDialogCommit(evt);
            }
        });

        setLayout(new java.awt.BorderLayout());

        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        showDate.setFont(new java.awt.Font("Tahoma", 1, 14));
        showDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        showDate.setText("...");
        showDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 5, 5, 5));
        add(showDate, java.awt.BorderLayout.NORTH);

        demonstratePane.setLayout(new java.awt.GridLayout(1, 2));

        ComponentsPane.setLayout(new java.awt.GridLayout(3, 1));

        ComboMultyPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        calendarComboMulty.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                onMultyCommit(evt);
            }
        });

        ComboMultyPane.add(calendarComboMulty);

        ComponentsPane.add(ComboMultyPane);

        ComboSinglePane.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        calendarComboSingle.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);
        calendarComboSingle.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                onSingleCommit(evt);
            }
        });

        ComboSinglePane.add(calendarComboSingle);

        ComponentsPane.add(ComboSinglePane);

        DialogPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        bShowDialog.setText("jButton1");
        bShowDialog.setText(getLocaleString("ShowDialog"));
        bShowDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onShowDialog(evt);
            }
        });

        DialogPane.add(bShowDialog);

        ComponentsPane.add(DialogPane);

        demonstratePane.add(ComponentsPane);

        dateChooserPanel.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                onPaneChange(evt);
            }
        });

        demonstratePane.add(dateChooserPanel);

        add(demonstratePane, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents

    private void onDialogCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_onDialogCommit
        show(calendarDialog.getSelectedPeriodSet().toString());
    }//GEN-LAST:event_onDialogCommit

    private void onSingleCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_onSingleCommit
        show(calendarComboSingle.getSelectedPeriodSet().toString());
    }//GEN-LAST:event_onSingleCommit

    private void onMultyCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_onMultyCommit
        show(calendarComboMulty.getSelectedPeriodSet().toString());
    }//GEN-LAST:event_onMultyCommit

    private void onShowDialog(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onShowDialog
        calendarDialog.showDialog(null, true);
    }//GEN-LAST:event_onShowDialog
                    
    private void onPaneChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_onPaneChange
        show(dateChooserPanel.getSelectedPeriodSet().toString());
    }//GEN-LAST:event_onPaneChange

   private void show(String text) {
        showDate.setText(text);
    }

    public int getStepCount() {
        return 5;
    }

    public String getText() {
        return getLocaleString("sayPane1_" + (getCurrentStep() + 1));
    }

    public void play() {
        if (!Demo.isCreated()) return;
        int step = getCurrentStep();
        Demo.MyDemoPanel demo = Demo.getInstance().getDrawPanel();
        demo.setVisible(true);
        switch (step) {
            case 1:
                demo.setTarget(dateChooserPanel);
                break;
            case 2:
                demo.setTarget(calendarComboMulty);
                break;
            case 3:
                demo.setTarget(calendarComboSingle);
                break;
            case 4:
                demo.setTarget(bShowDialog);
                break;
            case 5:
                demo.setTarget(dateChooserPanel);
                break;
            default:
                demo.setTarget(null);
                
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ComboMultyPane;
    private javax.swing.JPanel ComboSinglePane;
    private javax.swing.JPanel ComponentsPane;
    private javax.swing.JPanel DialogPane;
    private javax.swing.JButton bShowDialog;
    private datechooser.beans.DateChooserCombo calendarComboMulty;
    private datechooser.beans.DateChooserCombo calendarComboSingle;
    private datechooser.beans.DateChooserDialog calendarDialog;
    private datechooser.beans.DateChooserPanel dateChooserPanel;
    private javax.swing.JPanel demonstratePane;
    private javax.swing.JLabel showDate;
    // End of variables declaration//GEN-END:variables
    
}
