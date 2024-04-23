package az.code.trammanagementsystem;

import az.code.trammanagementsystem.entity.Route;
import az.code.trammanagementsystem.entity.Waypoint;
import az.code.trammanagementsystem.exceptions.InvalidRouteFormatException;
import az.code.trammanagementsystem.repository.RouteRepository;
import az.code.trammanagementsystem.services.RouteService;
import az.code.trammanagementsystem.services.helpers.TrajectoryHelper;
import az.code.trammanagementsystem.services.implementations.RouteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RouteServiceTests {

    @Mock
    private RouteRepository repository;

    @InjectMocks
    private RouteServiceImpl routeService;

    @Test
    public void testCreateRoute_Success() {
        Route route = new Route();
        List<Waypoint> waypoints = new ArrayList<>();
        waypoints.add(new Waypoint());
        route.setWaypoints(waypoints);

        when(repository.save(any(Route.class))).thenAnswer(invocation -> {
            Route savedRoute = invocation.getArgument(0);
            savedRoute.setId(1L);
            return savedRoute;
        });

//        when(TrajectoryHelper.getTramTrajectories(any(Route.class))).thenReturn(new ArrayList<>());

        Route createdRoute = routeService.createRoute(route);

        assertNotNull(createdRoute);
        assertEquals(1L, createdRoute.getId());
        assertNotNull(createdRoute.getTramTrajectories());
        assertTrue(createdRoute.getTramTrajectories().isEmpty());

        verify(repository, times(2)).save(any(Route.class));
    }


    @Test
    public void testCreateRoute_Exception() {
        Route route = new Route();
        List<Waypoint> waypoints = new ArrayList<>();
        waypoints.add(new Waypoint());
        route.setWaypoints(waypoints);

        when(repository.save(any(Route.class))).thenThrow(new RuntimeException("Failed to save route"));

        assertThrows(InvalidRouteFormatException.class, () -> routeService.createRoute(route));

        verify(repository).save(any(Route.class));
    }
}
