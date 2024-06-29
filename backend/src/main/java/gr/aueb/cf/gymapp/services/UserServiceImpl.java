package gr.aueb.cf.gymapp.services;

import gr.aueb.cf.gymapp.dto.user.UserInsertDTO;
import gr.aueb.cf.gymapp.dto.user.UserUpdateDTO;
import gr.aueb.cf.gymapp.mappers.UserMapper;
import gr.aueb.cf.gymapp.model.Client;
import gr.aueb.cf.gymapp.model.Role;
import gr.aueb.cf.gymapp.model.Trainer;
import gr.aueb.cf.gymapp.model.User;
import gr.aueb.cf.gymapp.repositories.ClientRepository;
import gr.aueb.cf.gymapp.repositories.TrainerRepository;
import gr.aueb.cf.gymapp.repositories.UserRepository;
import gr.aueb.cf.gymapp.services.exceptions.EntityNotFoundException;
import gr.aueb.cf.gymapp.services.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User findByUsername(String username) throws EntityNotFoundException {
        User user;
        try {
            user = repository.findByUsername(username)
                    .orElseThrow(() -> new UserNotFoundException(username));
        } catch (UserNotFoundException e) {
            throw e;
        }
        return user;
    }

    @Transactional
    @Override
    public User createUser(UserInsertDTO userInsertDTO) throws RuntimeException {
        User user;
        try {
            User userToPersist = UserMapper.mapFromInsertDTO(userInsertDTO);

            if (userToPersist.getRole().equals(Role.CLIENT)) {
                userToPersist.setClient(new Client());
                userToPersist.getClient().setUser(userToPersist);
            } else {
                userToPersist.setTrainer(new Trainer());
                userToPersist.getTrainer().setUser(userToPersist);
            }

            user = repository.save(userToPersist);
            if (user == null) {
                throw new RuntimeException("Error during insertion");
            }

        } catch (RuntimeException e) {
            throw e;
        }
        return user;
    }

    @Transactional
    @Override
    public User updateUser(UserUpdateDTO userUpdateDTO) throws UserNotFoundException {
        User user;
        try {
            User userToPersist = UserMapper.mapFromUpdateDTO(userUpdateDTO);
            repository.findByUsername(userToPersist.getUsername())
                    .orElseThrow(() -> new UserNotFoundException(userUpdateDTO.getUsername()));
            if (userToPersist.getRole().equals(Role.CLIENT)) {
                userToPersist.getClient().setUser(userToPersist);
            } else {
                userToPersist.getTrainer().setUser(userToPersist);
            }
            user = repository.save(UserMapper.mapFromUpdateDTO(userUpdateDTO));
        } catch (UserNotFoundException e) {
            throw e;
        }
        return user;
    }

    @Transactional
    @Override
    public User deleteUser(Long id) throws EntityNotFoundException {
        User user;
        try {
            user = repository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException(id));
            repository.delete(user);
        } catch (UserNotFoundException e) {
            throw e;
        }
        return user;
    }

}
