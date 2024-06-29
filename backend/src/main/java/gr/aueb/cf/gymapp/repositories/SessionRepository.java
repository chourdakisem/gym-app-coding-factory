package gr.aueb.cf.gymapp.repositories;

import gr.aueb.cf.gymapp.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {

    @Query("SELECT s FROM Session s WHERE s.trainer.id = ?1")
    List<Session> findSessionByTrainerId(Long id);

    @Query("SELECT s FROM Session s WHERE s.client.id = ?1")
    List<Session> findSessionByClientId(Long id);

    @Query("SELECT s.id, u.firstname, u.lastname, s.fee, s.date, s.status, s.duration " +
            "FROM Session s JOIN s.trainer t JOIN t.user u WHERE s.client.id = ?1")
    List<Object[]> findSessionDetailsByClientId(Long id);

    @Query("SELECT s.id, u.firstname, u.lastname, s.fee, s.date, s.status, s.duration " +
            "FROM Session s JOIN s.client c JOIN c.user u WHERE s.trainer.id = :trainerId")
    List<Object[]> findSessionDetailsByTrainerId(@Param("trainerId") Long id);
}
