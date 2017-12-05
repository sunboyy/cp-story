package utility;

public class NegativeWeightedRandomException extends Exception {

	private static final long serialVersionUID = 6521495151125732029L;

	public NegativeWeightedRandomException() {
		super();
	}
	
	public NegativeWeightedRandomException(String message) {
		super(message);
	}
	
	public NegativeWeightedRandomException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public NegativeWeightedRandomException(Throwable cause) {
		super(cause);
	}
	
}
