package az.code.trammanagementsystem.dto;

import az.code.trammanagementsystem.entity.Driver;
import az.code.trammanagementsystem.validation.ValidManufactureYear;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTramDTO {
    private Long driverId;
    private String model;
}
