package az.code.trammanagementsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "route_schedule")
public class RouteSchedule {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

}