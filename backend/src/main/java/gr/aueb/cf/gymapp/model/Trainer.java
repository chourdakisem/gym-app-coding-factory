package gr.aueb.cf.gymapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "trainers")
@Getter
@Setter
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "starting_time")
    private LocalTime startingTime;

    @Column(name = "ending_time")
    private LocalTime endingTime;
    private BigDecimal fee;
    private Integer duration;
    private String location;

    @JsonIgnore
    @OneToOne(optional = false)
    private User user;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.REMOVE)
    private List<Session> sessions;

    public void addSession(Session session) {
        if (this.sessions.contains(session)) return;
        this.sessions.add(session);
    }

    public void removeSession(Session session) {
        this.sessions.remove(session);
    }

}
