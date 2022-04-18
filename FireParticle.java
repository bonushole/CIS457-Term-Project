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
    
    public boolean simulation(ParticleCanvas renderer, double timeStep, ArrayList<AbstractParticle> particles){
        double dist = speed*timeStep;
        x += dist*Math.cos(angle);
        y += dist*Math.sin(angle);
        if (particleAge > 1.0) {
            
            return true;
        }
        particleAge += timeStep;
        return false;
    }
}
