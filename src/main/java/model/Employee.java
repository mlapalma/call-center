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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isAvailable() {
		return available;
	}

	public String getFullName(){
		return String.format("%s %s",getFirstName(),getLastName());
	}

	public synchronized void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public void answerCall(Call call) {
		this.setAvailable(false);
	}

	@Override
	public void finishCall(Call call) {
		this.setAvailable(true);
		call.setStatus(CallStatus.FINISHED);
	}

	@Override
	public String toString() {
		return "Employee{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
				+ ", available=" + available + '}';
	}
}
