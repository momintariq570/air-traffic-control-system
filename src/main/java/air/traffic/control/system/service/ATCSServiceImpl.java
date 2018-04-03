package air.traffic.control.system.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import air.traffic.control.system.data.AircraftRepository;
import air.traffic.control.system.exception.ATCSNotBootedException;
import air.traffic.control.system.model.Aircraft;

@Service
public class ATCSServiceImpl implements ATCSService {
	
	@Autowired
	private AircraftRepository aircraftRepository;
	
	private boolean isATCSServiceRunning = false;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public ATCSServiceImpl() {
		aircraftRepository = new AircraftRepository();
	}
	
	/**
	 * Boots the air traffic control system
	 * @return boolean whether or not system has booted
	 */
	public boolean boot() {
		this.isATCSServiceRunning = !this.isATCSServiceRunning;
		if(this.isATCSServiceRunning) {
			logger.info("Turned on the ATCS system");	
		} else {
			logger.info("Turned off the ATCS system");
		}
		return this.isATCSServiceRunning;
	}

	/**
	 * Adds aircraft to a queue
	 * and sorts the queue
	 * @param aircraft new aircraft
	 * @return boolean whether or not aircraft was added to a queue
	 * @throws ATCSNotBootedException 
	 */
	public boolean enqueue(final Aircraft aircraft) throws ATCSNotBootedException {
		if(!this.isATCSServiceRunning) {
			throw new ATCSNotBootedException("ATCS is not running");
		}
		aircraftRepository.getAircraftsQueue().add(aircraft);
		aircraftRepository.sort();
		logger.info("Added an aircraft {} to the queue", aircraft);
		return true;
	}

	/**
	 * Removes aircraft from a queue
	 * @return boolean whether or not aircraft was removed from a queue
	 * @throws ATCSNotBootedException 
	 */
	public boolean dequeue() throws ATCSNotBootedException {
		if(!this.isATCSServiceRunning) {
			throw new ATCSNotBootedException("ATCS is not running");
		}
		Aircraft aircraft = aircraftRepository.getAircraftsQueue().remove(0);
		logger.info("Removed an aircraft {} from the queue", aircraft);
		return true;
	}

	/**
	 * Lists the aircrafts in the queue
	 * @return list representing the aircrafts in the queue
	 * @throws ATCSNotBootedException 
	 */
	public List<Aircraft> list() throws ATCSNotBootedException {
		if(!this.isATCSServiceRunning) {
			throw new ATCSNotBootedException("ATCS is not running");
		}
		logger.info("Retrieved the aircrafts queue");
		return aircraftRepository.getAircraftsQueue();
	}
	
	/**
	 * Gets running status of ATCS
	 */
	public boolean getIsATCSServiceRunning() {
		return this.isATCSServiceRunning;
	}
}
