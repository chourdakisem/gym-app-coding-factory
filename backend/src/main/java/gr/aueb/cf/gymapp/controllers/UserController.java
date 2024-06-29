package gr.aueb.cf.gymapp.controllers;

import gr.aueb.cf.gymapp.dto.AuthResponseDTO;
import gr.aueb.cf.gymapp.dto.UserLoginDTO;
import gr.aueb.cf.gymapp.dto.user.UserInsertDTO;
import gr.aueb.cf.gymapp.dto.user.UserReadOnlyDTO;
import gr.aueb.cf.gymapp.dto.user.UserUpdateDTO;
import gr.aueb.cf.gymapp.mappers.UserMapper;
import gr.aueb.cf.gymapp.model.*;
import gr.aueb.cf.gymapp.services.AuthService;
import gr.aueb.cf.gymapp.services.IClientService;
import gr.aueb.cf.gymapp.services.ITrainerService;
import gr.aueb.cf.gymapp.services.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("")
public class UserController {

    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final IClientService clientService;
    private final ITrainerService trainerService;

    @Autowired
    public UserController(IUserService userService,
                          PasswordEncoder passwordEncoder,
                          AuthService authService,
                          IClientService clientService,
                          ITrainerService trainerService) {
        this.userService = userService;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
        this.clientService = clientService;
        this.trainerService = trainerService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserInsertDTO insertDTO,
                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var fieldErrors = bindingResult.getFieldErrors();
            List<ErrorDetails> errors = fieldErrors.stream()
                    .map(fieldError -> new ErrorDetails(fieldError.getField() + " " + fieldError.getDefaultMessage()))
                    .collect(Collectors.toList());
            return ResponseEntity
                    .status(400)
                    .body(errors);
        }

        insertDTO.setPassword(passwordEncoder.encode(insertDTO.getPassword()));
        User user = userService.createUser(insertDTO);
        UserReadOnlyDTO readOnlyDTO = UserMapper.mapFromDto(user);
        return ResponseEntity
                .status(201)
                .body(readOnlyDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody UserLoginDTO userLoginDTO) {
        AuthResponseDTO responseDTO = authService.authenticate(userLoginDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<?> update(@PathVariable("userId") Long id,
                                    @Valid @RequestBody UserUpdateDTO updateDTO,
                                                  BindingResult bindingResult) {
        if (!id.equals(updateDTO.getId())) {
            return ResponseEntity
                    .status(401)
                    .body(new ErrorDetails("Id does not match"));
        }

        if (bindingResult.hasErrors()) {
            var fieldErrors = bindingResult.getFieldErrors();
            List<ErrorDetails> errors = fieldErrors.stream()
                    .map(fieldError -> new ErrorDetails(fieldError.getField() + " " + fieldError.getDefaultMessage()))
                    .collect(Collectors.toList());
            return ResponseEntity
                    .status(400)
                    .body(errors);
        }

        User user = userService.findByUsername(updateDTO.getUsername());
        updateDTO.setPassword(user.getPassword());

        user = userService.updateUser(updateDTO);

        UserReadOnlyDTO readOnlyDTO = UserMapper.mapFromDto(user);
        return ResponseEntity.ok(readOnlyDTO);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> delete(@PathVariable("userId") Long id) {
        User user = userService.deleteUser(id);
        UserReadOnlyDTO readOnlyDTO = UserMapper.mapFromDto(user);
        return ResponseEntity.ok(readOnlyDTO);
    }
}
