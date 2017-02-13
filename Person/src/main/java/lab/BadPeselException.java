package lab;

/**
 * Created by Monika_ on 2016-11-10.
 */
public class BadPeselException extends RuntimeException {
	public BadPeselException() {
	}
	
	public BadPeselException(String message) {
		super(message);
	}
}
