/**
 * 
 */
package my_package.users;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
	 * 
	 */
	public int getCurrentCredits() {
		return courses.stream()
				.mapToInt(Course::getCredits)
				.sum();
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
		if (maxCredits < 0 || maxCredits > MAX_CREDITS || maxCredits < getCurrentCredits()) {
			throw new IllegalArgumentException();
		}
		this.maxCredits = maxCredits;
	}

	/* (non-Javadoc)
	 * @see my_package.users.User#canAddCourse(my_package.users.Course)
	 */
	@Override
	public boolean canAddCourse(Course c) {
		return (getCurrentCredits() + c.getCredits() <= maxCredits) && (courses.contains(c)==false);
	}

	/* (non-Javadoc)
	 * @see my_package.users.User#addCourse(my_package.users.Course)
	 */
	@Override
	public boolean addCourse(Course c) {
		if (canAddCourse(c)) {
			if (courses.add(c)) {
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see my_package.users.User#removeCourse(my_package.users.Course)
	 */
	@Override
	public boolean removeCourse(Course c) {
		return courses.remove(c);
	}

	/* (non-Javadoc)
	 * @see my_package.users.User#getCourses()
	 */
	@Override
	public Course[] getCourses() {
		Course [] c = new Course[courses.size()];
		return courses.toArray(c);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((courses == null) ? 0 : courses.hashCode());
		result = prime * result + maxCredits;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (courses == null) {
			if (other.courses != null)
				return false;
		} else if (!courses.equals(other.courses))
			return false;
		if (maxCredits != other.maxCredits)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String result = super.toString() + "," + maxCredits;
		if (courses.size() > 0) {
			result += ","  
					+ courses.stream()
					.map(Course::getName)
					.collect(Collectors.joining(","));
		}
		return result;
	}

}
