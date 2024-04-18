package az.code.trammanagementsystem.services;


import az.code.trammanagementsystem.entity.Stop;

import java.util.List;

public interface StopService {
    Stop createStop(Stop stop);
    Stop getStop(Long id);
    List<Stop> getAll();
    Stop updateStop(Long id, Stop stop);
    void deleteStop(Long id);
}
