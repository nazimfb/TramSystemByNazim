package az.code.trammanagementsystem.services;

import az.code.trammanagementsystem.entity.TramRoute;

import java.util.UUID;

public interface TramRouteService {
    TramRoute addTramToRoute(UUID tramUuid, Long routeId);
}
