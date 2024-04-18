package az.code.trammanagementsystem.services.implementations;

import az.code.trammanagementsystem.entity.Stop;
import az.code.trammanagementsystem.exceptions.StopNotFoundException;
import az.code.trammanagementsystem.repository.StopRepository;
import az.code.trammanagementsystem.services.StopService;
import az.code.trammanagementsystem.exceptions.InvalidStopFormatException;
import static az.code.trammanagementsystem.services.helpers.DistanceHelper.stopTooCloseToOther;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StopServiceImpl implements StopService {
    private final StopRepository repository;

    @Override
    public Stop createStop(Stop stop) {
        if (stop.getLatitude() == null || stop.getLongitude() == null
                || stop.getLatitude() == 0 || stop.getLongitude() == 0)
            throw new InvalidStopFormatException("latitude/longitude can not be null or 0");
        if (stopTooCloseToOther(stop, repository.findAll()))
            throw new InvalidStopFormatException("This stop is too close to other stop (min: 50m)");
        try {
            return repository.save(Stop.builder()
                    .latitude(stop.getLatitude())
                    .longitude(stop.getLongitude())
                    .build());
        } catch (Exception e) {
            throw new InvalidStopFormatException(e.getMessage());
        }
    }

    @Override
    public Stop getStop(Long id) {
        return repository.findById(id).orElseThrow(StopNotFoundException::new);
    }

    @Override
    public List<Stop> getAll() {
        return repository.findAll();
    }

    @Override
    public Stop updateStop(Long id, Stop updatedStop) {
        Stop stop = getStop(id);
        if (updatedStop.getLatitude() == null || updatedStop.getLongitude() == null
                || stop.getLatitude() == 0 || stop.getLongitude() == 0)
            throw new InvalidStopFormatException("latitude/longitude can not be null");
        stop.setLatitude(updatedStop.getLatitude());
        stop.setLongitude(updatedStop.getLongitude());
        try {
            return repository.save(stop);
        } catch (Exception e) {
            throw new InvalidStopFormatException(e.getMessage());
        }
    }

    @Override
    public void deleteStop(Long id) {
        Stop stop = getStop(id);
        try {
            repository.delete(stop);
        } catch (Exception e) {
            throw new InvalidStopFormatException(e.getMessage());
        }
    }
}
