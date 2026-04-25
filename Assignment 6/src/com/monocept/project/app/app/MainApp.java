package com.monocept.project.app.app;

import java.util.Scanner;

import com.monocept.project.app.inputvalidator.InputValidator;
import com.monocept.project.app.model.Registration;
import com.monocept.project.app.model.Student;
import com.monocept.project.app.service.StudentService;

public class MainApp {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		StudentService service = new StudentService();

		while (true) {

			System.out.println("\n===== Student Course Registration System =====");

			System.out.println("1. Add Student");
			System.out.println("2. Register for Course");
			System.out.println("3. View All Students with Courses");
			System.out.println("4. Search Student by ID");
			System.out.println("5. Update Student");
			System.out.println("6. Update Course Fee");
			System.out.println("7. Cancel Registration");
			System.out.println("8. Delete Student");
			System.out.println("9. High Paying Students Report");
			System.out.println("10. Course-wise Student Count");
			System.out.println("11. Exit");

			int choice = InputValidator.getValidInt(scanner, "Enter choice: ");

			switch (choice) {

			// ================= ADD STUDENT =================
			case 1:

				int id = InputValidator.getValidInt(scanner, "Enter Student ID: ");
				String name = InputValidator.getValidString(scanner, "Enter Name: ");
				int age = InputValidator.getValidInt(scanner, "Enter Age: ");
				int branchId = InputValidator.getBranchChoice(scanner);
				service.addNewStudent(new Student(id, name, branchId, age));
				break;

			// ================= REGISTER COURSE =================
			case 2:

				int studentId = InputValidator.getValidInt(scanner, "Enter Student ID: ");
				int courseId = InputValidator.getCourseChoice(scanner);
				double fee = InputValidator.getValidDouble(scanner, "Enter Fee Paid: ");
				service.studentRegistrationService(new Registration(studentId, courseId, fee));
				break;

			// ================= VIEW ALL =================
			case 3:

				service.viewAllStudentWithRegistration();
				break;

			// ================= SEARCH STUDENT =================
			case 4:

				int searchId = InputValidator.getValidInt(scanner, "Enter Student ID: ");
				service.viewAllDetailsOfStudentById(searchId);
				break;

			// ================= UPDATE STUDENT =================
			case 5:

				int updateId = InputValidator.getValidInt(scanner, "Enter Student ID: ");
				System.out.println("\n1. Update Name");
				System.out.println("2. Update Branch");
				int updateChoice = InputValidator.getValidInt(scanner, "Enter choice: ");
				if (updateChoice == 1) {
					String newName = InputValidator.getValidString(scanner, "Enter new name: ");
					service.updateStudentDetails(updateId, newName, 0, 1);
				} else if (updateChoice == 2) {
					int newBranchId = InputValidator.getBranchChoice(scanner);
					service.updateStudentDetails(updateId, null, newBranchId, 2);
				} else {
					System.out.println("Invalid choice");
				}
				break;

			// ================= UPDATE COURSE FEE =================
			case 6:

				int feeStudentId = InputValidator.getValidInt(scanner, "Enter Student ID: ");
				int feeCourseId = InputValidator.getCourseChoice(scanner);
				double newFee = InputValidator.getValidDouble(scanner, "Enter new fee: ");
				service.updateCourseFees(feeStudentId, feeCourseId, newFee);
				break;
			// ================= CANCEL REGISTRATION =================
			case 7:

				int cancelStudentId = InputValidator.getValidInt(scanner, "Enter Student ID: ");
				int cancelCourseId = InputValidator.getCourseChoice(scanner);
				service.cancelRegistration(cancelStudentId, cancelCourseId);
				break;
			// ================= DELETE STUDENT =================
			case 8:

				int deleteId = InputValidator.getValidInt(scanner, "Enter Student ID: ");
				service.deleteStudentCompletely(deleteId);
				break;

			// ================= HIGH PAYING REPORT =================
			case 9:

				double amount = InputValidator.getValidDouble(scanner, "Enter amount: ");
				service.highPayingStudents(amount);
				break;
			// ================= COURSE COUNT =================
			case 10:
				service.courseWiseCountService();
				break;
			// ================= EXIT =================
			case 11:
				System.out.println("Exiting application...");
				scanner.close();
				System.exit(0);
			default:
				System.out.println("Invalid choice");
			}
		}
	}
}