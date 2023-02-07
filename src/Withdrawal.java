
import java.io.IOException;
import java.util.Random;

public class Withdrawal implements Runnable{
	private static int MAX_WITHDRAWAL = 99;
	private static Random generator = new Random();
	private static Random sleepTime = new Random();
	private ABankAccount sharedLocation;
	String threadName;
	
	public Withdrawal(ABankAccount shared, String name) {
		this.sharedLocation = shared;
		this.threadName = name;
	}
	
	public void run() {
//		System.out.println("Hi! My name is " + threadName);
		while(true) {
			int amount = generator.nextInt(MAX_WITHDRAWAL) + 1;
			try {
				sharedLocation.withdrawal(amount, threadName);
				Thread.sleep(sleepTime.nextInt(1500)+1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
