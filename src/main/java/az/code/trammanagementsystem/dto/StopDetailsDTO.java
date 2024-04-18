package az.code.trammanagementsystem.dto;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StopDetailsDTO {
    private Long id;
    private double latitude;
    private double longitude;
}
