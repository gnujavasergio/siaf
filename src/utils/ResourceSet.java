package utils;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ResourceSet {

    /**
     * A map containing all {@link Icon}s that are needed by this application.
     * The mapping of key and image is read from the file
     * "/data/bibliothek/help/icons/icons.ini".<br>
     * The map is not modifiable.
     */
    public static final Map<String, Icon> ICON;
    public static final Map<String, Image> IMAGE;

    /**
     * iniciamos la Variable estatica ICONS
     */
    static {
        ICON = readApplicationIcon();
        IMAGE = readApplicationImage();
    }

    /**
     * Carga todas las images que necesitamos en nuestra aplicacion
     *
     * @return una imagen de tipo icono
     */
    private static Map<String, Icon> readApplicationIcon() {
        Map<String, Icon> icon = new HashMap<String, Icon>();

        try {
            Properties properties = new Properties();
            InputStream in = openStream("/resources/images/images.ini");
            properties.load(in);
            in.close();

            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                in = openStream("/resources/images/" + entry.getValue());
                icon.put(entry.getKey().toString(), new ImageIcon(ImageIO.read(in)));
                in.close();
            }
        } catch (IOException ex) {
        }

        return Collections.unmodifiableMap(icon);
    }

    /**
     *
     * @return una imagen de tipo IMAGE
     */
    private static Map<String, Image> readApplicationImage() {
        Map<String, Image> image = new HashMap<String, Image>();

        try {
            Properties properties = new Properties();
            InputStream in = openStream("/resources/images/images.ini");
            properties.load(in);
            in.close();

            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                in = openStream("/resources/images/" + entry.getValue());
                image.put(entry.getKey().toString(), ImageIO.read(in));
                in.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return Collections.unmodifiableMap(image);
    }

    /**
     * Gets a stream that reads a file from the same location as the class-files
     * for this application are stored.
     *
     * @param name the path of the file
     * @return the stream that reads <code>name</code>
     * @throws IOException if the path is not valid
     */
    public static InputStream openStream(String name) {
        InputStream in = ResourceSet.class.getResourceAsStream(name);
//		if( in == null )
//			throw new FileNotFoundException( "el archivo no existe " + name );
        return in;
    }

    /**
     * Converts an {@link Icon} in an image of the same size.
     *
     * @param icon the icon to convert
     * @return the image or <code>null</code> if the conversion couldn't be
     * performed
     */
    public static Image toImage(Icon icon) {
        if (icon instanceof ImageIcon) {
            return ((ImageIcon) icon).getImage();
        }
        return null;
    }
}
