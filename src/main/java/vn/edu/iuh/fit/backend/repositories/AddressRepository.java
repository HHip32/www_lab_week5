package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.backend.models.Address;

import java.util.Optional;


public interface AddressRepository extends JpaRepository<Address, Long> {

}
