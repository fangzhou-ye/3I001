
public class DataNotFoundException extends Exception {
	private String message;
	
	public DataNotFoundException(String msg) {
		super();
		message = msg;
		System.out.println("Exception: " + message);
	}
}
