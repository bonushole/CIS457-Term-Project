abstract class AbstractParticle{

	private int speed;
	private int[2] range;
	private int[6] orientations;
	
	abstract void setSpeed();
	abstract int getSpeed();
	abstract void setRange();
	abstract int[] getRange();
	abstract void setOrientations();
	abstract int[] getOrientations();
	
	abstract void emission();
	abstract void simulation();
	abstract void display();

}
