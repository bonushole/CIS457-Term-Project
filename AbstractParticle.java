import java.util.ArrayList;

public abstract class AbstractParticle {
    double x;
    double y;
    // in pixels per second
    double speed;
    double angle;
    public AbstractParticle(double x, double y, double speed, double angle) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.angle = angle;
    }
    
    abstract void emission(int toEmit, ArrayList<AbstractParticle> particles);
    abstract boolean simulation(ParticleCanvas renderer, double timeStep, ArrayList<AbstractParticle> particles);
}
