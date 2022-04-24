import java.util.ArrayList;

public class GravityParticle extends AbstractParticle {

	int iter = 0;
	
    GravityParticle(double x, double y, double speed, double angle) {
        super(x, y, speed, angle);
    }
    public void emission(int toEmit, ArrayList<AbstractParticle> particles){
    	// just emit 40 per frame for now
    	for (int i = 0; i < 40 && toEmit > 0; i++){
    		particles.add(new GravityParticle(
                    0,
                    -100,
                    100 + Math.random()*100,
                    Math.random() * 2 * Math.PI
                ));
                toEmit--;
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