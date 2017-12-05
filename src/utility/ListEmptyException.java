package utility;

public class ListEmptyException extends Exception {

	private static final long serialVersionUID = 4586090040773784993L;

	public ListEmptyException() {
		super();
	}
	
	public ListEmptyException(String message) {
		super(message);
	}
	
	public ListEmptyException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ListEmptyException(Throwable cause) {
		super(cause);
	}
	
}
