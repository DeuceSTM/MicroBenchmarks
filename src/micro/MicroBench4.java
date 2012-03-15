package micro;

import org.deuce.Atomic;

public class MicroBench4 extends MicroB {

	// micro-benchmark 3:
	// read-only in transaction

	private int counter = 0;
	private final Point p = new Point(2.5, 4.3);

	@Override
	@Atomic
	protected double atomicAction() {
		// read of the p's fields can be optimized.
		double z = 0;
		for (int i = 0; i < 1000; ++i) {
			// read from the new object
			z += (p.x + p.y) * i;
		}

		counter += z;

		return z;
	}

	public void setP(double x, double y) {
		p.x = x;
		p.y = y;
	}
}
