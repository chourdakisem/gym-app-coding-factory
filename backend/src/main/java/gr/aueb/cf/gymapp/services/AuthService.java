package gr.aueb.cf.gymapp.services;

import gr.aueb.cf.gymapp.dto.AuthResponseDTO;
import gr.aueb.cf.gymapp.dto.UserLoginDTO;
import gr.aueb.cf.gymapp.model.User;
import gr.aueb.cf.gymapp.services.exceptions.RefreshTokenNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Value("${refresh.secret.key}")
    private String refreshKey;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final IUserService userService;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager,
                       JwtService jwtService,
                       IUserService userService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    public AuthResponseDTO authenticate(UserLoginDTO userLoginDTO) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginDTO.getUsername(),
                userLoginDTO.getPassword()
        ));

        User user = userService.findByUsername(userLoginDTO.getUsername());
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return new AuthResponseDTO(accessToken, refreshToken, user);
    }

    public AuthResponseDTO refresh(String authHeader) {
        String refreshToken = authHeader.substring(7);
        String username = Optional.ofNullable(jwtService.extractUsername(refreshKey, refreshToken))
                .orElseThrow(() -> new RuntimeException("Something went wrong with the refresh token"));
        User user = userService.findByUsername(username);
        if (!jwtService.isValid(refreshKey, refreshToken, user)) {
            throw new RefreshTokenNotValidException();
        }
        String accessToken = jwtService.generateAccessToken(user);
        refreshToken = jwtService.generateRefreshToken(user);
        var authResponse = new AuthResponseDTO(accessToken, refreshToken, user);
        return authResponse;
    }
}
