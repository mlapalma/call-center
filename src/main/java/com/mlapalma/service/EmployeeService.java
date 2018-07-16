package com.mlapalma.service;

import com.mlapalma.model.Call;

public interface EmployeeService {

	boolean assignCall(Call call);

	void releaseCall(Call call);

}
