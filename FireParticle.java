import java.util.ArrayList;

public class FireParticle extends AbstractParticle {

	double particleAge = 0;

    FireParticle(double x, double y, double speed, double angle) {
        super(x, y, speed, angle);
    }
    public void emission(int toEmit, ArrayList<AbstractParticle> particles){
    	// just emit 40 per frame for now
    	for (int i = 0; i < 40 && toEmit > 0; i++){
    		particles.add(new FireParticle(
                    0,
                    0,
                    25 + (Math.random() * 100),
                    (Math.random() * 2 * Math.PI) % 45
                ));
                toEmit--;
    	}
    }
    public void simulation(ParticleCanvas renderer, double timeStep, ArrayList<AbstractParticle> particles){
    	ArrayList<AbstractParticle> toRemove = new ArrayList<>();
        for (AbstractParticle particle : particles) {
            double dist = particle.speed*timeStep;
            particle.x += dist*Math.cos(particle.angle);
            particle.y += dist*Math.sin(particle.angle);
            if (particleAge > 500) {
                toRemove.add(particle);
            }
            particleAge += timeStep;
        }
        
        for (AbstractParticle particle : toRemove) {
            particles.remove(particle);
        }
    }
}
