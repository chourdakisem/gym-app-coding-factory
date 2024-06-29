package gr.aueb.cf.gymapp.mappers;

import gr.aueb.cf.gymapp.dto.user.UserInsertDTO;
import gr.aueb.cf.gymapp.dto.user.UserReadOnlyDTO;
import gr.aueb.cf.gymapp.dto.user.UserUpdateDTO;
import gr.aueb.cf.gymapp.model.Role;
import gr.aueb.cf.gymapp.model.User;

public class UserMapper {

    private UserMapper() {}

    public static User mapFromInsertDTO(UserInsertDTO dto) {
        User user = new User();
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole().equals("TRAINER") ? Role.TRAINER : Role.CLIENT);
        user.setClient(dto.getClient());
        user.setTrainer(dto.getTrainer());
        return user;
    }

    public static User mapFromUpdateDTO(UserUpdateDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setRole(dto.getRole().equals("TRAINER") ? Role.TRAINER : Role.CLIENT);
        user.setClient(dto.getClient());
        user.setTrainer(dto.getTrainer());
        return user;
    }

    public static UserReadOnlyDTO mapFromDto(User user) {
        UserReadOnlyDTO dto = new UserReadOnlyDTO();
        dto.setId(user.getId());
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole().name());
        dto.setClient(user.getClient());
        dto.setTrainer(user.getTrainer());
        return dto;
    }
}
