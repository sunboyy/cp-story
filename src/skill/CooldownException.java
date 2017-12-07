package skill;

public class CooldownException extends Exception {

	private static final long serialVersionUID = 8133223892223651655L;

	public CooldownException() {
		super();
	}
	
	public CooldownException(String message) {
		super(message);
	}
	
	public CooldownException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public CooldownException(Throwable cause) {
		super(cause);
	}

}
