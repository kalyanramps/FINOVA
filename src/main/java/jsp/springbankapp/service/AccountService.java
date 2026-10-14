package jsp.springbankapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jsp.springbankapp.dto.ResponseStructure;
import jsp.springbankapp.entity.Account;
import jsp.springbankapp.entity.AccountType;
import jsp.springbankapp.entity.Bank;
import jsp.springbankapp.exception.DuplicateAccountNumberException;
import jsp.springbankapp.exception.IdNotFoundException;
import jsp.springbankapp.exception.InsufficientBalanceException;
import jsp.springbankapp.exception.InvalidAmountException;
import jsp.springbankapp.repository.AccountRepository;
import jsp.springbankapp.repository.BankRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accRepository;
	@Autowired
	private BankRepository bankRepository;

	public ResponseStructure<Account> saveAccount(Account acc) {
		ResponseStructure<Account> res = new ResponseStructure<>();
		res.setStatusCode(HttpStatus.CREATED.value());
		res.setMessage("Account Record saved successfully");
		res.setData(accRepository.save(acc));
		return res;

	}

	// Save the account
	public ResponseStructure<Account> saveAccount(Integer bankId, Account account) {
		// 1. Account Number must be unique
		if (accRepository.existsByAccNumber(account.getAccNumber())) { // if accNum already present then throw exception
			throw new DuplicateAccountNumberException("Account number already exists");
		}
		// Bank must exists
		Optional<Bank> opt = bankRepository.findById(bankId);

		// 2. Account cannot be save if bank does not exists
		if (opt.isEmpty()) {
			throw new IdNotFoundException("Bank with ID " + bankId + " not found");
		}

		// 3. Minimum balance for Savings account
		if (account.getAccType() == AccountType.SAVINGS && account.getBalance() < 1000) {
			throw new InsufficientBalanceException("Savings account must maintain minimum balance of 1000");
		}
		// 4. Minimum balance for Current account
		if (account.getAccType() == AccountType.CURRENT && account.getBalance() < 5000) {
			throw new InsufficientBalanceException("current account must maintain minimum balance of 5000");
		}

		Bank bank = opt.get();

		account.setBank(bank); // connect account with existing bank
		ResponseStructure<Account> res = new ResponseStructure<>();

		res.setStatusCode(HttpStatus.CREATED.value());
		res.setMessage("Account saved successfully");
		res.setData(accRepository.save(account));

		return res;
	}

	// Deposit the amount
	public ResponseStructure<Account> deposit(Long accNumber, Double amount) {
		// 1.Deposite amt should be possitive
		if (amount <= 0) {
			throw new InvalidAmountException("Deposit amount must be positive");
		}
		// Find the existing account
		Optional<Account> opt = accRepository.findByAccNumber(accNumber);

		if (opt.isEmpty()) {
			throw new IdNotFoundException("Account not found");
		}
		// Get the account from optional
		Account account = opt.get();
		// getBalance(5000)+amt(2000)=setBalance(7000) in total
		account.setBalance(account.getBalance() + amount);

		ResponseStructure<Account> res = new ResponseStructure<>();

		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Amount deposited successfully");
		res.setData(accRepository.save(account));

		return res;
	}

	// withdraw the amount
	public ResponseStructure<Account> withdraw(Long accNumber, Double amount) {

		// 1. Withdrawal amount must be positive
		if (amount <= 0) {
			throw new InvalidAmountException("Withdrawal amount must be positive");
		}

		// 2. Find existing account
		Optional<Account> opt = accRepository.findByAccNumber(accNumber);

		if (opt.isEmpty()) {
			throw new IdNotFoundException("Account not found");
		}

		// 3. Get account
		Account account = opt.get();

		// 4. Check sufficient balance
		if (account.getBalance() < amount) {
			throw new InsufficientBalanceException("Insufficient balance");
		}

		// 5. Deduct amount
		account.setBalance(account.getBalance() - amount);

		// 6. Prepare response
		ResponseStructure<Account> res = new ResponseStructure<>();

		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Amount withdrawn successfully");
		res.setData(accRepository.save(account));

		return res;
	}

	public ResponseStructure<Account> transfer(Long senderAccNumber, Long receiverAccNumber, Double amount) {

		// 1. Transfer amount must be positive
		if (amount <= 0) {
			throw new InvalidAmountException("Transfer amount must be positive");
		}

		// 2. Sender account must exist
		Optional<Account> senderOpt = accRepository.findByAccNumber(senderAccNumber);

		if (senderOpt.isEmpty()) {
			throw new IdNotFoundException("Sender account not found");
		}

		// 3. Receiver account must exist
		Optional<Account> receiverOpt = accRepository.findByAccNumber(receiverAccNumber);

		if (receiverOpt.isEmpty()) {
			throw new IdNotFoundException("Receiver account not found");
		}

		// 4. Sender and receiver must not be same
		if (senderAccNumber.equals(receiverAccNumber)) {
			throw new DuplicateAccountNumberException("Sender and receiver accounts must not be same");
		}
		// Get the sender and receiver account
		Account sender = senderOpt.get();
		Account receiver = receiverOpt.get();

		// 5. Sender must have sufficient balance
		if (sender.getBalance() < amount) {
			throw new InsufficientBalanceException("Insufficient balance in sender account");
		}

		// 6. Deduct amount from sender
		sender.setBalance(sender.getBalance() - amount);

		// 7. Add amount to receiver
		receiver.setBalance(receiver.getBalance() + amount);

		// 8. Save both accounts
		accRepository.save(sender);
		accRepository.save(receiver);

		ResponseStructure<Account> res = new ResponseStructure<>();

		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Amount transferred successfully");
		res.setData(sender);

		return res;
	}

}
