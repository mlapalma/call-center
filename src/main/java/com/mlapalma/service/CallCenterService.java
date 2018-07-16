package com.mlapalma.service;

import java.util.Optional;
import com.mlapalma.model.Manager;
import com.mlapalma.model.Operator;
import com.mlapalma.model.Supervisor;

public interface CallCenterService {

	Optional<Supervisor> findFreeSupervisor();

	Optional<Operator> findFreeOperator();

	Optional<Manager> findFreeManager();

}
