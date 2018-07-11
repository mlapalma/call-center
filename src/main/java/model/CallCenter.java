package model;

import java.util.List;

public class CallCenter {

	private Manager manager;
	private List<Supervisor> supervisors;
	private List<Operator> operators;

	public CallCenter(Manager manager, List<Supervisor> supervisors, List<Operator> operators) {
		this.manager = manager;
		this.supervisors = supervisors;
		this.operators = operators;
	}

	/**
	 * Getter for property 'manager'.
	 *
	 * @return Value for property 'manager'.
	 */
	public Manager getManager() {
		return manager;
	}

	/**
	 * Setter for property 'manager'.
	 *
	 * @param manager Value to set for property 'manager'.
	 */
	public void setManager(Manager manager) {
		this.manager = manager;
	}

	/**
	 * Getter for property 'supervisors'.
	 *
	 * @return Value for property 'supervisors'.
	 */
	public List<Supervisor> getSupervisors() {
		return supervisors;
	}

	/**
	 * Setter for property 'supervisors'.
	 *
	 * @param supervisors Value to set for property 'supervisors'.
	 */
	public void setSupervisors(List<Supervisor> supervisors) {
		this.supervisors = supervisors;
	}

	/**
	 * Getter for property 'operators'.
	 *
	 * @return Value for property 'operators'.
	 */
	public List<Operator> getOperators() {
		return operators;
	}

	/**
	 * Setter for property 'operators'.
	 *
	 * @param operators Value to set for property 'operators'.
	 */
	public void setOperators(List<Operator> operators) {
		this.operators = operators;
	}

	@Override
	public String toString() {
		return "CallCenter{" + "manager=" + manager + ", supervisors=" + supervisors + ", operators=" + operators + '}';
	}
}
