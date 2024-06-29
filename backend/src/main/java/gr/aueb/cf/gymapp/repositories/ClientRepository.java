package gr.aueb.cf.gymapp.repositories;

import gr.aueb.cf.gymapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE c.user.id = ?1")
    Optional<Client> findClientByUserId(Long id);
}
