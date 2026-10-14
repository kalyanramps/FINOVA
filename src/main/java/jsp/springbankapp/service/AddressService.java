package jsp.springbankapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jsp.springbankapp.dto.ResponseStructure;
import jsp.springbankapp.entity.Address;
import jsp.springbankapp.entity.Bank;
import jsp.springbankapp.exception.IdNotFoundException;
import jsp.springbankapp.exception.NoRecordsAvailableException;
import jsp.springbankapp.repository.AddressRepository;
import jsp.springbankapp.repository.BankRepository;

@Service
public class AddressService {
	@Autowired
	private AddressRepository addRepository;
	@Autowired
	private BankRepository bankRepository;

	public ResponseStructure<Address> saveAddress(Address address) {
		ResponseStructure<Address> res = new ResponseStructure<>();
		res.setStatusCode(HttpStatus.CREATED.value());
		res.setMessage("Address Record saved successfully");
		res.setData(addRepository.save(address));
		return res;

	}

	// Get Address By Id
	public ResponseStructure<Address> getAddressById(Integer id) {

		Optional<Address> opt = addRepository.findById(id);
		ResponseStructure<Address> res = new ResponseStructure<>();

		if (opt.isEmpty()) {
			throw new IdNotFoundException("Address with ID " + id + " not found");
		}

		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Address retrieved successfully");
		res.setData(opt.get());

		return res;

	}

	// Update Address
	public ResponseStructure<Address> updateAddress(Integer id, Address address) {

		Optional<Address> opt = addRepository.findById(id);
		ResponseStructure<Address> res = new ResponseStructure<>();

		if (opt.isEmpty()) {
			throw new IdNotFoundException("Address with ID " + id + " not found");
		}

		address.setAddId(id); // To update the existing record

		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Address updated successfully");
		res.setData(addRepository.save(address));

		return res;
	}

	// Get Address by Bank
	public ResponseStructure<Address> getAddressByBank(Integer bankId) {

		Optional<Bank> opt = bankRepository.findById(bankId);
		ResponseStructure<Address> res = new ResponseStructure<>();

		if (opt.isEmpty()) {
			throw new IdNotFoundException("Bank with ID " + bankId + " not found");
		}

		Bank bank = opt.get();

		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Address retrieved successfully");
		res.setData(bank.getAddress());

		return res;
	}
	
	//Get address by city
	public ResponseStructure<Address> getAddressByCity(String city){
		Optional<Address> opt=addRepository.findByCity(city);
		ResponseStructure<Address> res=new ResponseStructure<Address>();
		
		if(opt.isEmpty()) {
			throw new NoRecordsAvailableException("Address not found for city "+city);
		}
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Address retrieved successfully");
		res.setData(opt.get());
		return res;
	}

	// Get address by city and street
	public ResponseStructure<Address> getAddressByCityAndStreet(String city, String street) {
		Optional<Address> opt = addRepository.findByCityAndStreet(city, street);
		ResponseStructure<Address> res = new ResponseStructure<Address>();
		if (opt.isEmpty()) {
			throw new NoRecordsAvailableException("Address not found for city " + city + " and street " + street);
		}
		res.setStatusCode(HttpStatus.OK.value());
		res.setMessage("Address retrieved successfully");
		res.setData(opt.get());

		return res;

	}

}
