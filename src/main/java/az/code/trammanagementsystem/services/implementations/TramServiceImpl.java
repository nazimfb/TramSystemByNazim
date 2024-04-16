package az.code.trammanagementsystem.services.implementations;

import az.code.trammanagementsystem.dto.ErrorInfo;
import az.code.trammanagementsystem.entity.*;
import az.code.trammanagementsystem.exceptions.TramNotFoundException;
import az.code.trammanagementsystem.repository.TramRepository;
import az.code.trammanagementsystem.services.DriverService;
import az.code.trammanagementsystem.services.TramService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static az.code.trammanagementsystem.services.helpers.TrajectoryHelper.findClosestTrajectory;

@Service
@RequiredArgsConstructor
public class TramServiceImpl implements TramService {

    private final TramRepository repository;
    private final DriverService driverService;

    @Override
    public Tram addTram(Tram tram) {
        tram.setId(UUID.randomUUID());
        return repository.save(tram);
    }

    @Override
    public Tram getTram(UUID id) {
        Optional<Tram> tram = repository.findById(id);
        if (tram.isEmpty())
            throw new TramNotFoundException(ErrorInfo.builder()
                    .code(1)
                    .message("Tram not found")
                    .build());
        return tram.get();
    }

    @Override
    public List<Tram> getAll() {
        return repository.findAll();
    }

    @Override
    public Tram updateTram(UUID id, Tram updatedTram) {
        Tram tram = getTram(id);

        if (updatedTram.getDriver() != null) {
            Driver driver = driverService.getDriver(updatedTram.getDriver().getId());
            tram.setDriver(driver);
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
        List<Tram> trams = repository.findByCurrentRouteNotNull();
        for (Tram tram : trams) {
            Route currentRoute = tram.getCurrentRoute();
            if (currentRoute != null) {
                List<TramTrajectory> tramTrajectories = currentRoute.getTramTrajectories();
                if (tramTrajectories != null && !tramTrajectories.isEmpty()) {
                    if (tram.getLatitude() == null || tram.getLongitude() == null) {
                        TramTrajectory firstTrajectory = tramTrajectories.getFirst();
                        tram.setLatitude(firstTrajectory.getLatitude());
                        tram.setLongitude(firstTrajectory.getLongitude());
                    } else {
                        double currentLat = tram.getLatitude();
                        double currentLng = tram.getLongitude();
                        TramTrajectory closestTrajectory = findClosestTrajectory(tramTrajectories, currentLat, currentLng);

                        // Update tram location to the next tram trajectory point
                        tram.setLatitude(closestTrajectory.getLatitude());
                        tram.setLongitude(closestTrajectory.getLongitude());
                    }
                    repository.save(tram);
                }
            }
        }
    }

}