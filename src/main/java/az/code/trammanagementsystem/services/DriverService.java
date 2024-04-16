package az.code.trammanagementsystem.services;


import az.code.trammanagementsystem.entity.Driver;

import java.util.List;

public interface DriverService {
    Driver addDriver(Driver driver);
    Driver getDriver(Long id);
    List<Driver> getAllDrivers();
    Driver updateDriver(Long id, Driver driver);

    void deleteDriver(Long id);
}
