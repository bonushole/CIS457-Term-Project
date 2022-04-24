import java.util.ArrayList;
import java.awt.Color;

public abstract class AbstractParticle {
    double x;
    double y;
    // in pixels per second
    double speed;
    double angle;
    Color color;
    public AbstractParticle(double x, double y, double speed, double angle, Color color) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.angle = angle;
        this.color = color;
    }
    
    abstract void emission(int toEmit, ArrayList<AbstractParticle> particles);
    abstract boolean simulation(ParticleCanvas renderer, double timeStep, ArrayList<AbstractParticle> particles);
}
