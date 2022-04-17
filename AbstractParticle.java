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
}
