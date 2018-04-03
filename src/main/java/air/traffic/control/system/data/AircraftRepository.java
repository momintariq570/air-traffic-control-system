package air.traffic.control.system.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import air.traffic.control.system.model.Aircraft;

/**
 * Repository that holds the data structure
 * where aircrafts are stored in the queue
 * 
 * @author momintariq
 *
 */
@Repository
public class AircraftRepository {

	private List<Aircraft> aircraftsQueue;
	
	/**
	 * Constructor
	 */
	public AircraftRepository() {
		aircraftsQueue = new ArrayList<Aircraft>();
	}

	/**
	 * Returns the queue
	 * @return queue
	 */
	public List<Aircraft> getAircraftsQueue() {
		return aircraftsQueue;
	}
	
	/**
	 * Sorts the queue
	 */
	public void sort() {
		Collections.sort(this.aircraftsQueue, new AircraftComparator());
	}
}
