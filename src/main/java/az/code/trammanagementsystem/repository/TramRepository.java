package az.code.trammanagementsystem.repository;

import az.code.trammanagementsystem.entity.Tram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TramRepository extends JpaRepository<Tram, UUID> {

    List<Tram> findByCurrentRouteNotNull();
}
