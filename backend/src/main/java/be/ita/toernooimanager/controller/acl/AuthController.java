package be.ita.toernooimanager.controller.acl;

import be.ita.toernooimanager.controller.DTO.LoginCredentials;
import be.ita.toernooimanager.model.local.acl.User;
import be.ita.toernooimanager.security.util.JWTUtil;
import be.ita.toernooimanager.service.acl.UserService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authManager;

    @PostMapping("/login")
    public Map<String, Object> loginHandler(@Valid @Nullable @RequestBody LoginCredentials loginCredentials) {
        log.info("POST: /login");
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(loginCredentials.getName(), loginCredentials.getPassword());

            authManager.authenticate(authInputToken);
            User user = userService.getUserByName(loginCredentials.getName());
            String token = jwtUtil.generateToken(user);

            return Collections.singletonMap("jwt-token", token);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Invalid Login Credentials");
        }
    }
}