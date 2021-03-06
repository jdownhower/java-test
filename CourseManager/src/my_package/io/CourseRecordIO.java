/**
 * 
 */
package my_package.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import my_package.courses.Course;

/**
 * @author jim2
 *
 */
public class CourseRecordIO {

	/**
	 * Reads Course records from the given file.  If the file doesn't exist
	 * a FileNotFoundException is thrown.  A line with a format error will be
	 * ignored.
	 * @param fileName name of file to read
	 * @return Course records
	 * @throws FileNotFoundException if the file doesn't exist
	 */
	public static List<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		List<Course> courses = new ArrayList<Course>();
			
		Scanner fileScanner = new Scanner(new File(fileName));
		while (fileScanner.hasNextLine()) {
			try {
				courses.add(processCourse(fileScanner.nextLine()));
			} catch (IllegalArgumentException e) {
				//if the exception is thrown, ignore the Student line.  
			}
		}
		
		fileScanner.close();
		return courses;
	}

	/**
	 * Creates a Course from the String record.  An IllegalArgumentException is thrown
	 * if one of the items is missing or if the Course cannot be constructed.
	 * @param courseLine the line to process
	 * @return a valid Course
	 */
	private static Course processCourse(String courseLine) {
		Scanner lineScanner = new Scanner(courseLine);
		try {
			lineScanner.useDelimiter(",");
			String name = lineScanner.next();
			int credits = lineScanner.nextInt();
			int capacity = lineScanner.nextInt();
			lineScanner.close();
			return new Course(name, credits, capacity);
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
	public static void writeCourseRecords(String fileName, List<Course> courses) throws IOException {
		PrintWriter fileOut = new PrintWriter(new FileWriter(fileName));
		
		for (Course c: courses) {
			fileOut.println(c.toString());
		}
		
		fileOut.close();
	}
}
