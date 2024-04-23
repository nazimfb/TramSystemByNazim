package az.code.trammanagementsystem.services.implementations;

import az.code.trammanagementsystem.dto.UpdateTramDTO;
import az.code.trammanagementsystem.entity.*;
import az.code.trammanagementsystem.exceptions.*;
import az.code.trammanagementsystem.repository.DriverRepository;
import az.code.trammanagementsystem.repository.TramRepository;
import az.code.trammanagementsystem.services.TramService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

import static az.code.trammanagementsystem.services.helpers.TrajectoryHelper.findClosestTrajectory;

@Service
@RequiredArgsConstructor
public class TramServiceImpl implements TramService {

    private final TramRepository repository;
    private final DriverRepository driverRepository;

    @Override
    public Tram addTram(Tram tram) {
        try {
            tram.setId(UUID.randomUUID());
            return repository.save(tram);
        } catch (Exception e) {
            throw new InvalidTramFormatException(e.getMessage());
        }
    }

    @Override
    public Tram getTram(UUID id) {
        Optional<Tram> tram = repository.findById(id);
        if (tram.isEmpty())
            throw new TramNotFoundException();
        return tram.get();
    }

    @Override
    public List<Tram> getAll() {
        return repository.findAll();
    }

    @Override
    public Tram updateTram(UUID id, UpdateTramDTO updatedTram) {
        Tram tram = getTram(id);

        if (updatedTram.getDriverId() != null) {
            if (tram.getCurrentRoute() != null)
                throw new TramMustNotBeOnRouteException("Remove tram from route to change its driver");

            Optional<Driver> driver = driverRepository.findById(updatedTram.getDriverId());
            if (driver.isEmpty())
                throw new DriverNotFoundException();
            if (driver.get().getCurrentTram() != null)
                throw new DriverAlreadyInTramException();
            tram.setDriver(driver.get());
        }

        if (updatedTram.getModel() != null)
            tram.setModel(updatedTram.getModel());

        return repository.save(tram);
    }

    @Override
    public void deleteTram(UUID id) {
        Tram tram = getTram(id);
        repository.delete(tram);
    }

    @Override
    public List<Tram> getTramLocations() {
        return repository.findByCurrentRouteNotNull();
    }

    @Override
    public Tram deleteTramDriver(UUID tramId) {
        Tram tram = getTram(tramId);
        if (tram.getCurrentRoute() != null)
            throw new TramMustNotBeOnRouteException();

        if (tram.getDriver() != null) {
            Optional<Driver> driver = driverRepository.findById(tram.getDriver().getId());
            if (driver.isPresent()) {
                driver.get().setCurrentTram(null);
                tram.setDriver(null);
            }
        } else throw new TramHasNoDriverException();

        return repository.save(tram);
    }

    @Scheduled(fixedRate = 5000)
    private void updateTramsLocations() {
        List<Tram> activeTrams = repository.findByCurrentRouteNotNull();
        for (Tram activeTram : activeTrams) {
            Route currentRoute = activeTram.getCurrentRoute();
            if (currentRoute != null && currentRoute.getTramTrajectories() != null && !currentRoute.getTramTrajectories().isEmpty()) {
                List<TramTrajectory> tramTrajectories = currentRoute.getTramTrajectories();

                double currentLat = activeTram.getLatitude();
                double currentLng = activeTram.getLongitude();

                TramTrajectory closestTrajectory = findClosestTrajectory(tramTrajectories, currentLat, currentLng);

                int currentIndex = tramTrajectories.indexOf(closestTrajectory);
                int nextIndex = (currentIndex + 1) % tramTrajectories.size(); // Wrap around if reached the end

                TramTrajectory nextTrajectory = tramTrajectories.get(nextIndex);
                activeTram.setLatitude(nextTrajectory.getLatitude());
                activeTram.setLongitude(nextTrajectory.getLongitude());

                repository.save(activeTram);
            }
        }
    }
}