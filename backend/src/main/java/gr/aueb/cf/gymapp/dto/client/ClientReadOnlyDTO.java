package gr.aueb.cf.gymapp.dto.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gr.aueb.cf.gymapp.model.Session;
import gr.aueb.cf.gymapp.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientReadOnlyDTO {

    private Long id;
    private Short kg;
    private Short age;

    @JsonIgnore
    private User user;

    @JsonIgnore
    private List<Session> sessions;
}
