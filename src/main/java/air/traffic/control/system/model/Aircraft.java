package air.traffic.control.system.model;

/**
 * Model class to hold aircraft
 * type, size and id
 * 
 * @author momintariq
 *
 */
public class Aircraft {
	
	/**
	 * Aircraft enum types
	 * defined in order of priority
	 * 
	 * @author momintariq
	 *
	 */
	public enum Type {
		EMERGENCY, VIP, PASSENGER, CARGO
	}
	
	/**
	 * Aircraft enum sizes
	 * defined in order of priority
	 * 
	 * @author momintariq
	 *
	 */
	public enum Size {
		LARGE, SMALL
	}

	private int id;
	private Type type;
	private Size size;
	
	/**
	 * Constructor
	 * @param id aircraft id
	 * @param type aircraft type
	 * @param size aircraft size
	 */
	public Aircraft(final int id, final Type type, final Size size) {
		super();
		this.id = id;
		this.type = type;
		this.size = size;
	}

	/**
	 * id getter
	 * @return aircraft id
	 */
	public int getId() {
		return id;
	}

	/**
	 * id setter
	 * @param id aircraft id
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * type getter
	 * @return aircraft type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * type setter
	 * @param type aircraft type
	 */
	public void setType(final Type type) {
		this.type = type;
	}

	/**
	 * size getter
	 * @return aircraft size
	 */
	public Size getSize() {
		return size;
	}

	/**
	 * size setter
	 * @param size aircraft size
	 */
	public void setSize(final Size size) {
		this.size = size;
	}
	
	/**
	 * Returns a string representation of the aircraft object
	 */
	public String toString() {
		return "{"
				+ "id: " + this.id
				+ "\ttype: " + this.type 
				+ "\tsize: " + this.size
				+ "}";
	}
}
