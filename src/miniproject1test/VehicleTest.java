package miniproject1test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import p1.Model;
import p1.Vehicle;

public class VehicleTest {
	private Model v1;
	public VehicleTest() {
	}

	@Before
	public void setUp() throws Exception {
		v1=new Vehicle("12345","Toyota","Corolla",1994,"blue",2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
