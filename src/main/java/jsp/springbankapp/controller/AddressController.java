package jsp.springbankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.springbankapp.dto.ResponseStructure;
import jsp.springbankapp.entity.Address;
import jsp.springbankapp.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private AddressService addService;

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Address>> getAddressById(@PathVariable Integer id) {
		return new ResponseEntity<>(addService.getAddressById(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseStructure<Address>> updateAddress(@PathVariable Integer id,
			@RequestBody Address address) {
		return new ResponseEntity<>(addService.updateAddress(id, address), HttpStatus.OK);
	}

	@GetMapping("/bank/{bankId}")
	public ResponseEntity<ResponseStructure<Address>> getAddressByBank(@PathVariable Integer bankId) {
		return new ResponseEntity<>(addService.getAddressByBank(bankId), HttpStatus.OK);
	}

	@GetMapping("/city/{city}")
	public ResponseEntity<ResponseStructure<Address>> getAddressByCity(@PathVariable String city) {
		return new ResponseEntity<>(addService.getAddressByCity(city), HttpStatus.OK);
	}

	@GetMapping("/city/{city}/street/{street}")
	public ResponseEntity<ResponseStructure<Address>> getAddressByCityAndStreet(@PathVariable String city,
			@PathVariable String street) {
		return new ResponseEntity<>(addService.getAddressByCityAndStreet(city, street), HttpStatus.OK);
	}

}
