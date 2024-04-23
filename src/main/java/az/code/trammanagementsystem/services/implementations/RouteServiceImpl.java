package az.code.trammanagementsystem.services.implementations;

import az.code.trammanagementsystem.entity.Route;
import az.code.trammanagementsystem.entity.Tram;
import az.code.trammanagementsystem.entity.TramTrajectory;
import az.code.trammanagementsystem.entity.Waypoint;
import az.code.trammanagementsystem.exceptions.*;
import az.code.trammanagementsystem.repository.RouteRepository;
import az.code.trammanagementsystem.services.RouteService;
import az.code.trammanagementsystem.services.TramService;
import az.code.trammanagementsystem.services.helpers.TrajectoryHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final RouteRepository repository;
    private final TramService tramService;

    @Override
    public Route getRoute(Long id) {
        Optional<Route> route = repository.findById(id);
        return route.orElseThrow(RouteNotFoundException::new);
    }

    @Override
    public List<Route> getAll() {
        return repository.findAll();
    }

    @Override
    public Route createRoute(Route route) {
        try {
            //Save the route first to generate its ID
            for (Waypoint waypoint : route.getWaypoints())
                waypoint.setRoute(route);
            route = repository.save(route);

            if (route.getWaypoints() != null) {
                List<TramTrajectory> tramTrajectories = TrajectoryHelper.getTramTrajectories(route);

                route.setTramTrajectories(tramTrajectories);
                route = repository.save(route);
            }

            return route;
        } catch (Exception e) {
            throw new InvalidRouteFormatException(e.getMessage());
        }
    }


    @Override
    public Route updateRoute(Long id, Route updatedRoute) {
        Route route = getRoute(id);
        route.setName(updatedRoute.getName());
        route.setStops(updatedRoute.getStops());
        return repository.save(route);
    }

    @Override
    public void deleteRoute(Long id) {
        Route route = getRoute(id);
        repository.delete(route);
    }

    @Override
    public List<Tram> getTramsOnRoute(Long id) {
        return getRoute(id).getTrams();
    }


    public List<Waypoint> getRouteWaypoints(Long id) {
        return getRoute(id).getWaypoints();
    }

    @Override
    public void addTramToRoute(UUID tramId, Long routeId) {
        Route route = getRoute(routeId);
        Tram tram = tramService.getTram(tramId);

        if (tram.getCurrentRoute() != null)
            throw new TramAlreadyOnRouteException("Tram with id " + tram.getId()
                    + " is already on route with id "
                    + tram.getCurrentRoute().getId());
        else if (tram.getDriver() == null) {
            throw new InvalidTramFormatException("Tram must have a driver before adding to route");
        }

        tram.setCurrentRoute(route); /*important*/
        List<Tram> trams = route.getTrams();
        trams.add(tram);
        route.setTrams(trams); /*also important*/

        repository.save(route);
    }

    @Override
    public void deleteTramFromRoute(Long routeId, UUID tramId) {
        Route route = getRoute(routeId);
        Tram tram = tramService.getTram(tramId);

        if (tram.getCurrentRoute() == null || tram.getCurrentRoute().getId() != routeId)
            throw new TramNotAssignedToRouteException("Tram with ID " + tramId
                    + " is not assigned to the route " + routeId);

        tram.setCurrentRoute(null);
        tram.setLatitude(null);
        tram.setLongitude(null);

        List<Tram> trams = route.getTrams();
        trams.remove(tram);
        route.setTrams(trams);

        repository.save(route);
    }
}
