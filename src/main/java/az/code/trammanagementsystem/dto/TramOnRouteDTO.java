package az.code.trammanagementsystem.dto;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TramOnRouteDTO {
    private UUID id;
    private Double latitude;
    private Double longitude;
}
