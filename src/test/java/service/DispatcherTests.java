package service;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import model.*;

@RunWith(JUnit4.class)
public class DispatcherTests {

	private static CallCenter callCenter;
	private static CallCenterService callCenterService;

	@BeforeClass
	public static void init(){
		List<Operator> operators = mockOperators();
		List<Supervisor> supervisors = mockSupervisors();
		Manager manager = Manager.getManagerInstance(1,"Carlos","Muñoz");
		callCenter = new CallCenter(manager,supervisors,operators);
		callCenterService = new DefaultCallCenterService(callCenter);
	}

	@Test
	public void managerShouldBeASingleton(){
		Manager manager = Manager.getManagerInstance(2,"Andres","Iriarte");

		assertEquals(1, manager.getId());
		assertEquals("Carlos", manager.getFirstName());
		assertEquals("Muñoz", manager.getLastName());
	}

	@Test
	public void anEmployeeShouldBeAbleToAnswerAndFinishACall(){

		assertEquals(true,callCenter.getOperators().get(0).isAvailable());
		callCenter.getOperators().get(0).answerCall();
		assertEquals(false,callCenter.getOperators().get(0).isAvailable());
		callCenter.getOperators().get(0).finishCall();
		assertEquals(true,callCenter.getOperators().get(0).isAvailable());

	}

	@Test
	public void callCenterServiceShouldFindAnAvailableOperator(){
		assertEquals("Juan",callCenterService.findFreeOperator().get().getFirstName());
	}

	@Test
	public void callCenterServiceShouldFindAnAvailableSupervisor(){
		assertEquals("Mabel",callCenterService.findFreeSupervisor().get().getFirstName());
	}

	@Test
	public void callCenterServiceShouldFindManager(){
		assertEquals("Carlos",callCenterService.findManager().get().getFirstName());
	}

	private static List<Operator> mockOperators(){
		List<Operator> operators = new ArrayList<Operator>();
		operators.add(new Operator(1,"Juan","Perez"));
		operators.add(new Operator(2,"Damian","Sanchez"));
		operators.add(new Operator(3,"Yamila","Torres"));
		operators.add(new Operator(4,"Camilo","Garcia"));
		operators.add(new Operator(5,"Antonella","Caceres"));
		operators.add(new Operator(6,"Adriana","Lopez"));
		operators.add(new Operator(7,"Daniel","Galarza"));
		return operators;
	}

	private static List<Supervisor> mockSupervisors(){
		List<Supervisor> supervisors = new ArrayList<Supervisor>();
		supervisors.add(new Supervisor(1,"Mabel","Morales"));
		supervisors.add(new Supervisor(2,"Sebastian","Puerta"));
		return supervisors;
	}

}
