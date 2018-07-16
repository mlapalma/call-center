package com.mlapalma.service;

import com.mlapalma.model.dto.CallContextDto;

public interface CustomerService {

	CallContextDto initNewCall(Dispatcher dispatcher);
}
