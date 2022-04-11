import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;

public class ParticleDriver extends SwingWorker<Integer, Integer> {
    ParticleCanvas renderer;
    ArrayList<BasicParticle> particles;
    
    public ParticleDriver (ParticleCanvas renderer) {
        particles = new ArrayList<>();
        this.renderer = renderer;
    }
    
    protected void process(List<Integer> chunks) {
        renderer.setParticles(particles);
        renderer.repaint();
    }
    
    // Using a SwingWorker should prevent the gui thread from
    // locking up.
    public Integer doInBackground() {
        // TODO: Abstract out simulation and emission from this loop.
        int toEmit = 800;
        
        // 30 FPS
        double timeStep = (1.0d)/(30.0d);
        long timeStepMillis = (long)(timeStep * 1000);
        //System.out.println(timeStepMillis);
        long lastFrame = -1;
        System.out.println("ummmm");
        int frame = 0;
        
        
        
        while (toEmit > 0 || particles.size() > 0) {
            //System.out.print(".");
            
            ArrayList<BasicParticle> toRemove = new ArrayList<>();
            for (BasicParticle particle : particles) {
                double dist = particle.speed*timeStep;
                //System.out.println(dist);
                particle.x += dist*Math.cos(particle.angle);
                particle.y += dist*Math.sin(particle.angle);
                if (!renderer.fitsOnScreen(particle)) {
                    toRemove.add(particle);
                }
            }
            
            for (BasicParticle particle : toRemove) {
                particles.remove(particle);
            }
        
            // just emit 40 per frame for now
            for (int i = 0; i < 40 && toEmit > 0; i++) {
                particles.add(new BasicParticle(
                    0,
                    0,
                    100 + (Math.random() * 200),
                    Math.random() * 2 * Math.PI
                ));
                toEmit--;
            }
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
