package micro;

import java.util.ArrayList;
import java.util.List;

public class MicroMain {

	public static void main(String[] args) {
		MicroMain microMain = new MicroMain();
		microMain.test(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
	}

	public MicroMain() {
	}

	public long test(int type, int thread_count) {
		List<MicroThread> invokeAddThreads = invokeAddThreads(type,
				thread_count);
		System.out.println("warming up... " + thread_count
				+ " threads, microbenchmark: " + type);
		sleep(10000);

		advanceThreadsPhase(invokeAddThreads);
		System.out.println("Running...");
		sleep(10000);

		advanceThreadsPhase(invokeAddThreads);

		long steps = joinThreads(invokeAddThreads);

		System.out.println("All threads done. Total steps: " + steps);
		return steps;
	}

	private void advanceThreadsPhase(List<MicroThread> invokeAddThreads) {
		for (MicroThread thread : invokeAddThreads) {
			thread.phase++;
		}

	}

	private void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private long joinThreads(List<MicroThread> invokeAddThreads) {
		long steps = 0;
		for (MicroThread thread : invokeAddThreads) {
			try {
				thread.join();
				steps += thread.getSteps();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return steps;
	}

	private List<MicroThread> invokeAddThreads(int type, int thread_count) {
		ArrayList<MicroThread> threads = new ArrayList<MicroThread>();

		for (int i = 0; i < thread_count; ++i) {
			MicroThread t = null;
			if (type == 0)
				t = decideAccordingToParameter((i % 5) + 1);
			else
				t = decideAccordingToParameter(type);

			threads.add(t);
			t.start();
		}
		return threads;
	}

	private MicroThread decideAccordingToParameter(int type) {
		MicroThread t = null;
		if (type == 1)
			t = new MicroThread(new MicroBench1());
		if (type == 2)
			t = new MicroThread(new MicroBench2());
		if (type == 3)
			t = new MicroThread(new MicroBench3());
		if (type == 4)
			t = new MicroThread(new MicroBench4());
		if (type == 5)
			t = new MicroThread(new MicroBench5());
		if (type == 90)
			t = new MicroThread(new MicroBench90());
		if (type == 91)
			t = new MicroThread(new MicroBench91());
		if (type == 92)
			t = new MicroThread(new MicroBench92());
		if (type == 93)
			t = new MicroThread(new MicroBench93());

		return t;
	}

}
