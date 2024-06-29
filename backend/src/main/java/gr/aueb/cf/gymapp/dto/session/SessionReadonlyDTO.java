package gr.aueb.cf.gymapp.dto.session;

import gr.aueb.cf.gymapp.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class SessionReadonlyDTO {

    private Long id;
    private LocalDateTime date;
    private String status;
    private Integer duration;
    private BigDecimal fee;
    private Long trainerId;
    private Long clientId;
}
