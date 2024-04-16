package az.code.trammanagementsystem.exceptions;

import az.code.trammanagementsystem.dto.ErrorInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class InvalidRouteFormatException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 2L;
    private String message;
    private List<ErrorInfo> errors;

    public InvalidRouteFormatException(String message) {
        super(message);
    }
//    public InvalidRouteFormatException(String message, List<ErrorInfo> errors) {
//        super(message);
//        this.setErrors(errors);
//    }

    @Override
    public String getMessage() {
        return message;
    }
}
