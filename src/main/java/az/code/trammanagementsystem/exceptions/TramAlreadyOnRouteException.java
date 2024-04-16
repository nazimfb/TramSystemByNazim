package az.code.trammanagementsystem.exceptions;

import lombok.NoArgsConstructor;
import java.io.Serial;

@NoArgsConstructor
public class TramAlreadyOnRouteException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 3L;
    private String message;

    public TramAlreadyOnRouteException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
