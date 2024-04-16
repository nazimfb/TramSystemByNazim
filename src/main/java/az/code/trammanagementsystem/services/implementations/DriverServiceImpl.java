package az.code.trammanagementsystem.services.implementations;

import az.code.trammanagementsystem.entity.Driver;
import az.code.trammanagementsystem.exceptions.DriverNotFoundException;
import az.code.trammanagementsystem.exceptions.InvalidDriverFormatException;
import az.code.trammanagementsystem.repository.DriverRepository;
import az.code.trammanagementsystem.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository repository;

    @Override
    public Driver addDriver(Driver newDriver) {
        if (newDriver.getName() == null)
            throw new InvalidDriverFormatException();
        try {
            return repository.save(Driver.builder()
                    .name(newDriver.getName())
                    .currentTram(newDriver.getCurrentTram())
                    .build());
        } catch (Exception e) {
            throw new InvalidDriverFormatException(e.getMessage());
        }
    }

    @Override
    public Driver getDriver(Long id) {
        Optional<Driver> driverOptional = repository.findById(id);
        if (driverOptional.isEmpty())
            throw new DriverNotFoundException();
        return driverOptional.get();
    }

    @Override
    public List<Driver> getAllDrivers() {
        return repository.findAll();
    }

    @Override
    public Driver updateDriver(Long id, Driver updatedDriver) {
        Driver driver = getDriver(id);
        if (updatedDriver.getName() != null)
            driver.setName(updatedDriver.getName());
        if (updatedDriver.getCurrentTram() != null)
            driver.setCurrentTram(updatedDriver.getCurrentTram());
        return repository.save(driver);
    }

    @Override
    public void deleteDriver(Long id) {
        Driver driver = getDriver(id);
        repository.delete(driver);
    }
}