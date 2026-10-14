package jsp.springbankapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.springbankapp.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer> {
	boolean existsByIfsc(String ifsc);

	boolean existsByContact(Long contact);

	Optional<Bank> findByIfsc(String ifsc);
	
	Optional<Bank> findByAddress_AddId(Integer addId);
	
	List<Bank> findByAddress_City(String city);
	
	Optional<Bank> findByContact(Long contact);
}
