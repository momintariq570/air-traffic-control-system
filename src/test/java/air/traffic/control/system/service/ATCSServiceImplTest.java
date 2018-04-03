package air.traffic.control.system.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import air.traffic.control.system.exception.ATCSNotBootedException;
import air.traffic.control.system.model.Aircraft;

public class ATCSServiceImplTest {
	
	private ATCSService atcsService;
	
	@Before
	public void setup() {
		atcsService = new ATCSServiceImpl();
	}
	
	/**
	 * Tests starting the ATCS service
	 */
	@Test
	public void testTurnOn() {
		assertThat(atcsService.boot()).isEqualTo(true);
	}
	
	/**
	 * Tests starting and turning off the ATCS service
	 */
	@Test
	public void testTurnOnAndOff() {
		assertThat(atcsService.boot()).isEqualTo(true);
		assertThat(atcsService.boot()).isEqualTo(false);
	}
	
	/**
	 * Tests enqueing while the ATCS service is turned off
	 * @throws ATCSNotBootedException
	 */
	@Test(expected = ATCSNotBootedException.class)
	public void testEnqueingWithoutTurnOn() throws ATCSNotBootedException {
		atcsService.enqueue(new Aircraft(1, Aircraft.Type.CARGO, Aircraft.Size.LARGE));
	}
	
	/**
	 * Tests starting the ATCS service, adding one aircraft, 
	 * verifying the addition of one aircraft and stopping
	 * the ATCS service
	 * @throws ATCSNotBootedException
	 */
	@Test
	public void testTurnOnEnqueueOneTurnOff() throws ATCSNotBootedException {
		Aircraft aircraft1 = new Aircraft(1, Aircraft.Type.EMERGENCY, Aircraft.Size.SMALL);
		assertThat(atcsService.boot()).isEqualTo(true);
		assertThat(atcsService.enqueue(aircraft1)).isEqualTo(true);
		assertThat(atcsService.list().size()).isEqualTo(1);
		assertThat(atcsService.list().contains(aircraft1)).isEqualTo(true);
		assertThat(atcsService.list().get(0)).isEqualTo(aircraft1);
		assertThat(atcsService.boot()).isEqualTo(false);
	}
	
	/**
	 * Tests starting the ATCS service, adding multiple aircrafts, 
	 * verifying the addition of multiple aircrafts and stopping
	 * the ATCS service
	 * @throws ATCSNotBootedException
	 */
	@Test
	public void testTurnOnEnqueueMultipleTurnOff() throws ATCSNotBootedException {
		Aircraft aircraft1 = new Aircraft(1, Aircraft.Type.CARGO, Aircraft.Size.LARGE);
		Aircraft aircraft2 = new Aircraft(2, Aircraft.Type.VIP, Aircraft.Size.SMALL);
		Aircraft aircraft3 = new Aircraft(3, Aircraft.Type.EMERGENCY, Aircraft.Size.LARGE);
		Aircraft aircraft4 = new Aircraft(4, Aircraft.Type.EMERGENCY, Aircraft.Size.SMALL);
		Aircraft aircraft5 = new Aircraft(5, Aircraft.Type.PASSENGER, Aircraft.Size.LARGE);
		assertThat(atcsService.boot()).isEqualTo(true);
		assertThat(atcsService.enqueue(aircraft1)).isEqualTo(true);
		assertThat(atcsService.enqueue(aircraft2)).isEqualTo(true);
		assertThat(atcsService.enqueue(aircraft3)).isEqualTo(true);
		assertThat(atcsService.enqueue(aircraft4)).isEqualTo(true);
		assertThat(atcsService.enqueue(aircraft5)).isEqualTo(true);
		assertThat(atcsService.list().size()).isEqualTo(5);
		assertThat(atcsService.list().contains(aircraft1)).isEqualTo(true);
		assertThat(atcsService.list().contains(aircraft2)).isEqualTo(true);
		assertThat(atcsService.list().contains(aircraft3)).isEqualTo(true);
		assertThat(atcsService.list().contains(aircraft4)).isEqualTo(true);
		assertThat(atcsService.list().contains(aircraft5)).isEqualTo(true);
		assertThat(atcsService.list().get(0)).isEqualTo(aircraft3);
		assertThat(atcsService.list().get(1)).isEqualTo(aircraft4);
		assertThat(atcsService.list().get(2)).isEqualTo(aircraft2);
		assertThat(atcsService.list().get(3)).isEqualTo(aircraft5);
		assertThat(atcsService.list().get(4)).isEqualTo(aircraft1);
		assertThat(atcsService.boot()).isEqualTo(false);
	}
	
	/**
	 * Tests starting the ATCS service, adding multiple aircrafts, 
	 * removing one aircraft, verifying the actions and stopping
	 * the ATCS service
	 * @throws ATCSNotBootedException
	 */
	@Test
	public void testTurnOnEnqueueMultipleDequeueOneTurnOff() throws ATCSNotBootedException {
		Aircraft aircraft1 = new Aircraft(1, Aircraft.Type.CARGO, Aircraft.Size.LARGE);
		Aircraft aircraft2 = new Aircraft(2, Aircraft.Type.VIP, Aircraft.Size.SMALL);
		Aircraft aircraft3 = new Aircraft(3, Aircraft.Type.EMERGENCY, Aircraft.Size.LARGE);
		Aircraft aircraft4 = new Aircraft(4, Aircraft.Type.EMERGENCY, Aircraft.Size.SMALL);
		Aircraft aircraft5 = new Aircraft(5, Aircraft.Type.PASSENGER, Aircraft.Size.LARGE);
		assertThat(atcsService.boot()).isEqualTo(true);
		assertThat(atcsService.enqueue(aircraft1)).isEqualTo(true);
		assertThat(atcsService.enqueue(aircraft2)).isEqualTo(true);
		assertThat(atcsService.enqueue(aircraft3)).isEqualTo(true);
		assertThat(atcsService.enqueue(aircraft4)).isEqualTo(true);
		assertThat(atcsService.enqueue(aircraft5)).isEqualTo(true);
		assertThat(atcsService.list().size()).isEqualTo(5);
		assertThat(atcsService.list().contains(aircraft1)).isEqualTo(true);
		assertThat(atcsService.list().contains(aircraft2)).isEqualTo(true);
		assertThat(atcsService.list().contains(aircraft3)).isEqualTo(true);
		assertThat(atcsService.list().contains(aircraft4)).isEqualTo(true);
		assertThat(atcsService.list().contains(aircraft5)).isEqualTo(true);
		assertThat(atcsService.list().get(0)).isEqualTo(aircraft3);
		assertThat(atcsService.list().get(1)).isEqualTo(aircraft4);
		assertThat(atcsService.list().get(2)).isEqualTo(aircraft2);
		assertThat(atcsService.list().get(3)).isEqualTo(aircraft5);
		assertThat(atcsService.list().get(4)).isEqualTo(aircraft1);
		assertThat(atcsService.dequeue()).isEqualTo(true);
		assertThat(atcsService.list().size()).isEqualTo(4);
		assertThat(atcsService.list().contains(aircraft3)).isEqualTo(false);
		assertThat(atcsService.list().get(0)).isEqualTo(aircraft4);
		assertThat(atcsService.boot()).isEqualTo(false);
	}
}
