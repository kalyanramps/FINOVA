package jsp.springbankapp.exception;

public class AddressRequiredException extends RuntimeException {
	public AddressRequiredException(String message) {
		super(message);
	}

}
