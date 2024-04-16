package az.code.trammanagementsystem.exceptions;

import az.code.trammanagementsystem.dto.ErrorInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class TramNotFoundException extends TramException {
    @Serial
    private static final long serialVersionUID = 1L;

    public TramNotFoundException(ErrorInfo errorInfo) {
        super(errorInfo);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
