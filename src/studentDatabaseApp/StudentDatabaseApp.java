package studentDatabaseApp;

import java.util.Scanner;

public class StudentDatabaseApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of new stdents to enroll: ");
		int numOfStudent = sc.nextInt();
		Student[] students = new Student[numOfStudent];
		for (int i = 0; i < numOfStudent; i++) {
			students[i] = new Student();
			students[i].enroll();
			students[i].payTuition();
			System.out.println(students[i]);
		}
		sc.close();
	}

}
