package br.com.skillswap.profileservice.repository;

import br.com.skillswap.profileservice.model.Profile;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUserId(@NotNull Long userId);
}
