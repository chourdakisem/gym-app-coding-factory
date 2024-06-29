package gr.aueb.cf.gymapp.repositories;

import gr.aueb.cf.gymapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByLastname(String lastname);
}
