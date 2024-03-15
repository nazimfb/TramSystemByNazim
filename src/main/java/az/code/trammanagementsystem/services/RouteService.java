package az.code.trammanagementsystem.services;

import az.code.trammanagementsystem.entity.Route;

import java.util.List;

public interface RouteService {
    Route getRoute(Long id);
    List<Route> getAll();
    Route createRoute(Route route);
    Route updateRoute(Route route);
    void deleteRoute(Long id);
}
