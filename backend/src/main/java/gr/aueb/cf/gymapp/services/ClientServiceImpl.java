package gr.aueb.cf.gymapp.services;

import gr.aueb.cf.gymapp.model.Client;
import gr.aueb.cf.gymapp.model.User;
import gr.aueb.cf.gymapp.repositories.ClientRepository;
import gr.aueb.cf.gymapp.services.exceptions.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements IClientService {

    private final ClientRepository repository;

    @Autowired
    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Client getClientById(Long id) throws EntityNotFoundException {
        Client client;
        try {
            client = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(Client.class, id));
        } catch (EntityNotFoundException e) {
            throw e;
        }
        return client;
    }

    @Override
    public Client getClientByUserId(Long id) throws EntityNotFoundException {
        Client client;
        try {
            client = repository.findClientByUserId(id)
                    .orElseThrow(() -> new EntityNotFoundException(Client.class, User.class, id));
        } catch (EntityNotFoundException e) {
            throw e;
        }
        return client;
    }
}
