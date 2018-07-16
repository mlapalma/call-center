package com.mlapalma.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.mlapalma.model.CallCenter;
import com.mlapalma.model.Manager;
import com.mlapalma.model.Operator;
import com.mlapalma.model.Supervisor;

/**
 * The type Default call center service.
 * The call center service is encharged of find free operators, supervisors or managers
 * It will need a CallCenter object in order to find this Agents
 */
public class DefaultCallCenterService implements CallCenterService {

	private CallCenter callCenter;

	public DefaultCallCenterService(CallCenter callCenter) {
		this.callCenter = callCenter;
	}

	public Optional<Supervisor> findFreeSupervisor() {
		Optional<Supervisor> supervisor = Optional.empty();
		List<Supervisor> freeSupervisors = callCenter.getSupervisors().stream().filter(Supervisor::isAvailable)
				.collect(Collectors.toList());
		if (!freeSupervisors.isEmpty()) {
			supervisor = Optional.of(freeSupervisors.get(0));
		}
		return supervisor;
	}

	public Optional<Operator> findFreeOperator() {
		Optional<Operator> operator = Optional.empty();
		List<Operator> freeOperators = callCenter.getOperators().stream().filter(Operator::isAvailable)
				.collect(Collectors.toList());
		if (!freeOperators.isEmpty()) {
			operator = Optional.of(freeOperators.get(0));
		}
		return operator;
	}

	public Optional<Manager> findFreeManager() {
		Optional<Manager> manager = Optional.empty();
		if (callCenter.getManager().isAvailable()) {
			manager = Optional.of(callCenter.getManager());
		}
		return manager;
	}

}
