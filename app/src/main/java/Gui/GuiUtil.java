package Gui;

import javax.swing.*;
import java.awt.*;

public class GuiUtil {

    static final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    static final GraphicsDevice gd = ge.getDefaultScreenDevice();
    static final GraphicsConfiguration gc = gd.getDefaultConfiguration();
    static final Rectangle bounds = gc.getBounds();

    public static void showFullScreen(JFrame frame) {
        frame.setSize(bounds.width, bounds.height);
    }

    public static void showOnMiddleScreen(WindowConstants w) {
        if (w instanceof JFrame ) {
            JFrame frame = (JFrame) w;
            int x = bounds.x / 2 + frame.getWidth() / 2,
                    y = bounds.y / 2 + frame.getHeight() / 2;
            frame.setLocation(x, y);
        } else if (w instanceof JDialog) {
            JDialog dialog = (JDialog) w;
            int x = bounds.x / 2 + dialog.getWidth() / 2,
                    y = bounds.y / 2 + dialog.getHeight() / 2;
            dialog.setLocation(x, y);
        }
    }
}
