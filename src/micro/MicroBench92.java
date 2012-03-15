package micro;

import org.deuce.Atomic;

public class MicroBench92 extends MicroB {

	// micro-benchmark 91:

	static int SIZE = 1000;
	private final int x;
	private final int[] sum = new int[SIZE];
	private final int threadID;
	private int sharedCounter = 0;
	private final Point p;

	public MicroBench92() {
		x = 5;
		threadID = (int) Thread.currentThread().getId();
		p = new Point(111.21, 222.11);
	}

	@Override
	@Atomic
	protected double atomicAction() {
		double x = p.x;
		double y = p.y;
		sharedCounter++;
		// LastFieldActivity on an invocation
		updateCounter();

		ImmutablePoint pNew = new ImmutablePoint(4.2, 1.2);
		double z = pNew.x + pNew.y;
		

		for (int i = 0; i < 1000; ++i) {
			z += (x + y) * i;
		}

		// do something with the read values
		return z;
	}

	private void updateCounter() {
		sum[threadID & SIZE] = sharedCounter;
	}

}
