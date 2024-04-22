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
public class ActiveTramDTO {
    private UUID id;
    private Double latitude;
    private Double longitude;
    private Long driverId;
}
