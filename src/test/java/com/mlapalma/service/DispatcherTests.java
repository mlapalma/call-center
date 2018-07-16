package com.mlapalma.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import com.mlapalma.model.*;
import com.mlapalma.model.dto.CallContextDto;

@RunWith(JUnit4.class)
public class DispatcherTests {

	private static CallCenter callCenter;
	private static CallCenterService callCenterService;
	private static List<Customer> customers;
	private static List<Call> calls;

	@BeforeClass
	public static void init() {
		List<Operator> operators = mockOperators();
		List<Supervisor> supervisors = mockSupervisors();
		customers = mockCustomers();
		Manager manager = mockManager();
		callCenter = new CallCenter(manager, supervisors, operators);
		callCenterService = new DefaultCallCenterService(callCenter);
		calls = mockCalls();
	}

	private static List<Operator> mockOperators() {
		List<Operator> operators = new ArrayList<Operator>();
		operators.add(new Operator(0, "Juan", "Perez"));
		operators.add(new Operator(1, "Damian", "Sanchez"));
		operators.add(new Operator(2, "Yamila", "Torres"));
		operators.add(new Operator(3, "Camilo", "Garcia"));
		operators.add(new Operator(4, "Antonella", "Caceres"));
		return operators;
	}

	private static List<Supervisor> mockSupervisors() {
		List<Supervisor> supervisors = new ArrayList<Supervisor>();
		supervisors.add(new Supervisor(0, "Mabel", "Morales"));
		supervisors.add(new Supervisor(1, "Sebastian", "Puerta"));
		return supervisors;
	}

	private static Manager mockManager() {
		return new Manager(0, "Carlos", "Muñoz");
	}

	private static List<Customer> mockCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(new Customer(0, "Isabel"));
		customers.add(new Customer(1, "Benjamin"));
		customers.add(new Customer(2, "Adrian"));
		customers.add(new Customer(3, "Marcela"));
		customers.add(new Customer(4, "Pedro"));
		customers.add(new Customer(5, "Jeronimo"));
		customers.add(new Customer(6, "Gustavo"));
		customers.add(new Customer(7, "Fernanda"));
		customers.add(new Customer(8, "Rocío"));
		customers.add(new Customer(9, "Raul"));
		customers.add(new Customer(10, "Ariel"));
		customers.add(new Customer(11, "Matias"));
		return customers;
	}

	private static List<Call> mockCalls() {
		List<Call> calls = new ArrayList<Call>();
		calls.add(new Call(0));
		calls.add(new Call(1));
		calls.add(new Call(2));
		calls.add(new Call(3));
		calls.add(new Call(4));
		calls.add(new Call(5));
		calls.add(new Call(6));
		calls.add(new Call(7));
		calls.add(new Call(8));
		calls.add(new Call(9));
		calls.add(new Call(10));
		calls.add(new Call(11));
		return calls;
	}

	private List<DefaultCustomerServiceCallable> createCustomerServiceList(List<Customer> customers, List<Call> calls,
			Dispatcher dispatcher) {
		List<DefaultCustomerServiceCallable> customerServiceList = new ArrayList<>();
		customers.stream().forEach(customer -> {
			DefaultCustomerServiceCallable customerService = new DefaultCustomerServiceCallable(customer, dispatcher,
					calls.get((int) customer.getId()));
			customerServiceList.add(customerService);
		});

		return customerServiceList;
	}

	private List<Future<CallContextDto>> createFutureThreadTasks(
			List<DefaultCustomerServiceCallable> customerServiceList, ExecutorService executorService) {
		List<Future<CallContextDto>> futureTasks = new ArrayList<>();
		customerServiceList.stream().forEach(customerService -> {
			Future<CallContextDto> future = executorService.submit(customerService);
			futureTasks.add(future);
		});

		return futureTasks;
	}

	@Test
	public void anEmployeeShouldBeAbleToAnswerAndFinishACall() {

		assertEquals(true, callCenter.getOperators().get(0).isAvailable());
		callCenter.getOperators().get(0).answerCall(calls.get(0));
		assertEquals(false, callCenter.getOperators().get(0).isAvailable());
		callCenter.getOperators().get(0).finishCall(calls.get(0));
		assertEquals(true, callCenter.getOperators().get(0).isAvailable());

	}

	@Test
	public void callCenterServiceShouldFindAnAvailableOperator() {
		assertEquals("Juan", callCenterService.findFreeOperator().get().getFirstName());
	}

	@Test
	public void callCenterServiceShouldFindAnAvailableSupervisor() {
		assertEquals("Mabel", callCenterService.findFreeSupervisor().get().getFirstName());
	}

	@Test
	public void callCenterServiceShouldFindManager() {
		assertEquals("Carlos", callCenterService.findFreeManager().get().getFirstName());
	}

	@Test
	public void newCallMustHaveDurationBetweenLimits() {
		long minCallDurationMs = 5001;
		long maxCallDurationMs = 10001;
		Call call = new Call(1);
		assertTrue(call.getDuration() > minCallDurationMs && call.getDuration() < maxCallDurationMs);
	}

	@Test
	public void testFullScenario() {

		ExecutorService pool = Executors.newFixedThreadPool(12);

		Dispatcher dispatcher = new DefaultDispatcher(callCenterService);
		Set<Future<CallContextDto>> set = new HashSet<Future<CallContextDto>>();

		List<DefaultCustomerServiceCallable> customerServiceCallables = createCustomerServiceList(customers, calls,
				dispatcher);
		List<Future<CallContextDto>> futureThreadTasks = createFutureThreadTasks(customerServiceCallables, pool);

		futureThreadTasks.stream().map(set::add);

		List<CallContextDto> callContextDtos = futureThreadTasks.stream().map(task -> {
			CallContextDto callContextDto = null;
			try {
				callContextDto = task.get();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return callContextDto;
		}).collect(Collectors.toList());

		//This will print call summary
		//CallCenter have 8 agents: 5 operators, 2 supervisors, 1 manager.
		//In the context of process 12 calls the results must be:
		//5 calls will be answered by operators.
		//2 calls will be answered by supervisors.
		//1 call will be answered by manager
		//2 calls will be unnatended because of unavailable agents
		//2 last calls will be unnatended by dispatcher overflow capacity
		callContextDtos.stream().forEach(item -> {
			String message = String
					.format("Customer: %s, Status: %s", item.getCustomer().getName(), item.getCall().getStatus());
			System.out.println(message);
		});
	}

}
