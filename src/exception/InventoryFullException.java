package exception;

public class InventoryFullException extends Exception {

	private static final long serialVersionUID = -5271145235155303769L;

	public InventoryFullException() {
		super();
	}
	
	public InventoryFullException(String message) {
		super(message);
	}
	
	public InventoryFullException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public InventoryFullException(Throwable cause) {
		super(cause);
	}
	
}
