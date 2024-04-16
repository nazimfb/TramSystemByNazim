package az.code.trammanagementsystem.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class TramNotAssignedToRouteException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private String message;
    public TramNotAssignedToRouteException(String message) {
        this.setMessage(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
