/* Name: Christopher Santiago
 Course: CNT 4714 Spring 2023
 Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking
 Due Date: February 15, 2023
*/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulationDriver {
	
	// 10 withdrawal threads, 5 depositor threads, and 1 auditor thread
	public static final int MAX_AGENTS = 16;
	
	public static void main(String args[]) {
		ExecutorService application = Executors.newFixedThreadPool(MAX_AGENTS);
		ABankAccount sharedLocation = new ABankAccount();
		System.out.println("Deposit Agents\t\t\tWithdrawal Agents\t\t\tBalance\t\t\tTransaction Number");
		System.out.println("--------------\t\t\t------------------\t\t---------------------\t\t-------------------");
		System.out.println();
		try {
			application.execute(new Withdrawal(sharedLocation, "Agent WT5"));
			application.execute(new Depositor(sharedLocation, "Agent DT4"));
			application.execute(new Withdrawal(sharedLocation, "Agent WT1"));
			application.execute(new Withdrawal(sharedLocation, "Agent WT3"));
			application.execute(new Withdrawal(sharedLocation, "Agent WT6"));
			application.execute(new Withdrawal(sharedLocation, "Agent WT2"));
			application.execute(new Depositor(sharedLocation, "Agent DT3"));
			application.execute(new Depositor(sharedLocation, "Agent DT2"));
			application.execute(new Withdrawal(sharedLocation, "Agent WT4"));
			application.execute(new Withdrawal(sharedLocation, "Agent WT7"));
			application.execute(new Depositor(sharedLocation, "Agent DT5"));
			application.execute(new Depositor(sharedLocation, "Agent DT1"));
			application.execute(new Withdrawal(sharedLocation, "Agent WT8"));
			application.execute(new Withdrawal(sharedLocation, "Agent WT10"));
			application.execute(new Withdrawal(sharedLocation, "Agent WT9"));
			application.execute(new Auditor(sharedLocation));
		} catch ( Exception exception ) {
			exception.printStackTrace();
		}
		
		application.shutdown();
	}
}
