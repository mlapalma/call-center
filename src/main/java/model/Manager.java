package model;

public class Manager extends Employee {

	private static Manager manager;

	private Manager(long id, String firstName, String lastName) {
		super(id, firstName, lastName);
	}

	/**
	 * Get manager instance. Manager class apllies to a singleton pattern, because it can be only one instance of this type.
	 *
	 * @param id        the id
	 * @param firstName the first name
	 * @param lastName  the last name
	 * @return the manager
	 */
	public static Manager getManagerInstance(long id, String firstName, String lastName){
		if(manager==null){
			manager = new Manager(id,firstName,lastName);
		}

		return manager;
	}

}
