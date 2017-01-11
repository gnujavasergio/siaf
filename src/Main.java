
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import jdbc.Conexion;
import org.jc.UISwing;
import view.seguridad.Autentificarse;

/**
 * Main
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 */
public class Main {

    private Conexion conexion;
    private Autentificarse autentificarse;
    public Main() {
        conexion = new Conexion();
        if (conexion.conectarBD("vsiaf", "sa", "", "localhost", Conexion.HSQLDB)) {
            System.out.println("Se conecto a la base de datos hsqldb");
            try {
                UIManager.setLookAndFeel(UISwing.PLAF_AERO);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            JFrame frame =  new JFrame();
            autentificarse = new Autentificarse(frame, true, conexion);
            autentificarse.setLocationRelativeTo(new JFrame());
            autentificarse.setAlwaysOnTop(true);            
            autentificarse.setVisible(true);             
        } else {
            System.out.println("No se pudo conectar a la base de datos postgres");
        }
    }

    public static void main(String arg[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main main = new Main();
            }
        });
    }    
}
