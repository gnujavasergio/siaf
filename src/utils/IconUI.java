package utils;

import java.awt.Dimension;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

/**
 * IconUI
 *
 * @author Sergio Antonio Ochoa Martinez - gnu.java.sergio@gmail.com
 * @version 0.1
 */
public class IconUI {

    /**
     *
     * @param resource
     * @param width
     * @param height
     * @return
     */
    public static ResizableIcon getResizableIcon(final String resource, int width, int height) {
        final ResizableIcon resizeIcon = ImageWrapperResizableIcon.getIcon(
                ResourceSet.IMAGE.get(resource), new Dimension(width, height));
        return resizeIcon;
    }
}
