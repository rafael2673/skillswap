package br.com.skillswap.profileservice.repository;

import br.com.skillswap.profileservice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
