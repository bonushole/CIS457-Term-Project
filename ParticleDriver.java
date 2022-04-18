import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;

public class ParticleDriver extends SwingWorker<Integer, Integer> {
    ParticleCanvas renderer;
    AbstractParticle particleClass;
    ArrayList<AbstractParticle> particles;
    
    public ParticleDriver (ParticleCanvas renderer, AbstractParticle particle) {
        particles = new ArrayList<>();
        this.renderer = renderer;
        particleClass = particle;
        System.out.println(particle + "," + particleClass);
    }
    
    protected void process(List<Integer> chunks) {
        renderer.setParticles(particles);
        renderer.repaint();
    }
    
    // Using a SwingWorker should prevent the gui thread from
    // locking up.
    public Integer doInBackground() {
        int toEmit = 800; 
        
        // 30 FPS
        double timeStep = (1.0d)/(30.0d);
        long timeStepMillis = (long)(timeStep * 1000);
        //System.out.println(timeStepMillis);
        long lastFrame = -1;
        System.out.println("ummmm");
        int frame = 0;
        
        
        
        while (toEmit > 0 || particles.size() > 0) {
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
            publish(frame++);
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
}
