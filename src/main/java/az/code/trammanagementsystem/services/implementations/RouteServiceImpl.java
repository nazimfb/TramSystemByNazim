package az.code.trammanagementsystem.services.implementations;

import az.code.trammanagementsystem.entity.Route;
import az.code.trammanagementsystem.exceptions.RouteNotFoundException;
import az.code.trammanagementsystem.repository.RouteRepository;
import az.code.trammanagementsystem.services.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final RouteRepository repository;

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
        System.out.println(route);
        return repository.save(route);
    }

    @Override
    public Route updateRoute(Route updatedRoute) {
        Optional<Route> route = repository.findById(updatedRoute.getId());
        if (route.isEmpty())
            throw new RouteNotFoundException();
        route.get().setName(updatedRoute.getName());
        route.get().setStops(updatedRoute.getStops());
        route.get().setSchedule(updatedRoute.getSchedule());
        return repository.save(route.get());
    }

    @Override
    public void deleteRoute(Long id) {
        Optional<Route> route = repository.findById(id);
        if (route.isEmpty())
            throw new RouteNotFoundException();
        repository.delete(route.get());
    }
}
