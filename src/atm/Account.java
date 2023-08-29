package atm;

import java.util.ArrayList;

public class Account {
	private String name;
	private String uuid;
	private ArrayList<Transaction> transactions;

	public Account(String name, User holder, Bank theBank) {
		this.name = name;
		this.uuid = theBank.getNewAccountUUID();
		this.transactions = new ArrayList<Transaction>();
	}

	public String getUUID() {
		return this.uuid;
	}

	public String getSummaryLine() {
		double balance = this.getBalance();

		// Format the summary line, depending on the whether the balance is
		// Negative
		if (balance >= 0) {
			return String.format("%s : $%.02f : %s", this.uuid, balance, this.name);
		} else {
			return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.name);
		}
	}

	public double getBalance() {
		double balance = 0;
		for (Transaction t : this.transactions) {
			balance += t.getAmount();
		}
		return balance;
	}

	public void printTransactionHistory() {
		System.out.println("\nTransaction history for account " + this.uuid);
		for (int i = this.transactions.size() - 1; i >= 0; i--) {
			System.out.println(this.transactions.get(i).getSummaryLine());
		}
		System.out.println();
	}

	public void addTransaction(double amount, String memo) {
		Transaction newTransaction = new Transaction(amount, memo);
		this.transactions.add(newTransaction);
	}
}
