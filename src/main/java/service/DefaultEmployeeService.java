package service;

import model.Call;
import model.Employee;

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
	public boolean releaseCall(Call call) {
		boolean callReleased;
		if (!employee.isAvailable()) {
			employee.setAvailable(true);
			callReleased = true;
		} else {
			callReleased = false;
		}
		return callReleased;
	}

}
