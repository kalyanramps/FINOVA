package jsp.springbankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jsp.springbankapp.dto.ResponseStructure;
import jsp.springbankapp.entity.Account;
import jsp.springbankapp.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountService accService;

	@PostMapping("/bank/{bankId}")
	public ResponseEntity<ResponseStructure<Account>> saveAccount(@PathVariable Integer bankId,
			@RequestBody Account account) {

		return new ResponseEntity<>(accService.saveAccount(bankId, account), HttpStatus.CREATED);
	}

	@PostMapping("/deposit/amount/{accNumber}")
	public ResponseEntity<ResponseStructure<Account>> deposite(@PathVariable Long accNumber,
			@RequestParam Double amount) {
		return new ResponseEntity<>(accService.deposit(accNumber, amount), HttpStatus.OK);
	}

	@PostMapping("/withdraw/amount/{accNumber}")
	public ResponseEntity<ResponseStructure<Account>> withdraw(@PathVariable Long accNumber,
			@RequestParam Double amount) {

		return new ResponseEntity<>(accService.withdraw(accNumber, amount), HttpStatus.OK);
	}

	@PostMapping("/transfer/{senderAccNumber}/{receiverAccNumber}")
	public ResponseEntity<ResponseStructure<Account>> transfer(@PathVariable Long senderAccNumber,
			@PathVariable Long receiverAccNumber, @RequestParam Double amount) {

		return new ResponseEntity<>(accService.transfer(senderAccNumber, receiverAccNumber, amount), HttpStatus.OK);
	}
}
