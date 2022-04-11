import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Application extends Frame {
   public static void main(String[] args) {new Application();   }

   ParticleDriver driver;
   
   Application() {
      ParticleCanvas canvas = new ParticleCanvas();
      driver = new ParticleDriver(canvas);
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {System.exit(0);}
      });
      setSize(500, 500);
      add("Center", canvas);
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
      setVisible(true);
      driver.execute();
   }
}
