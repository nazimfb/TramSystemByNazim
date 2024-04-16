package az.code.trammanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonPropertyOrder({ "code", "message" })
public class ErrorInfo {

    private Integer code;

    private String message;

}