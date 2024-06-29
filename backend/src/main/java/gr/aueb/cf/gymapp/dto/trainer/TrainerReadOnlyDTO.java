package gr.aueb.cf.gymapp.dto.trainer;

import gr.aueb.cf.gymapp.model.Session;
import gr.aueb.cf.gymapp.model.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class TrainerReadOnlyDTO {

    private Long id;
    private LocalTime startingTime;
    private LocalTime endingTime;
    private Integer duration;
    private BigDecimal fee;
    private String location;
    private User user;
    private List<Session> sessions;
}
