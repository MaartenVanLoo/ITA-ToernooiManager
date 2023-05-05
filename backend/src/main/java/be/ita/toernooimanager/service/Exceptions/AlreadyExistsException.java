package be.ita.toernooimanager.service.Exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AlreadyExistsException extends Exception{
    public AlreadyExistsException(String message) {
        super(message);
    }
}
