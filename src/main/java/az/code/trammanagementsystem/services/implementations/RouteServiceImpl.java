package az.code.trammanagementsystem.services.implementations;

import az.code.trammanagementsystem.entity.Route;
import az.code.trammanagementsystem.entity.Tram;
import az.code.trammanagementsystem.exceptions.InvalidRouteFormatException;
import az.code.trammanagementsystem.exceptions.RouteNotFoundException;
import az.code.trammanagementsystem.repository.RouteRepository;
import az.code.trammanagementsystem.services.RouteService;
import az.code.trammanagementsystem.services.TramService;
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
        if (route.isEmpty())
            throw new RouteNotFoundException();
        return route.get();
    }

    @Override
    public List<Route> getAll() {
        return repository.findAll();
    }

    @Override
    public Route createRoute(Route route) {
        try {
            return repository.save(route);
        } catch (Exception e) {
            throw new InvalidRouteFormatException(e.getMessage());
        }
    }

    @Override
    public Route updateRoute(Route updatedRoute) {
        Route route = getRoute(updatedRoute.getId());
        route.setName(updatedRoute.getName());
        route.setStops(updatedRoute.getStops());
        route.setSchedule(updatedRoute.getSchedule());
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

    @Override
    public void addTramToRoute(UUID tramId, Long routeId) {
        Route route = getRoute(routeId);
        Tram tram = tramService.getTram(tramId);
        List<Tram> trams = route.getTrams();
        trams.add(tram);
        route.setTrams(trams);
        repository.save(route);
    }

    @Override
    public void deleteTramFromRoute(Long routeId, UUID tramId) {
        Route route = getRoute(routeId);
        Tram tram = tramService.getTram(tramId);

        List<Tram> updatedTrams = route.getTrams();
        updatedTrams.remove(tram);
        route.setTrams(updatedTrams);

        repository.save(route);

    }
}
