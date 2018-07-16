package com.mlapalma.model;

import java.util.List;

/**
 * The type Call center is composite of a manager, supervisors and operators.
 */
public class CallCenter {

	private Manager manager;
	private List<Supervisor> supervisors;
	private List<Operator> operators;

	public CallCenter(Manager manager, List<Supervisor> supervisors, List<Operator> operators) {
		this.manager = manager;
		this.supervisors = supervisors;
		this.operators = operators;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public List<Supervisor> getSupervisors() {
		return supervisors;
	}

	public void setSupervisors(List<Supervisor> supervisors) {
		this.supervisors = supervisors;
	}

	public List<Operator> getOperators() {
		return operators;
	}

	public void setOperators(List<Operator> operators) {
		this.operators = operators;
	}

	@Override
	public String toString() {
		return "CallCenter{" + "manager=" + manager + ", supervisors=" + supervisors + ", operators=" + operators + '}';
	}
}
