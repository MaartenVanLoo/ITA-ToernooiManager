package be.ita.toernooimanager.service.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    private long id;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}


