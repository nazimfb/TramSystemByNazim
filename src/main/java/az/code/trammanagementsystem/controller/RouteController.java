package az.code.trammanagementsystem.controller;

import az.code.trammanagementsystem.dto.*;
import az.code.trammanagementsystem.entity.Route;
import az.code.trammanagementsystem.entity.Tram;
import az.code.trammanagementsystem.entity.Waypoint;
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
    private ResponseEntity<RouteDetailDTO> createRoute(@RequestBody RouteDTO routeDto) {
        return new ResponseEntity<>(mapper.map(
                service.createRoute(mapper.map(routeDto, Route.class)), RouteDetailDTO.class),
                HttpStatus.OK);
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
    private ResponseEntity<List<TramOnRouteDTO>> getTramsOnRoute(@PathVariable Long id) {
        return new ResponseEntity<>(
                service.getTramsOnRoute(id).stream()
                        .map(tram -> mapper.map(tram, TramOnRouteDTO.class)).toList(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}/waypoints")
    private ResponseEntity<List<WaypointDTO>> getRouteWaypoints(@PathVariable Long id) {
        return new ResponseEntity<>(
                service.getRouteWaypoints(id).stream()
                        .map(waypoint -> mapper.map(waypoint, WaypointDTO.class))
                        .toList(),
                HttpStatus.OK);
    }

    @PostMapping("/{id}/trams")
    private HttpStatus addTramToRoute(@RequestBody AddTramToRouteDTO tramIdDto, @PathVariable Long id) {
        service.addTramToRoute(mapper.map(tramIdDto, Tram.class), id);
        return HttpStatus.ACCEPTED;
    }

    @DeleteMapping("/{id}/trams/{tramId}")
    private HttpStatus deleteTramFromRoute(@PathVariable("id") Long routeId, @PathVariable UUID tramId) {
        service.deleteTramFromRoute(routeId, tramId);
        return HttpStatus.NO_CONTENT;
    }

    @GetMapping("/waypoints")
    private ResponseEntity<List<RouteWaypointsDTO>> getRouteWaypoints() {
        return new ResponseEntity<>(
                service.getAll().stream()
                        .map(route -> mapper.map(route, RouteWaypointsDTO.class)).toList(),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Route> updateRoute(@PathVariable Long id, @RequestBody UpdateRouteDTO route) {
        return new ResponseEntity<>(service.updateRoute(id, mapper.map(route,Route.class)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private HttpStatus deleteRoute(@PathVariable Long id) {
        service.deleteRoute(id);
        return HttpStatus.NO_CONTENT;
    }
}
