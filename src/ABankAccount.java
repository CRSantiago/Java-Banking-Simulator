import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ABankAccount implements TheBank{
		private int balance = 0;
		private Lock accessLock = new ReentrantLock();
		private Condition sufficientFundsCondition = accessLock.newCondition();
		private int transactionNumber = 0;
		
		public void deposit(int depositAmount, String threadName) {
			accessLock.lock();
			try {
				balance += depositAmount;
				transactionNumber += 1;
				System.out.println(threadName + " deposits $" + depositAmount + "\t\t\t\t\t\t" + "(+) Balance is $" + balance + "\t\t\t\t" + transactionNumber);
				if(depositAmount > 350) {
					System.out.println();
					flagged_transactions(depositAmount, threadName, "Deposit");
				}
				sufficientFundsCondition.signalAll();
			} finally {
				accessLock.unlock();
			}
		}
		
		
		public void withdrawal(int withdrawalAmount, String threadName) throws InterruptedException {
			accessLock.lock();
//			System.out.print("\t\t\t\t" + threadName + " withdraws $" + withdrawalAmount);
			try {
				if(balance < withdrawalAmount) {
					System.out.println("\t\t\t\t" + threadName + " withdraws $" + withdrawalAmount + "\t\t(*****) WITHDRAWAL BLOCKED - INSUFFICIENT FUNDS!!!");
					sufficientFundsCondition.await();
				} else {
					balance -= withdrawalAmount;
					transactionNumber += 1;
					System.out.println("\t\t\t\t" + threadName + " withdraws $" + withdrawalAmount + "\t\t(-) Balance is $" + balance + "\t\t\t\t" + transactionNumber);
					if(withdrawalAmount > 75) {
						System.out.println();
						flagged_transactions(withdrawalAmount, threadName, "Withdrawal");
					}
				}
			} finally {
				accessLock.unlock();
			}
		}
		
		public int getBalance() {
	        return balance;
	    }
		
		public int getTransactionNumber() {
			return transactionNumber;
		}
		
		public void flagged_transactions(int transactionAmount,String threadName, String threadType) {
			double excessAmount = 0.0;
			if(threadType.equals("Withdrawal")) {
				excessAmount = 75.00;
			} else {
				excessAmount = 350.00;
			}
			System.out.println("* * * Flagged Transaction - " + threadType + " " + threadName + " Made a " + threadType + " In Excess of $" + excessAmount + " USD - See Flagged Transaction Log");
			System.out.println();
			File file = new File("flagged_transactions.txt");
			LocalDateTime now = LocalDateTime.now();
	        ZoneId zoneId = ZoneId.systemDefault();
	        String shortZoneId = zoneId.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	        String formattedDateTime = now.format(formatter) + " " + shortZoneId;
			try (FileWriter writer = new FileWriter(file, true)) {
	            writer.write(threadType + " " + threadName + " issued a " + threadType + " of $" + transactionAmount + " at " + formattedDateTime + "  Transaction Number : " + transactionNumber + System.lineSeparator());
	        } catch (IOException e) {
	            System.out.println("Error writing to file " + file + ": " + e.getMessage());
	        }
		}
}
