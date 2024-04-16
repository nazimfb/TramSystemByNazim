package az.code.trammanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DriverSummaryDTO {
    private Long id;
    private String name;
    private UUID currentTramId;
}
