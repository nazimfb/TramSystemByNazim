package az.code.trammanagementsystem.exceptions;

import az.code.trammanagementsystem.dto.ErrorInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class TramException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private final ErrorInfo errorInfo;

    public TramException(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }
}
