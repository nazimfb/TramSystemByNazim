package az.code.trammanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tram_trajectory")
public class TramTrajectory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double latitude;
    private double longitude;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id")
    private Route route;
}