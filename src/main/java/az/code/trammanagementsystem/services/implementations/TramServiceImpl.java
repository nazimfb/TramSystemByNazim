package az.code.trammanagementsystem.services.implementations;

import az.code.trammanagementsystem.dto.UpdateTramDTO;
import az.code.trammanagementsystem.entity.*;
import az.code.trammanagementsystem.exceptions.DriverNotFoundException;
import az.code.trammanagementsystem.exceptions.InvalidTramFormatException;
import az.code.trammanagementsystem.exceptions.TramNotFoundException;
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
            Optional<Driver> driver = driverRepository.findById(updatedTram.getDriverId());
            if (driver.isEmpty())
                throw new DriverNotFoundException();
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


    @Scheduled(fixedRate = 5000)
    private void updateLocation() {
        List<Tram> activeTrams = repository.findByCurrentRouteNotNull();
        for (Tram activeTram : activeTrams) {
            Route currentRoute = activeTram.getCurrentRoute();
            if (currentRoute != null
                    && currentRoute.getTramTrajectories() != null
                    && !currentRoute.getTramTrajectories().isEmpty()) {
                List<TramTrajectory> tramTrajectories = currentRoute.getTramTrajectories();
                List<TramTrajectory> reverseTramTrajectories = new ArrayList<>(tramTrajectories);
                Collections.reverse(reverseTramTrajectories);

                if (activeTram.getLatitude() == null || activeTram.getLongitude() == null) {
                    TramTrajectory firstTrajectory = tramTrajectories.getFirst();
                    activeTram.setLatitude(firstTrajectory.getLatitude());
                    activeTram.setLongitude(firstTrajectory.getLongitude());
                } else if (activeTram.getLatitude() == tramTrajectories.getLast().getLatitude()
                        && activeTram.getLongitude() == tramTrajectories.getLast().getLongitude()) {
                    TramTrajectory firstReverseTrajectory = reverseTramTrajectories.getFirst();
                    activeTram.setLatitude(firstReverseTrajectory.getLatitude());
                    activeTram.setLongitude(firstReverseTrajectory.getLongitude());
                } else {
                    double currentLat = activeTram.getLatitude();
                    double currentLng = activeTram.getLongitude();
                    TramTrajectory closestTrajectory = findClosestTrajectory(tramTrajectories, currentLat, currentLng);

                    activeTram.setLatitude(closestTrajectory.getLatitude());
                    activeTram.setLongitude(closestTrajectory.getLongitude());
                }

                repository.save(activeTram);
            }
        }
    }
}