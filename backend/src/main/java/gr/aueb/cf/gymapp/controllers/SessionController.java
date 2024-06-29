package gr.aueb.cf.gymapp.controllers;

import gr.aueb.cf.gymapp.dto.session.SessionDetailsDTO;
import gr.aueb.cf.gymapp.dto.session.SessionInsertDTO;
import gr.aueb.cf.gymapp.dto.session.SessionReadonlyDTO;
import gr.aueb.cf.gymapp.dto.session.SessionUpdateDTO;
import gr.aueb.cf.gymapp.mappers.SessionMapper;
import gr.aueb.cf.gymapp.model.ErrorDetails;
import gr.aueb.cf.gymapp.model.Session;
import gr.aueb.cf.gymapp.model.Status;
import gr.aueb.cf.gymapp.services.ISessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
public class SessionController {

    private final ISessionService sessionService;

    @Autowired
    public SessionController(ISessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/trainers/{trainerId}/sessions")
    public ResponseEntity<List<SessionReadonlyDTO>> getTrainerSessions(@PathVariable("trainerId") Long id) {
        List<Session> sessions = sessionService.getSessionsByTrainerId(id);
        List<SessionReadonlyDTO> readonlyDTOS = sessions.stream()
                .map(session -> SessionMapper.mapToDto(session))
                .collect(Collectors.toList());
        return ResponseEntity.ok(readonlyDTOS);
    }

    @GetMapping("/clients/{clientId}/sessions")
    public ResponseEntity<List<SessionReadonlyDTO>> getClientSessions(@PathVariable("clientId") Long id) {
        List<Session> sessions = sessionService.getSessionByClientId(id);
        List<SessionReadonlyDTO> readonlyDTOS = sessions.stream()
                .map(session -> SessionMapper.mapToDto(session))
                .collect(Collectors.toList());
        return ResponseEntity.ok(readonlyDTOS);
    }

    @PostMapping("/sessions")
    public ResponseEntity<SessionReadonlyDTO> createSession(@RequestBody SessionInsertDTO insertDTO) {
        Session session = sessionService.createSession(insertDTO);
        SessionReadonlyDTO readonlyDTO = SessionMapper.mapToDto(session);
        return ResponseEntity
                .status(201)
                .body(readonlyDTO);
    }

    @GetMapping("/clients/{clientId}/session-details")
    public ResponseEntity<List<SessionDetailsDTO>> getSessionDetailsByClientId(@PathVariable("clientId") Long id) {
        List<Object[]> sessionDetails = sessionService.getSessionDetailsByClientId(id);
        List<SessionDetailsDTO> sessionDetailsDTOS = sessionDetails.stream()
                .map(sessionData -> new SessionDetailsDTO(
                        (Long) sessionData[0],
                        (String) sessionData[1],
                        (String) sessionData[2],
                        (BigDecimal) sessionData[3],
                        ((LocalDateTime) sessionData[4]).format(SessionMapper.dateTimeFormatter),
                        ((Status) sessionData[5]).name(),
                        (Integer) sessionData[6]
                ))
                .sorted((s1, s2) -> {
                    LocalDateTime d1 = LocalDateTime.parse(s1.getDate(), SessionMapper.dateTimeFormatter);
                    LocalDateTime d2 = LocalDateTime.parse(s2.getDate(), SessionMapper.dateTimeFormatter);
                    return d1.compareTo(d2);
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(sessionDetailsDTOS);
    }

    @GetMapping("/trainers/{trainerId}/session-details")
    public ResponseEntity<List<SessionDetailsDTO>> getSessionDetailsByTrainerId(@PathVariable("trainerId") Long id) {
        List<Object[]> sessionDetails = sessionService.getSessionDetailsByTrainerId(id);
        List<SessionDetailsDTO> sessionDetailsDTOS = sessionDetails.stream()
                .map(sessionData -> new SessionDetailsDTO(
                        (Long) sessionData[0],
                        (String) sessionData[1],
                        (String) sessionData[2],
                        (BigDecimal) sessionData[3],
                        ((LocalDateTime) sessionData[4]).format(SessionMapper.dateTimeFormatter),
                        ((Status) sessionData[5]).name(),
                        (Integer) sessionData[6]
                ))
                .sorted((s1, s2) -> {
                    LocalDateTime d1 = LocalDateTime.parse(s1.getDate(), SessionMapper.dateTimeFormatter);
                    LocalDateTime d2 = LocalDateTime.parse(s2.getDate(), SessionMapper.dateTimeFormatter);
                    return d1.compareTo(d2);
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(sessionDetailsDTOS);
    }

    @DeleteMapping("/sessions/{sessionId}")
    public ResponseEntity<SessionReadonlyDTO> deleteSession(@PathVariable("sessionId") Long id) {
        Session session = sessionService.deleteSession(id);
        SessionReadonlyDTO readonlyDTO = SessionMapper.mapToDto(session);
        return ResponseEntity.ok(readonlyDTO);
    }

    @PutMapping("/sessions/{sessionId}")
    public ResponseEntity<?> updateSession(@PathVariable("sessionId") Long id,
                                                            @RequestBody SessionUpdateDTO dto) {
        if (!id.equals(dto.getId())) {
            return ResponseEntity
                    .status(401)
                    .body(new ErrorDetails("Id does not match"));
        }

        Session session = sessionService.updateSession(dto);
        SessionReadonlyDTO readonlyDTO = SessionMapper.mapToDto(session);
        return ResponseEntity.ok(readonlyDTO);
    }

    @GetMapping("/sessions/{sessionId}")
    public ResponseEntity<?> updateSession(@PathVariable("sessionId") Long id) {

        Session session = sessionService.getSessionById(id);
        SessionReadonlyDTO readonlyDTO = SessionMapper.mapToDto(session);
        return ResponseEntity.ok(readonlyDTO);
    }
}
