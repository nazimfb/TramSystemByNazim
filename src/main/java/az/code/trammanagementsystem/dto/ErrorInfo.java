package az.code.trammanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonPropertyOrder({ "status", "message", "timestamp" })
public class ErrorInfo {
    private Integer status;
    private String message;
    private final LocalDateTime timestamp = LocalDateTime.now();
}
