package gr.aueb.cf.gymapp.dto.session;

import gr.aueb.cf.gymapp.model.Client;
import gr.aueb.cf.gymapp.model.Trainer;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class SessionUpdateDTO {

    private Long id;
    private LocalDateTime date;
    private String status;
    private Integer duration;
    private BigDecimal fee;
    private Client client;
    private Trainer trainer;
}
