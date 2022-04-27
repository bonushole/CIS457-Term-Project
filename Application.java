import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Application extends JFrame {
    public static void main(String[] args) {
        new Application(args.length > 0? args[0] : "");   
    }

    ParticleDriver driver;
    JComboBox<String> typeBox;
    JSlider speedSlider;
    JSlider emissionRateSlider;
    final static String[] typeNames = {"basic", "fire", "water", "ring", "gravity", "tractor-beam"};
    DisplayWindow canvas;

    Application(String particleName) {
        canvas = new DisplayWindow();
        driver = new ParticleDriver(canvas, fromName(particleName), this::spawnNewDriver);
        addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        setSize(700, 500);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        contentPane.add(canvas, BorderLayout.LINE_START);
        
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
        typeBox = new JComboBox<>(typeNames);
        controlPanel.add(typeBox);
        
        speedSlider = new JSlider();
        controlPanel.add(createLabelAndControl("speed", speedSlider));
        
        emissionRateSlider = new JSlider();
        controlPanel.add(createLabelAndControl("emission rate", emissionRateSlider));
        
        controlPanel.add(Box.createVerticalGlue());
        //contentPane.add(Box.createHorizontalGlue());
        contentPane.add(controlPanel, BorderLayout.CENTER);
        
        typeBox.addActionListener(e -> {
            driver.cancel(true);
        });
        speedSlider.addChangeListener(e -> {
            driver.cancel(true);
        });
        emissionRateSlider.addChangeListener(e -> {
            driver.cancel(true);
        });
        setVisible(true);
        revalidate();
        driver.execute();
    }
    
    private JPanel createLabelAndControl(String labelText, Component control) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(new JLabel(labelText));
        panel.add(control);
        return panel;
    }
    
    private void spawnNewDriver() {
        AbstractParticle particle = fromName(typeBox.getSelectedItem().toString());
        particle.speed = ((double)(speedSlider.getValue() - speedSlider.getMinimum()))/((double)((speedSlider.getMaximum() - speedSlider.getMinimum())));
        particle.emissionRate = 5 + emissionRateSlider.getValue();
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
               return types[i];
            }
        }
        return basic;
    }
}
