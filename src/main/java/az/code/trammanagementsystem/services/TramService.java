package az.code.trammanagementsystem.services;

import az.code.trammanagementsystem.entity.Tram;

import java.util.List;
import java.util.UUID;

public interface TramService {
    Tram addTram(Tram tram);
    Tram getTram(UUID id);
    List<Tram> getAll();
    Tram update(Tram tram);
    void deleteTram(UUID id);
}
