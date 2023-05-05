package be.ita.toernooimanager.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginCredentials {
    private final String userName;
    private final String password;
}
