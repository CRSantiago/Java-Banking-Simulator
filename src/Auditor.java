import java.io.IOException;
import java.util.Random;

public class Auditor implements Runnable{
	
	private static Random sleepTime = new Random();
	private ABankAccount sharedLocation;
	private int transactionNumberAtLastAudit = 0;
	
	public Auditor(ABankAccount shared) {
		this.sharedLocation = shared;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				System.out.println();
				System.out.println("**************************************************************************************************************************************************");
				System.out.println();
				System.out.println("\t\tAUDITOR FIND CURRENT ACCOUNT BALANCE TO BE: $" + sharedLocation.getBalance() + "\t\t Number of transactions since last audit is: " + (sharedLocation.getTransactionNumber() - transactionNumberAtLastAudit));
				System.out.println();
				System.out.println("***************************************************************************************************************************************************");
				System.out.println();
				transactionNumberAtLastAudit = sharedLocation.getTransactionNumber();
				Thread.sleep(sleepTime.nextInt(5000)+1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
