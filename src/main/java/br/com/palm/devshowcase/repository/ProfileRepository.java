package br.com.palm.devshowcase.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.palm.devshowcase.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);
}
