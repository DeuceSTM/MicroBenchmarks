package micro;

import org.deuce.Atomic;

public class MicroBench5 extends MicroB {

	// micro-benchmark 3:
	// last field activity
		
	private double localCounter = 0;
	public static int sharedCounter = 0;
	Point p = new Point(2.5, 4.3);
	

	public MicroBench5() {
		// prevent the shared counter from becoming immutable
		sharedCounter = 1;
	}
	
	@Override
	@Atomic
	protected double atomicAction() {
		double x = p.x;
		double y = p.y;
		sharedCounter++;
		
		double z = 0;
		for (int i=0; i<1000; ++i) 
		{
			// read from the new object
			z  += (x+y)*i;
		}
		
		// do something with the read values
		return z;
	}

	public double getLocalCounter() { return localCounter; }
}
