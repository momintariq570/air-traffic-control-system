package air.traffic.control.system.web;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import air.traffic.control.system.exception.ATCSNotBootedException;
import air.traffic.control.system.model.Aircraft;
import air.traffic.control.system.model.Aircraft.Size;
import air.traffic.control.system.model.Aircraft.Type;
import air.traffic.control.system.service.ATCSService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = ATCSController.PATH)
public class ATCSController {

	static final String PATH = "/atcs";
	
	private final AtomicInteger aircraftId = new AtomicInteger();
	
	private static final Logger logger = LoggerFactory.getLogger(ATCSController.class);
	
	@Autowired
	ATCSService atcsService;
	
	/**
	 * Returns the status of ATCS service
	 * @return boolean ATCS service running status
	 */
	@GetMapping("/atcs-status")
	public ResponseEntity<Boolean> getATCSStatus() {
		return ResponseEntity.ok().body(atcsService.getIsATCSServiceRunning());
	}
	
	/**
	 * Boots the ATCS service
	 * @return list of ordered aircrafts
	 * @throws ATCSNotBootedException 
	 */
	@GetMapping("/boot")
	public ResponseEntity<List<Aircraft>> startATCS() throws ATCSNotBootedException {
		boolean atcsStatus = atcsService.boot();
		if(atcsStatus) {
			return ResponseEntity.ok().body(atcsService.list());	
		} else {
			return ResponseEntity.ok().body(null);
		}
	}
	
	/**
	 * Inserts an aircraft into the queue
	 * @param aircraftType aircraft's type
	 * @param size aircraft's size
	 * @return list of ordered aircrafts
	 * @throws ATCSNotBootedException
	 */
	@GetMapping("/enqueue/{aircraftType}/{size}")
	public ResponseEntity<List<Aircraft>> insertAircraft(
			final @PathVariable Type aircraftType, 
			final @PathVariable Size size) throws ATCSNotBootedException {
		logger.info("Received a request to add an aircraft");
		Aircraft aircraft = new Aircraft(aircraftId.incrementAndGet(), aircraftType, size);
		atcsService.enqueue(aircraft);
		return ResponseEntity.ok().body(atcsService.list());
	}
	
	/**
	 * Removes an aircraft from the queue
	 * @return list of ordered aircrafts
	 * @throws ATCSNotBootedException
	 */
	@GetMapping("/dequeue")
	public ResponseEntity<List<Aircraft>> removeAircraft() throws ATCSNotBootedException {
		logger.info("Received a request to remove an aircraft");
		atcsService.dequeue();
		return ResponseEntity.ok().body(atcsService.list());
	}
	
	/**
	 * Sends the aircrafts queue
	 * @return list of ordered aircrafts
	 * @throws ATCSNotBootedException
	 */
	@GetMapping("/queue")
	public ResponseEntity<List<Aircraft>> getAircraftsQueue() throws ATCSNotBootedException {
		logger.info("Received a request to send aircrafts queue");
		List<Aircraft> aircraftsQueue = atcsService.list();
		return ResponseEntity.ok().body(aircraftsQueue);
	}
}
