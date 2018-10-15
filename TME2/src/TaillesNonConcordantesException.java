
public class TaillesNonConcordantesException extends Exception {
	private String type;
	
	public TaillesNonConcordantesException(String type, String message) {
		super(message + " : " + type);
		this.type = type;
	}
}
