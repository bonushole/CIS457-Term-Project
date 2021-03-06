import java.util.ArrayList;
import java.awt.Color;

public class FireParticle extends AbstractParticle {

	double particleAge = 0;

    FireParticle(double x, double y, double speed, double angle, Color color) {
        super(x, y, speed, angle, color);
    }
    
    public void emission(ArrayList<AbstractParticle> particles){
    	// just emit 40 per frame for now
    	for (int i = 0; i < emissionRate; i++){
    		particles.add(new FireParticle(
                    0,
                    50,
                    5 + ((Math.random() + speed) * 100),
                    (Math.random() * 2 * Math.PI),
                    Color.orange
                ));
    	}
    }
    
    public boolean simulation(ParticleCanvas renderer, double timeStep, ArrayList<AbstractParticle> particles){
        double dist = speed*timeStep;
        x += dist*Math.cos(angle);
        y += (dist*Math.sin(angle) - 5*(particleAge));
        if (particleAge > 1.0) {
            
            return true;
        }
        particleAge += timeStep;
        return false;
    }
}
