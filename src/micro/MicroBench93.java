package micro;

import org.deuce.Atomic;

public class MicroBench93 extends MicroB {

	// micro-benchmark 93:

	static int SIZE = 1000;
	private final int x;
	private final int[] sum = new int[SIZE];
	private final int threadID;
	private final int sharedCounter = 0;
	private final Point p;

	public MicroBench93() {
		x = 5;
		threadID = (int) Thread.currentThread().getId();
		p = new Point(111.21, 222.11);

	}

	@Override
	protected double atomicAction() {
		int counter = 0;
		counter += baseRead();
		counter += baseWrite();
		counter += recursiveRead();
		counter += readerButNotOnly();
		counter += writerAccessBad();

		return counter;
	}

	@Atomic
	private int writerAccessBad() {
		for (int i = 0; i < sum.length; ++i) {
			sum[i] = threadID % i;
		}
		return baseReadAccessedBadly();
	}

	@Atomic
	private int readerButNotOnly() {
		return baseReadAccessedBadly();
	}

	@Atomic
	private int recursiveRead() {
		return baseRead0() + sum[0];

	}

	@Atomic
	private int baseWrite() {
		for (int i = 0; i < sum.length; ++i) {
			sum[i] = threadID % i;
		}
		return 0;
	}

	@Atomic
	private int baseRead() {
		int counter = 0;
		for (int i = 0; i < sum.length; ++i)
			counter += sum[i];
		return counter;
	}

	@Atomic
	private int baseReadAccessedBadly() {
		int counter = 0;
		for (int i = 0; i < sum.length; ++i)
			counter += sum[i];
		return counter;
	}

	private void baseWrite0() {
		for (int i = 0; i < sum.length; ++i) {
			sum[i] = threadID % i;
		}
	}

	private int baseRead0() {
		int counter = 0;
		for (int i = 0; i < sum.length; ++i)
			counter += sum[i];
		return counter;
	}
}
