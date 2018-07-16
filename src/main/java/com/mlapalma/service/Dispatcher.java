package com.mlapalma.service;

import com.mlapalma.model.Call;
import com.mlapalma.model.Customer;
import com.mlapalma.model.dto.CallContextDto;

public interface Dispatcher {

	CallContextDto dispatchCall(Call call, Customer customer);

}
