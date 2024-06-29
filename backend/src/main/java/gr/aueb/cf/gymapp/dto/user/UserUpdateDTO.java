package gr.aueb.cf.gymapp.dto.user;

import gr.aueb.cf.gymapp.model.Client;
import gr.aueb.cf.gymapp.model.Trainer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 3, max = 40)
    private String firstname;

    @NotNull
    @Size(min = 3, max = 40)
    private String lastname;

    @NotNull
    @Size(min = 3, max = 40)
    private String username;

    //@Pattern()
    private String password;

    @Email
    private String email;

    @NotNull
    private String phone;

    @NotNull
    private String role;
    private Trainer trainer;
    private Client client;
}
