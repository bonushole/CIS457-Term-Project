import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Application extends Frame {
    public static void main(String[] args) {
        new Application(args.length > 0? args[0] : "");   
    }

    ParticleDriver driver;
    JComboBox<String> typeBox;
    final static String[] typeNames = {"basic", "fire", "water", "ring", "gravity", "tractor-beam"};
    DisplayWindow canvas;

    Application(String particleName) {
        canvas = new DisplayWindow();
        driver = new ParticleDriver(canvas, fromName(particleName), this::spawnNewDriver);
        addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        setSize(700, 500);
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(canvas);
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
        typeBox = new JComboBox<>(typeNames);
        typeBox.addActionListener(e -> {
            driver.cancel(true);
        });
        controlPanel.add(typeBox);
        add(controlPanel);
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        setVisible(true);
        driver.execute();
    }
    
    private void spawnNewDriver() {
        AbstractParticle particle = fromName(typeBox.getSelectedItem().toString());
        driver = new ParticleDriver(canvas, particle, this::spawnNewDriver);
        driver.execute();
    }

    private AbstractParticle fromName(String name) {
        // explicitly declared array of all AbstractParticle implementations which matches to a list of strings in the same order.
        // probably the worst way to do this?
        AbstractParticle basic = new BasicParticle(0.0, 0.0, 0.0, 0.0, Color.black);
        AbstractParticle fire = new FireParticle(0.0, 0.0, 0.0, 0.0, Color.orange);
        AbstractParticle water = new WaterParticle(0.0, 0.0, 0.0, 0.0, Color.blue);
        AbstractParticle ring = new RingParticle(0.0, 0.0, 0.0, 0.0, Color.black);
        AbstractParticle gravity = new GravityParticle(0.0, 0.0, 0.0, 0.0, Color.black);
        AbstractParticle tbeam = new TractorBeamParticle(0.0, 0.0, 0.0, 0.0, Color.green);
        AbstractParticle[] types = {basic, fire, water, ring, gravity, tbeam};
        
        
        for (int i = 0; i < 6; i++){
            if (name.equals(typeNames[i])){
               System.out.println("found it!");
               return types[i];
            }
        }
        return basic;
    }
}
