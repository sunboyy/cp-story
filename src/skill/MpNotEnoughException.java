package skill;

public class MpNotEnoughException extends Exception {

	private static final long serialVersionUID = 4986733966665870045L;
	
	public MpNotEnoughException() {
		super();
	}
	
	public MpNotEnoughException(String message) {
		super(message);
	}
	
	public MpNotEnoughException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public MpNotEnoughException(Throwable cause) {
		super(cause);
	}

}
