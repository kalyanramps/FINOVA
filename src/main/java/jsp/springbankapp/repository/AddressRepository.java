package jsp.springbankapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.springbankapp.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	boolean existsByPincode(Long pincode);
	Optional<Address> findByCity(String city);
	Optional<Address> findByCityAndStreet(String city,String street);

}
