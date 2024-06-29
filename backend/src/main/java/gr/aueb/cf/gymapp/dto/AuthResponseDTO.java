package gr.aueb.cf.gymapp.dto;

import gr.aueb.cf.gymapp.model.Session;
import gr.aueb.cf.gymapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {

    private String accessToken;
    private String refreshToken;
    private User user;
}
