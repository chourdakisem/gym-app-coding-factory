package gr.aueb.cf.gymapp.repositories;

import gr.aueb.cf.gymapp.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    @Query("SELECT t FROM Trainer t WHERE t.user.id = ?1")
    Optional<Trainer> findTrainerByUserId(Long id);

    @Query("SELECT t FROM Trainer t WHERE t.user.lastname = ?1")
    List<Trainer> findTrainerByLastname(String lastname);
}
