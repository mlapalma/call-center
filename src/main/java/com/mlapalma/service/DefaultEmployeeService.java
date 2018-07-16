package com.mlapalma.service;

import com.mlapalma.model.Call;
import com.mlapalma.model.Employee;

/**
 * The type Default employee service.
 * This service allows consumers to assign and release calls to employees
 * It is used by the dispatcher
 */
public class DefaultEmployeeService implements EmployeeService {

	private Employee employee;

	public DefaultEmployeeService(Employee employee) {
		this.employee = employee;
	}

	@Override
	public boolean assignCall(Call call) {
		boolean callAssigned;
		if (employee.isAvailable()) {
			employee.answerCall(call);
			callAssigned = true;
		} else {
			callAssigned = false;
		}
		return callAssigned;
	}

	@Override
	public void releaseCall(Call call) {
		employee.setAvailable(true);
	}

}
