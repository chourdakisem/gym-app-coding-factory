package gr.aueb.cf.gymapp.controllers;

import gr.aueb.cf.gymapp.model.ErrorDetails;
import gr.aueb.cf.gymapp.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class RefreshController {

    private final AuthService authService;

    public RefreshController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refresh(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity
                    .status(400)
                    .body(new ErrorDetails("Bad credentials"));
        }
        var authResponse = authService.refresh(authHeader);
        return ResponseEntity.ok(authResponse);
    }
}
