package az.code.trammanagementsystem.dto;

import az.code.trammanagementsystem.validation.ValidManufactureYear;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class TramDTO {
    private String manufacturer;
    private String model;
    @ValidManufactureYear
    private Integer manufactureYear;
}
