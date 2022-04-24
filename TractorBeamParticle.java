import java.util.ArrayList;
import java.awt.Color;

public class TractorBeamParticle extends AbstractParticle {
    TractorBeamParticle(double x, double y, double speed, double angle, Color color) {
        super(x, y, speed, angle, color);
    }
    public void emission(int toEmit, ArrayList<AbstractParticle> particles){
    	// just emit 40 per frame for now
    	for (int i = 0; i < 40 && toEmit > 0; i++){
    		particles.add(new TractorBeamParticle(
                    0,
                    -100,
                    100,
                    Math.random() * 2 * Math.PI,
                    Color.green
                ));
                toEmit--;
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
