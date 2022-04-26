import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.BufferedImage;

class DisplayWindow extends Canvas {
    public BufferedImage image;
    
    public DisplayWindow() {
        setSize(500, 500);
    }
    
    public void paint(Graphics g) {
        if (image != null) {
            g.drawImage(image, 0, 0, null);
        }
    }
}
