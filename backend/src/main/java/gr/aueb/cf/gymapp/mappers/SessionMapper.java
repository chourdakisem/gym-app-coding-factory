package gr.aueb.cf.gymapp.mappers;

import gr.aueb.cf.gymapp.dto.session.SessionInsertDTO;
import gr.aueb.cf.gymapp.dto.session.SessionReadonlyDTO;
import gr.aueb.cf.gymapp.dto.session.SessionUpdateDTO;
import gr.aueb.cf.gymapp.model.Session;
import gr.aueb.cf.gymapp.model.Status;

import java.time.format.DateTimeFormatter;

public class SessionMapper {

    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private SessionMapper() {}

    public static SessionReadonlyDTO mapToDto(Session session) {
        SessionReadonlyDTO dto = new SessionReadonlyDTO();
        dto.setId(session.getId());
        dto.setDate(session.getDate());
        dto.setStatus(session.getStatus().name());
        dto.setFee(session.getFee());
        dto.setDuration(session.getDuration());
        dto.setClientId(session.getClient().getId());
        dto.setTrainerId(session.getTrainer().getId());
        return dto;
    }

    public static Session mapFromInsertDto(SessionInsertDTO dto) {
        Session session = new Session();
        session.setId(null);
        session.setDate(dto.getDate());
        session.setStatus(Status.PENDING);
        session.setFee(dto.getFee());
        session.setDuration(dto.getDuration());
        session.setClient(dto.getClient());
        session.setTrainer(dto.getTrainer());
        return session;
    }

    public static Session mapFromUpdateDto(SessionUpdateDTO dto) {
        Session session = new Session();
        session.setId(dto.getId());
        session.setDate(dto.getDate());
        session.setStatus(Status.ACCEPTED);
        session.setFee(dto.getFee());
        session.setDuration(dto.getDuration());
        session.setClient(dto.getClient());
        session.setTrainer(dto.getTrainer());
        return session;
    }
}
