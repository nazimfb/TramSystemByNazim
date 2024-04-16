package az.code.trammanagementsystem.dto;

import az.code.trammanagementsystem.entity.Driver;
import az.code.trammanagementsystem.entity.Route;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TramDetailDTO {
    private String manufacturer;
    private String model;
    private Integer manufactureYear;
    private Long driverId;
    private String driverName;
    private Long currentRouteId;
    private String currentRouteName;
}
