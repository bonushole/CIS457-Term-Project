import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Application extends Frame {
   static AbstractParticle selected;
   public static void main(String[] args) {
      // explicitly declared array of all AbstractParticle implementations which matches to a list of strings in the same order.
      // probably the worst way to do this?
      AbstractParticle basic = new BasicParticle(0.0, 0.0, 0.0, 0.0, Color.black);
      AbstractParticle fire = new FireParticle(0.0, 0.0, 0.0, 0.0, Color.orange);
      AbstractParticle water = new WaterParticle(0.0, 0.0, 0.0, 0.0, Color.blue);
      AbstractParticle ring = new RingParticle(0.0, 0.0, 0.0, 0.0, Color.black);
      AbstractParticle gravity = new GravityParticle(0.0, 0.0, 0.0, 0.0, Color.black);
      AbstractParticle tbeam = new TractorBeamParticle(0.0, 0.0, 0.0, 0.0, Color.green);
      AbstractParticle[] types = {basic, fire, water, ring, gravity, tbeam};
      String[] typeNames = {"basic", "fire", "water", "ring", "gravity", "tractor-beam"};
      // default value avoids errors
      selected = types[0]; 
      if (args.length > 0) {
         for (int i = 0; i < 6; i++){
            if (args[0].equals(typeNames[i])){
               System.out.println("found it!");
               selected = types[i];
            }
         }
      }
      new Application();   
   }

   ParticleDriver driver;
   
   Application() {
      
      DisplayWindow canvas = new DisplayWindow();
      System.out.println("aeiou " + selected);
      driver = new ParticleDriver(canvas, selected);
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
