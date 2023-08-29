package atm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
	private String firstName;
	private String lastName;
	// ID number of the user
	private String uuid;
	// MD hash of the user's pin number
	private byte pinHash[];
	private ArrayList<Account> accounts;

	public User(String firstName, String lastName, String pin, Bank theBank) {
		this.firstName = firstName;
		this.lastName = lastName;

//		Store pin's MD5 hash, rather than the original value, for security reasons
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.exit(1);
		}

//		Get new, unique universal ID for the user
		this.uuid = theBank.getNewUserUUID();

		this.accounts = new ArrayList<Account>();

		System.out.printf("New user %s, %s with ID %s created\n", lastName, firstName, this.uuid);
	}

	public void addAccount(Account account) {
		this.accounts.add(account);
	}

	public String getUUID() {
		return this.uuid;
	}

	public boolean validatePin(String pin) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(pin.getBytes()), this.pinHash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void printAccounsSummary() {
		System.out.println("\n" + this.getFirstName() + "'s accounts summary");
		for (int i = 0; i < this.accounts.size(); i++) {
			System.out.println((i + 1) + ": " + this.accounts.get(i).getSummaryLine());
		}
	}

	public int numAccounts() {
		return this.accounts.size();
	}

	public void printAccountTransactionHistory(int acc) {
		this.accounts.get(acc).printTransactionHistory();
	}

	public double getAccBalance(int fromAcc) {
		return this.accounts.get(fromAcc).getBalance();
	}

	public String getAccUUID(int toAcc) {
		return this.accounts.get(toAcc).getUUID();
	}

	public void addAccTransaction(int acc, double amount, String memo) {
		this.accounts.get(acc).addTransaction(amount, memo);
	}
}
