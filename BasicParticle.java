import java.util.ArrayList;

public class BasicParticle extends AbstractParticle {
    BasicParticle(double x, double y, double speed, double angle) {
        super(x, y, speed, angle);
    }
    public static void emission(int toEmit, ArrayList<BasicParticle> particles){
    	// just emit 40 per frame for no
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
    public static void simulation(ParticleCanvas renderer, double timeStep, ArrayList<BasicParticle> particles){
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
    }
}
