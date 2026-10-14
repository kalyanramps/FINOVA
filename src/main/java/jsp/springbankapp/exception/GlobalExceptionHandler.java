package jsp.springbankapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jsp.springbankapp.dto.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleINFE(IdNotFoundException exception) {
		ResponseStructure<String> res = new ResponseStructure<String>();
		res.setStatusCode(HttpStatus.NOT_FOUND.value());
		res.setMessage(exception.getMessage());
		res.setData("Failure");

		return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoRecordsAvailableException.class)
	public ResponseEntity<ResponseStructure<String>> handleNRAE(NoRecordsAvailableException exception) {
		ResponseStructure<String> res1 = new ResponseStructure<String>();
		res1.setStatusCode(HttpStatus.NOT_FOUND.value());
		res1.setMessage(exception.getMessage());
		res1.setData("Failure");

		return new ResponseEntity<ResponseStructure<String>>(res1, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(DuplicateIfscException.class)
	public ResponseEntity<ResponseStructure<String>> handleDIE(DuplicateIfscException exception) {
		ResponseStructure<String> res = new ResponseStructure<String>();
		res.setStatusCode(HttpStatus.CONFLICT.value());
		res.setMessage(exception.getMessage());
		res.setData("Failure");

		return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(DuplicateContactException.class)
	public ResponseEntity<ResponseStructure<String>> handleDCE(DuplicateContactException exception) {
		ResponseStructure<String> res = new ResponseStructure<String>();
		res.setStatusCode(HttpStatus.CONFLICT.value());
		res.setMessage(exception.getMessage());
		res.setData("Failure");

		return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ActiveAccountException.class)
	public ResponseEntity<ResponseStructure<String>> handleAAC(ActiveAccountException exception) {
		ResponseStructure<String> res = new ResponseStructure<String>();
		res.setStatusCode(HttpStatus.CONFLICT.value());
		res.setMessage(exception.getMessage());
		res.setData("Failure");

		return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(InvalidContactException.class)
	public ResponseEntity<ResponseStructure<String>> handleICE(InvalidContactException exception) {
		ResponseStructure<String> res = new ResponseStructure<String>();
		res.setStatusCode(HttpStatus.BAD_REQUEST.value());
		res.setMessage(exception.getMessage());
		res.setData("Failure");

		return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AddressRequiredException.class)
	public ResponseEntity<ResponseStructure<String>> handleARE(AddressRequiredException exception) {
		ResponseStructure<String> res = new ResponseStructure<String>();
		res.setStatusCode(HttpStatus.BAD_REQUEST.value());
		res.setMessage(exception.getMessage());
		res.setData("Failure");

		return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PincodeDuplicateException.class)
	public ResponseEntity<ResponseStructure<String>> handlePDE(PincodeDuplicateException exception) {
		ResponseStructure<String> res = new ResponseStructure<String>();
		res.setStatusCode(HttpStatus.CONFLICT.value());
		res.setMessage(exception.getMessage());
		res.setData("Failure");

		return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(InvalidPincodeException.class)
	public ResponseEntity<ResponseStructure<String>> handleIPE(InvalidPincodeException exception) {
		ResponseStructure<String> res = new ResponseStructure<String>();
		res.setStatusCode(HttpStatus.BAD_REQUEST.value());
		res.setMessage(exception.getMessage());
		res.setData("Failure");

		return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DuplicateAccountNumberException.class)
	public ResponseEntity<ResponseStructure<String>> handleDAE(DuplicateAccountNumberException exception) {
		ResponseStructure<String> res = new ResponseStructure<String>();
		res.setStatusCode(HttpStatus.BAD_REQUEST.value());
		res.setMessage(exception.getMessage());
		res.setData("Failure");

		return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<ResponseStructure<String>> handleIBE(InsufficientBalanceException exception) {
		ResponseStructure<String> res = new ResponseStructure<String>();
		res.setStatusCode(HttpStatus.BAD_REQUEST.value());
		res.setMessage(exception.getMessage());
		res.setData("Failure");

		return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidAmountException.class)
	public ResponseEntity<ResponseStructure<String>> handleIAE(InvalidAmountException exception) {
		ResponseStructure<String> res = new ResponseStructure<String>();
		res.setStatusCode(HttpStatus.BAD_REQUEST.value());
		res.setMessage(exception.getMessage());
		res.setData("Failure");

		return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.BAD_REQUEST);
	}

}
