package atm;

import java.util.Date;

public class Transaction {
	private double amount;
	// Time and date of transaction
	private Date timestamp;
	// A memo for this transaction
	private String memo;

	public Transaction(double amount) {
		this.amount = amount;
		this.timestamp = new Date();
		this.memo = "";
	}

	public Transaction(double amount, String memo) {
		// Call 1-arg construtor
		this(amount);
		this.memo = memo;
	}

	public double getAmount() {
		return this.amount;
	}

	public String getSummaryLine() {
		if (this.amount >= 0) {
			return String.format("%s : $%.02f : %s", this.timestamp.toString(), this.amount, this.memo);
		} else {
			return String.format("%s : $(%.02f) : %s", this.timestamp.toString(), this.amount, this.memo);
		}
	}
}
