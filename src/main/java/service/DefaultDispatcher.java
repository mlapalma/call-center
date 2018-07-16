package service;

import java.util.Optional;
import java.util.logging.Logger;
import model.*;
import model.dto.CallContextDto;

/**
 * The type Default dispatcher.
 * This dispatcher is the object who manage calls
 */
public class DefaultDispatcher implements Dispatcher {

	private static final int CALLS_LIMIT = 10;
	private static final Logger logger = Logger.getLogger(DefaultDispatcher.class.getName());
	private static int numberOfCallsOnProcess = 0;
	private CallCenterService callCenterService;

	public DefaultDispatcher(CallCenterService callCenterService) {
		this.callCenterService = callCenterService;
	}

	public CallContextDto dispatchCall(Call call, Customer customer) {
		return manageCall(call, customer);
	}

	private synchronized Employee assignCall(Call call) {
		CallStatus status;
		Employee employee = assignToOperator(call);
		if (employee != null) {
			status = CallStatus.ANSWERED_BY_OPERATOR;
		} else {
			employee = assignToSupervisor(call);
			if (employee != null) {
				status = CallStatus.ANSWERED_BY_SUPERVISOR;
			} else {
				employee = assignToManager(call);
				if (employee != null) {
					status = CallStatus.ANSWERED_BY_MANAGER;
				} else {
					status = CallStatus.UNNATENDED_UNAVAILABLE_AGENTS;
				}
			}
		}
		call.setStatus(status);
		return employee;
	}

	private Employee assignToOperator(Call call) {
		Employee employee = null;
		Optional<Operator> operator = callCenterService.findFreeOperator();
		if (operator.isPresent()) {
			assignCallToEmployee(call, operator.get());
			employee = operator.get();
		}
		return employee;
	}

	private Employee assignToSupervisor(Call call) {
		Employee employee = null;
		Optional<Supervisor> supervisor = callCenterService.findFreeSupervisor();
		if (supervisor.isPresent()) {
			assignCallToEmployee(call, supervisor.get());
			employee = supervisor.get();
		}
		return employee;
	}

	private Employee assignToManager(Call call) {
		Employee employee = null;
		Optional<Manager> manager = callCenterService.findFreeManager();
		if (manager.isPresent()) {
			assignCallToEmployee(call, manager.get());
			employee = manager.get();
		}
		return employee;
	}

	private CallContextDto manageCall(Call call, Customer customer) {
		numberOfCallsOnProcess++;
		Employee employee = null;
		if (numberOfCallsOnProcess <= CALLS_LIMIT) {
			employee = assignCall(call);
			boolean answeredCall = false;
			if (employee != null) {
				logger.info(String.format("Init call wit customer: %s. Call status: %s. Answered by: %s %s",
						customer.getName(), call.getStatus(), employee.getFirstName(), employee.getLastName()));
				answeredCall = true;
				try {
					Thread.sleep(call.getDuration());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				call.setStatus(CallStatus.UNNATENDED_UNAVAILABLE_AGENTS);
			}

			if (answeredCall) {
				finishCall(call, employee);
				logger.info(String.format("Call with customer %s finished with duration: %d ms", customer.getName(),
						call.getDuration()));
			}
		} else {
			call.setStatus(CallStatus.UNNATTENDED_DISPATCHER_OVERFLOW);
		}
		return CallContextDto.newBuilder().withAnswerer(employee).withCustomer(customer).withCall(call).build();
	}

	private boolean assignCallToEmployee(Call call, Employee employee) {
		EmployeeService employeeService = new DefaultEmployeeService(employee);
		return employeeService.assignCall(call);
	}

	private synchronized boolean finishCall(Call call, Employee employee) {
		EmployeeService employeeService = new DefaultEmployeeService(employee);
		return employeeService.releaseCall(call);
	}
}
