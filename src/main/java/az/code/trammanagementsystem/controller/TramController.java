package az.code.trammanagementsystem.controller;

import az.code.trammanagementsystem.dto.*;
import az.code.trammanagementsystem.entity.Tram;
import az.code.trammanagementsystem.services.TramService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/trams")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class TramController {
    private final TramService service;
    private final ModelMapper mapper;

    @PostMapping
    private ResponseEntity<TramSummaryDTO> addTram(@Valid @RequestBody TramDTO tramDto) {
        return new ResponseEntity<>(
                mapper.map(service.addTram(mapper.map(tramDto,Tram.class)),
                        TramSummaryDTO.class),
                HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<List<TramSummaryDTO>> getAllTrams() {
        return new ResponseEntity<>(service.getAll().stream()
                .map(tram -> mapper.map(tram, TramSummaryDTO.class))
                .toList(), HttpStatus.OK);
    }

//    @GetMapping("/locatee")
//    private String updateLocation() {
//        service.updateLocation();
//        return "yes";
//    }

    @GetMapping("/{id}")
    private ResponseEntity<TramDetailDTO> getTramById(@PathVariable UUID id) {
        return new ResponseEntity<>(mapper.map(service.getTram(id), TramDetailDTO.class), HttpStatus.OK);
    }

    @GetMapping("/active")
    private ResponseEntity<List<ActiveTramDTO>> getActiveTrams() {
        return new ResponseEntity<>(service.getTramLocations().stream()
                .map(tram -> mapper.map(tram, ActiveTramDTO.class)).toList(),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<TramDetailDTO> updateTram(@PathVariable UUID id, @RequestBody UpdateTramDTO tramDto) {
        return new ResponseEntity<>(
                mapper.map(
                        service.updateTram(id, mapper.map(tramDto, Tram.class)),
                        TramDetailDTO.class),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<HttpStatus> deleteTram(@PathVariable UUID id) {
        service.deleteTram(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
