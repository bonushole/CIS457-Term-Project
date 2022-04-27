import java.util.ArrayList;
import java.awt.Color;

public abstract class AbstractParticle {
    double x;
    double y;
    // in pixels per second
    double speed;
    double angle;
    int emissionRate = 40;
    Color color;
    public AbstractParticle(double x, double y, double speed, double angle, Color color) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.angle = angle;
        this.color = color;
    }
    
    // TODO should probable have a seperate class called 
    // AbstractParticle emitter or something since we effectively
    // use one instance of a particle class to do emission and use
    // the properties of that instance differently.
    abstract void emission(ArrayList<AbstractParticle> particles);
    abstract boolean simulation(ParticleCanvas renderer, double timeStep, ArrayList<AbstractParticle> particles);
}
