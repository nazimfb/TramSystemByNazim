package az.code.trammanagementsystem.controller;

import az.code.trammanagementsystem.entity.Route;
import az.code.trammanagementsystem.services.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/routes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class RouteController {

    private final RouteService service;

    @PostMapping
    private ResponseEntity<Route> createRoute(@RequestBody Route route) {
        return new ResponseEntity<>(service.createRoute(route), HttpStatus.OK);
    }
    @GetMapping
    private ResponseEntity<List<Route>> getAllRoutes() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    private ResponseEntity<Route> getRouteById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getRoute(id), HttpStatus.OK);
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
