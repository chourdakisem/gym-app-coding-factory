package gr.aueb.cf.gymapp.services;

import gr.aueb.cf.gymapp.model.Client;
import gr.aueb.cf.gymapp.services.exceptions.EntityNotFoundException;

import java.util.Optional;

public interface IClientService {

    Client getClientById(Long id) throws EntityNotFoundException;
    Client getClientByUserId(Long id) throws EntityNotFoundException;
}
