import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;
import java.awt.image.BufferedImage;

public class ParticleDriver extends SwingWorker<Integer, BufferedImage> {
    ParticleCanvas renderer;
    AbstractParticle particleClass;
    ArrayList<AbstractParticle> particles;
    DisplayWindow display;
    Runnable whenFinished;
    
    public ParticleDriver (DisplayWindow display, AbstractParticle particle, Runnable whenFinished) {
        particles = new ArrayList<>();
        this.renderer = new ParticleCanvas(500, 500);
        this.display = display;
        this.whenFinished = whenFinished;
        particleClass = particle;
        System.out.println(particle + "," + particleClass);
    }
    
    protected void process(List<BufferedImage> chunks) {
        display.image = chunks.get(chunks.size() - 1);
        display.repaint();
    }
    
    // Using a SwingWorker should prevent the gui thread from
    // locking up.
    public Integer doInBackground() {
        int toEmit = 2400; 
        
        // 30 FPS
        double timeStep = (1.0d)/(30.0d);
        long timeStepMillis = (long)(timeStep * 1000);
        //System.out.println(timeStepMillis);
        long lastFrame = -1;
        System.out.println("ummmm");
        int frame = 0;
        
        
        
        while (toEmit > 0 || particles.size() > 0) {
            if (isCancelled()) {
                break;
            }
            ArrayList<AbstractParticle> toRemove = new ArrayList<>();
            for (AbstractParticle particle : particles) {
                boolean shouldRemove = particle.simulation(renderer, timeStep, particles);
                if (shouldRemove) {
                    toRemove.add(particle);
                }
            }
            for (AbstractParticle particle : toRemove) {
                particles.remove(particle);
            }
            particleClass.emission(toEmit, particles);
            toEmit -= 40;
            // the property has to change to trigger, idk if I'm doing this right
            BufferedImage rendered = renderer.render(particles);
            publish(rendered);
            long toSleep = timeStepMillis - (System.currentTimeMillis() - lastFrame);
            // we're on a worker thread so we can do this (but should we???)
            if (lastFrame > 0 && toSleep > 0) {
                try {
                    Thread.sleep(toSleep);
                } catch (Exception e) {}
            }
            lastFrame = System.currentTimeMillis();
        }
        return 0;
    }
    
    protected void done() {
        whenFinished.run();
    }
}
