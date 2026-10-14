package jsp.springbankapp.exception;

public class DuplicateAccountNumberException extends RuntimeException {
	public  DuplicateAccountNumberException(String message) {
		super(message);
	}

}
