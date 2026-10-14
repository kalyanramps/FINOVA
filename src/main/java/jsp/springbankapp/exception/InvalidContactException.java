package jsp.springbankapp.exception;

public class InvalidContactException  extends RuntimeException{
	public InvalidContactException(String message) {
		super(message);
	}

}
