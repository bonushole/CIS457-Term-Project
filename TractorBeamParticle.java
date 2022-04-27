import java.util.ArrayList;
import java.awt.Color;

public class TractorBeamParticle extends AbstractParticle {
    TractorBeamParticle(double x, double y, double speed, double angle, Color color) {
        super(x, y, speed, angle, color);
    }
    public void emission(ArrayList<AbstractParticle> particles){
    	// just emit 40 per frame for now
    	for (int i = 0; i < emissionRate; i++){
    		particles.add(new TractorBeamParticle(
                    0,
                    -100,
                    10 + (speed * 90),
                    Math.random() * 2 * Math.PI,
                    Color.green
                ));
    	}
    }
    public boolean simulation(ParticleCanvas renderer, double timeStep, ArrayList<AbstractParticle> particles){
    
        double dist = speed*timeStep;
        //System.out.println(dist);
        x += dist*Math.cos(angle);
        y += dist*Math.sin(angle) + 9;
        if (!renderer.fitsOnScreen(this)) {
            return true;
        }
        
        return false;
    }
}
