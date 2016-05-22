package my_package.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import my_package.courses.Course;
import my_package.manager.CourseManager;
import my_package.users.Student;

public class StudentRecordIO {

	/**
	 * Reads Student records from the given file.  If the file doesn't exist
	 * a FileNotFoundException is thrown.  A line with a format error will be
	 * ignored.
	 * @param fileName name of file to read
	 * @return Student records
	 * @throws FileNotFoundException if the file doesn't exist
	 */
	public static List<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		List<Student> students = new ArrayList<Student>();
			
		Scanner fileScanner = new Scanner(new FileInputStream(fileName));
		while (fileScanner.hasNextLine()) {
			try {
				students.add(processStudent(fileScanner.nextLine()));
			} catch (IllegalArgumentException e) {
				//if the exception is thrown, ignore the Course line.  
			}
		}
		
		fileScanner.close();
		return students;
	}

	/**
	 * Creates a Student from the String record.  An IllegalArgumentException is thrown
	 * if one of the items is missing or if the Student cannot be constructed.
	 * @param studentLine the line to process
	 * @return a valid Student
	 */
	private static Student processStudent(String studentLine) {
		Scanner lineScanner = new Scanner(studentLine);
		try {
			lineScanner.useDelimiter(",");
			String firstName = lineScanner.next();
			String lastName = lineScanner.next();
			String id = lineScanner.next();
			String email = lineScanner.next();
			String password = lineScanner.next();
			int capacity = lineScanner.nextInt();
			Student s = new Student(firstName, lastName, id, email, password, capacity);
			while (lineScanner.hasNext()) {
				Course c = CourseManager.getInstance().getCourseByName(lineScanner.next());
				if (c == null) {
					throw new NoSuchElementException();
				}
				if (!s.addCourse(c)) {
					throw new NoSuchElementException();
				}
			} 
			lineScanner.close();
			return s;
		} catch (NoSuchElementException e) {
			lineScanner.close();
			throw new IllegalArgumentException();
		} 
	}
	
	/**
	 * Writes the information about the courses to the given file.
	 * @param fileName file name to record data
	 * @param courses list of courses
	 * @throws IOException if cannot write to file
	 */
	public static void writeStudentRecords(String fileName, List<Student> students) throws IOException {
		PrintWriter fileOut = new PrintWriter(new FileWriter(fileName));
		
		for (Student s: students) {
			fileOut.println(s.toString());
		}
		
		fileOut.close();
	}

}
