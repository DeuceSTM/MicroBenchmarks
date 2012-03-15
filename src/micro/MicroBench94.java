package micro;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.deuce.Atomic;

public class MicroBench94 extends MicroB {

	// micro-benchmark 94:

	static int SIZE = 1000;
	private static String staticString = "";
	private Point p;
	private int counterField = 0;
	private String stringField = "";

	@Override
	protected double atomicAction() {
		checkSameStmt();
		//		checkSameLocal1();
		writeThing();
		loops(1, 2);
		staticLoop();
		manyHeads();
		useHash();
		codeMotion1();
		return 0.0;
	}

	@Atomic
	private void codeMotion1() {
		int x = 5;
		int y = 7;
		int counter = 0;
		for (int i = 0; i < x * y; ++i) {
			counter++;
		}
		counterField += counter;
	}

	@Atomic
	private void useHash() {
		HashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("Arie", "1");
		map.put("Arie1", "2");
		map.put("Arie2", "3");

		stringField += map.get("Arie");
	}

	@Atomic
	private static void manyHeads() {
		try {
			FileReader fileReader1 = new FileReader("First try");
		} catch (FileNotFoundException e) {
			try {
				FileReader fileReader2 = new FileReader("second try");
			} catch (FileNotFoundException e1) {
				staticString += e1.getLocalizedMessage();
			}
		}
	}

	@Atomic
	private static void staticLoop() {
		// tailless
		while (Math.random() < 0.1)
			staticString += "looping";
	}

	@Atomic
	private void loops(int i, int k) {
		while (i < 0) {
			stringField += i;
		}
		while (k < 0) {
			stringField += k;
		}
	}

	@Atomic
	private double writeThing() {
		p = new Point(22, 333);
		//		if (Math.random() > 0.5) {
		// read after write
		//		p.x = 50;
		//		p.y = p.x;
		//		return 50.1;
		//		} else {
		// no read after write
		p.x = 60;
		p.y = p.y;
		return 50.2;
		//		}
	}

	/*	@Atomic
		private void checkSameLocal1() {
			MicroBench94 mb1 = new MicroBench94();
			mb1.p = new Point(0.2, 0.4);
			mb1.p.x = 4;

			MicroBench94 mb2 = new MicroBench94();
			mb2.p = new Point(0.6, 0.7);
			mb2.p.x = 5;

			// are the "p.x" same?
		}*/

	@Atomic
	private void checkSameStmt() {
		this.p = new Point(0.1, 0.2);
		double k1 = p.x;
		if (k1 > Math.random())
			k1++;
		k1 = p.x; // (1)
		if (k1 > Math.random())
			k1++;
		k1 = p.x; // (2)
	}
}
