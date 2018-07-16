package service;

import java.util.concurrent.Callable;
import model.Call;
import model.Customer;
import model.dto.CallContextDto;

public class DefaultCustomerServiceCallable implements CustomerService, Callable<CallContextDto> {
	private Customer customer;
	private Dispatcher dispatcher;
	private Call call;

	public DefaultCustomerServiceCallable(Customer customer, Dispatcher dispatcher, Call call) {
		this.customer = customer;
		this.dispatcher = dispatcher;
		this.call = call;
	}

	@Override
	public CallContextDto initNewCall(Dispatcher dispatcher) {
		return dispatcher.dispatchCall(call, customer);
	}

	@Override
	public CallContextDto call() {
		return initNewCall(dispatcher);
	}
}
