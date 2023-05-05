package be.ita.toernooimanager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StandardErrorFormat {
    private String reason;
    private String fieldName;

}