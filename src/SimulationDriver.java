import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulationDriver {
	
	// 10 withdrawal threads, 5 depositor threads, and 1 auditor thread
	public static final int MAX_AGENTS = 16;
	
	public static void main(String args[]) {
		ExecutorService application = Executors.newFixedThreadPool(MAX_AGENTS);
		ABankAccount sharedLocation = new ABankAccount();
		try {
			application.execute(new Withdrawal(sharedLocation, "Agent WT5"));
			application.execute(new Depositor(sharedLocation, "Agent DT4"));
//			application.execute(new Withdrawal("Agent WT1"));
//			application.execute(new Withdrawal("Agent WT3"));
//			application.execute(new Withdrawal("Agent WT6"));
//			application.execute(new Withdrawal("Agent WT2"));
//			application.execute(new Depositor("Agent DT3"));
//			application.execute(new Depositor("Agent DT2"));
//			application.execute(new Withdrawal("Agent WT4"));
//			application.execute(new Withdrawal("Agent WT7"));
			application.execute(new Depositor(sharedLocation, "Agent DT5"));
			application.execute(new Depositor(sharedLocation, "Agent DT1"));
			application.execute(new Withdrawal(sharedLocation, "Agent WT8"));
//			application.execute(new Withdrawal("Agent WT10"));
//			application.execute(new Withdrawal("Agent WT9"));
		} catch ( Exception exception ) {
			exception.printStackTrace();
		}
		
		application.shutdown();
	}
}
