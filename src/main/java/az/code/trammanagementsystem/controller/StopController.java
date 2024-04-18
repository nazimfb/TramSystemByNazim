package az.code.trammanagementsystem.controller;

import az.code.trammanagementsystem.dto.StopDTO;
import az.code.trammanagementsystem.dto.StopDetailsDTO;
import az.code.trammanagementsystem.entity.Stop;
import az.code.trammanagementsystem.services.StopService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/stops")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class StopController {
    private final StopService service;
    private final ModelMapper mapper;

    @PostMapping
    private ResponseEntity<StopDetailsDTO> addStop(@RequestBody StopDTO stopDTO) {
        return new ResponseEntity<>(
                mapper.map(
                        service.createStop(mapper.map(stopDTO, Stop.class)),
                        StopDetailsDTO.class),
                HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<List<StopDetailsDTO>> getAllStops() {
        return new ResponseEntity<>(service.getAll().stream()
                .map(stop -> mapper.map(stop, StopDetailsDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<StopDTO> getStopById(@PathVariable Long id) {
        return new ResponseEntity<>(
                mapper.map(service.getStop(id), StopDTO.class),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<StopDetailsDTO> updateStop(@PathVariable Long id, @RequestBody StopDTO stopDTO) {
        return new ResponseEntity<>(
                mapper.map(
                        service.updateStop(id, mapper.map(stopDTO, Stop.class)),
                        StopDetailsDTO.class),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<HttpStatus> removeStop(@PathVariable Long id) {
        service.deleteStop(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
