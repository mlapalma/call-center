package service;

import java.util.Optional;
import model.*;

public class DefaultDispatcher implements Dispatcher {

	private static final int CALLS_LIMIT=10;

	private CallCenterService callCenterService;

	public DefaultDispatcher(CallCenterService callCenterService) {
		this.callCenterService = callCenterService;
	}

	public CallStatus dispatchCall() {
		CallStatus status;
		if(assignToOperator()){
			status = CallStatus.ATTENDED_BY_OPERATOR;
		} else if (assignToSupervisor()) {
			status = CallStatus.ATTENDED_BY_SUPERVISOR;
		} else if (assignToManager()) {
			status = CallStatus.ATTENDED_BY_MANAGER;
		} else {
			status = CallStatus.UNNATENDED_UNAVAILABLE_AGENTS;
		}
		return status;
	}

	private boolean assignToOperator(){
		boolean assigned;
		Optional<Operator> operator = callCenterService.findFreeOperator();
		if (operator.isPresent()){
			EmployeeService employeeService=new DefaultEmployeeService(operator.get());
			assigned = employeeService.assignCall();
		} else {
			assigned=false;
		}
		return assigned;
	}

	private boolean assignToSupervisor(){
		boolean assigned;
		Optional<Supervisor> supervisor = callCenterService.findFreeSupervisor();
		if (supervisor.isPresent()){
			EmployeeService employeeService=new DefaultEmployeeService(supervisor.get());
			assigned = employeeService.assignCall();
		} else {
			assigned=false;
		}
		return assigned;
	}

	private boolean assignToManager(){
		boolean assigned;
		Optional<Manager> manager = callCenterService.findManager();
		if (manager.isPresent()){
			EmployeeService employeeService=new DefaultEmployeeService(manager.get());
			assigned = employeeService.assignCall();
		} else {
			assigned=false;
		}
		return assigned;
	}

}
