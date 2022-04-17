import java.util.ArrayList;

public class BasicParticle extends AbstractParticle {
    BasicParticle(double x, double y, double speed, double angle) {
        super(x, y, speed, angle);
    }
    public void emission(int toEmit, ArrayList<AbstractParticle> particles){
    	// just emit 40 per frame for now
    	for (int i = 0; i < 40 && toEmit > 0; i++){
    		particles.add(new BasicParticle(
                    0,
                    0,
                    100 + (Math.random() * 200),
                    Math.random() * 2 * Math.PI
                ));
                toEmit--;
    	}
    }
    public void simulation(ParticleCanvas renderer, double timeStep, ArrayList<AbstractParticle> particles){
    	ArrayList<AbstractParticle> toRemove = new ArrayList<>();
        for (AbstractParticle particle : particles) {
            double dist = particle.speed*timeStep;
            //System.out.println(dist);
            particle.x += dist*Math.cos(particle.angle);
            particle.y += dist*Math.sin(particle.angle);
            if (!renderer.fitsOnScreen(particle)) {
                toRemove.add(particle);
            }
        }
        
        for (AbstractParticle particle : toRemove) {
            particles.remove(particle);
        }
    }
}
