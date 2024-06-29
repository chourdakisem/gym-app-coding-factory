package gr.aueb.cf.gymapp.services;

import gr.aueb.cf.gymapp.model.Client;
import gr.aueb.cf.gymapp.model.Trainer;
import gr.aueb.cf.gymapp.model.User;
import gr.aueb.cf.gymapp.repositories.TrainerRepository;
import gr.aueb.cf.gymapp.services.exceptions.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerServiceImpl implements ITrainerService {

    private final TrainerRepository repository;

    @Autowired
    public TrainerServiceImpl(TrainerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Trainer> findTrainerByLastname(String lastname) throws EntityNotFoundException {
        List<Trainer> trainers;
        try {
            trainers = repository.findTrainerByLastname(lastname);
            if (trainers.isEmpty()) {
                throw new EntityNotFoundException(Trainer.class, "lastname", lastname);
            }
        } catch (EntityNotFoundException e) {
            throw e;
        }
        return trainers;
    }

    @Override
    public Trainer findTrainerById(Long id) throws EntityNotFoundException {
        Trainer trainer;
        try {
            trainer = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(Trainer.class, id));
        } catch (EntityNotFoundException e) {
            throw e;
        }
        return trainer;
    }

    @Override
    public Trainer findTrainerByUserId(Long id) throws EntityNotFoundException {
        Trainer trainer;
        try {
            trainer = repository.findTrainerByUserId(id)
                    .orElseThrow(() -> new EntityNotFoundException(Trainer.class, User.class, id));
        } catch (EntityNotFoundException e) {
            throw e;
        }
        return trainer;
    }
}
