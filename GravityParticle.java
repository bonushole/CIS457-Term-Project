import java.util.ArrayList;
import java.awt.Color;

public class GravityParticle extends AbstractParticle {

	int iter = 0;
	
    GravityParticle(double x, double y, double speed, double angle, Color color) {
        super(x, y, speed, angle, color);
    }
    public void emission(ArrayList<AbstractParticle> particles){
    	// just emit 40 per frame for now
    	for (int i = 0; i < emissionRate; i++){
    		particles.add(new GravityParticle(
                    0.0,
                    -100,
                    10 + ((Math.random() + speed) * 100),
                    Math.random() * 2 * Math.PI,
                    Color.black
                ));
    	}
    }
    public boolean simulation(ParticleCanvas renderer, double timeStep, ArrayList<AbstractParticle> particles){
    
        double dist = speed*timeStep;
        //System.out.println(dist);
        x += dist*Math.cos(angle);
        y += (dist*Math.sin(angle) + 1*(iter/4));
        if (!renderer.fitsOnScreen(this)) {
            return true;
        }
        iter++;
        return false;
    }
}
