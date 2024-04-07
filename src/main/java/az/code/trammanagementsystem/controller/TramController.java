package az.code.trammanagementsystem.controller;

import az.code.trammanagementsystem.dto.TramDTO;
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
    private ResponseEntity<Tram> addTram(@Valid @RequestBody TramDTO tramDto) {
        return new ResponseEntity<>(service.addTram(mapper.map(tramDto,Tram.class)), HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<List<Tram>> getAllTrams() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Tram> getTramById(@PathVariable UUID id) {
        return new ResponseEntity<>(service.getTram(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Tram> updateRoute(@PathVariable UUID id, @RequestBody Tram tram) {
        tram.setId(id);
        return new ResponseEntity<>(service.update(tram), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private HttpStatus deleteRoute(@PathVariable UUID id) {
        service.deleteTram(id);
        return HttpStatus.NO_CONTENT;
    }
}
