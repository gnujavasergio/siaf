/*
 * DemoBundle.java
 *
 * Created on 6 Декабрь 2006 г., 22:17
 *
 */

package datechooser.demo.locale;

import java.util.ResourceBundle;

/**
 *
 * @author Vadim
 */
public class DemoBundle {
    
    private static ResourceBundle bundle = null;
    
    public static String getLocaleString(String key) {
        if (bundle == null) {
            bundle = ResourceBundle.getBundle("datechooser/demo/locale/demo");
        }
        return bundle.getString(key);
    }
    
    public static void reset() {
        bundle = null;
    }
    
}
