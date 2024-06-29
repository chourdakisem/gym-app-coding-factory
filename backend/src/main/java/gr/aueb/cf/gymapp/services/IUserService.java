package gr.aueb.cf.gymapp.services;

import gr.aueb.cf.gymapp.dto.user.UserInsertDTO;
import gr.aueb.cf.gymapp.dto.user.UserUpdateDTO;
import gr.aueb.cf.gymapp.model.User;
import gr.aueb.cf.gymapp.services.exceptions.UserNotFoundException;

public interface IUserService {

    User findByUsername (String username) throws UserNotFoundException;
    User createUser(UserInsertDTO userInsertDTO) throws RuntimeException;
    User updateUser(UserUpdateDTO userUpdateDTO) throws UserNotFoundException;
    User deleteUser(Long id) throws UserNotFoundException;
}
