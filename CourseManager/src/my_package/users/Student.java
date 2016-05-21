/**
 * 
 */
package my_package.users;

import my_package.courses.Course;

/**
 * @author jim2
 *
 */
public class Student extends User {

	/**
	 * @param firstName
	 * @param lastName
	 * @param id
	 * @param email
	 * @param password
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		super(firstName, lastName, id, email, password);
		// TODO Auto-generated constructor stub
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
