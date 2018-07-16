package service;

import model.Call;
import model.Customer;
import model.dto.CallContextDto;

public interface Dispatcher {

	CallContextDto dispatchCall(Call call, Customer customer);

}
