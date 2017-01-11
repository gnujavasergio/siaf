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
import javax.swing.UIManager;
import javax.swing.plaf.PanelUI;
/**
 *
 * @author  Vadik
 */
public class Show3Skins extends DemoPanel {
    
    /** Creates new form ShowComponents */
    public Show3Skins() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        dateChooser = new datechooser.beans.DateChooserPanel();

        setLayout(new java.awt.BorderLayout());

        dateChooser.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        dateChooser.setCalendarPreferredSize(new java.awt.Dimension(450, 180));
        dateChooser.setWeekStyle(datechooser.view.WeekDaysStyle.FULL);
        add(dateChooser, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents

    public int getStepCount() {
        return 4;
    }

    public String getText() {
        return getLocaleString("sayPane3_" + (getCurrentStep() + 1));
    }

    public void play() {
        int step = getCurrentStep();
        switch(step) {
            case 0:
                dateChooser.getAppearancesList().setCurrent("Contrast");
                break;
            case 1:
                dateChooser.getAppearancesList().setCurrent("Light");
                break;
            case 2:
                dateChooser.getAppearancesList().setCurrent("Dali");
                break;
            case 3:
                dateChooser.getAppearancesList().setCurrent("Swing");
                break;
        }
        dateChooser.repaint();
    }
                    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserPanel dateChooser;
    // End of variables declaration//GEN-END:variables
    
}
