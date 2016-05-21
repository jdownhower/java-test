/**
 * 
 */
package my_package.courses;

import java.util.ArrayList;

import my_package.users.Student;
import my_package.users.User;

/**
 * @author jim2
 *
 */
public class Course implements Enrollable {
	/** Course name */
	private String name;
	/** Course credit hours */
	private int credits;
	/** Course student capacity */
	private int capacity;
	/** Minimum credit hours */
	public static final int MIN_HOURS = 1;
	/** Maximum credit hours */
	public static final int MAX_HOURS = 4;
	/** Students enrolled in the course */
	private ArrayList<User> enrolledStudents;

	/**
	 * @param name
	 * @param credits
	 * @param capacity
	 */
	public Course(String name, int credits, int capacity) {
		super();
		enrolledStudents = new ArrayList<User>();
		setName(name);
		setCredits(credits);
		setCapacity(capacity);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name + "," + credits + "," + capacity;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException();
		}
		this.name = name;
	}
	/**
	 * @param credits the credits to set
	 */
	public void setCredits(int credits) {
		if (credits < MIN_HOURS || credits > MAX_HOURS) {
			throw new IllegalArgumentException();
		}
		this.credits = credits;
	}
	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int capacity) {
		if (capacity <= 0 || capacity < enrolledStudents.size()) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}	
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}
	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * Returns the enrolled students as an array.
	 * @return enrolled students
	 */
	public Student [] getEnrolledStudents() {
		Student [] s = new Student[enrolledStudents.size()];
		return enrolledStudents.toArray(s);
	}

	/**
	 * Returns true if there is capacity to add a user to the course and the 
	 * user is not already enrolled.
	 * @param user User to add to the course
	 * @return true if there is capacity
	 */
	public boolean canEnroll(User user) {
		if (enrolledStudents.size() < capacity) {
			if (user instanceof Student) {
				Student s = (Student) user;
				for (int i = 0; i < enrolledStudents.size(); i++) {
					if (enrolledStudents.get(i).equals(s)) {
						return false;
					}
				}
				return true;
			}
			return false;
		}
		return false;
	}

	/**
	 * Enroll the user in the course if there is room.
	 * @param user user to enroll
	 * @return true if user is enrolled.
	 */
	public boolean enroll(User user) {
		return canEnroll(user) && enrolledStudents.add(user);
	}

	/**
	 * Drops the student from the course.
	 * @param user student to drop
	 * @return true if the student is dropped
	 */
	public boolean drop(User user) {
		return enrolledStudents.remove(user);
	}

	
}
