package micro;

import java.util.concurrent.atomic.AtomicLong;



public class MicroThread extends java.lang.Thread {

	public volatile int phase = 0;
	private MicroB microB;
	private long p_steps = 0;

	public MicroThread(MicroB microB) {
		this.microB = microB;
	}
	
	@Override
	public void run() {
		while (phase < 2)
		{
			microB.atomicAction();
			if (phase > 0)
				p_steps++;
		}
	}


	public long getSteps() {
		AtomicLong v_steps = new AtomicLong(p_steps);		
		return v_steps.get();
	}
	

}
