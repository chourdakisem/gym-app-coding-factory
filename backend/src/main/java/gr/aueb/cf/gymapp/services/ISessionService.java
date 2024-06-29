package gr.aueb.cf.gymapp.services;

import gr.aueb.cf.gymapp.dto.session.SessionInsertDTO;
import gr.aueb.cf.gymapp.dto.session.SessionUpdateDTO;
import gr.aueb.cf.gymapp.model.Session;
import gr.aueb.cf.gymapp.services.exceptions.EntityNotFoundException;

import java.util.List;

public interface ISessionService {

    List<Session> getSessionsByTrainerId(Long id) throws EntityNotFoundException;
    List<Session> getSessionByClientId(Long id) throws EntityNotFoundException;
    List<Object[]> getSessionDetailsByClientId(Long id) throws EntityNotFoundException;
    List<Object[]> getSessionDetailsByTrainerId(Long id) throws EntityNotFoundException;
    Session createSession(SessionInsertDTO sessionInsertDTO) throws RuntimeException;
    Session deleteSession(Long id) throws EntityNotFoundException;
    Session updateSession(SessionUpdateDTO sessionUpdateDTO) throws EntityNotFoundException;
    Session getSessionById(Long id) throws EntityNotFoundException;
}
