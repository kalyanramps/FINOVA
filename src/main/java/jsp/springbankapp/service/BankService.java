package jsp.springbankapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jsp.springbankapp.dto.ResponseStructure;
import jsp.springbankapp.entity.Bank;
import jsp.springbankapp.exception.AddressRequiredException;
import jsp.springbankapp.exception.DuplicateContactException;
import jsp.springbankapp.exception.DuplicateIfscException;
import jsp.springbankapp.exception.IdNotFoundException;
import jsp.springbankapp.exception.InvalidContactException;
import jsp.springbankapp.exception.InvalidPincodeException;
import jsp.springbankapp.exception.NoRecordsAvailableException;
import jsp.springbankapp.exception.PincodeDuplicateException;
import jsp.springbankapp.repository.AddressRepository;
import jsp.springbankapp.repository.BankRepository;

@Service
public class BankService {
	@Autowired
	private BankRepository bankRepository;
	@Autowired
	private AddressRepository addRepository;

	// Save Bank
	public ResponseStructure<Bank> saveBank(Bank bank) {
		ResponseStructure<Bank> res = new ResponseStructure<>();
		res.setStatusCode(HttpStatus.CREATED.value());
		res.setMessage("Bank Record saved successfully");

		// IFSC must be unique
		if (bankRepository.existsByIfsc(bank.getIfsc())) { // if ifsc exists throw exception
			throw new DuplicateIfscException("IFSC code already exists");
		}
		// Contact must be unique
		if (bankRepository.existsByContact(bank.getContact())) {
			throw new DuplicateContactException("Contact number already exists");
		}
		// Contact must be 10 digits
		if (String.valueOf(bank.getContact()).length() != 10) { // contact length and original length
			throw new InvalidContactException("Contact must be 10 digits");
		}
		// Bank must have an address
		if (bank.getAddress() == null) {
			throw new AddressRequiredException("Bank must have an address");
		}
		// PinCode must be unique
		if (addRepository.existsByPincode(bank.getAddress().getPincode())) {
			throw new PincodeDuplicateException("Pincode already exists");
		}
		if (String.valueOf(bank.getAddress().getPincode()).length() != 6) {
			throw new InvalidPincodeException("Pincode must be 6 digits");
		}

		res.setData(bankRepository.save(bank)); // If not save bank record
		return res;

	}

	// Get All Bank
	public ResponseStructure<List<Bank>> getAllBank() {
		List<Bank> banks = bankRepository.findAll();
		ResponseStructure<List<Bank>> res = new ResponseStructure<List<Bank>>();

		if (banks.isEmpty()) {
			throw new NoRecordsAvailableException("No bank records are available");
		}
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("All bank records retrieved successfully");
		res.setData(banks);
		return res;
	}

	// Get Bank By Id
	public ResponseStructure<Bank> getBankById(Integer id) {
		Optional<Bank> opt = bankRepository.findById(id);
		ResponseStructure<Bank> res = new ResponseStructure<>();
		if (opt.isEmpty()) {
			throw new IdNotFoundException("Bank with id :" + id + " not found");
		}
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Bank retrieved successfully");
		res.setData(opt.get());
		return res;
	}

	// Get By IFSC code
	public ResponseStructure<Bank> getByIfscCode(String ifsc) {
		Optional<Bank> opt = bankRepository.findByIfsc(ifsc);
		ResponseStructure<Bank> res = new ResponseStructure<>();
		if (opt.isEmpty()) {
			throw new NoRecordsAvailableException("Bank with IFSC " + ifsc + " not found");
		}
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Bank retrieved successfully");
		res.setData(opt.get());
		return res;
	}

	// Get Bank By Address
	public ResponseStructure<Bank> getBankByAddress(Integer addId) {
		Optional<Bank> opt = bankRepository.findByAddress_AddId(addId);
		ResponseStructure<Bank> res = new ResponseStructure<>();
		if (opt.isEmpty()) {
			throw new NoRecordsAvailableException("Bank with Address ID " + addId + " not found");
		}
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Bank retrieved successfully");
		res.setData(opt.get());
		return res;
	}

	// Get Bank By City
	public ResponseStructure<List<Bank>> getBankByCity(String city) {
		List<Bank> banks = bankRepository.findByAddress_City(city);
		ResponseStructure<List<Bank>> res = new ResponseStructure<List<Bank>>();
		if (banks.isEmpty()) {
			throw new NoRecordsAvailableException("Bank with city " + city + " not found");
		}
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Bank retrieved successfully");
		res.setData(banks);
		return res;

	}

	// Get Bank By Contact Number
	public ResponseStructure<Bank> getBankByContactNumber(Long contact) {
		Optional<Bank> opt = bankRepository.findByContact(contact);
		ResponseStructure<Bank> res = new ResponseStructure<>();

		if (opt.isEmpty()) {
			throw new NoRecordsAvailableException("Bank with Contact Number " + contact + " not found");
		}
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Bank retrieved successfully");
		res.setData(opt.get());

		return res;
	}

	// Get Bank By Pagination and Sorting
	public ResponseStructure<Page<Bank>> getBankByPaginationAndSorting(int pageNumber, int pageSize, String sortBy) {

		Page<Bank> bank = bankRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending()));
		ResponseStructure<Page<Bank>> res = new ResponseStructure<>();

		if (bank.isEmpty()) {
			throw new NoRecordsAvailableException("Page is empty");
		}
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Bank records retrieved and sorted successfully");
		res.setData(bank);

		return res;
	}

	// Delete Bank By Id
	public ResponseStructure<Bank> deleteBank(Integer id) {

		Optional<Bank> opt = bankRepository.findById(id);
		ResponseStructure<Bank> res = new ResponseStructure<>();

		if (opt.isEmpty()) {
			throw new IdNotFoundException("Bank with ID " + id + " not found");
		}

		Bank bank = opt.get();

		bankRepository.delete(bank);
		
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Bank deleted successfully");
		res.setData(bank);

		return res;
	}

}
