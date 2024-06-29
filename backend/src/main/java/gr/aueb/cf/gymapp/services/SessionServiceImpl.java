package gr.aueb.cf.gymapp.services;

import gr.aueb.cf.gymapp.dto.session.SessionInsertDTO;
import gr.aueb.cf.gymapp.dto.session.SessionUpdateDTO;
import gr.aueb.cf.gymapp.mappers.SessionMapper;
import gr.aueb.cf.gymapp.model.Client;
import gr.aueb.cf.gymapp.model.Session;
import gr.aueb.cf.gymapp.model.Trainer;
import gr.aueb.cf.gymapp.repositories.SessionRepository;
import gr.aueb.cf.gymapp.services.exceptions.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SessionServiceImpl implements ISessionService {

    private final SessionRepository repository;

    @Autowired
    public SessionServiceImpl(SessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Session> getSessionsByTrainerId(Long id) throws EntityNotFoundException {
        List<Session> sessions;
        try {
            sessions = Optional.ofNullable(repository.findSessionByTrainerId(id))
                    .orElseThrow(() -> new EntityNotFoundException(Session.class, Trainer.class, id));
        } catch (EntityNotFoundException e) {
            throw  e;
        }
        return sessions;
    }

    @Override
    public List<Session> getSessionByClientId(Long id) throws EntityNotFoundException {
        List<Session> sessions;
        try {
            sessions = Optional.ofNullable(repository.findSessionByTrainerId(id))
                    .orElseThrow(() -> new EntityNotFoundException(Session.class, Client.class, id));
        } catch (EntityNotFoundException e) {
            throw  e;
        }
        return sessions;
    }

    @Transactional
    @Override
    public Session createSession(SessionInsertDTO sessionInsertDTO) throws RuntimeException {
        Session session;
        try {
            Session sessionToPersist = SessionMapper.mapFromInsertDto(sessionInsertDTO);
            sessionToPersist.getClient().addSession(sessionToPersist);
            sessionToPersist.getTrainer().addSession(sessionToPersist);
            session = Optional.ofNullable(repository.save(sessionToPersist))
                    .orElseThrow(() -> new RuntimeException("Something went wrong during the insertion of this Session"));
        } catch (RuntimeException e) {
            throw e;
        }
        return session;
    }

    @Override
    public List<Object[]> getSessionDetailsByClientId(Long id) throws EntityNotFoundException {
        List<Object[]> sessionDetails;
        try {
            sessionDetails = repository.findSessionDetailsByClientId(id);
            if (sessionDetails.size() == 0) throw new EntityNotFoundException(Session.class, Client.class, id);
        } catch (EntityNotFoundException e) {
            throw e;
        }
        return sessionDetails;
    }

    @Override
    public List<Object[]> getSessionDetailsByTrainerId(Long id) throws EntityNotFoundException {
        List<Object[]> sessionDetails;
        try {
            sessionDetails = repository.findSessionDetailsByTrainerId(id);
            if (sessionDetails.size() == 0) throw new EntityNotFoundException(Session.class, Trainer.class, id);
        } catch (EntityNotFoundException e) {
            throw e;
        }
        return sessionDetails;
    }

    @Transactional
    @Override
    public Session deleteSession(Long id) throws EntityNotFoundException {
        Session session;
        try {
            session = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(Session.class, id));
            repository.delete(session);
        } catch (EntityNotFoundException e) {
            throw e;
        }
        return session;
    }

    @Transactional
    @Override
    public Session updateSession(SessionUpdateDTO sessionUpdateDTO) throws EntityNotFoundException {
        Session session;
        try {
            session = repository.findById(sessionUpdateDTO.getId())
                    .orElseThrow(() -> new EntityNotFoundException(Session.class, sessionUpdateDTO.getId()));
            sessionUpdateDTO.setClient(session.getClient());
            sessionUpdateDTO.setTrainer(session.getTrainer());
            session = repository.save(SessionMapper.mapFromUpdateDto(sessionUpdateDTO));
        } catch (EntityNotFoundException e) {
            throw e;
        }
        return session;
    }

    @Override
    public Session getSessionById(Long id) throws EntityNotFoundException {
        Session session;
        try {
            session = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(Session.class, id));
        } catch (EntityNotFoundException e) {
            throw e;
        }
        return session;
    }
}
