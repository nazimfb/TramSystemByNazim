package az.code.trammanagementsystem.controller;

import az.code.trammanagementsystem.dto.DriverDetailsDTO;
import az.code.trammanagementsystem.dto.DriverSummaryDTO;
import az.code.trammanagementsystem.entity.Driver;
import az.code.trammanagementsystem.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class DriverController {
    private final DriverService service;
    private final ModelMapper mapper;

    @PostMapping
    private ResponseEntity<DriverDetailsDTO> addDriver(@RequestBody DriverDetailsDTO driverDTO) {
        return new ResponseEntity<>(mapper.map(
                service.addDriver(mapper.map(driverDTO, Driver.class)), DriverDetailsDTO.class),
                HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<List<DriverSummaryDTO>> getAllDrivers() {
        return new ResponseEntity<>(service.getAllDrivers().stream()
                .map(driver -> mapper.map(driver, DriverSummaryDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<DriverDetailsDTO> getDriverById(@PathVariable Long id) {
        return new ResponseEntity<>(mapper.map(service.getDriver(id), DriverDetailsDTO.class), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<DriverDetailsDTO> updateDriverInfo(@PathVariable Long id, @RequestBody DriverDetailsDTO driverData) {
        return new ResponseEntity<>(mapper.map(
                service.updateDriver(id, mapper.map(driverData, Driver.class)),
                DriverDetailsDTO.class),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private HttpStatus deleteDriver(@PathVariable Long id) {
        service.deleteDriver(id);
        return HttpStatus.NO_CONTENT;
    }
}
