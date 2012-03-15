package micro;

import org.deuce.Atomic;


public class MicroBench90 extends MicroB {

	// micro-benchmark 90:
	// Guy's micro benchmark
		
	static int SIZE = 1000; 
	private int x;
	private int[] sum = new int[SIZE];
	private int threadID;
	

	public MicroBench90() {
		x = 5;
		threadID = (int)Thread.currentThread().getId();
	}
	
	@Override
	@Atomic
	protected double atomicAction() {
		Counter counter = new Counter(); // thread escape
		counter.x = x;					 //  write to thread escape + read of "final"
		counter.sum = sum[threadID%SIZE]; // write to thread escape + read with no write before + read of "final
		
		sum[threadID%SIZE] += x; // read of "final" + write with no read after
		
		int result = counter.x + counter.sum;  //  read of thread escape
		int multi = multiply(result); // re-scope
		return (double)multi;
	}

	private int multiply(int result) {
		return result * 10;
	}

}
