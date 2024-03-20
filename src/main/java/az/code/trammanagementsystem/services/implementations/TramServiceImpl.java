package az.code.trammanagementsystem.services.implementations;

import az.code.trammanagementsystem.entity.Tram;
import az.code.trammanagementsystem.exceptions.TramNotFoundException;
import az.code.trammanagementsystem.repository.TramRepository;
import az.code.trammanagementsystem.services.TramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TramServiceImpl implements TramService {

    private final TramRepository repository;

    @Override
    public Tram addTram(Tram tram) {
        tram.setId(UUID.randomUUID());
        return repository.save(tram);
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
    public Tram update(Tram updatedTram) {
        Optional<Tram> tram = repository.findById(updatedTram.getId());
        if (tram.isEmpty())
            throw new TramNotFoundException();
        tram.get().setDriver(updatedTram.getDriver());
        tram.get().setLatitude(updatedTram.getLatitude());
        tram.get().setLongitude(updatedTram.getLongitude());
        return repository.save(tram.get());
    }

    @Override
    public void deleteTram(UUID id) {
        Optional<Tram> tram = repository.findById(id);
        if (tram.isEmpty())
            throw new TramNotFoundException();
        repository.delete(tram.get());
    }
}
