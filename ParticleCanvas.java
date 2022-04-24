import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.BufferedImage;

class ParticleCanvas extends Canvas {
    private int width;
    private int height;
    
    ParticleCanvas(int width, int height) {
        this.width = width;
        this.height = height;
        System.out.println("width " + width + " height " + height);
    }
    
    public boolean fitsOnScreen(AbstractParticle particle) {
        return Math.abs(particle.x) < (width/2) && Math.abs(particle.y) < (height/2);
    }
    
    // Origin is at (0, 0)
    
    private int iX(double x) {
        return width/2 + ((int)x);
    }
    
    private int iY(double y) {
        return height/2 + ((int)y);
    }
    
    public BufferedImage render(Collection<AbstractParticle> particles) {
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = result.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        for (AbstractParticle particle : particles) {
        	g.setColor(particle.color); // this supports changing effect colors on the fly
            g.drawLine(iX(particle.x), iY(particle.y), iX(particle.x), iY(particle.y));
        }
        g.dispose();
        return result;
    }
}
