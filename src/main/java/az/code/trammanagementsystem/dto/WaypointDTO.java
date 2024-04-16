package az.code.trammanagementsystem.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WaypointDTO {
    private double lat;
    private double lng;
}
