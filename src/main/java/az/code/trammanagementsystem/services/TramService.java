package az.code.trammanagementsystem.services;

import az.code.trammanagementsystem.dto.UpdateTramDTO;
import az.code.trammanagementsystem.entity.Tram;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface TramService {
    Tram addTram(Tram tram);
    Tram getTram(UUID id);
    List<Tram> getAll();
    Tram updateTram(UUID id, UpdateTramDTO tram);
    void deleteTram(UUID id);

    List<Tram> getTramLocations();

    Tram deleteTramDriver(UUID tramId);
}
