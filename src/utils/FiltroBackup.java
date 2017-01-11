package utils;

import java.io.*;

/**
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 */
public class FiltroBackup extends javax.swing.filechooser.FileFilter {

    private String[] extensions;
    private String description;

    public FiltroBackup(String ext) {
        this(new String[]{ext}, null);
    }//fin del constructor

    public FiltroBackup(String[] exts, String descr) {
        extensions = new String[exts.length];
        for (int i = exts.length - 1; i >= 0; i--) {
            extensions[i] = exts[i].toLowerCase();
        }
        description = (descr == null ? exts[0] + " Archivos" : descr);
    }//fin del metodo FiltroBackup

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        String name = f.getName().toLowerCase();
        for (int i = extensions.length - 1; i >= 0; i--) {
            if (name.endsWith(extensions[i])) {
                return true;
            }
        }//fin for
        return false;
    }//fin del metodo accept

    @Override
    public String getDescription() {
        return description;
    }
}//fin FiltroBackup