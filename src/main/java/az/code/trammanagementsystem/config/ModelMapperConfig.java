package az.code.trammanagementsystem.config;

import az.code.trammanagementsystem.dto.*;
import az.code.trammanagementsystem.entity.Driver;
import az.code.trammanagementsystem.entity.Route;
import az.code.trammanagementsystem.entity.Tram;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.typeMap(Tram.class, TramDetailDTO.class)
                .addMappings(mapper -> mapper.map(tram -> tram.getCurrentRoute().getId(), TramDetailDTO::setCurrentRouteId))
                .addMappings(mapper -> mapper.map(tram -> tram.getCurrentRoute().getName(), TramDetailDTO::setCurrentRouteName))
                .addMappings(mapper -> mapper.map(tram -> tram.getDriver().getId(), TramDetailDTO::setDriverId))
                .addMappings(mapper -> mapper.map(tram -> tram.getDriver().getName(), TramDetailDTO::setDriverName));

        modelMapper.typeMap(Tram.class, TramSummaryDTO.class)
                .addMappings(mapper -> mapper.using(context -> mapRouteToActive((Route) context.getSource())).map(Tram::getCurrentRoute, TramSummaryDTO::setActive))
                .addMappings(mapper -> mapper.map(tram -> tram.getDriver().getName(), TramSummaryDTO::setDriverName));
//                .addMappings(mapper -> mapper.map(tram -> tram.getCurrentRoute().getId(), TramSummaryDTO::setCurrentRouteId))
//                .addMappings(mapper -> mapper.map(tram -> tram.getCurrentRoute().getName(), TramSummaryDTO::setCurrentRouteName));

        modelMapper.typeMap(Tram.class, ActiveTramDTO.class)
                .addMappings(mapper -> mapper.map(tram -> tram.getDriver().getId(), ActiveTramDTO::setDriverId));

        modelMapper.typeMap(Driver.class, DriverDetailsDTO.class)
                        .addMappings(mapper -> mapper.map(driver -> driver.getCurrentTram().getId(), DriverDetailsDTO::setCurrentTramId));

        modelMapper.typeMap(Driver.class, DriverSummaryDTO.class)
                .addMappings(mapper -> mapper.map(driver -> driver.getCurrentTram().getId(), DriverSummaryDTO::setCurrentTramId));

//        modelMapper.typeMap(DriverDetailsDTO.class, Driver.class)
//                .addMappings(mapper -> mapper.map(DriverDetailsDTO::getCurrentTramId, (driver, tramId) -> driver.setCurrentTram(Tram.builder().id((UUID) tramId).build())));
//        modelMapper.typeMap(Route.class, RouteWaypointsDTO.class)
//                .addMapping(Route::getId, RouteWaypointsDTO::setId)
//                .addMapping(Route::getWaypoints, RouteWaypointsDTO::setWaypoints);

        return modelMapper;
    }

    /*private WaypointDTO mapWaypoints(Waypoint waypoint) {
        return WaypointDTO.builder()
                .lat(waypoint.getLat())
                .lng(waypoint.getLng())
                .build();
    }

    private Driver mapIdToDriver(Long id) {
        return Driver.builder().id(id).build();
    }*/

    private boolean mapRouteToActive(Route route) {
        return route != null;
    }
}