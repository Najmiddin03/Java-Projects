package atm;

import java.util.ArrayList;
import java.util.Random;

public class Bank {
	private String name;
	private ArrayList<User> users;
	private ArrayList<Account> accounts;

	public Bank(String name) {
		this.name = name;
		this.users = new ArrayList<>();
		this.accounts = new ArrayList<>();
	}

	public String getNewUserUUID() {
		String uuid;
		Random rand = new Random();
		int len = 6;
		boolean nonUnique;
		do {
			// Generate number
			uuid = "";
			for (int i = 0; i < len; i++) {
				uuid += ((Integer) rand.nextInt(10)).toString();
			}

			// Check if it is unique
			nonUnique = false;
			for (User us : this.users) {
				if (uuid.compareTo(us.getUUID()) == 0) {
					nonUnique = true;
					break;
				}
			}
		} while (nonUnique);
		return uuid;
	}

	public String getNewAccountUUID() {
		String uuid;
		Random rand = new Random();
		int len = 10;
		boolean nonUnique;
		do {
			// Generate number
			uuid = "";
			for (int i = 0; i < len; i++) {
				uuid += ((Integer) rand.nextInt(10)).toString();
			}

			// Check if it is unique
			nonUnique = false;
			for (Account a : this.accounts) {
				if (uuid.compareTo(a.getUUID()) == 0) {
					nonUnique = true;
					break;
				}
			}
		} while (nonUnique);
		return uuid;
	}

	public User addUser(String firstName, String lastname, String pin) {
		User newUser = new User(firstName, lastname, pin, this);
		this.users.add(newUser);

		Account newAccount = new Account("Saving", newUser, this);
		newUser.addAccount(newAccount);
		addAccount(newAccount);
		return newUser;
	}

	public void addAccount(Account acc) {
		this.accounts.add(acc);
	}

	public User userLogin(String userID, String pin) {
		for (User u : this.users) {
			if (u.getUUID().equals(userID) || u.validatePin(pin)) {
				return u;
			}
		}
		return null;
	}

	public String getName() {
		return this.name;
	}
}
