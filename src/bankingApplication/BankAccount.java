package bankingApplication;

import java.util.Scanner;

public class BankAccount {
	int balance;
	int previousTransaction;
	String customerName;
	String customerID;

	public BankAccount(String customerName, String customerID) {
		this.customerName = customerName;
		this.customerID = customerID;
	}

	void deposit(int amount) {
		if (amount != 0) {
			balance += amount;
			previousTransaction = amount;
		}
	}

	void withdraw(int amount) {
		if (amount != 0) {
			balance -= amount;
			previousTransaction = -amount;
		}
	}

	void getPreviousTransaction() {
		if (previousTransaction > 0) {
			System.out.println("Deposited: " + previousTransaction);
		} else if (previousTransaction < 0) {
			System.out.println("Withdrawn: " + previousTransaction * -1);
		} else {
			System.out.println("No transaction occured");
		}
	}

	void showMenu() {
		char option = '\0';
		Scanner sc = new Scanner(System.in);

		System.out.println("Welcome " + customerName);
		System.out.println("Your ID is: " + customerID);
		System.out.println("\nA. Check Balance");
		System.out.println("B. Deposit");
		System.out.println("C. Withdraw");
		System.out.println("D. Previous Transaction");
		System.out.println("E. Exit");

		while (option != 'E') {
			System.out.println("=====================================");
			System.out.print("Enter an option: ");
			option = sc.next().charAt(0);
			System.out.println("=====================================\n");
			switch (option) {
			case 'A':
				System.out.println("-------------------------------------");
				System.out.println("Your balance is: " + balance);
				System.out.println("-------------------------------------\n");
				break;
			case 'B':
				System.out.println("-------------------------------------");
				System.out.print("Enter an amount to deposit: ");
				int amount = sc.nextInt();
				System.out.println("-------------------------------------\n");
				deposit(amount);
				break;
			case 'C':
				System.out.println("-------------------------------------");
				System.out.print("Enter an amount to withdraw: ");
				int amount2 = sc.nextInt();
				System.out.println("-------------------------------------\n");
				withdraw(amount2);
				break;
			case 'D':
				System.out.println("-------------------------------------");
				getPreviousTransaction();
				System.out.println("-------------------------------------\n");
				break;
			case 'E':
				break;
			default:
				System.out.println("Invalid option. Please, try again");
				break;
			}
		}
		System.out.println("Thank you for using our service!!!");
		sc.close();
	}
}
