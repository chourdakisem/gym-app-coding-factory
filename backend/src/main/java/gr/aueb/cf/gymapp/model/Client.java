package gr.aueb.cf.gymapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Short kg;
    private Short age;
    private String location;

    @JsonIgnore
    @OneToOne(optional = false)
    private User user;

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<Session> sessions;

    public void addSession(Session session) {
        if (this.sessions.contains(session)) return;
        this.sessions.add(session);
    }

    public void removeSession(Session session) {
        this.sessions.remove(session);
    }

}
