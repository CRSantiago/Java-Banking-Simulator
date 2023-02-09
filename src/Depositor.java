import java.io.IOException;
import java.util.Random;


public class Depositor implements Runnable{
	
	private static int MAX_DEPOSIT = 500;
	private static Random generator = new Random();
	private static Random sleepTime = new Random();
	private ABankAccount sharedLocation;
	String threadName;
	
	public Depositor(ABankAccount shared, String name) {
		this.sharedLocation = shared;
		this.threadName = name;
	}
	
	@Override
	public void run() {
//		System.out.println("Hi! My name is " + threadName);
//		sharedLocation.deposit(generator.nextInt(MAX_DEPOSIT) + 1, threadName);
		while(true) {
			try {
				sharedLocation.deposit(generator.nextInt(MAX_DEPOSIT) + 1, threadName);
				Thread.sleep(sleepTime.nextInt(3000)+1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
