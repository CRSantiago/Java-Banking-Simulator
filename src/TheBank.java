
public interface TheBank {
	
	// deposit arguments: deposit amount, thread name making the deposit
	public abstract void deposit(int depositAmount, String threadNamee);
	
	// withdrawal arguments: withdrawal amount, thread name making withdrawal
	public abstract void withdrawal(int withdrawalAmount, String threadName) throws InterruptedException;
	
	// flagged transaction are logged independently in a log file
	// flag transaction arguments: transactiona amount, thread name making transactions, type of thread making transactions
	//use "D" for depositor type thread, and "W" for mithdrawal type thread
	public abstract void flagged_transactions(int transactionAmount,String threadName, String threadType);
}
