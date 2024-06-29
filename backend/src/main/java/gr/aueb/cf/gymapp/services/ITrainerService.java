package gr.aueb.cf.gymapp.services;

import gr.aueb.cf.gymapp.model.Trainer;
import gr.aueb.cf.gymapp.services.exceptions.EntityNotFoundException;

import java.util.List;

public interface ITrainerService {

    List<Trainer> findTrainerByLastname(String lastname) throws EntityNotFoundException;
    Trainer findTrainerById(Long id) throws EntityNotFoundException;
    Trainer findTrainerByUserId(Long id) throws EntityNotFoundException;
}
