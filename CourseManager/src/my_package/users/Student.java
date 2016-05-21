/**
 * 
 */
package my_package.users;

import java.util.ArrayList;

import my_package.courses.Course;

/**
 * @author jim2
 *
 */
public class Student extends User {
	/** Courses enrolled in */
	private ArrayList<Course> courses;
	/** Maximum number of credits student is eligible to enroll in */
	private int maxCredits;
	/** Maximum number of credits any student can enroll in */
	public static final int MAX_CREDITS = 18;
	
	
	/**
	 * @param firstName
	 * @param lastName
	 * @param id
	 * @param email
	 * @param password
	 * @param maxCredits
	 */
	public Student(String firstName, String lastName, String id, String email, String password,	int maxCredits) {
		super(firstName, lastName, id, email, password);
		courses = new ArrayList<Course>();
		setMaxCredits(maxCredits);
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param id
	 * @param email
	 * @param password
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, MAX_CREDITS);
	}

	/**
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * @param maxCredits the maxCredits to set
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 0 || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException();
		}
		this.maxCredits = maxCredits;
	}

	/* (non-Javadoc)
	 * @see my_package.users.User#canAddCourse(my_package.users.Course)
	 */
	@Override
	public boolean canAddCourse(Course c) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see my_package.users.User#addCourse(my_package.users.Course)
	 */
	@Override
	public boolean addCourse(Course c) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see my_package.users.User#removeCourse(my_package.users.Course)
	 */
	@Override
	public boolean removeCourse(Course c) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see my_package.users.User#getCourses()
	 */
	@Override
	public Course[] getCourses() {
		// TODO Auto-generated method stub
		return null;
	}

}
