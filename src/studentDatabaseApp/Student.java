package studentDatabaseApp;

import java.util.Scanner;

public class Student {
	private String firstName;
	private String lastName;
	private int gradeYear;
	private int strudentID;
	private String courses = "";
	private int tuitionBalance;
	private static int costOfCourse = 600;
	private static int id = 1001;

	@SuppressWarnings("resource")
	public Student() {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter student first name: ");
		this.firstName = sc.nextLine();

		System.out.print("Enter student last  name: ");
		this.lastName = sc.nextLine();

		System.out.println("Enter student class level:");
		System.out.println("1 - Freshman");
		System.out.println("2 - Sophmore");
		System.out.println("3 - Junior");
		System.out.println("4 - Senior");
		this.gradeYear = sc.nextInt();

		setStudentId();

		System.out.println(firstName + " " + lastName + "\nGrade: " + gradeYear + "\nStudent ID: " + strudentID);
	}

	private void setStudentId() {
		this.strudentID = gradeYear * 10000 + id;
		id++;
	}

	@SuppressWarnings("resource")
	public void enroll() {
		System.out.println("Enter course to enroll (Q to quit):");
		Scanner sc = new Scanner(System.in);
		while (true) {
			String course = sc.nextLine();
			if (course.equals("Q")) {
				break;
			}
			courses += course + "\n";
			tuitionBalance += costOfCourse;
		}
		System.out.println("Enrolled in:\n" + courses);
		System.out.println("Tuition balance: " + tuitionBalance);
	}

	public void viewBalance() {
		System.out.println("Your balance is: $" + tuitionBalance);
	}

	@SuppressWarnings("resource")
	public void payTuition() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter your payment: $");
		int payment = sc.nextInt();
		tuitionBalance -= payment;
		System.out.println("Paid: $" + payment);
		viewBalance();
	}

	@Override
	public String toString() {
		return "====================\nName: " + firstName + " " + lastName + "\nGrade: " + gradeYear
				+ "\nCourses enrolled:\n" + courses + "Balance: $" + tuitionBalance + "\n====================";
	}
}
