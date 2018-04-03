package air.traffic.control.system.service;

import java.util.List;

import air.traffic.control.system.exception.ATCSNotBootedException;
import air.traffic.control.system.model.Aircraft;

/**
 * Interface to declare type and methods
 * of the ATCS service
 * 
 * @author momintariq
 *
 */
public interface ATCSService {

	/**
	 * Boots the air traffic control system
	 * @return boolean whether or not system has booted
	 */
	public boolean boot();
	
	/**
	 * Adds aircraft to a queue
	 * @param aircraft
	 * @return boolean whether or not aircraft was added to a queue
	 * @throws ATCSNotBootedException 
	 */
	public boolean enqueue(final Aircraft aircraft) throws ATCSNotBootedException;
	
	/**
	 * Removes aircraft from a queue
	 * @return boolean whether or not aircraft was removed from a queue
	 */
	public boolean dequeue() throws ATCSNotBootedException;
	
	/**
	 * Lists the aircrafts in the queue
	 * @return list representing the aircrafts in the queue
	 */
	public List<Aircraft> list() throws ATCSNotBootedException;
	
	/**
	 * Gets the running status of ATCS
	 * @return
	 */
	public boolean getIsATCSServiceRunning();
}
