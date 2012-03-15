package micro;

import org.deuce.Atomic;

public class MicroBench2 extends MicroB {

	// micro-benchmark 2:
	// creating a new object

	private int privateCounter = 0;

	@Override
	@Atomic
	protected double atomicAction() {
		// create a new object
		Point p = new Point(2.5, 4.3);
		p.x = 500.2;
		p.y = 600.1;

		double z = 0;
		for (int i = 0; i < 1000; ++i) {
			// read from the new object
			z += (p.x + p.y) * i;
		}

		privateCounter += z;

		return z;
	}
}
