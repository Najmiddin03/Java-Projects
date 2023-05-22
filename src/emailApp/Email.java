package emailApp;

import java.util.Scanner;

public class Email {
	private String firstName;
	private String lastName;
	private String password;
	private int passwordLength = 8;
	private String department;
	private String email;
	private int mailboxCapacity = 500;
	private String alternateEmail;

	public Email(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		System.out.println("Email created: " + firstName + " " + lastName);

		this.department = setDepartment();
		System.out.println("Department: " + this.department);

		this.password = randomPassword(passwordLength);
		System.out.println("Your password is: " + this.password);

		email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + department + ".company.com";
		System.out.println("Your email is: " + email);
	}

	private String setDepartment() {
		System.out.println("Enter the department code:");
		System.out.println("1 for Sales");
		System.out.println("2 for Development");
		System.out.println("3 for Accounting");
		System.out.println("0 for none");
		Scanner sc = new Scanner(System.in);
		int deptChoice = sc.nextInt();
		sc.close();
		switch (deptChoice) {
		case 1:
			return "sales";
		case 2:
			return "devs";
		case 3:
			return "acct";
		default:
			return "";
		}
	}

	private String randomPassword(int length) {
		String passSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%";
		char[] password = new char[length];
		for (int i = 0; i < length; i++) {
			password[i] = passSet.charAt((int) (Math.random() * passSet.length()));
		}
		return new String(password);
	}

	public void setMailboxCapacity(int mailboxCapacity) {
		this.mailboxCapacity = mailboxCapacity;
	}

	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}

	public void changePassword(String password) {
		this.password = password;
	}

	public int getMailboxCapacity() {
		return this.mailboxCapacity;
	}

	public String getAlternateEmail() {
		return this.alternateEmail;
	}

	public String getPassword() {
		return this.password;
	}

	public String showInfo() {
		return "========================================\nFull name: " + firstName + " " + lastName + "\nEmail: "
				+ email + "\nMailbox capacity: " + mailboxCapacity + " mb\n========================================";
	}
}
