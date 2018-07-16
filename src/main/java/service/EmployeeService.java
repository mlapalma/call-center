package service;

import model.Call;
import model.CallStatus;

public interface EmployeeService {

	boolean assignCall(Call call);
	boolean releaseCall(Call call);

}
