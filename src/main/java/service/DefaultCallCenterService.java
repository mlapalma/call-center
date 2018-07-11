package service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import model.CallCenter;
import model.Manager;
import model.Operator;
import model.Supervisor;

public class DefaultCallCenterService implements CallCenterService {

	private CallCenter callCenter;

	public DefaultCallCenterService(CallCenter callCenter) {
		this.callCenter = callCenter;
	}

	public Optional<Supervisor> findFreeSupervisor(){
		List<Supervisor> supervisorList = callCenter.getSupervisors().stream().filter(Supervisor::isAvailable).collect(Collectors.toList());
		return Optional.ofNullable(supervisorList.get(0));
	}

	public Optional<Operator> findFreeOperator(){
		List<Operator> freeOperators = callCenter.getOperators().stream().filter(Operator::isAvailable).collect(
				Collectors.toList());
		return Optional.ofNullable(freeOperators.get(0));
	}

	public Optional<Manager> findManager(){
		return Optional.of(callCenter.getManager());
	}

}
