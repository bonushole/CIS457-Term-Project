import java.util.ArrayList;
import java.awt.Color;

public class WaterParticle extends AbstractParticle {
   
    WaterParticle(double x, double y, double speed, double angle, Color color) {
        super(x, y, speed, angle, color);
    }
    
    public void emission(ArrayList<AbstractParticle> particles){
    	// just emit 40 per frame for now
    	for (int i = 0; i < emissionRate; i++){
    		particles.add(new WaterParticle(
                    -90.0,
                    0,
                    10 + ((Math.random() + speed) * 100),
                    (Math.random() * 2 * Math.PI) % 5,
                    Color.blue
                ));
    	}
    }
    
    public boolean simulation(ParticleCanvas renderer, double timeStep, ArrayList<AbstractParticle> particles){
        double dist = speed*timeStep;
        //System.out.println(dist);
        x += dist;
        y += -Math.log10(4*dist*Math.cos(angle));
        if (!renderer.fitsOnScreen(this)) {
            return true;
        }
        
        return false;
    }
}
