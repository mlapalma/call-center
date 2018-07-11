package model;

public abstract class Employee implements CallAgent{

	private long id;
	private String firstName;
	private String lastName;
	private boolean available = true;

	public Employee(long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Getter for property 'id'.
	 *
	 * @return Value for property 'id'.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Setter for property 'id'.
	 *
	 * @param id Value to set for property 'id'.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Getter for property 'firstName'.
	 *
	 * @return Value for property 'firstName'.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter for property 'firstName'.
	 *
	 * @param firstName Value to set for property 'firstName'.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter for property 'lastName'.
	 *
	 * @return Value for property 'lastName'.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter for property 'lastName'.
	 *
	 * @param lastName Value to set for property 'lastName'.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter for property 'available'.
	 *
	 * @return Value for property 'available'.
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * Setter for property 'available'.
	 *
	 * @param available Value to set for property 'available'.
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public void answerCall() {
		this.setAvailable(false);
	}

	@Override
	public void finishCall() {
		this.setAvailable(true);
	}

	@Override
	public String toString() {
		return "Employee{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
				+ ", available=" + available + '}';
	}
}
