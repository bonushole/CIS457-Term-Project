import java.util.ArrayList;

public class WaterParticle extends AbstractParticle {
   
    WaterParticle(double x, double y, double speed, double angle) {
        super(x, y, speed, angle);
    }
    
    public void emission(int toEmit, ArrayList<AbstractParticle> particles){
    	// just emit 40 per frame for now
    	for (int i = 0; i < 40 && toEmit > 0; i++){
    		particles.add(new WaterParticle(
                    -90,
                    0,
                    100 + (Math.random() * 200),
                    (Math.random() * 2 * Math.PI) % 5
                ));
                toEmit--;
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