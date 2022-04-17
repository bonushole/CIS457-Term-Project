import java.awt.*;
import java.awt.event.*;
import java.util.*;

class ParticleCanvas extends Canvas {
    private ArrayList<AbstractParticle> particles = new ArrayList<>();
    
    public void setParticles(ArrayList<AbstractParticle> particles) {
        this.particles = particles;
    }
    
    public boolean fitsOnScreen(AbstractParticle particle) {
        return Math.abs(particle.x) < (getWidth()/2) && Math.abs(particle.y) < (getHeight()/2);
    }
    
    // Origin is at (0, 0)
    
    private int iX(double x) {
        return getWidth()/2 + ((int)x);
    }
    
    private int iY(double y) {
        return getHeight()/2 + ((int)y);
    }
    
    public void paint(Graphics g) {
        // TODO: add more advanced rendering, maybe make abstract
        //       Renderer class?
        //System.out.println("painting");
        g.setColor(Color.blue);
        for (AbstractParticle particle : particles) {
            //System.out.println(iX(particle.x)+ "  " +iY(particle.y));
            g.drawLine(iX(particle.x), iY(particle.y), iX(particle.x), iY(particle.y));
        }
    }
}
