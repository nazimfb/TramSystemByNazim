package az.code.trammanagementsystem.services;

import az.code.trammanagementsystem.entity.Route;
import az.code.trammanagementsystem.entity.Tram;

import java.util.List;
import java.util.UUID;

public interface RouteService {
    Route getRoute(Long id);
    List<Route> getAll();
    Route createRoute(Route route);
    Route updateRoute(Route route);
    void deleteRoute(Long id);
    List<Tram> getTramsOnRoute(Long id);
    void addTramToRoute(UUID tramId, Long routeId);
    void deleteTramFromRoute(Long routeId, UUID tramId);
}
