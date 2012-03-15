package micro;

import org.deuce.Atomic;
import org.deuce.transaction.Context;

public class MicroBench95 extends MicroB {

	// micro-benchmark 95:

	static int SIZE = 1000;
	private static String staticString = "";
	private Point p = new Point(0, 0);
	private final int counterField = 0;
	private final String stringField = "";
	private final double random;

	public MicroBench95() {
		random = Math.random();
	}

	@Override
	protected double atomicAction() {
		ffa1();
		ffa2();
		ffa3();
		ffa4();
		ffa5();
		
		return 0;
	}

	@Atomic
	private void ffa5() {
		for (int i = 0; i < 5; ++i) {
			ImmutablePoint pp = new ImmutablePoint(i, i);
		}
		p.x += 1;
	}

	@Atomic
	private void ffa4() {
		for (int i = 0; i < 5; ++i) {
			p.x += 1;
		}
	}

	@Atomic
	private void ffa3() {
		if (random > 0.6)
			pureMethod1();
		else
			pureMethod2();
		p.x += 1;
		pureMethod1();
		p.x += 2;
	}

	@Atomic
	private void ffa2() {
		if (random > 0.5)
			p.x += 2;	// possible init point
		p.y += 3;	// recurring init point
	}

	@Atomic
	private void ffa1() {
		pureMethod();
		impureMethod();	// init point
	}

	private void impureMethod() {
		p.x += 1;
	}

	private ImmutablePoint pureMethod() {
		return new ImmutablePoint(1.2, 2.3);
	}

	private ImmutablePoint pureMethod1() {
		return new ImmutablePoint(11.2, 21.3);
	}

	private ImmutablePoint pureMethod2() {
		return new ImmutablePoint(12.2, 22.3);
	}
	
	void stam(Context context)
	{
		boolean isInited = false;
		
		if (!isInited)
		{
			isInited = true;
			context.init(0);
		}
	}
	
}