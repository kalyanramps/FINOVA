package jsp.springbankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jsp.springbankapp.dto.ResponseStructure;
import jsp.springbankapp.entity.Bank;
import jsp.springbankapp.service.BankService;

@RestController
@RequestMapping("/bank")
public class BankController {
	@Autowired
	private BankService bankService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Bank>> saveBank(@RequestBody Bank bank) {
		return new ResponseEntity<>(bankService.saveBank(bank), HttpStatus.CREATED);

	}

	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Bank>>> getAllBank() {
		return new ResponseEntity<>(bankService.getAllBank(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Bank>> getBankById(@PathVariable Integer id) {
		return new ResponseEntity<>(bankService.getBankById(id), HttpStatus.OK);
	}

	@GetMapping("/ifsc/{ifsc}")
	public ResponseEntity<ResponseStructure<Bank>> getByIfscCode(@PathVariable String ifsc) {
		return new ResponseEntity<>(bankService.getByIfscCode(ifsc), HttpStatus.OK);
	}

	@GetMapping("/address/{addId}")
	public ResponseEntity<ResponseStructure<Bank>> getBankByAddress(@PathVariable Integer addId) {
		return new ResponseEntity<>(bankService.getBankByAddress(addId), HttpStatus.OK);
	}

	@GetMapping("/city/{city}")
	public ResponseEntity<ResponseStructure<List<Bank>>> getBankByCity(@PathVariable String city) {
		return new ResponseEntity<>(bankService.getBankByCity(city), HttpStatus.OK);
	}

	@GetMapping("/contact/{contact}")
	public ResponseEntity<ResponseStructure<Bank>> getBankByContactNumber(@PathVariable Long contact) {

		return new ResponseEntity<>(bankService.getBankByContactNumber(contact), HttpStatus.OK);
	}

	// bank/pagination/sorting?pageNumber=0&pageSize=5&sortBy=bankName
	@GetMapping("/pagination/sorting")
	public ResponseEntity<ResponseStructure<Page<Bank>>> getBankByPaginationAndSorting(@RequestParam int pageNumber,
			@RequestParam int pageSize, @RequestParam String sortBy) {

		return new ResponseEntity<>(bankService.getBankByPaginationAndSorting(pageNumber, pageSize, sortBy),
				HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Bank>> deleteBank(@PathVariable Integer id) {

		return new ResponseEntity<>(bankService.deleteBank(id), HttpStatus.OK);
	}

}
