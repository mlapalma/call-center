package service;

import model.dto.CallContextDto;

public interface CustomerService {

	CallContextDto initNewCall(Dispatcher dispatcher);
}
