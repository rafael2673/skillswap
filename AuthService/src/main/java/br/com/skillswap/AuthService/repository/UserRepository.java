package br.com.skillswap.AuthService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.skillswap.AuthService.model.User;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

}
