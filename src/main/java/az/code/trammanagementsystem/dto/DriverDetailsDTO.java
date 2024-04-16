package az.code.trammanagementsystem.dto;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DriverDetailsDTO {
    private String name;
    private UUID currentTramId;
}
