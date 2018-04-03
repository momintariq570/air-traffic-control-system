package air.traffic.control.system.exception;

/**
 * Exception when ATCS is not booted
 * 
 * @author momintariq
 *
 */
public class ATCSNotBootedException extends Exception {

	/**
	 * Constructor
	 * @param message exception message
	 */
	public ATCSNotBootedException(final String message) {
		super(message);
	}
}
