package micro;

import org.deuce.Atomic;

public class MicroBench1 extends MicroB {

	// micro-benchmark 1:
	// reading from immutable fields

	ImmutablePoint p = new ImmutablePoint(2.5, 4.3);

	private int privateCounter = 0;

	@Override
	@Atomic
	protected double atomicAction() {
		double z = 0;
		for (int i = 0; i < 1000; ++i) {
			// read from the immutable object
			z += (p.x + p.y) * i;
		}

		privateCounter += z;

		return z;
	}

}
