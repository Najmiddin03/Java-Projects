package atm;

import java.util.Scanner;

public class ATM {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Bank b = new Bank("Kapital");
		User u = b.addUser("Najmiddin", "Nazarmatov", "5656");
		Account a = new Account("Savings", u, b);
		Account a2 = new Account("Checking", u, b);
		u.addAccount(a);
		u.addAccount(a2);

		User curUser;
		while (true) {
			// Stay in the login prompt until successful login
			curUser = ATM.mainMenuPrompt(b, sc);
			// Stay in the main menu until user quits
			ATM.printUserMenu(curUser, sc);
		}

	}

	private static void printUserMenu(User u, Scanner sc) {
		// Print accounts summary
		u.printAccounsSummary();
		int choice;

		// User menu
		do {
			System.out.printf("\nWelcome %s, what would you like to do?\n\n", u.getFirstName());
			System.out.println("1. Show account transaction history");
			System.out.println("2. Withdrawal");
			System.out.println("3. Deposit");
			System.out.println("4. Transfer");
			System.out.println("5. Quit\n");
			System.out.print("Enter choice: ");
			choice = sc.nextInt();

			if (choice < 1 || choice > 5) {
				System.out.println("Invalid choice. Please, choose 1-5");
			}
		} while (choice < 1 || choice > 5);
		switch (choice) {
		case 1:
			ATM.showTransactionHistory(u, sc);
			break;
		case 2:
			ATM.withdrawFunds(u, sc);
			break;
		case 3:
			ATM.depositFunds(u, sc);
			break;
		case 4:
			ATM.transferFunds(u, sc);
			break;
		}
		if (choice != 5) {
			ATM.printUserMenu(u, sc);
		}
	}

	private static void showTransactionHistory(User u, Scanner sc) {
		int acc;
		// Get account whose transaction history to look at
		do {
			System.out.printf("Enter the number (1-%d) of the account whose transaction you want to see",
					u.numAccounts());
			acc = sc.nextInt() - 1;
			if (acc < 0 || acc > u.numAccounts()) {
				System.out.println("Invalid account. Please, try again");
			}
		} while (acc < 0 || acc > u.numAccounts());
		u.printAccountTransactionHistory(acc);
	}

	private static void transferFunds(User u, Scanner sc) {
		int fromAcc;
		int toAcc;
		double amount;
		double accBal;// Account balance
		// Get account to transfer from
		do {
			System.out.printf("Enter the number (1-%d) of the account to transfer from: ", u.numAccounts());
			fromAcc = sc.nextInt() - 1;
			if (fromAcc < 0 || fromAcc > u.numAccounts()) {
				System.out.println("Invalid account. Please, try again");
			}
		} while (fromAcc < 0 || fromAcc > u.numAccounts());
		accBal = u.getAccBalance(fromAcc);

		// Get account to transfer to
		do {
			System.out.printf("Enter the number (1-%d) of the account to transfer to: ", u.numAccounts());
			toAcc = sc.nextInt() - 1;
			if (toAcc < 0 || toAcc > u.numAccounts()) {
				System.out.println("Invalid account. Please, try again");
			}
		} while (toAcc < 0 || toAcc > u.numAccounts());

		// Get the amount to transfer
		do {
			System.out.printf("Enter the amount to transfer (max $%.02f): $", accBal);
			amount = sc.nextDouble();
			if (amount < 0) {
				System.out.println("Amount should be greater than zero!");
			} else if (amount > accBal) {
				System.out.println("Amount should not be greater than balance: " + accBal);
			}
		} while (amount < 0 || amount > accBal);

		// Transfer
		u.addAccTransaction(fromAcc, -1 * amount, String.format("Transfer to account %s", u.getAccUUID(toAcc)));
		u.addAccTransaction(toAcc, amount, String.format("Transfer from account %s", u.getAccUUID(fromAcc)));
	}

	private static void withdrawFunds(User u, Scanner sc) {
		int acc;
		double amount;
		String memo;
		double accBal;// Account balance
		// Get account to withdraw funds
		do {
			System.out.printf("Enter the number (1-%d) of the account to withdraw funds from: ", u.numAccounts());
			acc = sc.nextInt() - 1;
			if (acc < 0 || acc > u.numAccounts()) {
				System.out.println("Invalid account. Please, try again");
			}
		} while (acc < 0 || acc > u.numAccounts());
		accBal = u.getAccBalance(acc);
		// Get the amount to withdraw
		do {
			System.out.printf("Enter the amount to withdraw (max $%.02f): $", accBal);
			amount = sc.nextDouble();
			if (amount < 0) {
				System.out.println("Amount should be greater than zero!");
			} else if (amount > accBal) {
				System.out.println("Amount should not be greater than balance: " + accBal);
			}
		} while (amount < 0 || amount > accBal);
		// gobble up rest of previous input
		sc.nextLine();
		// Get memo
		System.out.println("Enter a memo:");
		memo = sc.nextLine();
		// Withdrawal
		u.addAccTransaction(acc, -1 * amount, memo);
	}

	private static void depositFunds(User u, Scanner sc) {
		int acc;
		double amount;
		String memo;
		// Get account to deposit funds
		do {
			System.out.printf("Enter the number (1-%d) of the account to deposit funds to: ", u.numAccounts());
			acc = sc.nextInt() - 1;
			if (acc < 0 || acc > u.numAccounts()) {
				System.out.println("Invalid account. Please, try again");
			}
		} while (acc < 0 || acc > u.numAccounts());
		// Get the amount to deposit
		do {
			System.out.print("Enter the amount to deposit: $");
			amount = sc.nextDouble();
			if (amount < 0) {
				System.out.println("Amount should be greater than zero!");
			}
		} while (amount < 0);
		// gobble up rest of previous input
		sc.nextLine();
		// Get memo
		System.out.println("Enter a memo:");
		memo = sc.nextLine();
		// Deposit
		u.addAccTransaction(acc, amount, memo);
	}

	public static User mainMenuPrompt(Bank b, Scanner sc) {
		String userID;
		String pin;
		User authUser;

		// prompt the user for user ID/pin combo until a correct one is reached
		do {
			System.out.printf("\nWelcome to %s\n", b.getName());
			System.out.print("Enter user ID: ");
			userID = sc.nextLine();
			System.out.print("Enter pin: ");
			pin = sc.nextLine();

			// Try to login to the corresponding User object
			authUser = b.userLogin(userID, pin);
			if (authUser == null) {
				System.out.println("Incorrect user ID/pin combination");
				System.out.println("Please try again");
			}
		} while (authUser == null);
		return authUser;
	}

}
