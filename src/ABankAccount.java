
public class ABankAccount implements TheBank{
		private int balance = 0;
		
		public void deposit(int depositAmount, String threadName) {
			balance += depositAmount;
			System.out.println(threadName + " deposits $" + depositAmount + "\t\t" + "(+) Balance is $" + balance);
		}
		
		
		public void withdrawal(int withdrawalAmount, String threadName) {
			System.out.print("\t\t" + threadName + " withdraws " + withdrawalAmount);
			if(withdrawalAmount <= balance) {
				balance -= withdrawalAmount;
				System.out.print("\t\t(-) Balance is " + balance);
				System.out.println();
			} else {
				System.out.print("\t\t(*****) WITHDRAWAL BLOCKED - INSUFFICIENT FUNDS!!!");
				System.out.println();
			}
			
		}
		
		public void flagged_transactions(int transactionAmount,String threadName, String threadType) {
			
		}
}
