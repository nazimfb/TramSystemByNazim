package az.code.trammanagementsystem.controller;

import az.code.trammanagementsystem.dto.RouteDTO;
import az.code.trammanagementsystem.dto.RouteDetailDTO;
import az.code.trammanagementsystem.dto.RouteSummaryDTO;
import az.code.trammanagementsystem.entity.Route;
import az.code.trammanagementsystem.entity.Tram;
import az.code.trammanagementsystem.services.RouteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/routes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class RouteController {

    private final RouteService service;
    private final ModelMapper mapper;

    @PostMapping
    private ResponseEntity<Route> createRoute(@RequestBody RouteDTO routeDto) {
        return new ResponseEntity<>(service.createRoute(mapper.map(routeDto,Route.class)), HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<List<RouteSummaryDTO>> getAllRoutes() {
        return new ResponseEntity<>(service.getAll().stream()
                .map(route -> mapper.map(route, RouteSummaryDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<RouteDetailDTO> getRouteById(@PathVariable Long id) {
        return new ResponseEntity<>(mapper.map(service.getRoute(id), RouteDetailDTO.class), HttpStatus.OK);
    }

    @GetMapping("/{id}/trams")
    private ResponseEntity<List<Tram>> getTramsOnRoute(@PathVariable Long id) {
        return new ResponseEntity<>(service.getTramsOnRoute(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/trams")
    private HttpStatus addTramToRoute(@RequestBody UUID tramId, @PathVariable Long id) {
        service.addTramToRoute(tramId, id);
        return HttpStatus.ACCEPTED;
    }

    @DeleteMapping("/{id}/trams/{tramId}")
    private HttpStatus deleteTramFromRoute(@PathVariable("id") Long routeId, @PathVariable UUID tramId) {
        service.deleteTramFromRoute(routeId, tramId);
        return HttpStatus.NO_CONTENT;
    }

    @PutMapping("/{id}")
    private ResponseEntity<Route> updateRoute(@PathVariable Long id, @RequestBody Route route) {
        route.setId(id);
        return new ResponseEntity<>(service.updateRoute(route), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private HttpStatus deleteRoute(@PathVariable Long id) {
        service.deleteRoute(id);
        return HttpStatus.NO_CONTENT;
    }
}
