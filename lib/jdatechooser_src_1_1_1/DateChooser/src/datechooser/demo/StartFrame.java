/*
 * StartFrame.java
 *
 * Created on 15 ќкт€брь 2006 г., 9:22
 *
 */

package datechooser.demo;

import datechooser.autorun.Logo;
import datechooser.demo.locale.DemoBundle;
import static datechooser.demo.locale.DemoBundle.getLocaleString;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Vadik
 */
public class StartFrame extends JFrame implements ActionListener, ItemListener {
    
//    private static final int WIDTH = 300;
//    private static final int HEIGHT = 200;
    private JPanel control;
    private JComboBox selLocale;
    private JButton startDemo;
    private Demo demo;
    private JLabel caption;
    
    private Locale[] supportsLocales = new Locale[] {
        Locale.ENGLISH, new Locale("ru", "RU")
    };
    
    private String[] localeNames;
    
    public StartFrame() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        prepareLocales();
        initInterface();
        
        setTitle(getLocaleString("Start"));
//        setSize(WIDTH, HEIGHT);
        
        makeCentered();
    }
    
    private void initInterface() {
        JPanel main = new JPanel(new BorderLayout());
        main.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        caption  = new JLabel(getLocaleString("demo_caption"));
        caption.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        caption.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        caption.setFont(new Font("Arial", Font.BOLD, 14));
        main.add(caption, BorderLayout.NORTH);
        
        control = new JPanel(new BorderLayout());
        selLocale  = new JComboBox(localeNames);
        selLocale.setSelectedIndex(Locale.getDefault().equals(supportsLocales[1]) ? 1 : 0);
        selLocale.addItemListener(this);
        startDemo = new JButton(getLocaleString("Start"));
        control.add(selLocale, BorderLayout.NORTH);
        control.add(startDemo, BorderLayout.SOUTH);
        
        JLabel info = Logo.createInfoLabel(false);
        control.add(info, BorderLayout.CENTER);
        
        main.add(control, BorderLayout.CENTER);
        setContentPane(main);
        pack();
        
        startDemo.addActionListener(this);
    }
    
    public void itemStateChanged(ItemEvent e) {
        Locale.setDefault(supportsLocales[selLocale.getSelectedIndex()]);
        DemoBundle.reset();
        updateTextOutput();
    }
    
    public void actionPerformed(ActionEvent e) {
        toWaitMode();
        Thread openDemo = new Thread(new Waiter());
        openDemo.start();
    }
    
    private void toWaitMode() {
        startDemo.removeActionListener(this);
        control.remove(startDemo);
        control.remove(selLocale);
        JProgressBar progress = new JProgressBar();
        progress.setIndeterminate(true);
        control.add(progress, BorderLayout.SOUTH);
        int width = getWidth();
        pack();
        setSize(width, getHeight());
        validate();
    }
    
    private void updateTextOutput() {
        caption.setText(getLocaleString("demo_caption"));
        startDemo.setText(getLocaleString("Start"));
        setTitle(getLocaleString("Start"));
        pack();
        makeCentered();
    }
    
    private void prepareLocales() {
        localeNames = new String[supportsLocales.length];
        for (int i = 0; i < supportsLocales.length; i++) {
            localeNames[i] = supportsLocales[i].getDisplayName(supportsLocales[i]);
        }
    }
    
    private void makeCentered() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        Dimension bounds = getSize();
        if ((bounds.width > screenSize.width) || (bounds.height > screenSize.height)) {
            setLocation(0, 0);
        } else {
            setLocation(
                    (screenSize.width - bounds.width) / 2,
                    (screenSize.height - bounds.height) / 2);
        }
    }
    
    private void close() {
        setVisible(false);
    }

    private class Waiter implements  Runnable {
        public void run() {
            Thread starter = new Thread(new Starter());
            starter.start();
            while (starter.isAlive()) {
                try {Thread.currentThread().sleep(1000);
                } catch (InterruptedException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            demo.setVisible(true);
            close();
        }
    }
    
    private class Starter implements Runnable {
        public void run() {
            demo = Demo.getInstance();
        }        
    }
    
}
