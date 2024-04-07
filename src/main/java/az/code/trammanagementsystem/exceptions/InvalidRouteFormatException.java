package az.code.trammanagementsystem.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class InvalidRouteFormatException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 2L;
    private String message;

    public InvalidRouteFormatException(String message) {
        this.setMessage(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
