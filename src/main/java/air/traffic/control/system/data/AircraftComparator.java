package air.traffic.control.system.data;

import java.util.Comparator;

import air.traffic.control.system.model.Aircraft;

/**
 * Aircraft comparator
 * 
 * @author momintariq
 *
 */
public class AircraftComparator implements Comparator<Aircraft> {

	/**
	 * Compares one aircraft with another
	 */
	public int compare(final Aircraft ac1, final Aircraft ac2) {
		int comparison = 0;
		
		// Sort by type
		comparison = ac1.getType().compareTo(ac2.getType());
		
		// If type is equal, sort by size
		if(comparison == 0) {
			comparison = ac1.getSize().compareTo(ac2.getSize());
			
			// If size is equal, sort by id
			if(comparison == 0) {
				comparison = ac1.getId() - ac2.getId();
			}
		}
		return comparison;
	}
}
