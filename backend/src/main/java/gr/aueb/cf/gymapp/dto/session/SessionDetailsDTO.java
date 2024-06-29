package gr.aueb.cf.gymapp.dto.session;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SessionDetailsDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private BigDecimal fee;
    private String date;
    private String status;
    private Integer duration;
}
