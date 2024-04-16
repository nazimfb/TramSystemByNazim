package az.code.trammanagementsystem.exceptions;

import az.code.trammanagementsystem.dto.ErrorInfo;
import lombok.*;

import java.io.Serial;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class InvalidDriverFormatException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
//    private String message;
    private List<ErrorInfo> errors;
    public InvalidDriverFormatException(String message) {
        super(message);
        this.errors.add(ErrorInfo.builder().message(message).build());
    }

    public InvalidDriverFormatException(List<String> messages) {

    }

}
