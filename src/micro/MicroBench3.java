package micro;

import org.deuce.Atomic;

public class MicroBench3 extends MicroB {

	// micro-benchmark 3:
	// thread-local objects

	private static int sharedCounter = 0;
	
	@Override
	@Atomic
	protected double atomicAction() {
		Point p = new Point(2.5, 4.3);
		initializePoint(p);
		
		double z = 0;
		for (int i=0; i<1000; ++i) 
		{
			// read from the new object
			z  += (p.x+p.y)*i;
		}
		
//		sharedCounter += z;
		
		return z;
	}

	private void initializePoint(Point p) {
		// p is thread-local here
		p.x = 500.2;
		p.y = 600.1;
	}
}
