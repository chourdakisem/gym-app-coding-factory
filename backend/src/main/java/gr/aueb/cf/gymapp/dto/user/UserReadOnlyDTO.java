package gr.aueb.cf.gymapp.dto.user;

import gr.aueb.cf.gymapp.model.Client;
import gr.aueb.cf.gymapp.model.Trainer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReadOnlyDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String phone;
    private String role;
    private Trainer trainer;
    private Client client;
}
