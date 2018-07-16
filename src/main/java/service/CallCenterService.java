package service;

import java.util.Optional;
import model.Manager;
import model.Operator;
import model.Supervisor;

public interface CallCenterService {

	Optional<Supervisor> findFreeSupervisor();

	Optional<Operator> findFreeOperator();

	Optional<Manager> findFreeManager();

}
