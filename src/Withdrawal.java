
import java.io.IOException;
import java.util.Random;

public class Withdrawal implements Runnable{
	private static int MAX_WITHDRAWAL = 99;
	private static Random generator = new Random();
	private static Random sleepTime = new Random();
	private ABankAccount sharedLocation;
	String threadName;
	
	public Withdrawal(ABankAccount shared, String name) {
		sharedLocation = shared;
		threadName = name;
	}
	
	public void run() {
//		System.out.println("Hi! My name is " + threadName);
		sharedLocation.withdrawal(generator.nextInt(MAX_WITHDRAWAL) + 1, threadName);
	}
}
