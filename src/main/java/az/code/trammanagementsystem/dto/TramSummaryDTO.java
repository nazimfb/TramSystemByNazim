package az.code.trammanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TramSummaryDTO {
    private UUID id;
    private String model;
    private Integer manufactureYear;
    private boolean active;
    private Integer driverId;
    private String driverName;
}
