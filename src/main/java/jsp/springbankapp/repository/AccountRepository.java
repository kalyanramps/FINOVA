package jsp.springbankapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.springbankapp.entity.Account;

public interface AccountRepository  extends JpaRepository<Account, Integer>{
	boolean existsByAccNumber(Long accNumber);
	Optional<Account> findByAccNumber(Long accNumber);

}
